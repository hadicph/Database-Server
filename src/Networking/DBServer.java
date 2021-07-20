package Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DBServer
{
  public void initiateServer() throws IOException
  {
    ServerSocket serverSocket = new ServerSocket(2910);

    while (true)
    {
      try{
        System.out.println("Waiting for client to connect.");
        Socket client = serverSocket.accept();
        DBSocketHandler  dbSocketHandler = new DBSocketHandler(client);
        new Thread(dbSocketHandler).start();
        System.out.println("Client Connected to Server.");
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
  }
}
