package Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DBServer
{
  public void initiateServer() throws IOException
  {
    ServerSocket serverSocket = new ServerSocket(1235);

    while (true)
    {
      try{
        System.out.println("Waiting for client to connect.");
        Socket socket = serverSocket.accept();
        DBSocketHandler  dbSocketHandler = new DBSocketHandler(socket);
        Thread thread = new Thread(dbSocketHandler);
        thread.setDaemon(true);
        thread.start();
        System.out.println("Client Connected to Server.");
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
  }
}
