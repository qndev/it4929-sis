package vn.edu.hust.student.quangnd.devofappformobiledevices.dao;

import vn.edu.hust.student.quangnd.devofappformobiledevices.objects.Student;

public interface IStudentHelper {
    public void getStudentProfile(String studentId);

    public Student getStudentById(String studentId);
}
