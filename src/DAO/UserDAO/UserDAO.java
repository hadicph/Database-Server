package DAO.UserDAO;

import Models.Case;
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

  @Override public List<Case> getCasesForSpecificUser(String userid)
  {
    Connection connection = null;
    List<Case> caseList = new ArrayList<>();
    try
    {
      connection = dbConnection.getDBConnection();
      String query = "select * from \"SEP3\".cases where userid = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1,userid);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next())
      {
        Case case1 = new Case();
        case1.setDate(resultSet.getString(1));
        case1.setUserid(resultSet.getString(2));
        case1.setCasedata(resultSet.getString(3));
        caseList.add(case1);
        System.out.println(case1);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return caseList;
  }

  @Override public boolean addUser(User user)
  {
    Connection connection = null;
    try
    {
      connection = dbConnection.getDBConnection();
      String query = "INSERT INTO \"SEP3\".users(\n"
          + "\tuserid, password, role, firstname, lastname, phonenb)\n"
          + "\tVALUES (?, ?, ?, ?, ?, ?);";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, user.getUserid());
      statement.setString(2, user.getPassword());
      statement.setString(3,user.getRole());
      statement.setString(4,user.getFirstname());
      statement.setString(5,user.getLastname());
      statement.setString(6,user.getPhonenb());
      statement.executeQuery();

      return true;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
   return false;
  }

  @Override public User loadData(String userid)
  {
    Connection connection = null;
    User user = new User();
    try
    {
      connection = dbConnection.getDBConnection();
      String query = "select * from \"SEP3\".users where userid = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1,userid);
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next())
      {
        user.setUserid(resultSet.getString(1));
        user.setPassword(resultSet.getString(2));
        user.setRole(resultSet.getString(3));
        user.setFirstname(resultSet.getString(4));
        user.setLastname(resultSet.getString(5));
        user.setPhonenb(resultSet.getString(6));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return user;
  }
}
