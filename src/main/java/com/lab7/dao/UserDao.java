package com.lab7.dao;

import com.lab7.message.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Stack;

public class UserDao implements GenericDao<User, String> {

    private JdbcConfig jdbc;

    public UserDao() {
        try {
            jdbc = new JdbcConfig();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User save(User user) {
        try {
            PreparedStatement statement = jdbc.getConnection().prepareStatement(
                    "INSERT INTO user_lab (" +
                            "name, " +
                            "password, " +
                            "address) " +
                            "VALUES (?, ?, ?)"
            );

            convertToStatement(user, statement);

            int rowsInserted = statement.executeUpdate();

            if(rowsInserted > 0) {
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> saveAll(List<User> entities) {
        entities.forEach(this::save);
        return entities;
    }

    @Override
    public Stack<User> findAll() {

        Stack<User> users = new Stack<>();

        try {
            PreparedStatement statement = jdbc.getConnection().prepareStatement(
                    "SELECT * FROM user_lab"
            );

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(convertToUser(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

    @Override
    public User findById(String name) {
        try {
            PreparedStatement statement = jdbc.getConnection().prepareStatement(
                    "SELECT * FROM DRAGON WHERE name=?"
            );

            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return convertToUser(resultSet);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(User user) {

        try {
            PreparedStatement statement = jdbc.getConnection().prepareStatement(
                    "UPDATE user_lab SET " +
                            "password=?, " +
                            "address=? " +
                            "where name=?"
            );

            convertToStatement(user, statement);

            return statement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        try {
            PreparedStatement statement = jdbc.getConnection().prepareStatement(
                    "DELETE FROM user_lab WHERE name=?"
            );
            statement.setString(1, user.getName());
            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(String name) {
        try {
            PreparedStatement statement = jdbc.getConnection().prepareStatement(
                    "DELETE FROM user_lab WHERE name=?"
            );
            statement.setString(1, name);
            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        try {
            PreparedStatement statement = jdbc.getConnection().prepareStatement("TRUNCATE user_lab");
            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private User convertToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setName(resultSet.getString(1));
        user.setPassword(resultSet.getString(2));
        user.setAddress(resultSet.getString(3));
        return user;
    }

    private void convertToStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getAddress());
    }
}
