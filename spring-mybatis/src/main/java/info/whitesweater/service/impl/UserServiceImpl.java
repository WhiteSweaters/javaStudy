package info.whitesweater.service.impl;

import info.whitesweater.mapper.UserMapper;
import info.whitesweater.pojo.User;
import info.whitesweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.getUserList();
    }

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }
}
