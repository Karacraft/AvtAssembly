package com.auvitronics.avtmoldmanagement.DB;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.auvitronics.avtmoldmanagement.Interfaces.DAOAuxillaries;
import com.auvitronics.avtmoldmanagement.Interfaces.DAOMold;
import com.auvitronics.avtmoldmanagement.Interfaces.DAOPerson;
import com.auvitronics.avtmoldmanagement.Interfaces.DAOWorkStation;
import com.auvitronics.avtmoldmanagement.Models.Auxillaries;
import com.auvitronics.avtmoldmanagement.Models.Mold;
import com.auvitronics.avtmoldmanagement.Models.Person;
import com.auvitronics.avtmoldmanagement.Models.WorkStation;

@Database(entities = {Person.class, Mold.class , WorkStation.class, Auxillaries.class}, exportSchema = false, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DAOPerson personDao();
    public abstract DAOMold moldDao();
    public abstract DAOWorkStation workStationDao();
    public abstract DAOAuxillaries auxillariesDao();

    public static final String DB_NAME = "app_db";
    public static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
