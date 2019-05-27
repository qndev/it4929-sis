package vn.edu.hust.student.quangnd.devofappformobiledevices.dao;

import vn.edu.hust.student.quangnd.devofappformobiledevices.objects.Student;

public interface IStudentHelper {
    public Student getStudentById(String studentId);

    public long insertStudent(Student insertStudent);

    public int updateStudent(Student updateStudent);

    public int deleteStudent(String studentId);
}
