package info.whitesweater.test02.factory;

import info.whitesweater.test02.dao.OrderDao;
import info.whitesweater.test02.dao.impl.OrderDaoImpl;
import org.springframework.beans.factory.FactoryBean;

public class OrderBeanFactory implements FactoryBean<OrderDao> {
    public OrderDao getObject() throws Exception {
        return new OrderDaoImpl();
    }

    /**
     * 是否单例创建  默认为true
     * @return
     */
    public boolean isSingleton() {
        return true;
    }

    public Class<?> getObjectType() {
        return OrderDao.class;
    }
}
