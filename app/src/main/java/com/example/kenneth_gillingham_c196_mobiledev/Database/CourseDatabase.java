package com.example.kenneth_gillingham_c196_mobiledev.Database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.kenneth_gillingham_c196_mobiledev.Entities.CourseEntity;
import com.example.kenneth_gillingham_c196_mobiledev.DAO.CourseDAO;

/**
 * Project: Kenneth_Gillingham_C196_MobileDev
 * Package: com.example.kenneth_gillingham_c196_mobiledev.Database
 * <p>
 * User: KG19780
 * Date: 2/15/2023
 * <p>
 * Created with Android Studio
 */
@Database(entities = {CourseEntity.class}, version = 9, exportSchema = false)

public abstract class CourseDatabase extends RoomDatabase {

    public abstract CourseDAO courseDAO();

    public static volatile CourseDatabase INSTANCE;

    static CourseDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (CourseDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),CourseDatabase.class,"CourseDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

   