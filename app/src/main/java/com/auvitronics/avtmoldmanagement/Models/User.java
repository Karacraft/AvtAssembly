package com.auvitronics.avtmoldmanagement.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class User {

    @Expose
    @SerializedName("department")
    private String department;
    @Expose
    @SerializedName("designation")
    private String designation;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("api_token")
    private String api_token;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
