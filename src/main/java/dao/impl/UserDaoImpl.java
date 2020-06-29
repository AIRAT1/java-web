package dao.impl;

import dao.UserDao;
import entity.Users;

public class UserDaoImpl implements UserDao {
    @Override
    public int createUser(Users user) {
        return 0;
    }

    @Override
    public int editUser(Users user) {
        return 0;
    }

    @Override
    public Users getUserByPasswordAndLogin(String password, String login) {
        return null;
    }
}
