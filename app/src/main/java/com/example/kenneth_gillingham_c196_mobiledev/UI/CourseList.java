package com.example.kenneth_gillingham_c196_mobiledev.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenneth_gillingham_c196_mobiledev.Database.Repository;
import com.example.kenneth_gillingham_c196_mobiledev.Entities.CourseEntity;
import com.example.kenneth_gillingham_c196_mobiledev.Entities.TermEntity;
import com.example.kenneth_gillingham_c196_mobiledev.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
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
public class CourseList extends AppCompatActivity {

    private Repository repository;
    EditText editTitle;
    EditText editStartDate;
    EditText editEndDate;
    TermEntity currentTerm;
    public static int numberCourses;
    RecyclerView recyclerView;
    private int ID;
    List<TermEntity> allTerms;
    private CourseEntity currentCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        ID = getIntent().getIntExtra("termID", -1);
        repository = new Repository(getApplication());
        allTerms = repository.getAllTerms();
        for (TermEntity t:allTerms){
            if (t.getTermID() == ID) currentTerm = t;
        }

        editTitle = findViewById(R.id.termTitleEditText);
        editStartDate = findViewById(R.id.termStartDateEditText);
        editEndDate = findViewById(R.id.termEndDateEditText);

        if (currentTerm != null){
            editTitle.setText(currentTerm.getTermTitle());
            editStartDate.setText(currentTerm.getTermStartDate());
            editEndDate.setText(currentTerm.getTermEndDate());
        }
        RecyclerView recyclerView = findViewById(R.id.associatedCoursesRecyclerView);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //List<CourseEntity> allCourses = repository.getAllCourses();
        //courseAdapter.setCourses(allCourses);
        List<CourseEntity> filteredCourses = new ArrayList<>();
        for (CourseEntity c : repository.getAllCourses()){
            if (c.getTermID() == ID)
                filteredCourses.add(c);
        }
        numberCourses = filteredCourses.size();
        courseAdapter.setCourses(filteredCourses);

        FloatingActionButton fab = findViewById(R.id.addCourseFAB);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(CourseList.this,AssessmentList.class);
            if (currentCourses != null)
                intent.putExtra("courseID", currentCourses.getTermID());
            intent.putExtra("termID",ID);
            startActivity(intent);
        });

        Button button = findViewById(R.id.saveTermButton);
        button.setOnClickListener(view -> {
            if(ID == -1){
                ID = allTerms.get(allTerms.size()-1).getTermID();
                TermEntity t = new TermEntity(++ID,editTitle.getText().toString(),editStartDate.getText().toString(),editEndDate.getText().toString());
                repository.insert(t);
            }
            else{
                TermEntity t = new TermEntity(ID,editTitle.getText().toString(),editStartDate.getText().toString(),editEndDate.getText().toString());
                repository.update(t);
            }
            Intent intent = new Intent(CourseList.this,TermList.class);
            startActivity(intent);
        });
    }


    @Override
    protected void onResume(){
        super.onResume();
        List<CourseEntity> allCourses = repository.getAllCourses();
        RecyclerView recyclerView = findViewById(R.id.associatedCoursesRecyclerView);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<CourseEntity>  filteredCourses = new ArrayList<>();

        for (CourseEntity c : allCourses){
            if (c.getTermID() == ID)
                filteredCourses.add(c);
        }

        numberCourses = filteredCourses.size();
        courseAdapter.setCourses(filteredCourses);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.delete:
                if (numberCourses == 0){
                    repository.delete(currentTerm);
                    Toast.makeText(getApplicationContext(),"Term SuccessfullyDeleted", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CourseList.this, TermList.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Cannot Delete a Term with an Associated Course", Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate();
        return true
    }





















    /*
    public void saveTerm(View view){
        if(ID == -1){
            ID = allTerms.get(allTerms.size()).getTermID();
            TermEntity t = new TermEntity(++ID,editTitle.getText().toString(),editStartDate.getText().toString(),editEndDate.getText().toString());
            repository.insert(t);
        }
        else{
            TermEntity t = new TermEntity(ID,editTitle.getText().toString(),editStartDate.getText().toString(),editEndDate.getText().toString());
            repository.update(t);
        }
        Intent intent = new Intent(CourseList.this,TermList.class);
        startActivity(intent);
    }

    */
}

   