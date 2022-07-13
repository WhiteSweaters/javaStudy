package info.whitesweater.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface LogMapper {


    @Transactional(propagation = Propagation.REQUIRES_NEW)  //设置事务的传递性  这个配置代表不加入整体事务
    @Insert("insert into tb_log(out1,in1,time1,money) values(#{out},#{in},#{time},#{money})")
    void logTransfer(@Param("out") String out, @Param("in") String in, @Param("time") String time, @Param("money") BigDecimal money);

}
