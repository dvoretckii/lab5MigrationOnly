package ru.dvoretckii.Rabbit;

import com.rabbitmq.client.Channel;

public class RabbitConfig {
    public void declareQueueAndBind(Channel channel, String queueName) throws Exception {
        channel.queueDeclare(queueName, true, false, false, null);
    }
}
