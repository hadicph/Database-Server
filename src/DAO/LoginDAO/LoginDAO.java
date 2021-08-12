package DAO.LoginDAO;

import Models.User;
import Networking.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO implements ILoginDAO
{
  private DBConnection dbConnection = new DBConnection();

  @Override public User userLogin(User user)
  {
    Connection connection = null;
    String userid = user.getUserid();
    String password = user.getPassword();
    User user1 = new User();
    try {
      connection = dbConnection.getDBConnection();
      String query = "select * from \"SEP3\".users where userid = ? and password = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1,userid);
      statement.setString(2, password);

      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        user1.setUserid(resultSet.getString(1));
        user1.setPassword(resultSet.getString(2));
        user1.setRole(resultSet.getString(3));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return user1;
  }
}
