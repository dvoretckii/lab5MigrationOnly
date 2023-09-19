package ru.dvoretckii.Rabbit;

public class RabbitConfig {
    private String exchangeName;
    private String queueName;

    public RabbitConfig(String exchangeName, String queueName) {
        this.exchangeName = exchangeName;
        this.queueName = queueName;
    }

    public RabbitClient createClient() {
        return new RabbitClient(exchangeName, queueName);
    }
}

