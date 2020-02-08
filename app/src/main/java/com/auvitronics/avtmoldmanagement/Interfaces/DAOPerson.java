package com.auvitronics.avtmoldmanagement.Interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.auvitronics.avtmoldmanagement.Models.Person;

import java.util.List;

@Dao
public interface DAOPerson {

    @Query("Select * from persons")
    List<Person> getPersonList();

    @Query("Select * from persons where person_id = :person_id LIMIT 1")
    Person getPersonById(int person_id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPerson(Person person);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updatePerson(Person person);

    @Delete
    void deletePerson(Person... person);
}
