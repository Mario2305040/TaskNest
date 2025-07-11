package com.example.tasknest;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Ausencias extends AppCompatActivity {

    // Variables de UI (interfaz de usuario)
    private Spinner spinnerMateria; // Spinner para seleccionar materia
    private EditText editTextAusencias; // EditText para ingresar número de ausencias
    private Button btnAddAusencia; // Botón para añadir ausencias
    private ListView listViewAusencias; // ListView para mostrar las ausencias
    private TextView txtTotalAusencias; // TextView para mostrar el total de ausencias

    // Variables lógicas
    private ArrayList<String> materias; // Lista de materias disponibles
    private ArrayList<String> ausenciasList; // Lista de ausencias para mostrar en el ListView
    private ArrayAdapter<String> ausenciasAdapter; // Adaptador para el ListView de ausencias
    private int totalAusencias = 0; // Contador para el total de ausencias

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ausencias); // Establece el layout de la actividad

        // Inicializar elementos de la interfaz de usuario
        spinnerMateria = findViewById(R.id.spinnerMateria); // Asocia el spinner de materias
        editTextAusencias = findViewById(R.id.editTextAusencias); // Asocia el EditText de ausencias
        btnAddAusencia = findViewById(R.id.btn_add_ausencia); // Asocia el botón de agregar ausencia
        listViewAusencias = findViewById(R.id.listViewAusencias); // Asocia la ListView para mostrar ausencias
        txtTotalAusencias = findViewById(R.id.txtTotalAusencias); // Asocia el TextView para mostrar el total de ausencias

        // Configuración del Spinner con materias ficticias
        configurarSpinner();

        // Inicializar lista de ausencias y su adaptador
        ausenciasList = new ArrayList<>(); // Crea una lista vacía para las ausencias
        ausenciasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ausenciasList); // Crea un adaptador para la ListView
        listViewAusencias.setAdapter(ausenciasAdapter); // Asocia el adaptador a la ListView

        // Configurar el botón para añadir ausencias
        btnAddAusencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarAusencia(); // Llama al método para agregar una ausencia
            }
        });
    }

    // Método para configurar el Spinner con una lista de materias ficticias
    private void configurarSpinner() {
        materias = new ArrayList<>(); // Crea una lista de materias
        materias.add("Matemáticas"); // Agrega "Matemáticas" a la lista de materias
        materias.add("Historia"); // Agrega "Historia" a la lista de materias
        materias.add("Física"); // Agrega "Física" a la lista de materias
        materias.add("Literatura"); // Agrega "Literatura" a la lista de materias
        materias.add("Química"); // Agrega "Química" a la lista de materias

        // Crea un adaptador para el Spinner con las materias
        ArrayAdapter<String> adapterMaterias = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materias);
        adapterMaterias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Configura el estilo del dropdown
        spinnerMateria.setAdapter(adapterMaterias); // Asocia el adaptador al Spinner
    }

    // Método para agregar una ausencia
    private void agregarAusencia() {
        // Obtiene la materia seleccionada del Spinner
        String materiaSeleccionada = spinnerMateria.getSelectedItem().toString();
        // Obtiene el texto ingresado en el EditText de ausencias
        String ausenciasTexto = editTextAusencias.getText().toString().trim();

        // Verifica que el campo de ausencias no esté vacío
        if (!ausenciasTexto.isEmpty()) {
            try {
                // Intenta convertir el texto ingresado a un número entero
                int numAusencias = Integer.parseInt(ausenciasTexto);

                // Actualiza la lista de ausencias con la nueva entrada
                ausenciasList.add(materiaSeleccionada + ": " + numAusencias + " ausencias");
                ausenciasAdapter.notifyDataSetChanged(); // Notifica al adaptador que la lista ha cambiado

                // Suma el número de ausencias al total
                totalAusencias += numAusencias;
                // Actualiza el TextView con el total de ausencias
                txtTotalAusencias.setText("Total de Ausencias: " + totalAusencias);

                // Limpia el campo de texto de ausencias
                editTextAusencias.setText("");
                // Muestra un mensaje confirmando que la ausencia fue añadida
                Toast.makeText(this, "Ausencia añadida correctamente", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                // Si el texto ingresado no es un número válido, muestra un mensaje de error
                Toast.makeText(this, "Por favor, ingresa un número válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Si el campo de ausencias está vacío, muestra un mensaje de error
            Toast.makeText(this, "Por favor, ingresa el número de ausencias", Toast.LENGTH_SHORT).show();
        }
    }
}
