package com.projectsky.IT_ch_backend.dto.schedule;


public class SchedulePatchDto {
    private String frequency ;
    private Integer academicHours ;
    private String dayOfWeek ;
    private String startTime ;
    private String startDate ;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getAcademicHours() {
        return academicHours;
    }

    public void setAcademicHourse(Integer academicHours) {
        this.academicHours = academicHours;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
