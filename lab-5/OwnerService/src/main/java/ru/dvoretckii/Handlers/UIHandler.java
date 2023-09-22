package ru.dvoretckii.Handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dvoretckii.Service.BusinessOwner;
import ru.dvoretckii.Service.ServiceOwner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UIHandler {

    private final BusinessOwner businessOwner;

    @Autowired
    public UIHandler(BusinessOwner businessOwner) {
        this.businessOwner = businessOwner;
    }
    public ObjectNode startOwner(JsonNode node) {
        if (node.get("type").toString().replaceAll("[^a-zA-Z0-9]", "").equals("GET"))
            return getOwner(node);
        if (node.get("type").toString().replaceAll("[^a-zA-Z0-9]", "").equals("UPDATE"))
            return updateOwner(node);
        if (node.get("type").toString().replaceAll("[^a-zA-Z0-9]", "").equals("DELETE"))
            return deleteOwner(node);
        return (ObjectNode) node;
    }

    public ObjectNode getOwner(JsonNode node){
        Long id  = Long.valueOf(node.get("id").toString());
        ServiceOwner serviceOwner = businessOwner.getOwnerById(id);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode parameters = mapper.createObjectNode();

        parameters.put("id", serviceOwner.getOwner_id());
        parameters.put("birth_date", serviceOwner.getOwner_birth_date().toString());
        return parameters;
    }


    public ObjectNode updateOwner(JsonNode node) {

        Long id  = Long.valueOf(node.get("id").toString());
        ServiceOwner owner = businessOwner.getOwnerById(id);

        if (!node.get("birthdate").toString().replaceAll("[^0-9-]", "").isEmpty()) {
            String birthDate = node.get("birthdate").toString().replaceAll("[^0-9-]", "");
            System.out.println(birthDate);
            Date date = null;
            try {
                date = new SimpleDateFormat("dd-MM-yyyy").parse(birthDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            owner.setOwner_birth_date(date);
        }

        ServiceOwner serviceCat = businessOwner.updateOwner(owner);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode parameters = mapper.createObjectNode();
        parameters.put("id", serviceCat.getOwner_id());
        parameters.put("birthdate", serviceCat.getOwner_birth_date().toString());
        return parameters;
    }

    public ObjectNode deleteOwner(JsonNode node) {
        Long id  = Long.valueOf(node.get("id").toString());
        ServiceOwner serviceOwner = businessOwner.getOwnerById(id);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode parameters = mapper.createObjectNode();
        parameters.put("id", serviceOwner.getOwner_id());
        parameters.put("birth_date", serviceOwner.getOwner_birth_date().toString());
        businessOwner.deleteOwner(id);
        return parameters;

    }
}
