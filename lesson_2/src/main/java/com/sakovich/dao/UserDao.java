package com.sakovich.dao;

import com.sakovich.bd.DataSourceUtil;


import java.sql.*;

public class UserDao {

    public static int findByLogin(String login, String password) {
        try {
            Connection connection = DataSourceUtil.INSTANCE.getConnection();

            PreparedStatement statement = connection.prepareStatement("select * from users where login=?");
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            //если в бд нет такого логина, возвращаем false
            if (!resultSet.next()) {
                DataSourceUtil.INSTANCE.returnConnection(connection);
                return 0;
            }

            String passDb = resultSet.getString("password");
            if (!passDb.equals(password)) {
                DataSourceUtil.INSTANCE.returnConnection(connection);
                return 1;
            }

            DataSourceUtil.INSTANCE.returnConnection(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 2;
    }

    public static boolean addAccount(String login, String password, String name) {

        try {
            Connection connection = DataSourceUtil.INSTANCE.getConnection();

            PreparedStatement statement = connection.prepareStatement("insert into users (login, password, name)" +
                    " values (?, ?, ?)");

            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, name);

            statement.executeUpdate();

            DataSourceUtil.INSTANCE.returnConnection(connection);

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
