package com.example.fitlifeapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleRutinaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_rutina);

        TextView diaText = findViewById(R.id.textDia);
        TextView descripcionText = findViewById(R.id.textDescripcion);

        String dia = getIntent().getStringExtra("dia");
        String descripcion = getIntent().getStringExtra("descripcion");

        diaText.setText("Día: " + dia);
        descripcionText.setText("Ejercicios: " + descripcion);
    }
}
