package com.example.tasknest;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class Agenda extends AppCompatActivity {

    // Variables de UI (interfaz de usuario)
    private EditText editTextTarea; // Campo para ingresar el texto de la tarea
    private Button buttonSeleccionarFecha, buttonAgregarTarea; // Botones para seleccionar fecha y agregar tarea
    private TextView textViewFechaSeleccionada; // TextView que muestra la fecha seleccionada
    private ListView listViewTareas; // ListView para mostrar las tareas

    // Variables auxiliares
    private String fechaSeleccionada = null; // Almacena la fecha seleccionada para la tarea
    private ArrayList<String> listaTareas = new ArrayList<>(); // Lista que contiene todas las tareas
    private ArrayAdapter<String> adaptadorTareas; // Adaptador para mostrar las tareas en el ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda); // Establece el layout de la actividad

        // Inicializar elementos de la interfaz de usuario
        editTextTarea = findViewById(R.id.Tarea); // Asocia el EditText para ingresar tarea
        buttonSeleccionarFecha = findViewById(R.id.SeleccionarFecha); // Asocia el botón para seleccionar fecha
        buttonAgregarTarea = findViewById(R.id.AgregarTarea); // Asocia el botón para agregar tarea
        textViewFechaSeleccionada = findViewById(R.id.FechaSeleccionada); // Asocia el TextView para mostrar fecha seleccionada
        listViewTareas = findViewById(R.id.ListaTareas); // Asocia el ListView para mostrar las tareas

        // Configurar adaptador para la lista de tareas
        adaptadorTareas = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaTareas);
        listViewTareas.setAdapter(adaptadorTareas); // Asocia el adaptador al ListView

        // Acción cuando se presiona el botón "Seleccionar Fecha Límite"
        buttonSeleccionarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarSelectorDeFecha(); // Llama al método para mostrar el selector de fecha
            }
        });

        // Acción cuando se presiona el botón "Agregar Tarea"
        buttonAgregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarTarea(); // Llama al método para agregar una tarea a la lista
            }
        });
    }

    // Método para mostrar el selector de fecha
    private void mostrarSelectorDeFecha() {
        final Calendar calendario = Calendar.getInstance(); // Obtiene la fecha actual
        int anio = calendario.get(Calendar.YEAR); // Año actual
        int mes = calendario.get(Calendar.MONTH); // Mes actual
        int dia = calendario.get(Calendar.DAY_OF_MONTH); // Día actual

        // Crear el DatePickerDialog para seleccionar una fecha
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            // Formatear la fecha seleccionada
            fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
            textViewFechaSeleccionada.setText("Fecha límite: " + fechaSeleccionada); // Mostrar la fecha seleccionada en el TextView
        }, anio, mes, dia); // Pasa la fecha actual como valores iniciales

        // Mostrar el selector de fecha
        datePickerDialog.show();
    }

    // Método para agregar una tarea a la lista
    private void agregarTarea() {
        String tarea = editTextTarea.getText().toString().trim(); // Obtener la tarea escrita en el EditText

        // Validar si el campo de tarea está vacío
        if (TextUtils.isEmpty(tarea)) {
            Toast.makeText(this, "Por favor, ingresa una tarea", Toast.LENGTH_SHORT).show(); // Mostrar mensaje de error
            return; // Detener la ejecución si no hay tarea
        }

        // Validar si no se ha seleccionado una fecha
        if (fechaSeleccionada == null) {
            Toast.makeText(this, "Por favor, selecciona una fecha límite", Toast.LENGTH_SHORT).show(); // Mostrar mensaje de error
            return; // Detener la ejecución si no hay fecha seleccionada
        }

        // Crear la tarea completa con la fecha seleccionada
        String tareaCompleta = tarea + " (Fecha: " + fechaSeleccionada + ")";
        listaTareas.add(tareaCompleta); // Agregar la tarea a la lista

        adaptadorTareas.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado

        // Limpiar los campos de entrada
        editTextTarea.setText(""); // Limpiar el EditText de tarea
        textViewFechaSeleccionada.setText("Fecha límite: No seleccionada"); // Resetear el TextView de fecha
        fechaSeleccionada = null; // Resetear la fecha seleccionada
    }
}
