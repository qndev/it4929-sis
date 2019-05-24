package vn.edu.hust.student.quangnd.devofappformobiledevices.dao;

import java.sql.SQLException;

import vn.edu.hust.student.quangnd.devofappformobiledevices.objects.User;

public interface IUserDAO {
    public boolean getStudentUserLogin(User user) throws SQLException;
}
