package com.example.fitlifeapp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.fitlifeapp.Usuario;
import com.example.fitlifeapp.LoginResponse;
import com.example.fitlifeapp.ApiService;
import com.example.fitlifeapp.RetrofitClient;

public class LoginPresenterImpl implements LoginPresenter {
    private final LoginView view;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
    }

    @Override
    public void login(String email, String password) {
        ApiService api = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Usuario usuario = new Usuario(email, password);

        Call<LoginResponse> call = api.loginUsuario(usuario);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    view.onLoginSuccess("Bienvenido");
                } else {
                    view.onLoginError("Credenciales inválidas");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.onLoginError("Error de red");
            }
        });
    }
}
