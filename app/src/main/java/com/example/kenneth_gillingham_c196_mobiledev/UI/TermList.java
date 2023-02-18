package com.example.kenneth_gillingham_c196_mobiledev.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenneth_gillingham_c196_mobiledev.Database.Repository;
import com.example.kenneth_gillingham_c196_mobiledev.Entities.TermEntity;
import com.example.kenneth_gillingham_c196_mobiledev.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
public class TermList extends AppCompatActivity {
    private Repository repository;
    private RecyclerView recyclerView;
    private int numTerms;
    private int termID;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());
        List<TermEntity> allTerms = repository.getAllTerms();
        termAdapter.setTerms(allTerms);
        FloatingActionButton fab = findViewById(R.id.addTermFAB);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(TermList.this, CourseList.class);
            startActivity(intent);
        });
    }

    @Override protected void onResume(){
        super.onResume();
        List<TermEntity> allTerms = repository.getAllTerms();
        RecyclerView recyclerView = findViewById(R.id.addTermFAB);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);
    }
}

   