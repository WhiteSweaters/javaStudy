package info.whitesweater.service;

import info.whitesweater.pojo.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

}
