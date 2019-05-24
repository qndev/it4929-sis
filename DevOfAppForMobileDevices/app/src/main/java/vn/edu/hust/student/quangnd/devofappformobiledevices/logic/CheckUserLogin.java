package vn.edu.hust.student.quangnd.devofappformobiledevices.logic;

import java.sql.SQLException;

import vn.edu.hust.student.quangnd.devofappformobiledevices.dao.UserDAO;
import vn.edu.hust.student.quangnd.devofappformobiledevices.objects.Student;
import vn.edu.hust.student.quangnd.devofappformobiledevices.objects.User;

public class CheckUserLogin {
    public boolean checkStudentLogin(String userName, String password) throws SQLException {
        String role = "student";
        User student = new User(userName, password, role);
        UserDAO userDAO = new UserDAO();
        boolean loginSuccess = userDAO.getStudentUserLogin(student);
        if(loginSuccess == true){
            return true;
        }else{
            return false;
        }
    }
}
