package Networking;


import DAO.UserDAO.IUserDAO;
import Models.User;
import Util.Request;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.Socket;

public class DBSocketHandler implements Runnable
{
  private Socket socket;
  private OutputStream outputStream;
  private InputStream inputStream;
  private Gson gson;

  private IUserDAO userDAO;

  public DBSocketHandler(Socket socket)
  {
    this.socket = socket;
    gson = new Gson();

    String jsonResponse;
    try
    {
      outputStream = socket.getOutputStream();
      inputStream = socket.getInputStream();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    byte[] inputFromTier2 = new byte[1024*1024];
    try {

      int arrayLength = inputStream.read(inputFromTier2, 0, inputFromTier2.length);
      String arrString = new String(inputFromTier2, 0, arrayLength);
      Request request = gson.fromJson(arrString, Request.class);

      switch (request.getEnumRequest())
      {
        case CreateUser:
        {
          JsonReader jsonReader = new JsonReader(new StringReader(request.getUser().toString()));
          jsonReader.setLenient(true);
          User user = request.getUser();
          userDAO.createUser(user);
          break;
        }
        case GetSpecificUserLogin:
        {
          User user = userDAO.getSpecificUserLogin(request.getUsername(), request.getPassword());
          String jsonString = new Gson().toJson(user);
          byte[] array = jsonString.getBytes();
          outputStream.write(array,0,array.length);
          break;
        }

        /*case GetFromTier3:
        {
          dummy.setName("Chris");
          dummy.setAge(26);
          dummy.setSalary(6500.00);

          String jsonString = new Gson().toJson(dummy);
          byte[] array = jsonString.getBytes();
          outputStream.write(array,0,array.length);
          break;
        }

        case SendToTier3:
        {
          JsonReader reader = new JsonReader(new StringReader(request.getDummyObject().toString()));
          reader.setLenient(true);

          DummyObject dummy = request.getDummyObject();
          //dummyDAO.createDummy(dummy); <-- Something like this to call a function in the DAO

          System.out.println("Name: " + dummy.getName() + ", Age: " + dummy.getAge() + ", Salary: " + dummy.getSalary());
          break;
        }

         */


      }

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
