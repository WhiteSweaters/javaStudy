package info.whitesweater.test01;

import info.whitesweater.test01.service.UserService;
import info.whitesweater.test01.service.impl.UserServiceImpl;

public class App {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.register();
    }
}
