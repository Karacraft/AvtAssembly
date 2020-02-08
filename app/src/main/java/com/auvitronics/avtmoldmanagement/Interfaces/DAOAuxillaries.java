package com.auvitronics.avtmoldmanagement.Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.auvitronics.avtmoldmanagement.Models.Auxillaries;

import java.util.List;

@Dao
public interface DAOAuxillaries {

    @Query("Select * from auxillaries")
    List<Auxillaries> getAuxillariesList();

    @Query("Select * from auxillaries where aux_id = :aux_id LIMIT 1")
    Auxillaries getAuxillariesById(int aux_id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAuxillaries(Auxillaries Auxillaries);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateAuxillaries(Auxillaries Auxillaries);

    @Delete
    void deleteAuxillaries(Auxillaries... Auxillaries);

}
