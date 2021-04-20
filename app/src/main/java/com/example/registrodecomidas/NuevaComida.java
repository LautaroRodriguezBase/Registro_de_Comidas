package com.example.registrodecomidas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.pm.FeatureGroupInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;

public class NuevaComida extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText etDesayuno, etAlmuerzo, etMerienda, etCena;
    private Spinner spnD, spnM, spnA;

    private int faDia, faMes, faAno;

    final Calendar c = Calendar.getInstance();

    ArrayList<String> elementosSpinnerD = new ArrayList<>();

    String Fecha = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_comida);

        etDesayuno = findViewById(R.id.etDesayuno);
        etAlmuerzo = findViewById(R.id.etAlmuerzo);
        etMerienda = findViewById(R.id.etMerienda);
        etCena = findViewById(R.id.etCena);
        spnD = this.findViewById(R.id.etFechad);
        spnM = this.findViewById(R.id.etFecham);
        spnA = this.findViewById(R.id.etFechaa);

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

        faAno = c.get(Calendar.YEAR);
        faMes = c.get(Calendar.MONTH);

        faAno = faAno - 2021;

        spnA.setSelection(faAno);
        spnM.setSelection(faMes);
    }

    public void Guardar(View view){

        if(etDesayuno.getText().toString().trim().equalsIgnoreCase("") && etAlmuerzo.getText().toString().trim().equalsIgnoreCase("") && etMerienda.getText().toString().trim().equalsIgnoreCase("") && etCena.getText().toString().trim().equalsIgnoreCase("")){

            Toast.makeText(this, "Debe escribir al menos una comida", Toast.LENGTH_LONG).show();

        }else{

            String Comidas = UnirComidas();

            try{

                OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(Fecha, Activity.MODE_PRIVATE));
                archivo.write(Comidas);
                archivo.flush();
                archivo.close();

            }catch (IOException ignored){

            }

            Toast.makeText(this, "Se han guardado los datos", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private String UnirComidas() {

        String Desayuno = etDesayuno.getText().toString(), Almuerzo = etAlmuerzo.getText().toString();
        String Merienda = etMerienda.getText().toString(), Cena = etCena.getText().toString();

        return "•Su desayuno fue: " + Desayuno + ".\n •Luego almorzó: " + Almuerzo + ".\n •En la tarde merendo: " + Merienda + ".\n •Por último ceno: " + Cena + ".";
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

        faDia = c.get(Calendar.DAY_OF_MONTH);
        faDia = faDia - 1;

        spnD.setSelection(faDia);
    }
}