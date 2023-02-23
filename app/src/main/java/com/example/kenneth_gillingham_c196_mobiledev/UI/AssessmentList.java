package com.example.kenneth_gillingham_c196_mobiledev.UI;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenneth_gillingham_c196_mobiledev.Database.Repository;
import com.example.kenneth_gillingham_c196_mobiledev.Entities.AssessmentEntity;
import com.example.kenneth_gillingham_c196_mobiledev.Entities.CourseEntity;
import com.example.kenneth_gillingham_c196_mobiledev.Entities.TermEntity;
import com.example.kenneth_gillingham_c196_mobiledev.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    CourseEntity currentCourse;
    public static int numberAssessments;
    RecyclerView recyclerView;
    public static int termID;
    private int courseID;
    List<CourseEntity> allCourses;
    EditText editTitle;
    EditText editStartDate;
    EditText editEndDate;
    EditText editStatus;
    EditText editInstructorName;
    EditText editInstructorPhone;
    EditText editInstructorEmail;
    EditText editNotes;



    @Override protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
        courseID = getIntent().getIntExtra("courseID",-1);
        termID = getIntent().getIntExtra("termID",-1);
        repository = new Repository(getApplication());
        allCourses = repository.getAllCourses();
        for (CourseEntity c : repository.getAllCourses()){
            //Log.d("Course ID", "ID: " + c.getCourseID());
            if (c.getCourseID() == (courseID)) currentCourse = c;
        }

        editTitle=findViewById(R.id.courseTitleEditText);
        editStartDate=findViewById(R.id.courseStartDateEditText);
        editEndDate=findViewById(R.id.courseEndDateEditText);
        editStatus=findViewById(R.id.courseStatusEditText);
        editInstructorName=findViewById(R.id.courseInstructorNameEditText);
        editInstructorPhone=findViewById(R.id.courseInstructorPhoneEditText);
        editInstructorEmail=findViewById(R.id.courseInstructorEmailEditText);
        editNotes=findViewById(R.id.courseNotesEditText);

        if (currentCourse != null){
            editTitle.setText(currentCourse.getCourseTitle());
            editStartDate.setText(currentCourse.getCourseStartDate());
            editEndDate.setText(currentCourse.getCourseEndDate());
            editStatus.setText(currentCourse.getCourseStatus());
            editInstructorName.setText(currentCourse.getCourseInstructorName());
            editInstructorPhone.setText(currentCourse.getCourseInstructorPhone());
            editInstructorEmail.setText(currentCourse.getCourseInstructorEmail());
            editNotes.setText(currentCourse.getCourseNotes());
        }

        RecyclerView recyclerView = findViewById(R.id.associatedAssessmentRecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<AssessmentEntity> filteredAssessments = new ArrayList<>();
        for (AssessmentEntity a : repository.getAllAssessments()){
            if (a.getCourseID() == courseID) filteredAssessments.add(a);
        }

        numberAssessments = filteredAssessments.size();
        assessmentAdapter.setAssessments(filteredAssessments);


        FloatingActionButton fab = findViewById(R.id.addAssessmentFAB);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(AssessmentList.this,AssessmentDetail.class);
            if (currentCourse != null) intent.putExtra("courseID", currentCourse.getCourseID());
            intent.putExtra("courseID",courseID);
            startActivity(intent);
        });


        Button button = findViewById(R.id.saveCourseButton);
        button.setOnClickListener(view -> {
            if(courseID == -1){
                //ID = allCourses.get(allCourses.size()-1).getCourseID();
                CourseEntity newCourse = new CourseEntity(0,editTitle.getText().toString(),editStartDate.getText().toString(),editEndDate.getText().toString(),editStatus.getText().toString(),editInstructorName.getText().toString(),editInstructorPhone.getText().toString(),editInstructorEmail.getText().toString(),editNotes.getText().toString(),termID);
                repository.insert(newCourse);
            }
            else{
                CourseEntity editCourse = new CourseEntity(courseID,editTitle.getText().toString(),editStartDate.getText().toString(),editEndDate.getText().toString(),editStatus.getText().toString(),editInstructorName.getText().toString(),editInstructorPhone.getText().toString(),editInstructorEmail.getText().toString(),editNotes.getText().toString(),termID);
                repository.update(editCourse);
            }
            Intent intent = new Intent(AssessmentList.this,CourseList.class);
            startActivity(intent);
        });


        if (NotificationManagerCompat.from(this).areNotificationsEnabled()) {
            // Notifications are enabled, proceed with showing the notification
        } else {
            // Notifications are not enabled, prompt the user to enable them
            Intent settingsIntent = new Intent();
            settingsIntent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            settingsIntent.putExtra("app_package", getPackageName());
            settingsIntent.putExtra("app_uid", getApplicationInfo().uid);
            startActivity(settingsIntent);
        }

    }

    @Override public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            //case android.R.home:
                //this.finish();
                //return true;

            case R.id.courseListDelete:
                repository.delete(currentCourse);
                Toast.makeText(getApplicationContext(), "Course Deleted",Toast.LENGTH_LONG).show();
                Intent deleteIntent = new Intent(AssessmentList.this,CourseList.class);
                startActivity(deleteIntent);

            case R.id.courseListNotifyStart:
                String courseStartDate = editStartDate.getText().toString();
                String courseTitle = editTitle.getText().toString();
                String dateFormat = "MM/DD/YYYY";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                Date date = null;
                try {
                    date = simpleDateFormat.parse(courseStartDate);
                }
                catch (ParseException e){
                    e.printStackTrace();
                }
                Long trig = date.getTime();
                Intent startIntent = new Intent(AssessmentList.this, Receiver.class);
                startIntent.putExtra("key","COURSE" + courseTitle + "STARTS" + courseStartDate);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AssessmentList.this, ++MainActivity.numberAlert,startIntent,PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP,trig,pendingIntent);
                return true;

            case R.id.courseListNotifyEnd:
                String courseEndDate = editEndDate.getText().toString();
                courseTitle = editTitle.getText().toString();
                dateFormat = "MM/DD/YYYY";
                simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                date = null;
                try {
                    date = simpleDateFormat.parse(courseEndDate);
                }
                catch (ParseException e){
                    e.printStackTrace();
                }
                trig = date.getTime();
                Intent endIntent = new Intent(AssessmentList.this, Receiver.class);
                endIntent.putExtra("key", "COURSE" + courseTitle + "ENDS" + courseEndDate);
                PendingIntent pendingIntent1 = PendingIntent.getBroadcast(AssessmentList.this, ++MainActivity.numberAlert,endIntent,PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager1 = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager1.set(AlarmManager.RTC_WAKEUP,trig,pendingIntent1);
                return true;

            case R.id.courseListShare:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TITLE,"NOTES");
                sendIntent.putExtra(Intent.EXTRA_TEXT,"Course" + editTitle.getText().toString() + "notes" + ":" + editNotes.getText().toString());
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent,null);
                startActivity(shareIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.course_list_menu,menu);
        return true;
    }


    @Override
    protected void onResume(){
        super.onResume();
        List<AssessmentEntity> allAssessments = repository.getAllAssessments();
        RecyclerView recyclerView = findViewById(R.id.associatedAssessmentRecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<AssessmentEntity>  filteredAssessments = new ArrayList<>();

        for (AssessmentEntity a : allAssessments){
            if (a.getCourseID() == courseID)
                filteredAssessments.add(a);
        }

        numberAssessments = filteredAssessments.size();
        assessmentAdapter.setAssessments(filteredAssessments);
    }

}

   