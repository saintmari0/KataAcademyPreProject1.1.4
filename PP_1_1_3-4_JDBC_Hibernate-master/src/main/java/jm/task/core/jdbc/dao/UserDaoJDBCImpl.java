package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable(){
        String sql = "CREATE TABLE IF NOT EXISTS Users (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, lastName CHAR(30) NOT NULL, age MEDIUMINT NOT NULL, PRIMARY KEY (id));";
        try (Connection connection = Util.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "drop table Users";
        try (Connection connection = Util.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into Users (name, lastName, age) values(?, ?, ?)";
        try (Connection connection = Util.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from Users where id = ?";
        try (Connection connection = Util.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";
        try (Connection connection = Util.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(Long.valueOf(rs.getString("id")));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(Byte.valueOf(rs.getString("age")));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "delete from Users";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
