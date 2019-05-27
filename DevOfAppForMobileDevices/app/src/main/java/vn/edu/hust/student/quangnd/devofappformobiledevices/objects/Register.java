package vn.edu.hust.student.quangnd.devofappformobiledevices.objects;

public class Register {
    private String registerClassId;
    private String studentId;
    private String classId;

    public String getRegisterClassId() {
        return registerClassId;
    }

    public void setRegisterClassId(String registerClassId) {
        this.registerClassId = registerClassId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Register(String registerClassId, String studentId, String classId) {
        this.registerClassId = registerClassId;
        this.studentId = studentId;
        this.classId = classId;
    }
}
