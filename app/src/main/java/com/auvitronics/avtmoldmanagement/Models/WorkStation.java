package com.auvitronics.avtmoldmanagement.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "workstations", indices = {@Index(value="workstation_id",unique = true)})
public class WorkStation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "workstation_id")
    private int workstation_id;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="location")
    private String location;
    @ColumnInfo(name="elec_per_hour_idle")
    private float elec_per_hour_idle;

    public WorkStation(int workstation_id, String name, String location, float elec_per_hour_idle) {
        this.workstation_id = workstation_id;
        this.name = name;
        this.location = location;
        this.elec_per_hour_idle = elec_per_hour_idle;
    }

    public int getWorkstation_id() {
        return workstation_id;
    }

    public void setWorkstation_id(int workstation_id) {
        this.workstation_id = workstation_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getElec_per_hour_idle() {
        return elec_per_hour_idle;
    }

    public void setElec_per_hour_idle(float elec_per_hour_idle) {
        this.elec_per_hour_idle = elec_per_hour_idle;
    }

    @Override
    public String toString() {
        return "WorkStation{" +
                "workstation_id=" + workstation_id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", elec_per_hour_idle=" + elec_per_hour_idle +
                '}';
    }
}
