package com.auvitronics.avtmoldmanagement.Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.auvitronics.avtmoldmanagement.Models.WorkStation;

import java.util.List;

@Dao
public interface DAOWorkStation {

    @Query("Select * from workstations")
    List<WorkStation> getWorkStationsList();

    @Query("Select * from workstations where workstation_id = :workstation_id LIMIT 1")
    WorkStation getWorkStationById(int workstation_id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWorkStation(WorkStation workStation);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateWorkStation(WorkStation workStation);

    @Delete
    void deleteWorkStation(WorkStation... workStation);

}
