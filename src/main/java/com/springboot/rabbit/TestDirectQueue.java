package com.springboot.rabbit;


import org.springframework.amqp.core.Queue;

/**
 * created by zhanglong and since  2019/11/25  3:52 下午
 *
 * @description: 描述
 */
public class TestDirectQueue extends Queue {

    public TestDirectQueue( String name ) {
        super(name);
    }
}
