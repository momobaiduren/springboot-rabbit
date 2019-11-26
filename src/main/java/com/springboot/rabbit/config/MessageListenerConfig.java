package com.springboot.rabbit.config;

import com.springboot.rabbit.listener.DirectReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by zhanglong and since  2019/11/26  3:26 下午
 *
 * @description: 描述
 */
@Configuration
public class MessageListenerConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private DirectReceiver directReceiver;//Direct消息接收处理类
    //    @Autowired
//    FanoutReceiverA fanoutReceiverA;//Fanout消息接收处理类A
    @Autowired
    DirectRabbitConfig directRabbitConfig;
    //    @Autowired
//    FanoutRabbitConfig fanoutRabbitConfig;
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息
        container.setQueues(directRabbitConfig.TestDirectQueue());
        container.setMessageListener(directReceiver);
//        container.addQueues(fanoutRabbitConfig.queueA());
//        container.setMessageListener(fanoutReceiverA);
        return container;
    }

}
