package ru.dvoretckii.rabbitMQ;
import com.rabbitmq.client.*;
public class RabbitMQsetup {
    public Connection createConnection(String host, int port, String username, String password) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        return factory.newConnection();
    }

    public Channel createChannel(Connection connection) throws Exception {
        return connection.createChannel();
    }
}
