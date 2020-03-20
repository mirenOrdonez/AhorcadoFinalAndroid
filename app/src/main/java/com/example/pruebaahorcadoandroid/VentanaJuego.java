package com.example.pruebaahorcadoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class VentanaJuego extends AppCompatActivity {

    String palabraOculta = eligePalabra();
    int numeroDeFallos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_juego);
    }

    @Override
    protected void onStart() {
        super.onStart();
        palabraOculta = eligePalabra();
        String guion = "";
        for (int i = 0; i < palabraOculta.length(); i++) {
            guion = guion + "_ ";
        }
        ((TextView) findViewById(R.id.palabraConGuiones)).setText(guion);

    }

    public String eligePalabra() {
        String [] listaPalabras = {"CETYS", "SLAPCHOP", "SUPERFUERTE", "OSEA", "CANTIMPALO", "ALMORRANA", "KAREL"};
        Random aleatorio = new Random();
        int posicion = aleatorio.nextInt(listaPalabras.length);
        return listaPalabras[posicion].toUpperCase();
    }

    public void botonPulsado(View vista) {
        Button boton = findViewById(vista.getId());
        boton.setVisibility(View.INVISIBLE);
        chequeaLetra(boton.getText().toString());
        
    }

    private void chequeaLetra(String letra) {
        letra = letra.toUpperCase();
        ImageView imagenAhorcado = ((ImageView) findViewById(R.id.imagenAhorcado));
        TextView textoConGuiones = ((TextView) findViewById(R.id.palabraConGuiones));
        String palabraConGuiones = textoConGuiones.getText().toString();

        boolean acierto = false;

        for (int i = 0; i < palabraOculta.length(); i++) {
            if (palabraOculta.charAt(i) == letra.charAt(0)) {
                palabraConGuiones = palabraConGuiones.substring(0, 2 * i) + letra + palabraConGuiones.substring(2 * i + 1);
                acierto = true;

            }
        }

        if (!palabraConGuiones.contains("_")) {
            imagenAhorcado.setImageResource(R.drawable.acertastetodo);

        }
        textoConGuiones.setText(palabraConGuiones);

        if (!acierto) {
            numeroDeFallos++;
            switch (numeroDeFallos) {
                case 0:
                    imagenAhorcado.setImageResource(R.drawable.ahorcado_0);
                    break;
                case 1:
                    imagenAhorcado.setImageResource(R.drawable.ahorcado_1);
                    break;
                case 2:
                    imagenAhorcado.setImageResource(R.drawable.ahorcado_2);
                    break;
                case 3:
                    imagenAhorcado.setImageResource(R.drawable.ahorcado_3);
                    break;
                case 4:
                    imagenAhorcado.setImageResource(R.drawable.ahorcado_4);
                    break;
                case 5:
                    imagenAhorcado.setImageResource(R.drawable.ahorcado_5);
                    break;
                default:
                    imagenAhorcado.setImageResource(R.drawable.ahorcado_fin);
                    break;
            }
        }
    }
}
