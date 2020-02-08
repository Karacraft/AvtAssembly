package com.auvitronics.avtmoldmanagement.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "auxillaries",indices = {@Index(value = "aux_id", unique = true)})
public class Auxillaries {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="aux_id")
    private int aux_id;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="location")
    private String location;
    @ColumnInfo(name="consumes")
    private String consumes;
    @ColumnInfo(name="produces")
    private String produces;
    @ColumnInfo(name="elec_per_hour")
    private float elec_per_hour;
    @ColumnInfo(name="gas_per_hour")
    private float gas_per_hour;
    @ColumnInfo(name="diesel_per_hour")
    private float diesel_per_hour;


    public Auxillaries(int aux_id, String name, String location, String consumes, String produces, float elec_per_hour, float gas_per_hour, float diesel_per_hour) {
        this.aux_id = aux_id;
        this.name = name;
        this.location = location;
        this.consumes = consumes;
        this.produces = produces;
        this.elec_per_hour = elec_per_hour;
        this.gas_per_hour = gas_per_hour;
        this.diesel_per_hour = diesel_per_hour;
    }

    public int getAux_id() {
        return aux_id;
    }

    public void setAux_id(int aux_id) {
        this.aux_id = aux_id;
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

    public String getConsumes() {
        return consumes;
    }

    public void setConsumes(String consumes) {
        this.consumes = consumes;
    }

    public String getProduces() {
        return produces;
    }

    public void setProduces(String produces) {
        this.produces = produces;
    }

    public float getElec_per_hour() {
        return elec_per_hour;
    }

    public void setElec_per_hour(float elec_per_hour) {
        this.elec_per_hour = elec_per_hour;
    }

    public float getGas_per_hour() {
        return gas_per_hour;
    }

    public void setGas_per_hour(float gas_per_hour) {
        this.gas_per_hour = gas_per_hour;
    }

    public float getDiesel_per_hour() {
        return diesel_per_hour;
    }

    public void setDiesel_per_hour(float diesel_per_hour) {
        this.diesel_per_hour = diesel_per_hour;
    }

    @Override
    public String toString() {
        return "Auxillaries{" +
                "aux_id=" + aux_id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", consumes='" + consumes + '\'' +
                ", produces='" + produces + '\'' +
                ", elec_per_hour=" + elec_per_hour +
                ", gas_per_hour=" + gas_per_hour +
                ", diesel_per_hour=" + diesel_per_hour +
                '}';
    }
}
