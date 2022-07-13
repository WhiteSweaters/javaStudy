package info.whitesweater;

public class UserDao {

    private String username;

    public void register(){
        System.out.println(this.username);
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
