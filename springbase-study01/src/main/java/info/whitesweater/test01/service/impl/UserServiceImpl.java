package info.whitesweater.test01.service.impl;

import info.whitesweater.test01.dao.UserDao;
import info.whitesweater.test01.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void register() {
        userDao.register();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
