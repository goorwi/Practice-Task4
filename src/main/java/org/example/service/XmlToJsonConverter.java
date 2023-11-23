package org.example.service;

import org.example.model.Phone;
import org.example.model.Phones;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class XmlToJsonConverter {
    public void Convert(String in, String out) throws JAXBException, FileNotFoundException {
        Phones phones = ReadXml(in);
        WriteJson(out, phones);
    }

    public Phones getPhones(String in) {
        try {
            return ReadXml(in);
        } catch (JAXBException e) {
            System.out.println("Произошла ошибка при чтении файла!");
            return null;
        }
    }

    private Phones ReadXml(String path) throws JAXBException {
        File file = new File(path);
        JAXBContext jaxbContext = JAXBContext.newInstance(Phones.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Phones) jaxbUnmarshaller.unmarshal(file);
    }
    private void WriteJson(String path, Phones phones) throws FileNotFoundException {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder modelsBuilder = Json.createArrayBuilder();

        for (Phone p : phones.getPhones())
        {
            JsonObjectBuilder modelBuilder = Json.createObjectBuilder();
            JsonObjectBuilder phoneBuilder = Json.createObjectBuilder();
            JsonObjectBuilder specBuilder = Json.createObjectBuilder();

            modelBuilder.add("model", p.getModel());
            phoneBuilder.add("brand", p.getBrand());
            phoneBuilder.add("color", p.getColor());
            phoneBuilder.add("price", p.getPrice());
            specBuilder.add("cpu",p.getSpecifications().getCpu());
            specBuilder.add("storage",p.getSpecifications().getStorage());
            phoneBuilder.add("specifications", specBuilder);
            modelBuilder.add("phone", phoneBuilder);

            modelsBuilder.add(modelBuilder);
        }
        objectBuilder.add("models", modelsBuilder);

        JsonObject jo = objectBuilder.build();

        OutputStream os = new FileOutputStream(path);
        JsonWriter jsonWriter;

        Map< String, Boolean > config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);

        JsonWriterFactory factory = Json.createWriterFactory(config);
        jsonWriter = factory.createWriter(os);

        jsonWriter.writeObject(jo);
        jsonWriter.close();
    }
}
