package Networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DBSocketHandler implements Runnable
{
  private OutputStream outputStream;
  private InputStream inputStream;
  public DBSocketHandler(Socket socket){
    String jsonResponse;
    try
    {
      outputStream = socket.getOutputStream();
      inputStream = socket.getInputStream();
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }
  @Override public void run()
  {

  }
}
