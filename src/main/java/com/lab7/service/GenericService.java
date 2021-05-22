package com.lab7.service;

import java.util.List;
import java.util.Stack;

public interface GenericService<E, ID> {

    E save(E dragon);
    List<E> saveAll(List<E> entities);

    Stack<E> findAll();
    E findById(ID id);

    boolean update(E entity);
    boolean delete(E entity);
    boolean deleteById(ID id);
    boolean deleteAll();
}
