package com.example.tasknest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalculoRapido extends AppCompatActivity {

    // Variables de UI
    private EditText editTextPeriodo1, editTextPeriodo2, editTextPeriodo3;
    private Button btnAsk;
    private TextView txtPromedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_rapido);

        // Inicializar elementos de la interfaz
        editTextPeriodo1 = findViewById(R.id.editTextPeriodo1);
        editTextPeriodo2 = findViewById(R.id.editTextPeriodo2);
        editTextPeriodo3 = findViewById(R.id.editTextPeriodo3);
        btnAsk = findViewById(R.id.btn_ask);
        txtPromedio = findViewById(R.id.txtPromedio);

        // Configurar el botón para calcular el promedio
        btnAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularPromedio(); // Llamar al método para calcular el promedio
            }
        });
    }

    // Método para calcular el promedio
    private void calcularPromedio() {
        String nota1Texto = editTextPeriodo1.getText().toString().trim();
        String nota2Texto = editTextPeriodo2.getText().toString().trim();
        String nota3Texto = editTextPeriodo3.getText().toString().trim();

        // Validar que los campos no estén vacíos
        if (nota1Texto.isEmpty() || nota2Texto.isEmpty() || nota3Texto.isEmpty()) {
            // Si algún campo está vacío, mostrar un mensaje de error
            Toast.makeText(this, "Por favor, ingresa todas las notas", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Convertir los textos ingresados a números decimales
            double nota1 = Double.parseDouble(nota1Texto);
            double nota2 = Double.parseDouble(nota2Texto);
            double nota3 = Double.parseDouble(nota3Texto);

            // Calcular el promedio
            double promedio = (nota1 + nota2 + nota3) / 3;

            // Mostrar el promedio en el TextView
            txtPromedio.setText(String.format("Promedio: %.2f", promedio));

        } catch (NumberFormatException e) {
            // Manejar errores si las notas no se pueden convertir a números
            Toast.makeText(this, "Por favor, ingresa valores numéricos válidos", Toast.LENGTH_SHORT).show();
        }
    }
}
