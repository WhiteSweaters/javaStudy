package info.whitesweater.mapper;


import info.whitesweater.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("select * from tb_user")
    List<User> getUserList();

    @Select("select * from tb_user where id = #{id}")
    User getUserById(int id);

    @Delete("delete from tb_user where id = #{id}")
    void deleteUser(int id);
}
