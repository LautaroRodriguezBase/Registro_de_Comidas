package com.example.registrodecomidas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Proxy;

public class CambiarComida extends AppCompatActivity {

    private TextView etAyuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_comida);

        etAyuda = findViewById(R.id.tvAyuda);

        SpannableString Instrucciones = Ayuda();

        etAyuda.setText(Instrucciones);
    }

    private SpannableString Ayuda() {

        String ayuda = "Nueva Comida\n\n"+
                "  •Para crear o modificar una comida nueva debe tocar el botón de 'Nueva Comida'." +
                " Una vez ahí verá el día, mes y año actual. Si desea ingresar una comida en una" +
                " fecha previa o posterior puede seleccionar la fecha que desee cambiar tocando sobre la misma." +
                "\n  •Luego deberá escribir al menos una de las comidas para que pueda ser guardada," +
                " y, cuando desee buscarla, aparecerá lo escrito en las ventanas de las comidas." +
                "\n  •ADVERTENCIA: Al modificar una comida debera reescribir todas las comidas ya que" +
                " estas estarán vacías, por lo que se recomienda escribir todas las comidas de una vez.\n"+
                "\nBuscar Comida\n\n" +
                "  •Para buscar una comida debe de tocar el botón de 'Buscar Comida'. Una vez seleccionado" +
                " deberá ingresar la fecha (al igual que en Nueva Comida). Con esta fecha se buscará" +
                " la comida de ese día para mostrarsela en la ventana de abajo." +
                "\n  •En caso de no encontrarla no verá cambios en la ventana." +
                " Esto puede pasar si usted:\n" +
                "   ►Ingresó mal la fecha al buscar la comida.\n" +
                "   ►Olvidó crear una nueva comida antes de buscarla.";

        String CadenaBuscada = "Buscar Comida";

        int inicioDeCB = BuscarCadena(CadenaBuscada, ayuda);
        int finDeCB = inicioDeCB + CadenaBuscada.length();

        SpannableString ss = new SpannableString(ayuda);

        StyleSpan titulo2 = new StyleSpan(Typeface.BOLD);
        UnderlineSpan subrallado2 = new UnderlineSpan();
        ss.setSpan(titulo2, inicioDeCB, finDeCB, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(subrallado2, inicioDeCB, finDeCB, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CadenaBuscada = "Nueva Comida";
        inicioDeCB = BuscarCadena(CadenaBuscada, ayuda);
        finDeCB = inicioDeCB + CadenaBuscada.length();

        StyleSpan titulo = new StyleSpan(Typeface.BOLD);
        UnderlineSpan subrallado = new UnderlineSpan();
        ss.setSpan(titulo, inicioDeCB, finDeCB, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(subrallado, inicioDeCB, finDeCB, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CadenaBuscada = "ADVERTENCIA";
        inicioDeCB = BuscarCadena(CadenaBuscada, ayuda);
        finDeCB = inicioDeCB + CadenaBuscada.length();

        StyleSpan advertencia = new StyleSpan(Typeface.BOLD);
        ss.setSpan(advertencia, inicioDeCB, finDeCB, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ss;
    }

    private int BuscarCadena(String cadenaBuscada, String txt) {

        int largoCB = cadenaBuscada.length();
        int totalAyuda = txt.length() - largoCB;

        int inicioDeCB = 0;

        for (int i = 0; i < totalAyuda+1; i++){

            String Cadena = txt.substring(i, i+largoCB);

            if (cadenaBuscada.equals(Cadena)){

                inicioDeCB = i;
                return inicioDeCB;
            }
        }

        return 0;
    }
}