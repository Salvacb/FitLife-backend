package com.example.fitlifeapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("/api/login") // Asegúrate de que tu backend tenga este endpoint REST
    Call<LoginResponse> loginUsuario(@Body Usuario usuario);
}
