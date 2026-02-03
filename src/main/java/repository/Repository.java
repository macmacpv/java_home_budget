package repository;

import models.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends Identifiable> {

    T save(T entity);
    Optional<T> findById(int id);
    List<T> findAll();
    void deleteById(int id);
    void deleteAll();

}
