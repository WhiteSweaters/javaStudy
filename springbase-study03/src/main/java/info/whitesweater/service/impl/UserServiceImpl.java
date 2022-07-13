package info.whitesweater.service.impl;

import info.whitesweater.dao.UserDao;
import info.whitesweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void register() {
        System.out.println("userService run ...");
        userDao.register();
    }


}
