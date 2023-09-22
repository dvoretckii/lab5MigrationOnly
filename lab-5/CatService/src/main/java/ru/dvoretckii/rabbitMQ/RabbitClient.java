package ru.dvoretckii.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitClient {
    private Connection connection;
    private Channel channel;
    private String queueName;

    public RabbitClient( String queueName) {
        this.queueName = queueName;
    }

    public void connect(String localhost, int port, String username, String password) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(localhost);
        factory.setPort(port);
        factory.setUsername(password);
        factory.setPassword(password);
        this.connection = factory.newConnection();
        this.channel = connection.createChannel();
        this.channel.queueDeclare(queueName, true, false, false, null);
    }

    public void publishMessage(String message) throws IOException {
        this.channel.basicPublish("", queueName, null, message.getBytes());
    }

    public void consumeMessages() throws IOException {
        this.channel.basicConsume(queueName, true, (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Received message: " + message);
        }, consumerTag -> {});
    }

    public void disconnect() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }
}
