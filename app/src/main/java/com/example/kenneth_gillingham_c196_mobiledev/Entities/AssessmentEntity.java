package com.example.kenneth_gillingham_c196_mobiledev.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Project: Kenneth_Gillingham_C196_MobileDev
 * Package: com.example.kenneth_gillingham_c196_mobiledev.Entities
 * <p>
 * User: KG19780
 * Date: 2/15/2023
 * <p>
 * Created with Android Studio
 */

@Entity(tableName = "assessment_table")

public class AssessmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String assessmentType;
    private String assessmentTitle;
    private String assessmentStartDate;
    private String assessmentEndDate;
    private int courseID;


    public AssessmentEntity(int assessmentID, String assessmentType, String assessmentTitle, String assessmentStartDate, String assessmentEndDate, int courseID){
        this.assessmentID = assessmentID;
        this.assessmentType = assessmentType;
        this.assessmentTitle = assessmentTitle;
        this.assessmentStartDate = assessmentStartDate;
        this.assessmentEndDate = assessmentEndDate;
        this.courseID = courseID;
    }


    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public String getAssessmentStartDate() {
        return assessmentStartDate;
    }

    public void setAssessmentStartDate(String assessmentStartDate) {
        this.assessmentStartDate = assessmentStartDate;
    }

    public String getAssessmentEndDate() {
        return assessmentEndDate;
    }

    public void setAssessmentEndDate(String assessmentEndDate) {
        this.assessmentEndDate = assessmentEndDate;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "AssessmentEntity{" +
                "assessmentTitle='" + assessmentTitle + '\'' +
                ", assessmentType='" + assessmentType + '\'' +
                ", assessmentStartDate='" + assessmentStartDate + '\'' +
                ", assessmentEndDate='" + assessmentEndDate + '\'' +
                '}';
    }
}

   