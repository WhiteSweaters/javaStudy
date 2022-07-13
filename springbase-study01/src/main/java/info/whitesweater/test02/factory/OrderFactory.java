package info.whitesweater.test02.factory;

import info.whitesweater.test02.dao.OrderDao;
import info.whitesweater.test02.dao.impl.OrderDaoImpl;

public class OrderFactory {

    public static OrderDao getOrderDao(){
        return new OrderDaoImpl();
    }
    public OrderDao getOrderDao1(){
        return new OrderDaoImpl();
    }
}
