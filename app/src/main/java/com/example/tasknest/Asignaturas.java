package com.example.tasknest;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Asignaturas extends AppCompatActivity {

    // Declaración de variables
    private Spinner spinnerSemestre;  // Spinner para seleccionar el semestre
    private EditText editTextMateria; // EditText para ingresar el nombre de la materia
    private Button btnAddMateria;     // Botón para agregar la materia
    private ListView listViewMaterias; // ListView para mostrar las materias añadidas

    private ArrayList<String> listaMaterias; // Lista de materias
    private ArrayAdapter<String> adapterMaterias; // Adaptador para el ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);

        // Inicialización de elementos de la interfaz
        spinnerSemestre = findViewById(R.id.spinnerSemestre);
        editTextMateria = findViewById(R.id.editTextMateria);
        btnAddMateria = findViewById(R.id.btn_add_materia);
        listViewMaterias = findViewById(R.id.listViewMaterias);

        // Configuración del Spinner
        configurarSpinner();

        // Inicialización de la lista y el adaptador para ListView
        listaMaterias = new ArrayList<>();
        adapterMaterias = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaMaterias);
        listViewMaterias.setAdapter(adapterMaterias);

        // Configurar el botón para añadir materias
        btnAddMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarMateria(); // Llamar al método para agregar la materia
            }
        });
    }

    // Método para configurar el Spinner
    private void configurarSpinner() {
        // Definir los semestres disponibles en el Spinner
        String[] semestres = {"Semestre 1", "Semestre 2", "Semestre 3", "Semestre 4", "Semestre 5", "Semestre 6"};

        // Crear el adaptador para el Spinner
        ArrayAdapter<String> adapterSemestre = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, semestres);
        adapterSemestre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Establecer el adaptador al Spinner
        spinnerSemestre.setAdapter(adapterSemestre);
    }

    // Método para añadir una materia
    private void agregarMateria() {
        String materia = editTextMateria.getText().toString().trim(); // Obtener el nombre de la materia
        String semestreSeleccionado = spinnerSemestre.getSelectedItem().toString(); // Obtener el semestre seleccionado

        // Verificar que el campo de materia no esté vacío
        if (!materia.isEmpty()) {
            // Agregar la materia y semestre a la lista
            listaMaterias.add(semestreSeleccionado + ": " + materia);

            // Actualizar el ListView con el nuevo elemento
            adapterMaterias.notifyDataSetChanged();

            // Limpiar el campo de texto de la materia
            editTextMateria.setText("");

            // Mostrar un mensaje de éxito
            Toast.makeText(this, "Materia añadida correctamente", Toast.LENGTH_SHORT).show();
        } else {
            // Mostrar un mensaje si no se ingresó el nombre de la materia
            Toast.makeText(this, "Por favor, ingresa el nombre de la materia", Toast.LENGTH_SHORT).show();
        }
    }
}
