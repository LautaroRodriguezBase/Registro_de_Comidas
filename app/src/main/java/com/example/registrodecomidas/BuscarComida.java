package com.example.registrodecomidas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

public class BuscarComida extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spnD, spnM, spnA;
    private TextView tvComidas;
    ArrayList<String> elementosSpinnerD = new ArrayList<>();
    String  Fecha = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_comida);

        tvComidas = findViewById(R.id.tvComidas);

        spnD = this.findViewById(R.id.etBFechad);
        spnM = this.findViewById(R.id.etBFecham);
        spnA = this.findViewById(R.id.etBFechaa);

        spnM.setOnItemSelectedListener(this);

        ArrayList<String> elementosSpinnerA = new ArrayList<>();
        for(int i = 2021; i < 2100; i++) {
            elementosSpinnerA.add(String.valueOf(i));
        }
        ArrayAdapter<String> AdaptadorA = new ArrayAdapter<>(this, R.layout.contenedor_spinner, elementosSpinnerA);
        spnA.setAdapter(AdaptadorA);


        ArrayList<String> elementosSpinnerM = new ArrayList<>();
        for(int i = 1; i < 13; i++) {
            elementosSpinnerM.add(String.valueOf(i));
        }
        ArrayAdapter<String> AdaptadorM = new ArrayAdapter<>(this, R.layout.contenedor_spinner, elementosSpinnerM);
        spnM.setAdapter(AdaptadorM);
    }

    public void Buscar(View view){

            String archivos[] = fileList();

            if(ArchivoExiste(archivos, Fecha)){
                try {

                    InputStreamReader archivo = new InputStreamReader(openFileInput(Fecha));
                    BufferedReader br = new BufferedReader(archivo);
                    String linea = br.readLine(), bitacoraCompleta = "";

                    while (linea != null){

                        bitacoraCompleta = bitacoraCompleta + linea + "\n";
                        linea = br.readLine();
                    }

                    br.close();
                    archivo.close();
                    tvComidas.setText(bitacoraCompleta);

                }catch (IOException ignored){


                }
        }
    }

    private boolean ArchivoExiste(String[] archivos, String nombreArchivo){

        for (int i = 0; i < archivos.length; i++)
            if(nombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int Mes = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
        CortarDias(Mes);

        Fecha = spnD.getItemAtPosition(i).toString() + spnM.getItemAtPosition(i).toString() + spnA.getItemAtPosition(i).toString() + ".txt";
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void CortarDias(int M){

        elementosSpinnerD.clear();

        if(M == 1 || M == 3 || M == 5 || M == 7 || M == 8 || M == 10 || M == 12){

            for(int i = 1; i < 32; i++) {
                elementosSpinnerD.add(String.valueOf(i));
            }
            ArrayAdapter<String> AdaptadorD = new ArrayAdapter<>(this, R.layout.contenedor_spinner, elementosSpinnerD);
            spnD.setAdapter(AdaptadorD);

        }else if (M == 2){

            for(int i = 1; i < 30; i++) {
                elementosSpinnerD.add(String.valueOf(i));
            }
            ArrayAdapter<String> AdaptadorD = new ArrayAdapter<>(this, R.layout.contenedor_spinner, elementosSpinnerD);
            spnD.setAdapter(AdaptadorD);
        }else{

            for(int i = 1; i < 31; i++) {
                elementosSpinnerD.add(String.valueOf(i));
            }
            ArrayAdapter<String> AdaptadorD = new ArrayAdapter<>(this, R.layout.contenedor_spinner, elementosSpinnerD);
            spnD.setAdapter(AdaptadorD);
        }
    }
}