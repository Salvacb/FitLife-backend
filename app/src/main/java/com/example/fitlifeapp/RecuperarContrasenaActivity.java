package com.example.fitlifeapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecuperarContrasenaActivity extends AppCompatActivity {

    EditText emailInput;
    Button sendButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        emailInput = findViewById(R.id.emailInput);
        sendButton = findViewById(R.id.sendButton);
        backButton = findViewById(R.id.backButton);

        sendButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString();

            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            Call<Void> call = apiService.recuperarContrasena(email);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(getApplicationContext(), "Si el correo existe, se enviará un enlace", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error al intentar enviar el correo", Toast.LENGTH_LONG).show();
                }
            });
        });



        backButton.setOnClickListener(v -> finish());
    }
}
