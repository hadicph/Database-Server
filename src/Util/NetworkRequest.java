package Util;

import java.io.Serializable;

public class NetworkRequest implements Serializable
{
  private String data;
  private NetworkType type;

  public NetworkRequest(NetworkType networkType, String data)
  {
    this.data = data;
    this.type = networkType;
  }

  public String getData()
  {
    return data;
  }

  public void setData(String data)
  {
    this.data = data;
  }

  public NetworkType getType()
  {
    return type;
  }

  public void setType(NetworkType type)
  {
    this.type = type;
  }

  public NetworkRequest(NetworkType type)
  {
    this.type = type;
  }

  @Override public String toString()
  {
    return "NetworkRequest{" + "data='" + data + '\'' + ", type=" + type + '}';
  }
}

