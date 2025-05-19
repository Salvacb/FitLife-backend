package com.example.fitlifeapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;




import java.util.List;


public interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("/api/login")
    Call<LoginResponse> loginUsuario(@Body Usuario usuario);
    @POST("/api/register")
    Call<LoginResponse> registerUsuario(@Body Usuario usuario);

    @GET("/api/rutinas")
    Call<List<Rutina>> getRutinas();

    @POST("/api/sync/rutinas")
    Call<Void> enviarRutinas(@Body List<Rutina> rutinas);

    @FormUrlEncoded
    @POST("/recuperar")
    Call<Void> recuperarContrasena(@Field("email") String email);


}
