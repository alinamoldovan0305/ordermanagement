package com.example.ordermanagement.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.DeserializationFeature;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InFileRepository<T> implements GenericRepository<T> {

    private final String filePath;
    private final Class<T> type;

    // FIX: Jackson module for dates/enums
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
            .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);



    private List<T> items = new ArrayList<>();

    public InFileRepository(String filePath, Class<T> type) {
        this.filePath = filePath;
        this.type = type;
        loadFromFile();
    }

    private void loadFromFile() {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                saveToFile();
            }

            items = mapper.readValue(
                    file,
                    mapper.getTypeFactory().constructCollectionType(List.class, type)
            );

        } catch (Exception e) {
            items = new ArrayList<>();
        }
    }

    private void saveToFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(filePath), items);
        } catch (Exception e) {
            throw new RuntimeException("Eroare la salvarea în fișier: " + filePath);
        }
    }

    @Override
    public void save(String id, T entity) {

        try {
            entity.getClass().getMethod("setId", String.class).invoke(entity, id);
        } catch (Exception e) {
            throw new RuntimeException("Modelul trebuie să aibă setId(String id).");
        }

        items.add(entity);
        saveToFile();
    }


    @Override
    public List<T> findAll() {
        return items;
    }

    @Override
    public T findById(String id) {
        return items.stream()
                .filter(x -> {
                    try {
                        String objectId = (String) x.getClass().getMethod("getId").invoke(x);
                        return objectId.equals(id);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(String id) {
        items.removeIf(x -> {
            try {
                String objectId = (String) x.getClass().getMethod("getId").invoke(x);
                return objectId.equals(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        saveToFile();
    }

    @Override
    public void update(String id, T entity) {
        delete(id);
        save(id, entity);
    }
}
