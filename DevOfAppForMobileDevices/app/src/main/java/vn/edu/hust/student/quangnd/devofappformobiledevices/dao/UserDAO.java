package vn.edu.hust.student.quangnd.devofappformobiledevices.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.edu.hust.student.quangnd.devofappformobiledevices.database.DBConnect;
import vn.edu.hust.student.quangnd.devofappformobiledevices.logic.PasswordUtils;
import vn.edu.hust.student.quangnd.devofappformobiledevices.objects.User;

public class UserDAO implements IUserDAO{

    public boolean getStudentUserLogin(User studentUser) throws SQLException {
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sqlQuery = "SELECT user_name, password, is_admin FROM dang_nhap WHERE user_name=?";
        boolean checkOpenConnection = DBConnect.openConnection();
        if(checkOpenConnection == true){
            ps = DBConnect.cnn.prepareStatement(sqlQuery);
            ps.setString(1, studentUser.getUserName());
            rs = ps.executeQuery();
            if(rs.first() && rs != null){
                String userNameGetFromDB = rs.getString("user_name");
                String passwordGetFromDB = rs.getString("password");
                String roleGetFromDB = rs.getString("is_admin");
                User studentUserFromDB = new User(userNameGetFromDB, passwordGetFromDB, roleGetFromDB);
                boolean encryptedPassword = PasswordUtils.checkPassword(studentUser.getPassword(), studentUserFromDB.getPassword());
                if(studentUser.getUserName().equals(studentUserFromDB.getUserName()) && studentUser.getIsAdmin().equals(studentUserFromDB.getIsAdmin()) && encryptedPassword == true){
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}
