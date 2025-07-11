package com.example.tasknest;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Profesores extends AppCompatActivity {

    // Variables de UI (Interfaz de usuario)
    private EditText editTextNombreProfesor, editTextAsignaturaProfesor, editTextContactoProfesor;
    private Button buttonAgregarProfesor;
    private ListView listViewProfesores;

    // Variables auxiliares
    private ArrayList<String> listaProfesores = new ArrayList<>(); // Lista para almacenar la información de los profesores
    private ArrayAdapter<String> adaptadorProfesores; // Adaptador para mostrar la lista de profesores en ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores); // Establece el layout de la actividad

        // Inicializar los elementos de la interfaz de usuario
        editTextNombreProfesor = findViewById(R.id.NombreProfesor); // Asocia el EditText para ingresar el nombre del profesor
        editTextAsignaturaProfesor = findViewById(R.id.AsignaturaProfesor); // Asocia el EditText para ingresar la asignatura del profesor
        editTextContactoProfesor = findViewById(R.id.ContactoProfesor); // Asocia el EditText para ingresar el contacto del profesor
        buttonAgregarProfesor = findViewById(R.id.AgregarProfesor); // Asocia el botón para agregar el profesor
        listViewProfesores = findViewById(R.id.ListaProfesores); // Asocia el ListView para mostrar los profesores

        // Configurar el adaptador para la lista de profesores
        adaptadorProfesores = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProfesores);
        listViewProfesores.setAdapter(adaptadorProfesores); // Establece el adaptador al ListView

        // Acción cuando se presiona el botón "Agregar Profesor"
        buttonAgregarProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarProfesor(); // Llama al método para agregar el profesor
            }
        });
    }

    // Método para agregar un profesor a la lista
    private void agregarProfesor() {
        // Obtener los valores de los campos de texto
        String nombre = editTextNombreProfesor.getText().toString().trim();
        String asignatura = editTextAsignaturaProfesor.getText().toString().trim();
        String contacto = editTextContactoProfesor.getText().toString().trim();

        // Validar que los campos no estén vacíos
        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(this, "Por favor, ingresa el nombre del profesor", Toast.LENGTH_SHORT).show();
            return; // Detener la ejecución si el nombre está vacío
        }
        if (TextUtils.isEmpty(asignatura)) {
            Toast.makeText(this, "Por favor, ingresa la asignatura", Toast.LENGTH_SHORT).show();
            return; // Detener la ejecución si la asignatura está vacía
        }
        if (TextUtils.isEmpty(contacto)) {
            Toast.makeText(this, "Por favor, ingresa el contacto del profesor", Toast.LENGTH_SHORT).show();
            return; // Detener la ejecución si el contacto está vacío
        }

        // Crear una cadena que contenga la información del profesor
        String profesorInfo = "Nombre: " + nombre + "\nAsignatura: " + asignatura + "\nContacto: " + contacto;

        // Agregar la información del profesor a la lista
        listaProfesores.add(profesorInfo);
        adaptadorProfesores.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado

        // Limpiar los campos de entrada
        editTextNombreProfesor.setText(""); // Limpiar el EditText del nombre
        editTextAsignaturaProfesor.setText(""); // Limpiar el EditText de la asignatura
        editTextContactoProfesor.setText(""); // Limpiar el EditText del contacto
    }
}