package DAO.UserDAO;

import Models.User;

import java.util.ArrayList;
import java.util.List;

public interface IUserDAO
{
  List<User> getAllUsers();
  boolean deleteUser(String userid);
}
