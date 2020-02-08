package com.auvitronics.avtmoldmanagement.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "molds", indices ={@Index(value="itemcode",unique = true)})
public class Mold {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "itemcode")
    private int itemcode;

    @ColumnInfo(name = "partname")
    private String partname;

    @ColumnInfo(name = "customer")
    private String customer;

    @ColumnInfo(name = "unit")
    private String unit;

    @ColumnInfo(name = "production_order_id")
    private int production_order_id;

    @ColumnInfo(name = "production_per_hour")
    private float production_per_hour;

    @ColumnInfo(name = "production_halfhourly")
    private float production_halfhourly;

    @ColumnInfo(name = "electricity_per_set")
    private float electricity_per_set;

    public Mold(int itemcode, String partname, String customer, String unit, int production_order_id, float production_per_hour, float production_halfhourly, float electricity_per_set) {
        this.itemcode = itemcode;
        this.partname = partname;
        this.customer = customer;
        this.unit = unit;
        this.production_order_id = production_order_id;
        this.production_per_hour = production_per_hour;
        this.production_halfhourly = production_halfhourly;
        this.electricity_per_set = electricity_per_set;
    }

    public int getItemcode() {
        return itemcode;
    }

    public void setItemcode(int itemcode) {
        this.itemcode = itemcode;
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getProduction_order_id() {
        return production_order_id;
    }

    public void setProduction_order_id(int production_order_id) {
        this.production_order_id = production_order_id;
    }

    public float getProduction_per_hour() {
        return production_per_hour;
    }

    public void setProduction_per_hour(float production_per_hour) {
        this.production_per_hour = production_per_hour;
    }

    public float getProduction_halfhourly() {
        return production_halfhourly;
    }

    public void setProduction_halfhourly(float production_halfhourly) {
        this.production_halfhourly = production_halfhourly;
    }

    public float getElectricity_per_set() {
        return electricity_per_set;
    }

    public void setElectricity_per_set(float electricity_per_set) {
        this.electricity_per_set = electricity_per_set;
    }

    @Override
    public String toString() {
        return "Mold{" +
                "itemcode=" + itemcode +
                ", partname='" + partname + '\'' +
                ", customer='" + customer + '\'' +
                ", unit='" + unit + '\'' +
                ", production_order_id=" + production_order_id +
                ", production_per_hour=" + production_per_hour +
                ", production_halfhourly=" + production_halfhourly +
                ", electricity_per_set=" + electricity_per_set +
                '}';
    }
}
