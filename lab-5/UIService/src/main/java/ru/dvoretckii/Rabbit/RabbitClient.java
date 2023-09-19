package ru.dvoretckii.Rabbit;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitClient {
    private Connection connection;
    private Channel channel;
    private String exchangeName;
    private String queueName;

    public RabbitClient(String exchangeName, String queueName) {
        this.exchangeName = exchangeName;
        this.queueName = queueName;
    }

    public void connect() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("f6fe-31-134-189-173.ngrok-free.app");
        factory.setPort(5672); // Default RabbitMQ port
        factory.setUsername("hui"); // Default guest username
        factory.setPassword("hui"); // Default guest password
        this.connection = factory.newConnection();
        this.channel = connection.createChannel();

//this.channel.exchangeDeclare(exchangeName, "direct", true);
        this.channel.queueDeclare("hui2", true, false, false, null);
  //      this.channel.queueBind(queueName, exchangeName, "");
    }

    public void publishMessage(String message) throws IOException {
        this.channel.basicPublish(exchangeName, "", null, message.getBytes());
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
