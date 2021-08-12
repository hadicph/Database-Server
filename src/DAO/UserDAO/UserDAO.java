package DAO.UserDAO;

import Models.User;
import Networking.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO
{
  private DBConnection dbConnection = new DBConnection();

  @Override public List<User> getAllUsers()
  {
    Connection connection = null;
    List<User> userList = new ArrayList<>();
    try
    {
      connection = dbConnection.getDBConnection();
      String query = "select * from \"SEP3\".users where role != 'admin'";
      PreparedStatement statement = connection.prepareStatement(query);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next())
      {
        User user1 = new User();
        user1.setUserid(resultSet.getString(1));
        user1.setPassword(resultSet.getString(2));
        user1.setRole(resultSet.getString(3));
        userList.add(user1);
        System.out.println(user1);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return userList;
  }

  @Override public boolean deleteUser(String userid)
  {
    Connection connection = null;
    try
    {
      connection = dbConnection.getDBConnection();
      String query = "DELETE FROM \"SEP3\".users WHERE userid = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, userid);

      statement.executeQuery();
      return true;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;

  }
}
