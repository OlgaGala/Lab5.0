package com.lab7.service;

import com.lab7.dao.DragonDao;
import com.lab7.dragon.Dragon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;

@AllArgsConstructor
@Getter @Setter
public class DragonService implements GenericService<Dragon, Integer> {

    private DragonDao dao;

    @Override
    public Dragon save(Dragon dragon) {
        return dao.save(dragon);
    }

    @Override
    public List<Dragon> saveAll(List<Dragon> entities) {
        return dao.saveAll(entities);
    }

    @Override
    public Stack<Dragon> findAll() {
        return dao.findAll();
    }

    @Override
    public Dragon findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public boolean update(Dragon dragon) {
        return dao.update(dragon);
    }

    @Override
    public boolean delete(Dragon dragon) {
        return dao.delete(dragon);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dao.deleteById(id);
    }

    @Override
    public boolean deleteAll() {
        return dao.deleteAll();
    }

}
