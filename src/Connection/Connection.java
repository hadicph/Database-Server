package Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection
{
  private static final String url = "";
  private static final String user="";
  private static final String password="";

  public Connection()
  {
    try
    {
      DriverManager.registerDriver(new org.postgresql.Driver());
    }
    catch (SQLException throwables){
      throwables.printStackTrace();
    }
  }
}
