package ru.dvoretckii.Controllers.CatControllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.dvoretckii.Rabbit.RabbitClient;
import ru.dvoretckii.Rabbit.RabbitConfig;
import ru.dvoretckii.Rabbit.RabbitConsumer;
import ru.dvoretckii.Rabbit.RabbitMQsetup;

@RestController
@RequestMapping("/cat")

public class CatController {
    @GetMapping(value = "/get")
    public String getCatById(@RequestParam Long id) {
        String queueName = "ui-cat";

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


            String queueNameBack = "cat-ui";


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

    @GetMapping(value = "/create")
    public String createCat(@RequestParam String name, @RequestParam(required = false, defaultValue = "barn") String breed,
                            @RequestParam(required = false, defaultValue = "06-11-1988") String birthdate,
                            @RequestParam(required = false, defaultValue = "BLACK") String color,
                            @RequestParam(required = false) Long owner) {
        String queueName = "ui-cat";
        try {
            RabbitClient client = new RabbitClient(queueName);
            client.connect("localhost", 5672, "hui", "hui");

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode parameters = mapper.createObjectNode();
            parameters.put("type", "CREATE");
            parameters.put("client", "UI");
            parameters.put("name", name);
            parameters.put("owner", owner);
            parameters.put("breed", breed);
            parameters.put("birthdate", birthdate);;
            parameters.put("color", color);
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
            String message = "Cat was created :\n" + rabbitMQConsumer.startConsuming(channel, queueNameBack);
            channel.close();
            connection.close();
            return message;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "успех";
    }

    @GetMapping(value = "/update")
    public String updateCat(@RequestParam Long id,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String breed,
                            @RequestParam(required = false) String birthdate,
                            @RequestParam(required = false) String color,
                            @RequestParam(required = false) Long owner) {
        String queueName = "ui-cat";
        try {
            RabbitClient client = new RabbitClient(queueName);
            client.connect("localhost", 5672, "hui", "hui");

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode parameters = mapper.createObjectNode();
            parameters.put("type", "UPDATE");
            parameters.put("client", "UI");
            parameters.put("id", id);
            parameters.put("name", name);
            parameters.put("owner", owner);
            parameters.put("breed", breed);
            parameters.put("birthdate", birthdate);;
            parameters.put("color", color);
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
            String message = "Cat was updated :\n" + rabbitMQConsumer.startConsuming(channel, queueNameBack);
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
        String queueName = "ui-cat";

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


            String queueNameBack = "cat-ui";


            RabbitConsumer rabbitMQConsumer = new RabbitConsumer();
            String message = rabbitMQConsumer.startConsuming(channel, queueNameBack);
            channel.close();
            connection.close();
            return "Cat was deleted:\n" + message;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "успех";
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
