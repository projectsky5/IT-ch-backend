package com.projectsky.IT_ch_backend.dto.course;

public class CoursePatchRequest {
    private String courseName ;
    private String location ;
    private DurationRange duration ;
    private String refToChat ;
    private String refToGrades ;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public DurationRange getDuration() {
        return duration;
    }

    public void setDuration(DurationRange duration) {
        this.duration = duration;
    }

    public String getRefToChat() {
        return refToChat;
    }

    public void setRefToChat(String refToChat) {
        this.refToChat = refToChat;
    }

    public String getRefToGrades() {
        return refToGrades;
    }

    public void setRefToGrades(String refToGrades) {
        this.refToGrades = refToGrades;
    }

}
