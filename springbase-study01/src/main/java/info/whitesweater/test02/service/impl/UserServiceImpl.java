package info.whitesweater.test02.service.impl;

import info.whitesweater.test02.dao.UserDao;
import info.whitesweater.test02.service.UserService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class UserServiceImpl implements UserService, InitializingBean, DisposableBean {

    private UserDao userDao;

    public void register() {
        userDao.register();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
        System.out.println("属性设置");
    }

    public void init(){
        System.out.println("初始化方法...");
    }

    public void destroy(){
        System.out.println("销毁方法 ...");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("属性设置之后...");
    }


}
