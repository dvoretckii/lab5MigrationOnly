package ru.dvoretckii;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.dvoretckii.Rabbit.RabbitConfig;
import ru.dvoretckii.Rabbit.RabbitConsumer;
import ru.dvoretckii.Rabbit.RabbitSetup;

@SpringBootApplication
@EnableJpaRepositories("ru.dvoretckii.DAO.Repository")
@EntityScan("ru.dvoretckii.*")
public class Application {

    public static void main(String[] args) throws Exception {

        RabbitSetup rabbitMQSetup = new RabbitSetup();
        Connection connection = rabbitMQSetup.createConnection("localhost", 5672, "hui", "hui");
        Channel channel = rabbitMQSetup.createChannel(connection);


        RabbitConfig rabbitMQConfig = new RabbitConfig();
        String queueName = "ui-owner";
        rabbitMQConfig.declareQueueAndBind(channel, queueName);

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        RabbitConsumer rabbitMQConsumer = context.getBean(RabbitConsumer.class);
        rabbitMQConsumer.startConsuming(channel, queueName);
    }
}