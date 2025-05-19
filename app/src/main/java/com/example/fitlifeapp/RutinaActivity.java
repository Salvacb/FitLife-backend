package com.example.fitlifeapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Intent;
import java.util.ArrayList;


public class RutinaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutina);

        recyclerView = findViewById(R.id.recyclerViewRutinas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cargarRutinas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SyncManager.sincronizarRutinas(this);
    }

    private void cargarRutinas() {
        ApiService api = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Rutina>> call = api.getRutinas();

        call.enqueue(new Callback<List<Rutina>>() {
            @Override
            public void onResponse(Call<List<Rutina>> call, Response<List<Rutina>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Rutina> rutinas = response.body();

                    // Guardar en Room
                    List<RutinaEntity> entidades = new ArrayList<>();
                    for (Rutina r : rutinas) {
                        entidades.add(new RutinaEntity(r.getDia(), r.getDescripcion()));
                    }
                    AppDatabase.getDatabase(RutinaActivity.this)
                            .rutinaDao()
                            .insertarRutinas(entidades);

                    // Mostrar en UI
                    RutinaAdapter adapter = new RutinaAdapter(rutinas, rutina -> {
                        Intent intent = new Intent(RutinaActivity.this, DetalleRutinaActivity.class);
                        intent.putExtra("dia", rutina.getDia());
                        intent.putExtra("descripcion", rutina.getDescripcion());
                        startActivity(intent);
                    });
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Rutina>> call, Throwable t) {
                // Si falla la conexión, cargar rutinas locales
                List<RutinaEntity> locales = AppDatabase.getDatabase(RutinaActivity.this)
                        .rutinaDao()
                        .obtenerTodas();

                List<Rutina> rutinasLocales = new ArrayList<>();
                for (RutinaEntity re : locales) {
                    rutinasLocales.add(new Rutina(re.getDia(), re.getDescripcion()));
                }

                recyclerView.setAdapter(new RutinaAdapter(rutinasLocales, rutina -> {
                    Intent intent = new Intent(RutinaActivity.this, DetalleRutinaActivity.class);
                    intent.putExtra("dia", rutina.getDia());
                    intent.putExtra("descripcion", rutina.getDescripcion());
                    startActivity(intent);
                }));
            }
        });
    }

}
