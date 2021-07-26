import Networking.DBServer;

import java.io.IOException;

public class RunDatabaseServer
{
  public static void main(String[] args) throws IOException
  {
    DBServer dbServer = new DBServer();
    dbServer.initiateServer();
  }
}
