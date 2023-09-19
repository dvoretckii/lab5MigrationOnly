package ru.dvoretckii;

import ru.dvoretckii.Rabbit.RabbitClient;

public class Application {
    public static void main(String[] args) {
        String exchangeName = "my-exchange";
        String queueName = "hui2";

        try {
            RabbitClient client = new RabbitClient(exchangeName, queueName);
            client.connect();

            String message = "Hello, RabbitMQ!";
            client.publishMessage(message);

            System.out.println("Message sent!");
            client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
