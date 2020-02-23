package com.auvitronics.avtmoldmanagement.Utilities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.auvitronics.avtmoldmanagement.BuildConfig;
import com.auvitronics.avtmoldmanagement.DB.AppDatabase;
import com.auvitronics.avtmoldmanagement.Models.Mold;
import com.auvitronics.avtmoldmanagement.Models.Person;
import com.auvitronics.avtmoldmanagement.Models.WorkStation;

import java.util.List;


public class Helper {

    //----------------------------------getAppVersionNumber()-----------------------------//
    public static String getAppVersionNumber(Context context) {

        String versionNumber = "";
        try {
            versionNumber = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            if (BuildConfig.DEBUG) {
                Log.d(Constants.TAG, "Helper->getAppVersionNumber: " + e.getMessage());
            }
        }

        return versionNumber;
    }

    public static boolean loadData(Context context) {
        try {
            AppDatabase appDb = AppDatabase.getInstance(context);
            Person person = new Person(1459, "Syed Ahmed", "SUPERVISOR", 32000, (32000 / 176));
            appDb.personDao().insertPerson(person);
            //  Operators
            Person person1 = new Person(21350, "Muhammad Maqbool", "OPERATOR", 30000, (30000 / 176));
            appDb.personDao().insertPerson(person1);
            Person person2 = new Person(10350, "Qasim Ali", "OPERATOR", 28000, (28000 / 176));
            appDb.personDao().insertPerson(person2);
            Person person3 = new Person(19210, "Mohsin Khalil", "OPERATOR", 27000, (27000 / 176));
            appDb.personDao().insertPerson(person3);
            //  Helpers
            Person person4 = new Person(21993, "Sumair Ali", "HELPER", 18000, (18000 / 176));
            appDb.personDao().insertPerson(person4);
            Person person5 = new Person(20693, "Khadim Hussain", "HELPER", 18000, (18000 / 176));
            appDb.personDao().insertPerson(person5);
            Person person6 = new Person(10893, "Fahad Khan", "HELPER", 18000, (18000 / 176));
            appDb.personDao().insertPerson(person6);
            Person person7 = new Person(9893, "Mobeen Haq", "HELPER", 18000, (18000 / 176));
            appDb.personDao().insertPerson(person7);

            List<Person> persons = appDb.personDao().getPersonList();
            for (Person p :
                    persons) {

                Log.d(Constants.TAG, "onCreate: " + p.toString());
            }

            //  Add Workstation Data
            WorkStation workStation1 = new WorkStation(1, "KM-320", "Molding", 8.5f);
            WorkStation workStation2 = new WorkStation(2, "KM-620", "Molding", 10.0f);
            WorkStation workStation3 = new WorkStation(3, "KM-800", "Molding", 12.5f);
            WorkStation workStation4 = new WorkStation(4, "KM-1080", "Molding", 14.5f);
            appDb.workStationDao().insertWorkStation(workStation1);
            appDb.workStationDao().insertWorkStation(workStation2);
            appDb.workStationDao().insertWorkStation(workStation3);
            appDb.workStationDao().insertWorkStation(workStation4);
            List<WorkStation> workStations = appDb.workStationDao().getWorkStationsList();
            for (WorkStation w : workStations) {
                Log.d(Constants.TAG, "loadWorkstationData: " + w.toString());
            }
            //  Add Mold Data
            Mold mold1 = new Mold(7962, "PANEL INSTRUMENT CLUSTER FINISH", "INDUS MOTOR COMPANY"
                    , "SET", 10000001, 45, 1.5f);
            Mold mold2 = new Mold(8592, "PANEL S/A INSTR PANEL FIN.LWR", "INDUS MOTOR COMPANY"
                    , "SET", 10000002, 80, 2.5f);
            Mold mold3 = new Mold(50022, "MUDGUARD FRONT LH CPD", "INDUS MOTOR COMPANY"
                    , "SET", 10000003, 60, 1.75f);

            appDb.moldDao().insertMold(mold1);
            appDb.moldDao().insertMold(mold2);
            appDb.moldDao().insertMold(mold3);
            List<Mold> molds = appDb.moldDao().getMoldList();
            for (Mold m : molds) {
                Log.d(Constants.TAG, "loadPersonData: " + m.toString());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
