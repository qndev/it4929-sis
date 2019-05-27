package vn.edu.hust.student.quangnd.devofappformobiledevices.objects;

public class User {
    private String userName;
    private String password;
    private String isAdmin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public User(String userName, String password, String isAdmin) {
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}
