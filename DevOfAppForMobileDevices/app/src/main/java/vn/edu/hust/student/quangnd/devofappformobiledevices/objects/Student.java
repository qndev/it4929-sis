package vn.edu.hust.student.quangnd.devofappformobiledevices.objects;
import java.sql.Date;

public class Student {
    private String studentId;
    private String name;
    private String dateOfBirth;
    private String studentClass;
    private String eduProgram;
    private String trainingSystem;
    private String state;
    private String year;
    private String department;
    private String email;
    private Double tuitionUnit;

    public Student() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getEduProgram() {
        return eduProgram;
    }

    public void setEduProgram(String eduProgram) {
        this.eduProgram = eduProgram;
    }

    public String getTrainingSystem() {
        return trainingSystem;
    }

    public void setTrainingSystem(String trainingSystem) {
        this.trainingSystem = trainingSystem;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getTuitionUnit() {
        return tuitionUnit;
    }

    public void setTuitionUnit(Double tuitionUnit) {
        this.tuitionUnit = tuitionUnit;
    }

    public Student(String studentId, String name, String dateOfBirth, String studentClass, String eduProgram, String trainingSystem, String state, String year, String department, String email, Double tuitionUnit) {
        this.studentId = studentId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.studentClass = studentClass;
        this.eduProgram = eduProgram;
        this.trainingSystem = trainingSystem;
        this.state = state;
        this.year = year;
        this.department = department;
        this.email = email;
        this.tuitionUnit = tuitionUnit;
    }

    public Student(String studentId, String name, String dateOfBirth, String studentClass, String eduProgram, String trainingSystem, String state, String year, String department, String email) {
        this.studentId = studentId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.studentClass = studentClass;
        this.eduProgram = eduProgram;
        this.trainingSystem = trainingSystem;
        this.state = state;
        this.year = year;
        this.department = department;
        this.email = email;
    }
}
