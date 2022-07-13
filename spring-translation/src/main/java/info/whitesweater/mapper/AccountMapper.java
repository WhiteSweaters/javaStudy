package info.whitesweater.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountMapper {

    @Update("update tb_account set money=money-#{money} where username=#{from}")
    void transferFrom(@Param("from") String from,@Param("money") BigDecimal money);

    @Update("update tb_account set money=money+#{money} where username=#{to}")
    void transferTo(@Param("to") String to, @Param("money") BigDecimal money);
}
