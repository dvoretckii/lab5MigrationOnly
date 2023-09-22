package ru.dvoretckii.rabbitMQ;
import com.rabbitmq.client.Channel;
public class RabbitMqConfig {
        public void declareQueueAndBind(Channel channel, String queueName) throws Exception {
            channel.queueDeclare(queueName, true, false, false, null);
    }

}
