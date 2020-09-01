package com.xnea.memorid.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xnea.memorid.MessageDialog;
import com.xnea.memorid.adapters.AdaptadorLista;
import com.xnea.memorid.R;
import com.xnea.memorid.dao.Sentencias;
import com.xnea.memorid.model.Card;

import java.util.ArrayList;

public class Lista extends Fragment{

    private static ArrayList<Card> listCards;
    private static AdaptadorLista adapter;
    private static ListView listView;

    public Lista() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lista, container, false);

        listView = root.findViewById(R.id.listaTarjetas);
        updateList(getContext());

        return root;
    }

    public static void updateList(final Context context){
        Sentencias sentencias = new Sentencias(context);
        listCards = (ArrayList<Card>) sentencias.getCards();
        adapter = new AdaptadorLista(context, listCards);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Card card = adapter.getItem(i);

                Bundle args = new Bundle();
                args.putString("palabra", card.getWord());
                MessageDialog md = new MessageDialog();
                md.setArguments(args);
                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                md.show(manager, "TAG");
            }
        });
    }
}