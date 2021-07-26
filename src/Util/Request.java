package Util;

import Models.User;

import java.io.Serializable;

public class Request implements Serializable
{
  private EnumRequest enumRequest;

  private User user;

  private String username;
  private String password;

  public EnumRequest getEnumRequest()
  {
    return enumRequest;
  }

  public void setEnumRequest(EnumRequest enumRequest)
  {
    this.enumRequest = enumRequest;
  }

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }
}
