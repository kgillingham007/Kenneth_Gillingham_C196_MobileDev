package com.example.kenneth_gillingham_c196_mobiledev.Database;

import android.app.Application;

import com.example.kenneth_gillingham_c196_mobiledev.Entities.AssessmentEntity;
import com.example.kenneth_gillingham_c196_mobiledev.DAO.AssessmentDAO;
import com.example.kenneth_gillingham_c196_mobiledev.Entities.CourseEntity;
import com.example.kenneth_gillingham_c196_mobiledev.DAO.CourseDAO;
import com.example.kenneth_gillingham_c196_mobiledev.Entities.TermEntity;
import com.example.kenneth_gillingham_c196_mobiledev.DAO.TermDAO;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Project: Kenneth_Gillingham_C196_MobileDev
 * Package: com.example.kenneth_gillingham_c196_mobiledev.Database
 * <p>
 * User: KG19780
 * Date: 2/15/2023
 * <p>
 * Created with Android Studio
 */
public class Repository {
    private AssessmentDAO mAssessmentDAO;
    private CourseDAO mCourseDAO;
    private TermDAO mTermDAO;
    private List<AssessmentEntity> mAllAssessments;
    private List<CourseEntity> mAllCourses;
    private List<TermEntity> mAllTerms;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        AssessmentDatabase abd = AssessmentDatabase.getDatabase(application);
        mAssessmentDAO = abd.assessmentDAO();

        CourseDatabase cbd = CourseDatabase.getDatabase(application);
        mCourseDAO = cbd.courseDAO();

        TermDatabase db = TermDatabase.getDatabase(application);
        mTermDAO = db.termDAO();
    }




    //Assessment related code
    public List<AssessmentEntity> getAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments = mAssessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllAssessments;
    }
    public void insert (AssessmentEntity assessmentEntity){
        databaseExecutor.execute(()->{
            mAssessmentDAO.insert(assessmentEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void update (AssessmentEntity assessmentEntity){
        databaseExecutor.execute(()->{
            mAssessmentDAO.update(assessmentEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void delete (AssessmentEntity assessmentEntity){
        databaseExecutor.execute(()->{
            mAssessmentDAO.delete(assessmentEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }




    //Course related code
    public List<CourseEntity> getAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses = mCourseDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllCourses;
    }
    public void insert (CourseEntity courseEntity){
        databaseExecutor.execute(()->{
            mCourseDAO.insert(courseEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void update (CourseEntity courseEntity){
        databaseExecutor.execute(()->{
            mCourseDAO.update(courseEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void delete (CourseEntity courseEntity){
        databaseExecutor.execute(()->{
            mCourseDAO.delete(courseEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }




    //Term related code
    public List<TermEntity> getAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms = mTermDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllTerms;
    }
    public void insert (TermEntity termEntity){
        databaseExecutor.execute(()->{
            mTermDAO.insert(termEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void update (TermEntity termEntity){
        databaseExecutor.execute(()->{
            mTermDAO.update(termEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void delete (TermEntity termEntity){
        databaseExecutor.execute(()->{
            mTermDAO.delete(termEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}

   