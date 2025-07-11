package com.example.tasknest;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Horario extends AppCompatActivity {

    // Variables de UI (interfaz de usuario)
    private Spinner spinnerMateria, spinnerDia; // Spinners para seleccionar materia y día
    private TimePicker timePickerClase; // TimePicker para seleccionar hora de la clase
    private EditText editTextSalon, editTextInstructor; // EditText para ingresar salón e instructor
    private Button btnAddClase; // Botón para agregar clase al horario
    private TableLayout tableLayoutHorario; // TableLayout donde se mostrarán las clases

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario); // Establece el layout de la actividad

        // Inicializar elementos de la interfaz de usuario
        spinnerMateria = findViewById(R.id.MateriaHorario); // Asocia el Spinner de materia
        spinnerDia = findViewById(R.id.Dia); // Asocia el Spinner de día
        timePickerClase = findViewById(R.id.Clase); // Asocia el TimePicker de hora
        editTextSalon = findViewById(R.id.editTextSalon); // Asocia el EditText de salón
        editTextInstructor = findViewById(R.id.editTextInstructor); // Asocia el EditText de instructor
        btnAddClase = findViewById(R.id.btn_add_clase); // Asocia el botón para agregar clase
        tableLayoutHorario = findViewById(R.id.tableLayoutHorario); // Asocia el TableLayout para mostrar clases

        // Configurar el TimePicker para usar formato de 24 horas
        timePickerClase.setIs24HourView(true);

        // Configurar el botón para añadir clases
        btnAddClase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarClase(); // Llama al método para agregar una clase
            }
        });
    }

    // Método para agregar una clase al horario
    private void agregarClase() {
        // Obtener valores de la UI
        String materia = spinnerMateria.getSelectedItem().toString(); // Obtener materia seleccionada
        String dia = spinnerDia.getSelectedItem().toString(); // Obtener día seleccionado
        int hora = timePickerClase.getHour(); // Obtener hora seleccionada
        int minuto = timePickerClase.getMinute(); // Obtener minuto seleccionado
        String salon = editTextSalon.getText().toString().trim(); // Obtener salón ingresado
        String instructor = editTextInstructor.getText().toString().trim(); // Obtener instructor ingresado

        // Validar que los campos no estén vacíos
        if (TextUtils.isEmpty(materia) || TextUtils.isEmpty(salon) || TextUtils.isEmpty(instructor)) {
            // Si algún campo está vacío, mostrar un mensaje de error
            Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
            return; // Detener la ejecución si falta información
        }

        // Formatear la hora en un formato HH:mm
        String horaFormateada = String.format("%02d:%02d", hora, minuto);

        // Crear el contenido de la clase en formato texto
        String contenidoClase = String.format("%s\nSalón: %s\nInstructor: %s", materia, salon, instructor);

        // Calcular la fila y columna correspondientes según la hora y el día
        int fila = calcularFilaPorHora(hora);
        int columna = calcularColumnaPorDia(dia);

        // Si no se encuentran filas o columnas válidas, mostrar un mensaje de error
        if (fila == -1 || columna == -1) {
            Toast.makeText(this, "Hora o día no válidos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Actualizar la celda correspondiente en el TableLayout
        TableRow tableRow = (TableRow) tableLayoutHorario.getChildAt(fila);
        if (tableRow != null) {
            // Obtener la celda correspondiente
            TextView celda = (TextView) tableRow.getChildAt(columna);
            if (celda != null) {
                // Establecer el contenido de la celda
                celda.setText(contenidoClase);
                celda.setGravity(Gravity.CENTER); // Centrar el texto
                celda.setBackgroundResource(android.R.color.holo_blue_light); // Establecer color de fondo
            } else {
                // Si no se encuentra la celda, mostrar un mensaje de error
                Toast.makeText(this, "No se encontró la celda", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Si no se encuentra la fila, mostrar un mensaje de error
            Toast.makeText(this, "No se encontró la fila", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para calcular la fila según la hora seleccionada
    private int calcularFilaPorHora(int hora) {
        // Supongamos que la tabla comienza a las 8:00 a.m. y cada fila representa una hora
        if (hora < 8 || hora > 18) { // Limitar el horario entre 8:00 a.m. y 6:00 p.m.
            return -1; // Si la hora está fuera de rango, devolver -1
        }
        return hora - 8 + 1; // Fila 1 para 8:00 a.m., fila 2 para 9:00 a.m., etc.
    }

    // Método para calcular la columna según el día seleccionado
    private int calcularColumnaPorDia(String dia) {
        // Determina la columna en función del día seleccionado
        switch (dia) {
            case "Lunes":
                return 1;
            case "Martes":
                return 2;
            case "Miércoles":
                return 3;
            case "Jueves":
                return 4;
            case "Viernes":
                return 5;
            case "Sábado":
                return 6;
            default:
                return -1; // Si el día no es válido, devolver -1
        }
    }
}
