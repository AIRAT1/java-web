package dao;

import entity.Users;

public interface UserDao {
    int createUser(Users user);
    int editUser(Users user);
    Users getUserByPasswordAndLogin(String password, String login);
}
