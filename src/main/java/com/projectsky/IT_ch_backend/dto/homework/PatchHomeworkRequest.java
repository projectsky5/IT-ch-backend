package com.projectsky.IT_ch_backend.dto.homework;

import java.time.LocalDate;

public class PatchHomeworkRequest {
    private String title ;
    private LocalDate deadline ;
    private String refToHomework ;
    private String refToSubmitForm ;
    private String refToSolutionView ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getRefToHomework() {
        return refToHomework;
    }

    public void setRefToHomework(String refToHomework) {
        this.refToHomework = refToHomework;
    }

    public String getRefToSubmitForm() {
        return refToSubmitForm;
    }

    public void setRefToSubmitForm(String refToSubmitForm) {
        this.refToSubmitForm = refToSubmitForm;
    }

    public String getRefToSolutionView() {
        return refToSolutionView;
    }

    public void setRefToSolutionView(String refToSolutionView) {
        this.refToSolutionView = refToSolutionView;
    }
}
