package ru.dvoretckii.Rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
public class RabbitConsumer {
    public String myMessage = null;
    public String startConsuming(Channel channel, String queue) throws IOException, InterruptedException {
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(
                    String consumerTag, Envelope envelope,
                    AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                myMessage = message;
                System.out.println("UI получил сообщение от детей донбасса " + message);
            }
        };
        channel.basicConsume(queue, true, consumer);
        Thread.sleep(2000);
        return myMessage;
    }

}
