package com.example.kenneth_gillingham_c196_mobiledev.UI;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kenneth_gillingham_c196_mobiledev.Database.AssessmentDatabase;
import com.example.kenneth_gillingham_c196_mobiledev.Database.Repository;
import com.example.kenneth_gillingham_c196_mobiledev.Entities.AssessmentEntity;
import com.example.kenneth_gillingham_c196_mobiledev.Entities.CourseEntity;
import com.example.kenneth_gillingham_c196_mobiledev.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Project: Kenneth_Gillingham_C196_MobileDev
 * Package: com.example.kenneth_gillingham_c196_mobiledev.UI
 * <p>
 * User: KG19780
 * Date: 2/20/2023
 * <p>
 * Created with Android Studio
 */
public class AssessmentDetail extends AppCompatActivity {

    private Repository repository;
    private int assessmentID;
    private int courseID;
    List<AssessmentEntity> allAssessments;
    AssessmentEntity currentAssessments;
    EditText editTitle;
    EditText editType;
    EditText editStartDate;
    EditText editEndDate;

    @Override protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);
        assessmentID = getIntent().getIntExtra("assessmentID",-1);
        courseID = getIntent().getIntExtra("courseID",-1);
        repository = new Repository(getApplication());
        allAssessments = repository.getAllAssessments();
        for (AssessmentEntity a : repository.getAllAssessments()){
            if (a.getAssessmentID() == assessmentID) currentAssessments = a;
        }

        editTitle = findViewById(R.id.assessmentTitleEditText);
        editType = findViewById(R.id.assessmentTypeEditText);
        editStartDate = findViewById(R.id.assessmentStartDateEditText);
        editEndDate = findViewById(R.id.assessmentEndDateEditText);

        if (currentAssessments != null){
            editTitle.setText(currentAssessments.getAssessmentTitle());
            editType.setText(currentAssessments.getAssessmentType());
            editStartDate.setText(currentAssessments.getAssessmentStartDate());
            editEndDate.setText(currentAssessments.getAssessmentEndDate());
        }

        Button button = findViewById(R.id.saveAssessmentButton);
        button.setOnClickListener(view -> {
            if(assessmentID == -1){
                AssessmentEntity newAssessment = new AssessmentEntity(0,editTitle.getText().toString() , editType.getText().toString() , editStartDate.getText().toString() , editEndDate.getText().toString() , courseID);
                repository.insert(newAssessment);
            }
            else{
                AssessmentEntity editAssessment = new AssessmentEntity(assessmentID,editTitle.getText().toString() , editType.getText().toString() , editStartDate.getText().toString() , editEndDate.getText().toString() , courseID);
                repository.update(editAssessment);
            }
            Intent intent = new Intent(AssessmentDetail.this,AssessmentList.class);
            finish();
        });

    }

    @Override public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.assessmentListDelete:
                repository.delete(currentAssessments);
                Toast.makeText(getApplicationContext(),"Assessment Deleted" , Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AssessmentDetail.this , AssessmentList.class);
                startActivity(intent);

            case R.id.assessmentListNotifyStart:
                String assessmentTitle = editTitle.getText().toString();
                String assessmentStartDate = editStartDate.getText().toString();
                String dateFormat = "MM/dd/yy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat , Locale.US);
                Date date = null;
                try {
                    date = simpleDateFormat.parse(assessmentStartDate);
                }
                catch (ParseException e){
                    e.printStackTrace();
                }
                Long trig = date.getTime();
                Intent notifyIntent = new Intent(AssessmentDetail.this , MyReceiver.class);
                notifyIntent.putExtra("key", "Assessment: " + assessmentTitle + " Starts: " + assessmentStartDate);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AssessmentDetail.this, ++MainActivity.numberAlert,notifyIntent,PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP , trig , pendingIntent);
                return true;

            case R.id.assessmentListNotifyEnd:
                assessmentTitle = editTitle.getText().toString();
                String assessmentEndDate = editEndDate.getText().toString();
                dateFormat = "MM/dd/yy";
                simpleDateFormat = new SimpleDateFormat(dateFormat , Locale.US);
                date = null;
                try {
                    date = simpleDateFormat.parse(assessmentEndDate);
                }
                catch (ParseException e){
                    e.printStackTrace();
                }
                trig = date.getTime();
                notifyIntent = new Intent(AssessmentDetail.this , MyReceiver.class);
                notifyIntent.putExtra("key", "Assessment: " + assessmentTitle + " Ends: " + assessmentEndDate);
                pendingIntent = PendingIntent.getBroadcast(AssessmentDetail.this , ++MainActivity.numberAlert , notifyIntent , PendingIntent.FLAG_IMMUTABLE);
                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP , trig , pendingIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.assessment_list_menu , menu);
        return true;
    }

}

   