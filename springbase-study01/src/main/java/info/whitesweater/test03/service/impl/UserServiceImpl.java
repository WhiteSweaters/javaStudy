package info.whitesweater.test03.service.impl;

import info.whitesweater.test03.dao.UserDao;
import info.whitesweater.test03.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void register() {
        userDao.register();
    }


}
