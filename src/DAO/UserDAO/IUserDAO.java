package DAO.UserDAO;

import Models.User;

import java.util.ArrayList;

public interface IUserDAO
{
  void createUser(User user);
  void deleteUser(String username);
  void updateUser(User user);
  ArrayList<User> getUserList(String username);
  User getSpecificUserLogin(String username,String password);
}
