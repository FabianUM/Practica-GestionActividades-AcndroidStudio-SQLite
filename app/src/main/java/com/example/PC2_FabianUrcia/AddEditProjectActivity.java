package com.example.PC2_FabianUrcia;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.PC2_FabianUrcia.dao.ProyectoDAO;
import com.example.PC2_FabianUrcia.dto.Proyecto;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEditProjectActivity extends AppCompatActivity {

    private TextInputLayout txtCodigoProyecto, txtCodigoActividad, txtObservacion, txtFecha;
    private Spinner spnEstado;
    private ProyectoDAO proyectoDAO;
    private Proyecto proyecto;
    private boolean isEditMode = false;
    private Button btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);

        try {
            txtCodigoProyecto = findViewById(R.id.txtCodigoProyecto);
            txtCodigoActividad = findViewById(R.id.txtCodigoActividad);
            txtObservacion = findViewById(R.id.txtObservacion);
            txtFecha = findViewById(R.id.txtFecha);
            spnEstado = findViewById(R.id.spnEstado);
            Button btnGuardar = findViewById(R.id.btnGuardar);
            btnEliminar = findViewById(R.id.btnEliminar);

            proyectoDAO = new ProyectoDAO(this);

            // Set default date
            txtFecha.getEditText().setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estados_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnEstado.setAdapter(adapter);

            Intent intent = getIntent();
            if (intent != null && intent.hasExtra("id")) {
                isEditMode = true;
                proyecto = new Proyecto();
                proyecto.setId(intent.getIntExtra("id", 0));
                proyecto.setCodigoProyecto(intent.getStringExtra("codigo_proyecto"));
                proyecto.setCodigoActividad(intent.getStringExtra("codigo_actividad"));
                proyecto.setEstado(intent.getStringExtra("estado"));
                proyecto.setObservacion(intent.getStringExtra("observacion"));
                proyecto.setFecha(intent.getStringExtra("fecha"));

                txtCodigoProyecto.getEditText().setText(proyecto.getCodigoProyecto());
                txtCodigoActividad.getEditText().setText(proyecto.getCodigoActividad());
                txtObservacion.getEditText().setText(proyecto.getObservacion());
                txtFecha.getEditText().setText(proyecto.getFecha());

                // Set spinner selection
                if (proyecto.getEstado() != null) {
                    int spinnerPosition = adapter.getPosition(proyecto.getEstado());
                    spnEstado.setSelection(spinnerPosition);
                }

                // Mostrar el botón de eliminar si estamos en modo edición
                btnEliminar.setVisibility(View.VISIBLE);
            }

            btnGuardar.setOnClickListener(view -> {
                Proyecto p = new Proyecto();
                p.setCodigoProyecto(txtCodigoProyecto.getEditText().getText().toString());
                p.setCodigoActividad(txtCodigoActividad.getEditText().getText().toString());
                p.setEstado(spnEstado.getSelectedItem().toString());
                p.setObservacion(txtObservacion.getEditText().getText().toString());
                p.setFecha(txtFecha.getEditText().getText().toString());

                if (isEditMode) {
                    p.setId(proyecto.getId());
                    proyectoDAO.update(p);
                } else {
                    proyectoDAO.insert(p);
                }
                finish();
            });

            btnEliminar.setOnClickListener(view -> {
                if (isEditMode) {
                    proyectoDAO.delete(proyecto);
                }
                finish();
            });

        } catch (Exception ex) {
            Log.e("ERROR", ex.getMessage(), ex);
        }
    }
}