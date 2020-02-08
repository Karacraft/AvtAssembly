package com.auvitronics.avtmoldmanagement.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "persons", indices ={@Index(value="person_id",unique = true)})
public class Person {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "person_id")
    private int person_id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name="function")
    private String function;
    @ColumnInfo(name="salary")
    private float salary;
    @ColumnInfo(name="hourly_rate")
    private float hourly_rate;
    @ColumnInfo(name="shift_start_time")
    private String shift_start_time;
    @ColumnInfo(name="shift_end_time")
    private String shift_end_time;

    public Person(int person_id, String name, String function, float salary, float hourly_rate) {
        this.person_id = person_id;
        this.name = name;
        this.function = function;
        this.salary = salary;
        this.hourly_rate = hourly_rate;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getHourly_rate() {
        return hourly_rate;
    }

    public void setHourly_rate(float hourly_rate) {
        this.hourly_rate = hourly_rate;
    }

    public String getShift_start_time() {
        return shift_start_time;
    }

    public void setShift_start_time(String shift_start_time) {
        this.shift_start_time = shift_start_time;
    }

    public String getShift_end_time() {
        return shift_end_time;
    }

    public void setShift_end_time(String shift_end_time) {
        this.shift_end_time = shift_end_time;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id='" + person_id + '\'' +
                ", name='" + name + '\'' +
                ", function='" + function + '\'' +
                ", salary=" + salary +
                ", hourly_rate=" + hourly_rate +
                ", shift_start_time='" + shift_start_time + '\'' +
                ", shift_end_time='" + shift_end_time + '\'' +
                '}';
    }
}
