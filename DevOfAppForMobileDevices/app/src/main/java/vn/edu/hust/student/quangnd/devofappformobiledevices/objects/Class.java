package vn.edu.hust.student.quangnd.devofappformobiledevices.objects;

public class Class {
    private String classId;
    private String startTime;
    private String finishTime;
    private String day;
    private String week;
    private String typeClass;
    private String subjectId;
    private String group;
    private String room;
    private String registered;
    private String maxRegister;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getMaxRegister() {
        return maxRegister;
    }

    public void setMaxRegister(String maxRegister) {
        this.maxRegister = maxRegister;
    }

    public Class(String classId, String startTime, String finishTime, String day, String week, String typeClass, String subjectId, String group, String room, String registered, String maxRegister) {
        this.classId = classId;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.day = day;
        this.week = week;
        this.typeClass = typeClass;
        this.subjectId = subjectId;
        this.group = group;
        this.room = room;
        this.registered = registered;
        this.maxRegister = maxRegister;
    }
}
