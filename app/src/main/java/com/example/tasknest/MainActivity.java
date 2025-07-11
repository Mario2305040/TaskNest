package com.example.tasknest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.widget.SwitchCompat;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Establece el layout para la actividad principal

        // Referencias a los elementos de la interfaz
        SwitchCompat switchModoOscuro = findViewById(R.id.switch1); // Referencia al switch para el modo oscuro
        TextView txtFecha = findViewById(R.id.txt_fecha); // Referencia al TextView para mostrar la fecha
        ListView listViewTareas = findViewById(R.id.listTareasDia); // Referencia a la ListView para las tareas del día

        // Referencias a los botones de la interfaz
        ImageButton btnAsignaturas = findViewById(R.id.btn_asignaturas); // Botón de Asignaturas
        ImageButton btnAusencias = findViewById(R.id.btn_ausencias); // Botón de Ausencias
        ImageButton btnCalculoRapido = findViewById(R.id.btn_calculo_rapido); // Botón de Cálculo rápido
        ImageButton btnHorario = findViewById(R.id.btn_horario); // Botón de Horario
        ImageButton btnAgenda = findViewById(R.id.btn_agenda); // Botón de Agenda
        ImageButton btnProfesores = findViewById(R.id.btn_profesores); // Botón de Profesores

        // Mostrar la fecha actual en el TextView
        String fechaActual = "Fecha actual: " + Calendar.getInstance().getTime().toString(); // Obtiene la fecha actual
        txtFecha.setText(fechaActual); // Establece la fecha en el TextView

        // Configuración del Switch para activar/desactivar el modo oscuro
        switchModoOscuro.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) { // Si el switch está activado, se activa el modo oscuro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else { // Si el switch está desactivado, se desactiva el modo oscuro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        // Población de la lista de tareas del día
        ArrayList<String> tareasDelDia = new ArrayList<>(); // Se crea una lista de tareas
        tareasDelDia.add("Tarea 1"); // Agregar tarea 1 a la lista
        tareasDelDia.add("Tarea 2"); // Agregar tarea 2 a la lista
        tareasDelDia.add("Tarea 3"); // Agregar tarea 3 a la lista

        // Configuración del adaptador para la ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tareasDelDia); // Crea un adaptador para la lista
        listViewTareas.setAdapter(adapter); // Establece el adaptador en la ListView

        // Configuración de los botones para navegar a diferentes actividades
        btnAsignaturas.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Asignaturas", Toast.LENGTH_SHORT).show(); // Muestra un mensaje cuando se presiona el botón
            // Navegar a la actividad de Asignaturas
            Intent intent = new Intent(MainActivity.this, Asignaturas.class);
            startActivity(intent); // Inicia la actividad de Asignaturas
        });

        btnAusencias.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Ausencias", Toast.LENGTH_SHORT).show(); // Muestra un mensaje cuando se presiona el botón
            // Navegar a la actividad de Ausencias
            Intent intent = new Intent(MainActivity.this, Ausencias.class);
            startActivity(intent); // Inicia la actividad de Ausencias
        });

        btnCalculoRapido.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Cálculo Rápido", Toast.LENGTH_SHORT).show(); // Muestra un mensaje cuando se presiona el botón
            // Navegar a la actividad de Cálculo rápido
            Intent intent = new Intent(MainActivity.this, CalculoRapido.class);
            startActivity(intent); // Inicia la actividad de Cálculo rápido
        });

        btnHorario.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Horario", Toast.LENGTH_SHORT).show(); // Muestra un mensaje cuando se presiona el botón
            // Navegar a la actividad de Horario
            Intent intent = new Intent(MainActivity.this, Horario.class);
            startActivity(intent); // Inicia la actividad de Horario
        });

        btnAgenda.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Agenda", Toast.LENGTH_SHORT).show(); // Muestra un mensaje cuando se presiona el botón
            // Navegar a la actividad de Agenda
            Intent intent = new Intent(MainActivity.this, Agenda.class);
            startActivity(intent); // Inicia la actividad de Agenda
        });

        btnProfesores.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Profesores", Toast.LENGTH_SHORT).show(); // Muestra un mensaje cuando se presiona el botón
            // Navegar a la actividad de Profesores
            Intent intent = new Intent(MainActivity.this, Profesores.class);
            startActivity(intent); // Inicia la actividad de Profesores
        });
    }
}
