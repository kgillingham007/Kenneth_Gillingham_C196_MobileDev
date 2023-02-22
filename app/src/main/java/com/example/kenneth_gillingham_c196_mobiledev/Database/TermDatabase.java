package com.example.kenneth_gillingham_c196_mobiledev.Database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.kenneth_gillingham_c196_mobiledev.Entities.TermEntity;
import com.example.kenneth_gillingham_c196_mobiledev.DAO.TermDAO;

/**
 * Project: Kenneth_Gillingham_C196_MobileDev
 * Package: com.example.kenneth_gillingham_c196_mobiledev.Database
 * <p>
 * User: KG19780
 * Date: 2/15/2023
 * <p>
 * Created with Android Studio
 */
@Database(entities = {TermEntity.class}, version = 4, exportSchema = false)

public abstract class TermDatabase extends RoomDatabase {

    public abstract TermDAO termDAO();

    public static volatile TermDatabase INSTANCE;

    static TermDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (CourseDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),TermDatabase.class,"TermDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

   