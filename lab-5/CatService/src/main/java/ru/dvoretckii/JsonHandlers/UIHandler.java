package ru.dvoretckii.JsonHandlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.RedirectView;
import ru.dvoretckii.DAO.Entities.Color;
import ru.dvoretckii.Service.BusinessCat;
import ru.dvoretckii.Service.ServiceCat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Component
public class UIHandler {

    private final BusinessCat businessCat;

    @Autowired
    public UIHandler(BusinessCat businessCat) {
        this.businessCat = businessCat;
    }
    public ObjectNode startCat(JsonNode node) {
        if (node.get("type").toString().replaceAll("[^a-zA-Z0-9]", "").equals("GET"))
            return getCat(node);
        if (node.get("type").toString().replaceAll("[^a-zA-Z0-9]", "").equals("CREATE"))
            return createCat(node);
        if (node.get("type").toString().replaceAll("[^a-zA-Z0-9]", "").equals("UPDATE"))
            return updateCat(node);
        if (node.get("type").toString().replaceAll("[^a-zA-Z0-9]", "").equals("DELETE"))
            return deleteCat(node);
        if (node.get("type").toString().replaceAll("[^a-zA-Z0-9]", "").equals("DELETEDOWNER"))
            return deleteOwnedCats(node);
        if (node.get("type").toString().replaceAll("[^a-zA-Z0-9]", "").equals("GETOWNEDCATS"))
            return getOwnedCats(node);
        return (ObjectNode) node;
    }

    public ObjectNode getCat(JsonNode node){
        Long id  = Long.valueOf(node.get("id").toString());
        ServiceCat serviceCat = businessCat.getCatById(id);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode parameters = mapper.createObjectNode();

        parameters.put("id", serviceCat.getServiceCat_id());
        parameters.put("name", serviceCat.getServiceCat_name());
        parameters.put("breed", serviceCat.getServiceCat_breed());
        parameters.put("color", serviceCat.getColor().toString());
        parameters.put("birth_date", serviceCat.getServiceCat_birth_date().toString());
        parameters.put("friends", serviceCat.getFriends().toString());
        return parameters;
    }

    public ObjectNode createCat(JsonNode node) {
        ServiceCat cat = new ServiceCat();
        String name = node.get("name").toString().replaceAll("[^a-zA-Z0-9]", "");
        String breed = node.get("breed").toString().replaceAll("[^a-zA-Z0-9]", "");
        String color = node.get("color").toString().replaceAll("[^a-zA-Z0-9]", "");
        String birthDate = node.get("birthdate").toString().replaceAll("[^0-9-]", "");
        Long owner = Long.valueOf(node.get("owner").toString());
        cat.setServiceCat_name(name);
        cat.setServiceCat_breed(breed);
        cat.setColor(Color.valueOf(color.toUpperCase()));
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(birthDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        cat.setServiceCat_birth_date(date);
        cat.setServiceOwner(owner);
        ServiceCat serviceCat = businessCat.createCat(cat);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode parameters = mapper.createObjectNode();
        parameters.put("id", serviceCat.getServiceCat_id());
        parameters.put("name", serviceCat.getServiceCat_name());
        parameters.put("breed", serviceCat.getServiceCat_breed());
        parameters.put("color", serviceCat.getColor().toString());
        parameters.put("birth_date", serviceCat.getServiceCat_birth_date().toString());
        parameters.put("owner", serviceCat.getServiceOwner());
        return parameters;
    }

    public ObjectNode updateCat(JsonNode node) {

        Long id  = Long.valueOf(node.get("id").toString());
        ServiceCat cat = businessCat.getCatById(id);

        if (!node.get("name").toString().replaceAll("[^a-zA-Z0-9]", "").equals("null")) {
            String name = node.get("name").toString().replaceAll("[^a-zA-Z0-9]", "");
            cat.setServiceCat_name(name);
        }

        if (!node.get("owner").toString().replaceAll("[^a-zA-Z0-9]", "").equals("null")) {
            Long owner  = Long.valueOf(node.get("owner").toString());
            cat.setServiceOwner(owner);
        }

        if (!node.get("breed").toString().replaceAll("[^a-zA-Z0-9]", "").equals("null")) {
            String breed = node.get("breed").toString().replaceAll("[^a-zA-Z0-9]", "");
            cat.setServiceCat_breed(breed);
        }

        if (!node.get("color").toString().replaceAll("[^a-zA-Z0-9]", "").equals("null")) {
            String color = node.get("color").toString().replaceAll("[^a-zA-Z0-9]", "");
            cat.setColor(Color.valueOf(color.toUpperCase()));
        }

        if (!node.get("birthdate").toString().replaceAll("[^0-9-]", "").isEmpty()) {
            String birthDate = node.get("birthdate").toString().replaceAll("[^0-9-]", "");
            System.out.println(birthDate);
            Date date = null;
            try {
                date = new SimpleDateFormat("dd-MM-yyyy").parse(birthDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            cat.setServiceCat_birth_date(date);
        }

        ServiceCat serviceCat = businessCat.updateCat(cat);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode parameters = mapper.createObjectNode();
        parameters.put("id", serviceCat.getServiceCat_id());
        parameters.put("name", serviceCat.getServiceCat_name());
        parameters.put("breed", serviceCat.getServiceCat_breed());
        parameters.put("color", serviceCat.getColor().toString());
        parameters.put("birthdate", serviceCat.getServiceCat_birth_date().toString());
        parameters.put("owner", serviceCat.getServiceOwner());
        return parameters;
    }

    public ObjectNode deleteCat(JsonNode node) {
        Long id  = Long.valueOf(node.get("id").toString());
        ServiceCat serviceCat = businessCat.getCatById(id);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode parameters = mapper.createObjectNode();
        parameters.put("id", serviceCat.getServiceCat_id());
        parameters.put("name", serviceCat.getServiceCat_name());
        parameters.put("breed", serviceCat.getServiceCat_breed());
        parameters.put("color", serviceCat.getColor().toString());
        parameters.put("birth_date", serviceCat.getServiceCat_birth_date().toString());
        parameters.put("friends", serviceCat.getFriends().toString());
        businessCat.deleteCat(id);
        return parameters;
    }

    public ObjectNode deleteOwnedCats(JsonNode node) {
        Long id  = Long.valueOf(node.get("owner").toString());
        businessCat.deleteCatsFromOwner(id);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode parameters = mapper.createObjectNode();
        return parameters;
    }

    public ObjectNode getOwnedCats(JsonNode node) {
        Long id  = Long.valueOf(node.get("id").toString());
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode parameters = mapper.createObjectNode();
        parameters.put("cats", businessCat.getOwnedCats(id).toString());
        return parameters;
    }
}
