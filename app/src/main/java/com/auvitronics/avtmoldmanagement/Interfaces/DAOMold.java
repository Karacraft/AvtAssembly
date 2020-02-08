package com.auvitronics.avtmoldmanagement.Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.auvitronics.avtmoldmanagement.Models.Mold;

import java.util.List;


@Dao
public interface DAOMold {

    @Query("Select * from molds")
    List<Mold> getMoldList();

    @Query("Select * from molds where itemcode = :itemcode LIMIT 1")
    Mold getMoldById(int itemcode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMold(Mold Mold);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateMold(Mold Mold);

    @Delete
    void deleteMold(Mold... Mold);

}
