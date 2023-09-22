package ru.dvoretckii;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.dvoretckii.rabbitMQ.RabbitMQConsumer;
import ru.dvoretckii.rabbitMQ.RabbitMQsetup;
import ru.dvoretckii.rabbitMQ.RabbitMqConfig;

@SpringBootApplication
@EnableJpaRepositories("ru.dvoretckii.DAO.Repositories")
@EntityScan("ru.dvoretckii.*")
public class Application {

    public static void main(String[] args) throws Exception {

            RabbitMQsetup rabbitMQSetup = new RabbitMQsetup();
            Connection connection = rabbitMQSetup.createConnection("localhost", 5672, "hui", "hui");
            Channel channel = rabbitMQSetup.createChannel(connection);


            RabbitMqConfig rabbitMQConfig = new RabbitMqConfig();
            String queueName = "ui-cat";
            rabbitMQConfig.declareQueueAndBind(channel, queueName);

            ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

            RabbitMQConsumer rabbitMQConsumer = context.getBean(RabbitMQConsumer.class);
            rabbitMQConsumer.startConsuming(channel, queueName);
    }
}
