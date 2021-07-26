package DAO.UserDAO;

import Models.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements IUserDAO
{
  public Connection getDBConnection()
  {
    Connection connection = null;
    Statement statement = null;
    try{
      return connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=SEP3\", admin, 7637");
    }
    catch (SQLException e){
      e.printStackTrace();
    }
    return null;
  }
  @Override public void createUser(User user)
  {
    Connection connection = getDBConnection();

    try
    {
      String query = "Insert into Users (username, password, role) values (?,?,?)";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, user.getUsername());
      statement.setString(2, user.getPassword());
      statement.setString(3, user.getLevel());

      statement.executeUpdate();
    }
    catch (SQLException e){
      e.printStackTrace();
    }
  }

  @Override public void deleteUser(String username)
  {

  }

  @Override public void updateUser(User user)
  {

  }

  @Override public ArrayList<User> getUserList(String username)
  {
    return null;
  }

  @Override public User getSpecificUserLogin(String username, String password)
  {
    Connection connection = getDBConnection();
    User user = new User();

    try {
      String query = "select * from users where username = ? and password = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1,username);
      statement.setString(2, password);

      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        user.setUsername(resultSet.getString(1));
        user.setPassword(resultSet.getString(2));
        user.setLevel(resultSet.getString(3));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return user;
  }
}
