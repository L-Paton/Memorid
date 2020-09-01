package com.xnea.memorid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xnea.memorid.dao.Sentencias;

public class Add extends AppCompatActivity {


    private EditText etWord;
    private EditText etDescription;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etWord = findViewById(R.id.etWord);
        etDescription = findViewById(R.id.etDescription);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String word = etWord.getText().toString();
                    String description = etDescription.getText().toString();
                    if(word.length() > 0 && description.length() > 0){
                        Sentencias sentencias = new Sentencias(getApplicationContext());
                        sentencias.addCard(word, description);
                        finish();
                    }
                }catch(Exception e){
                    Toast toast = Toast.makeText(getApplicationContext(), "ERROR", Toast. LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}