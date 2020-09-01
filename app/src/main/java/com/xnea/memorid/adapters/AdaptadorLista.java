package com.xnea.memorid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xnea.memorid.R;
import com.xnea.memorid.model.Card;

import java.util.ArrayList;

public class AdaptadorLista extends ArrayAdapter<Card> {

    private ArrayList<Card> tarjetas;
    private Context context;

    public AdaptadorLista(@NonNull Context context, ArrayList<Card> tarjetas) {
        super(context, R.layout.item_lista,tarjetas);
        this.context = context;
        this.tarjetas = tarjetas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService((context.LAYOUT_INFLATER_SERVICE));

        if(convertView == null) convertView = inflater.inflate(R.layout.item_lista, parent, false);

        TextView tituloTarjeta = convertView.findViewById(R.id.txtTituloTarjeta);
        TextView porcentajeTarjeta = convertView.findViewById(R.id.txtPorcentajeTarjeta);
        TextView descripcionTarjeta = convertView.findViewById(R.id.txtDescripcionTarjeta);

        int total = tarjetas.get(position).getAciertos()+tarjetas.get(position).getFallos();
        double porcentaje = 100;
        if(total != 0) porcentaje = (double)(tarjetas.get(position).getAciertos()*100/total);

        tituloTarjeta.setText(tarjetas.get(position).getWord());
        porcentajeTarjeta.setText(porcentaje + "%");
        descripcionTarjeta.setText(tarjetas.get(position).getDescription());

        return convertView;
    }
}
