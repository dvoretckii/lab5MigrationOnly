package ru.dvoretckii.Controllers.OwnerControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dvoretckii.Rabbit.RabbitClient;
import ru.dvoretckii.Rabbit.RabbitConsumer;
import ru.dvoretckii.Rabbit.RabbitMQsetup;
import ru.dvoretckii.Service.AuthService;
import ru.dvoretckii.Service.ServiceOwner;

import static ru.dvoretckii.Controllers.AuthControllers.LoginController.username;

@RestController
@RequestMapping("/owner")

public class OwnerController {
    @Autowired
    AuthService authService;
    @GetMapping(value = "/get")
    public String getOwnerById(@RequestParam Long id) {
        String queueName = "ui-owner";

        try {
            RabbitClient client = new RabbitClient(queueName);
            client.connect("localhost", 5672, "hui", "hui");

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode parameters = mapper.createObjectNode();
            parameters.put("type", "GET");
            parameters.put("client", "UI");
            parameters.put("id", id);
            String message = parameters.toString();
            client.publishMessage(message);

            System.out.println("Message sent! " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            RabbitMQsetup rabbitMQSetup = new RabbitMQsetup();
            Connection connection = rabbitMQSetup.createConnection("localhost", 5672, "hui", "hui");
            Channel channel = rabbitMQSetup.createChannel(connection);
            String queueNameBack = "owner-ui";
            RabbitConsumer rabbitMQConsumer = new RabbitConsumer();
            String message = rabbitMQConsumer.startConsuming(channel, queueNameBack);
            channel.close();
            connection.close();
            return message;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "успех";
    }

    @GetMapping(value = "/update")
    public String updateOwner(@RequestParam Long id,
                              @RequestParam(required = false) String birthdate) {
        String queueName = "ui-owner";
        try {
            RabbitClient client = new RabbitClient(queueName);
            client.connect("localhost", 5672, "hui", "hui");

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode parameters = mapper.createObjectNode();
            parameters.put("type", "UPDATE");
            parameters.put("client", "UI");
            parameters.put("id", id);
            parameters.put("birthdate", birthdate);;
            String message = parameters.toString();
            client.publishMessage(message);

            System.out.println("Message sent! " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            RabbitMQsetup rabbitMQSetup = new RabbitMQsetup();
            Connection connection = rabbitMQSetup.createConnection("localhost", 5672, "hui", "hui");
            Channel channel = rabbitMQSetup.createChannel(connection);
            String queueNameBack = "owner-ui";
            RabbitConsumer rabbitMQConsumer = new RabbitConsumer();
            String message = "Owner was updated :\n" + rabbitMQConsumer.startConsuming(channel, queueNameBack);
            channel.close();
            connection.close();
            return message;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "успех";
    }

    @GetMapping(value = "/delete")
    public String deleteCat(@RequestParam Long id) {
        String queueName = "ui-owner";

        try {
            RabbitClient client = new RabbitClient(queueName);
            client.connect("localhost", 5672, "hui", "hui");

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode parameters = mapper.createObjectNode();
            parameters.put("type", "DELETE");
            parameters.put("client", "UI");
            parameters.put("id", id);
            String message = parameters.toString();
            client.publishMessage(message);

            System.out.println("Message sent! " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            RabbitMQsetup rabbitMQSetup = new RabbitMQsetup();
            Connection connection = rabbitMQSetup.createConnection("localhost", 5672, "hui", "hui");
            Channel channel = rabbitMQSetup.createChannel(connection);


            String queueNameBack = "owner-ui";


            RabbitConsumer rabbitMQConsumer = new RabbitConsumer();
            String message = rabbitMQConsumer.startConsuming(channel, queueNameBack);
            channel.close();
            connection.close();
            return "Owner was deleted:\n" + message;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "успех";
    }

    @GetMapping(value = "/start")
    public String start() {
        String queueName = "ui-cat";
        ServiceOwner owner = (ServiceOwner) authService.loadUserByUsername(username);
        Long id = owner.getOwner_id();

        try {
            RabbitClient client = new RabbitClient(queueName);
            client.connect("localhost", 5672, "hui", "hui");

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode parameters = mapper.createObjectNode();
            parameters.put("type", "GETOWNEDCATS");
            parameters.put("client", "UI");
            parameters.put("id", id);
            String message = parameters.toString();
            client.publishMessage(message);

            System.out.println("Message sent! " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            RabbitMQsetup rabbitMQSetup = new RabbitMQsetup();
            Connection connection = rabbitMQSetup.createConnection("localhost", 5672, "hui", "hui");
            Channel channel = rabbitMQSetup.createChannel(connection);


            String queueNameBack = "cat-ui";


            RabbitConsumer rabbitMQConsumer = new RabbitConsumer();
            String message = rabbitMQConsumer.startConsuming(channel, queueNameBack);
            channel.close();
            connection.close();
            return "Your cats:\n" + message;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "успех";
    }
}
