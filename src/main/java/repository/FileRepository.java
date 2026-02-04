package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.interfaces.Identifiable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileRepository<T extends Identifiable> implements Repository<T> {

    private final String filePath;
    private final Class<T> typeClass;
    private final Gson gson;
    private final List<T> cache;

    public FileRepository(String filePath, Class<T> typeClass) {
        this.filePath = filePath;
        this.typeClass = typeClass;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.cache = loadCache();
    }

    private List<T> loadCache(){
        Path path = Paths.get(filePath);
        if (!Files.notExists(path)) {
            return new ArrayList<>();
        }

        try {
            String json = Files.readString(path);
            Type type = TypeToken.getParameterized(List.class, typeClass).getType();
            List<T> list = gson.fromJson(json, type);
            return list != null ? list : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveCache(){
        String json = gson.toJson(cache);
        try {
            Files.writeString(Paths.get(filePath), json);
        } catch (IOException e) {
            System.err.println("Error saving file '" + filePath + "'");
        }
    }

    @Override
    public T save(T entity) {
        return null;
    }

    @Override
    public Optional<T> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(int id) {

    }
}
