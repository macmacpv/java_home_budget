package repository;

import models.Identifiable;

import java.util.*;

public class AccountRepository<T extends Identifiable> implements Repository<T> {

    private final Map<Integer, T> storage = new HashMap<>();

    @Override
    public T save(T entity) {
        //@TODO: System for json save
        return null;
    }

    @Override
    public Optional<T> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(int id) {
        storage.remove(id);
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }

}
