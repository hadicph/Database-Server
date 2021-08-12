package Networking;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
  private String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=SEP3";
  private String user = "postgres";
  private String password = "7637";

  public DBConnection()
  {
    try
    {
      DriverManager.registerDriver(new org.postgresql.Driver());
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  public Connection getDBConnection() throws SQLException
  {
    return DriverManager.getConnection(url,user,password);
  }
}
