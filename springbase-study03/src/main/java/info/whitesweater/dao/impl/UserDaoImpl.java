package info.whitesweater.dao.impl;

import info.whitesweater.dao.UserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Repository(value = "userDao")
@Scope("singleton")
public class UserDaoImpl implements UserDao {

    @Value("${name}")
    private String name;

    public void register() {
        System.out.println("用户注册");
        System.out.println(name);
    }

  /*  @PostConstruct
    public void init(){
        System.out.println("init ...");
    }*/

    /*@PreDestroy
    public void destroy(){
        System.out.println("destroy ...");
    }*/
}
