package com.xnea.memorid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.xnea.memorid.dao.Sentencias;
import com.xnea.memorid.fragments.Lista;
import com.xnea.memorid.fragments.Practice;

public class MessageDialog extends DialogFragment {

    private String palabra;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Bundle args = getArguments();
        palabra = args.getString("palabra");

        Log.e("PALABRA: ", palabra);

        builder.setMessage("Eliminar tarjeta")
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Sentencias sentencias = new Sentencias(getContext());
                        sentencias.deleteCard(palabra);
                        Lista.updateList(getContext());
                        Practice.generarTarjetaRandom();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();

    }
}
