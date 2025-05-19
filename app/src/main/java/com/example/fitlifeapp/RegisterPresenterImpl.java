package com.example.fitlifeapp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenterImpl implements RegisterPresenter {

    private final RegisterView view;

    public RegisterPresenterImpl(RegisterView view) {
        this.view = view;
    }

    @Override
    public void register(String correo, String contrasena) {
        ApiService api = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Usuario usuario = new Usuario(correo, contrasena);

        Call<LoginResponse> call = api.registerUsuario(usuario);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    view.onRegisterSuccess("Registro exitoso");
                } else {
                    view.onRegisterError("Registro fallido");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.onRegisterError("Error de red");
            }
        });
    }
}
