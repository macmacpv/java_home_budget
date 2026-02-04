package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.interfaces.Identifiable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (Files.notExists(path)) {
            return new ArrayList<>();
        }

        try {
            String json = Files.readString(path);
            Type type = TypeToken.getParameterized(List.class, typeClass).getType();
            List<T> list = gson.fromJson(json, type);
            return list != null ? list : new ArrayList<>();
        } catch (IOException e) {
            System.err.println(e.toString());
            return new ArrayList<>();
        }
    }

    private void saveCache(){
        String json = gson.toJson(cache);
        try {
            Files.writeString(Paths.get(filePath), json);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    @Override
    public T save(T entity) {
        if (entity.getId() == 0) {
            int maxId = cache.stream().map(Identifiable::getId).max(Integer::compareTo).orElse(0);
            entity.setId(maxId + 1);
            cache.add(entity);
        } else {
            cache.removeIf(existing -> existing.getId() == entity.getId());
            cache.add(entity);
        }

        saveCache();
        return entity;
    }

    @Override
    public Optional<T> findById(int id) {
        return cache.stream().filter(entity -> entity.getId() == id).findFirst();
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(cache);
    }

    @Override
    public void deleteById(int id) {
        boolean removed = cache.removeIf(entity -> entity.getId() == id);
        if (removed) saveCache();
    }
}
