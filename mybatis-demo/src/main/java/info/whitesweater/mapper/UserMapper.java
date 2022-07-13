package info.whitesweater.mapper;

import info.whitesweater.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    List<User> getUserList();
}
