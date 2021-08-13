package DAO.UserDAO;

import Models.Case;
import Models.User;

import java.util.ArrayList;
import java.util.List;

public interface IUserDAO
{
  List<User> getAllUsers();
  boolean deleteUser(String userid);
  List<Case> getCasesForSpecificUser(String userid);
  boolean addUser(User user);
  User loadData(String userid);
}
