package com.xnea.memorid.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xnea.memorid.R;
import com.xnea.memorid.dao.Sentencias;
import com.xnea.memorid.model.Card;

public class Practice extends Fragment {
    private EditText etRespuesta;
    private Button btnCheck;
    private static TextView etDescription;
    private static Sentencias sentencias;
    private static Card card;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_practice, container, false);

        etDescription = root.findViewById(R.id.txtDescriptionPractice);
        etRespuesta = root.findViewById(R.id.etRespuesta);
        btnCheck = root.findViewById(R.id.btnCheck);

        sentencias = new Sentencias(getContext());

        generarTarjetaRandom();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String respuesta = etRespuesta.getText().toString();
                if(respuesta.equalsIgnoreCase(card.getWord())){
                    card.setAciertos(card.getAciertos()+1);
                    mostrarToast("Correct!");
                }else{
                    card.setFallos(card.getFallos()+1);
                    mostrarToast("Wrong! The answer is: \n\n" + card.getWord());
                }
                sentencias.updatePoints(card);
                etRespuesta.setText("");
                Lista.updateList(getContext());
                generarTarjetaRandom();
            }
        });

        return root;
    }

    public static void generarTarjetaRandom(){
        card = sentencias.getRamdonCard();

        try{
            etDescription.setText(card.getDescription());
        }catch(Exception e){
            etDescription.setText("No hay tarjetas");
        }
    }

    private void mostrarToast(String mensaje){
        Toast toast = Toast.makeText(getContext(), mensaje, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
}