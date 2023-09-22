package ru.dvoretckii.Rabbit;

import com.rabbitmq.client.Channel;

public class RabbitConfig {
    private String queueName;

    public  RabbitConfig(String queueName) {
        this.queueName = queueName;
    }

    public void declareQueueAndBind(Channel channel, String queueName, String exchangeName) throws Exception {
        channel.queueDeclare(queueName, true, false, false, null);
    }
    public RabbitClient createClient() {
        return new RabbitClient(queueName);
    }
}

