package com.auvitronics.avtmoldmanagement.Interfaces;

import com.auvitronics.avtmoldmanagement.Models.User;

import retrofit2.Call;
import retrofit2.http.POST;

public interface UserClient {

    @POST("getusertoken")
    Call<User> getUserToken(User user);

}
