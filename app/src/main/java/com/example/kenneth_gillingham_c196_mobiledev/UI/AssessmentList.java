package com.example.kenneth_gillingham_c196_mobiledev.UI;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenneth_gillingham_c196_mobiledev.Database.Repository;
import com.example.kenneth_gillingham_c196_mobiledev.Entities.CourseEntity;

import java.util.List;

/**
 * Project: Kenneth_Gillingham_C196_MobileDev
 * Package: com.example.kenneth_gillingham_c196_mobiledev.UI
 * <p>
 * User: KG19780
 * Date: 2/17/2023
 * <p>
 * Created with Android Studio
 */
public class AssessmentList extends AppCompatActivity {

    private Repository repository;
    CourseEntity currentCourses;
    public static int numberAssessments;
    RecyclerView recyclerView;
    public static int termID;
    private int assessmentID;
    List<CourseEntity> alCourses;
    EditText edit;
}

   