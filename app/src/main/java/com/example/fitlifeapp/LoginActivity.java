package com.example.fitlifeapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitlifeapp.LoginPresenter;
import com.example.fitlifeapp.LoginPresenterImpl;
import androidx.appcompat.app.AppCompatActivity;





public class LoginActivity extends AppCompatActivity implements LoginView {
    private EditText emailEditText, passwordEditText;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        presenter = new LoginPresenterImpl(this);

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            presenter.login(email, password);
        });
    }

    @Override
    public void onLoginSuccess(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        // Ir a la siguiente pantalla
    }

    @Override
    public void onLoginError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
