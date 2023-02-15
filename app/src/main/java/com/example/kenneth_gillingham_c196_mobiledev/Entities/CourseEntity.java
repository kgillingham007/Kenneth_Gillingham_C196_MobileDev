package com.example.kenneth_gillingham_c196_mobiledev.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.kenneth_gillingham_c196_mobiledev.Database.CourseDatabase;

/**
 * Project: Kenneth_Gillingham_C196_MobileDev
 * Package: com.example.kenneth_gillingham_c196_mobiledev.Entities
 * <p>
 * User: KG19780
 * Date: 2/15/2023
 * <p>
 * Created with Android Studio
 */

@Entity(tableName = "course_table")

public class CourseEntity {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String courseTitle;
    private String courseStartDate;
    private String courseEndDate;
    private String courseStatus;
    private String courseInstructorName;
    private String courseInstructorPhone;
    private String courseInstructorEmail;
    private String courseNotes;
    private int termID;


    public CourseEntity(int courseID, String courseTitle, String courseStartDate, String courseEndDate, String courseStatus, String courseInstructorName,
                        String courseInstructorPhone, String courseInstructorEmail, String courseNotes, int termID){
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseStatus = courseStatus;
        this.courseInstructorName = courseInstructorName;
        this.courseInstructorPhone = courseInstructorPhone;
        this.courseInstructorEmail = courseInstructorEmail;
        this.courseNotes = courseNotes;
        this.termID = termID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public String getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseInstructorName() {
        return courseInstructorName;
    }

    public void setCourseInstructorName(String courseInstructorName) {
        this.courseInstructorName = courseInstructorName;
    }

    public String getCourseInstructorPhone() {
        return courseInstructorPhone;
    }

    public void setCourseInstructorPhone(String courseInstructorPhone) {
        this.courseInstructorPhone = courseInstructorPhone;
    }

    public String getCourseInstructorEmail() {
        return courseInstructorEmail;
    }

    public void setCourseInstructorEmail(String courseInstructorEmail) {
        this.courseInstructorEmail = courseInstructorEmail;
    }

    public String getCourseNotes() {
        return courseNotes;
    }

    public void setCourseNotes(String courseNotes) {
        this.courseNotes = courseNotes;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseTitle='" + courseTitle + '\'' +
                ", courseStartDate='" + courseStartDate + '\'' +
                ", courseEndDate='" + courseEndDate + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", courseInstructorName='" + courseInstructorName + '\'' +
                ", courseInstructorPhone='" + courseInstructorPhone + '\'' +
                ", courseInstructorEmail='" + courseInstructorEmail + '\'' +
                ", courseNotes='" + courseNotes + '\'' +
                '}';
    }
}

   