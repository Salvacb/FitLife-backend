package com.example.fitlifeapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    private EditText emailEditText, passwordEditText;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button registerButton = findViewById(R.id.registerButton);

        presenter = new RegisterPresenterImpl(this);

        registerButton.setOnClickListener(v -> {
            String correo = emailEditText.getText().toString();
            String contrasena = passwordEditText.getText().toString();
            presenter.register(correo, contrasena);
        });
    }

    @Override
    public void onRegisterSuccess(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onRegisterError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
