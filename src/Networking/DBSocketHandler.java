package Networking;

import DAO.LoginDAO.ILoginDAO;
import DAO.LoginDAO.LoginDAO;
import DAO.UserDAO.IUserDAO;
import DAO.UserDAO.UserDAO;
import Models.User;
import Util.NetworkRequest;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class DBSocketHandler implements Runnable
{
  private Socket socket;
  private OutputStream outputStream;
  private InputStream inputStream;
  private Gson gson;

  private ILoginDAO loginDAO = new LoginDAO();
  private IUserDAO userDAO = new UserDAO();

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
    byte[] inputFromTier2 = new byte[1024 * 1024];
    try
    {

      int arrayLength = inputStream
          .read(inputFromTier2, 0, inputFromTier2.length);
      String arrString = new String(inputFromTier2, 0, arrayLength);
      NetworkRequest request = gson.fromJson(arrString, NetworkRequest.class);

      switch (request.getType())
      {
        case LOGIN:
        {
          System.out.println(request.getData());
          try
          {
            User user = gson.fromJson(request.getData(), User.class);

            System.out.println(
                "userid: " + user.getUserid() + " password: " + user
                    .getPassword()); //TO BE REMOVED!!

            User userResult = loginDAO.userLogin(user);
            String jsonString = new Gson().toJson(userResult);
            byte[] array = jsonString.getBytes();
            outputStream.write(array, 0, array.length);
            break;
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }

        }
        case USERS:
        {
          System.out.println(request.getData());
          try
          {
            List<User> userList = userDAO.getAllUsers();
            String jsonString = new Gson().toJson(userList);
            byte[] array = jsonString.getBytes();
            outputStream.write(array, 0, array.length);
            break;
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
        case DELETE_USER:
        {
          System.out.println("Deleting User: " + request.getData());
          try
          {
            String userid = gson.fromJson(request.getData(), String.class);
            boolean response = userDAO.deleteUser(userid);
            String jsonString = new Gson().toJson(response);
            byte[] array = jsonString.getBytes();
            outputStream.write(array, 0, array.length);
            break;
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
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


    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }
}
