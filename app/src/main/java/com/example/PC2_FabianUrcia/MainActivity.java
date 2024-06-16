package com.example.PC2_FabianUrcia;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Button;

import com.example.PC2_FabianUrcia.dao.ProyectoDAO;
import com.example.PC2_FabianUrcia.dto.Proyecto;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProyectoDAO proyectoDAO;
    private ListView lstProyectos;
    private ArrayAdapter<String> adapter;
    private List<Proyecto> proyectos;
    private SearchView searchView;
    private Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Log.d("DEBUG", "onCreate: Inicializando componentes");

            proyectoDAO = new ProyectoDAO(this);
            lstProyectos = findViewById(R.id.lstProyectos);
            searchView = findViewById(R.id.searchView);
            btnBuscar = findViewById(R.id.btnBuscar);

            listar();

            lstProyectos.setOnItemClickListener((parent, view, position, id) -> {
                Proyecto proyecto = proyectos.get(position);
                Intent intent = new Intent(MainActivity.this, AddEditProjectActivity.class);
                intent.putExtra("id", proyecto.getId());
                intent.putExtra("codigo_proyecto", proyecto.getCodigoProyecto());
                intent.putExtra("codigo_actividad", proyecto.getCodigoActividad());
                intent.putExtra("estado", proyecto.getEstado());
                intent.putExtra("observacion", proyecto.getObservacion());
                intent.putExtra("fecha", proyecto.getFecha());
                startActivity(intent);
            });

            findViewById(R.id.btnAdd).setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, AddEditProjectActivity.class);
                startActivity(intent);
            });

            btnBuscar.setOnClickListener(v -> buscarProyectos());

        } catch (Exception ex) {
            Log.e("ERROR", "Error in onCreate: " + ex.getMessage(), ex);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("DEBUG", "onResume: Actualizando lista de proyectos");
        listar();
    }

    private void listar() {
        try {
            Log.d("DEBUG", "listar: Obteniendo lista de proyectos");
            proyectos = proyectoDAO.getList();
            cargarProyectos(proyectos);
        } catch (Exception e) {
            Log.e("ERROR", "Error in listar: " + e.getMessage(), e);
        }
    }

    private void buscarProyectos() {
        try {
            Log.d("DEBUG", "buscarProyectos: Buscando proyectos");
            String query = searchView.getQuery().toString();
            List<Proyecto> filteredProyectos = new ArrayList<>();
            if (!query.isEmpty()) {
                if (query.matches("\\d+")) { // Check if the query is an ID
                    Proyecto proyecto = proyectoDAO.getProyectoById(Integer.parseInt(query));
                    if (proyecto != null) {
                        filteredProyectos.add(proyecto);
                    }
                } else { // Assume the query is a date
                    filteredProyectos = proyectoDAO.getProyectosByFecha(query);
                }
            } else {
                filteredProyectos = proyectoDAO.getList();
            }
            cargarProyectos(filteredProyectos);
        } catch (Exception e) {
            Log.e("ERROR", "Error in buscarProyectos: " + e.getMessage(), e);
        }
    }

    private void cargarProyectos(List<Proyecto> proyectos) {
        try {
            Log.d("DEBUG", "cargarProyectos: Cargando proyectos en el adaptador");
            String[] items = new String[proyectos.size()];
            for (int i = 0; i < proyectos.size(); i++) {
                Proyecto p = proyectos.get(i);
                items[i] = p.getId() + " - " + p.getObservacion() + " - " + p.getEstado();
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
            lstProyectos.setAdapter(adapter);
        } catch (Exception e) {
            Log.e("ERROR", "Error in cargarProyectos: " + e.getMessage(), e);
        }
    }
}
