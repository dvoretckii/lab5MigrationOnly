package ru.dvoretckii.rabbitMQ;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dvoretckii.JsonHandlers.UIHandler;
import ru.dvoretckii.Service.BusinessCat;

import java.io.IOException;
@Component
public class RabbitMQConsumer {
    private final UIHandler uiHandler;

    @Autowired
    public RabbitMQConsumer(UIHandler uiHandler) {
        this.uiHandler = uiHandler;
    }

    public void startConsuming(Channel channel, String queue) throws IOException {
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(
                    String consumerTag, Envelope envelope,
                    com.rabbitmq.client.AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(message);
                System.out.println("Consumer Cat received " + node.get("id"));
                String queueName = "cat-ui";

                try {
                    RabbitClient client = new RabbitClient(queueName);
                    client.connect("localhost", 5672, "hui", "hui");

                    message = uiHandler.startCat(node).toString();
                    if (!node.get("client").toString().replaceAll("[^a-zA-Z0-9]", "").equals("system"))
                        client.publishMessage(message);

                    System.out.println("Message sent! "  + message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        channel.basicConsume(queue, true, consumer);
    }
}

