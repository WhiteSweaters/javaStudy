package info.whitesweater.test02.dao.impl;

import info.whitesweater.test02.dao.UserDao;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class UserDaoImpl implements UserDao {

    private String dataBaseName;
    private int connectNum;

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public void setConnectNum(int connectNum) {
        this.connectNum = connectNum;
    }

    public void register() {
        System.out.println("用户注册");
        System.out.println(dataBaseName);
        System.out.println(connectNum);
    }




}
