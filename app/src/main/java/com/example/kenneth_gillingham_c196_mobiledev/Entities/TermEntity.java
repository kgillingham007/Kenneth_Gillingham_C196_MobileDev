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

@Entity (tableName = "term_table")

public class TermEntity {
    @PrimaryKey(autoGenerate = true)
    private int termID;
    private String termTitle;
    private String termStartDate;
    private String termEndDate;

    public TermEntity(int termID, String termTitle, String termStartDate, String termEndDate){
        this.termID = termID;
        this.termTitle = termTitle;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public String getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(String termStartDate) {
        this.termStartDate = termStartDate;
    }

    public String getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(String termEndDate) {
        this.termEndDate = termEndDate;
    }

    @Override
    public String toString(){
        return "TermEntity{" + "termTitle='" + termTitle + '\'' +
                ", termStartDate='" + termStartDate + '\'' +
                ", termEndDate='" + termEndDate + '\'' +
                '}';
    }
}

   