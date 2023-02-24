package com.example.kenneth_gillingham_c196_mobiledev.Database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.kenneth_gillingham_c196_mobiledev.Entities.AssessmentEntity;
import com.example.kenneth_gillingham_c196_mobiledev.DAO.AssessmentDAO;

/**
 * Project: Kenneth_Gillingham_C196_MobileDev
 * Package: com.example.kenneth_gillingham_c196_mobiledev.Database
 * <p>
 * User: KG19780
 * Date: 2/15/2023
 * <p>
 * Created with Android Studio
 */

@Database(entities = {AssessmentEntity.class}, version = 10, exportSchema = false)

public abstract class AssessmentDatabase extends RoomDatabase {

    public abstract AssessmentDAO assessmentDAO();

    public static volatile AssessmentDatabase INSTANCE;

    static AssessmentDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (AssessmentDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AssessmentDatabase.class,"AssessmentDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

   