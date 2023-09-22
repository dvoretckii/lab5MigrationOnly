package ru.dvoretckii.Rabbit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dvoretckii.Handlers.UIHandler;

import java.io.IOException;

@Component
public class RabbitConsumer {
    private final UIHandler uiHandler;

    @Autowired
    public RabbitConsumer(UIHandler uiHandler) {
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
                System.out.println("Consumer Owner received " + node.get("id"));
                if (node.get("type").toString().replaceAll("[^a-zA-Z0-9]", "").equals("DELETE")) {
                    String queueToCat = "ui-cat";
                    try {
                        RabbitClient client = new RabbitClient(queueToCat);
                        client.connect("localhost", 5672, "hui", "hui");
                        ObjectNode toCat = mapper.createObjectNode();
                        toCat.put("owner", Long.valueOf(node.get("id").toString()));
                        toCat.put("type", "DELETEDOWNER");
                        toCat.put("client", "system");
                        message = toCat.toString();
                        client.publishMessage(message);
                        System.out.println("Message sent to cats! "  + message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                String queueName = "owner-ui";

                try {
                    RabbitClient client = new RabbitClient(queueName);
                    client.connect("localhost", 5672, "hui", "hui");
                    message = uiHandler.startOwner(node).toString();
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
