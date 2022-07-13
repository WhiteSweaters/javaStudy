package info.whitesweater.service.impl;


import info.whitesweater.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    public void insert() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            System.out.println("insert ...");
        }
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        System.out.println("循环一万次总用时："+ costTime);
    }

    public void delete() {
        System.out.println("delete ...");
    }

    public void update() {
        System.out.println("update ...");
    }

    public void select() {
        System.out.println("select ...");
    }
}
