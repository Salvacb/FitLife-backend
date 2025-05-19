package com.example.fitlifeapp;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncManager {

    public static void sincronizarRutinas(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        List<RutinaEntity> pendientes = db.rutinaDao().obtenerNoSincronizadas();

        if (pendientes.isEmpty()) return;

        // Convertir a objetos Rutina (o usar RutinaEntity si es igual)
        List<Rutina> rutinasSync = new ArrayList<>();
        for (RutinaEntity re : pendientes) {
            rutinasSync.add(new Rutina(re.getDia(), re.getDescripcion()));
        }

        ApiService api = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Void> call = api.enviarRutinas(rutinasSync);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    for (RutinaEntity re : pendientes) {
                        db.rutinaDao().marcarComoSincronizada(re.getId());
                    }
                    Log.d("SyncManager", "Rutinas sincronizadas exitosamente.");
                } else {
                    Log.e("SyncManager", "Fallo al sincronizar rutinas. Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("SyncManager", "Error de red al sincronizar rutinas: " + t.getMessage());
            }
        });
    }
}
