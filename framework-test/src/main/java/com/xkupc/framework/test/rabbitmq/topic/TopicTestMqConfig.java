package com.xkupc.framework.test.rabbitmq.topic;

import com.xkupc.framework.mq.base.MqExchange;
import com.xkupc.framework.mq.base.QueueBinding;
import com.xkupc.framework.mq.base.RabbitConfig;
import com.xkupc.framework.mq.routing.RoutingWorkQueueConfig;
import com.xkupc.framework.mq.topic.TopicPublisherConfig;
import com.xkupc.framework.mq.topic.TopicReceiverConfig;
import com.xkupc.framework.mq.topic.TopicWorkQueueConfig;
import com.xkupc.framework.test.rabbitmq.common.TestMqExchange;
import com.xkupc.framework.test.rabbitmq.common.TestMqQueueName;
import com.xkupc.framework.test.rabbitmq.common.TestMqRoutingkey;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author xk
 * @createTime 2018/1/9 0009 下午 4:20
 * @description 路由模式-生产者将指定routingKey的消息同时推送给多个消费者，只有匹配routingKey的消费者可以接收到消息
 */
@AutoConfigureAfter(value = RabbitConfig.class)
@Configuration
@Import({TopicWorkQueueConfig.class, TopicPublisherConfig.class, TopicReceiverConfig.class})
public class TopicTestMqConfig {

    public TopicTestMqConfig(){
        //初始化exchange
        MqExchange mqExchange = TestMqExchange.TEST_DIRECT_MQ_EXCHANGE;
        RoutingWorkQueueConfig.setMqExchange(mqExchange);
        //消费者队列初始化并绑定exchange
        TopicReceiverConfig.setQueueBindings(new QueueBinding[]{
                new QueueBinding(TestMqQueueName.TEST_MQ_QUEUE_NAME_1, TestMqRoutingkey.TEST_MQ_ROUTINGKEY_1,mqExchange),
                new QueueBinding(TestMqQueueName.TEST_MQ_QUEUE_NAME_2,TestMqRoutingkey.TEST_MQ_ROUTINGKEY_2,mqExchange)});
    }
}
