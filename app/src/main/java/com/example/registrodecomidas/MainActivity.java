package com.example.registrodecomidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void NuevaComida(View view){

        Intent i = new Intent(this, NuevaComida.class);
        startActivity(i);
    }

    public void BuscarComida(View view){

        Intent i = new Intent(this, BuscarComida.class);
        startActivity(i);
    }

    public void CambiarComida(View view){

        Intent i = new Intent(this, CambiarComida.class);
        startActivity(i);
    }
}