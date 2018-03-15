package com.example.david.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by david on 15/03/2018.
 */

public class Formulaire extends AppCompatActivity {
    private Button buttonConfirme;
    private Button buttonAnnule;
    private EditText ajoutPrenom;
    private EditText ajoutNom;
    public static String NOM;
    public static String PRENOM;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout);
        buttonConfirme = (Button)findViewById(R.id.boutonConfirme);
        buttonAnnule = (Button)findViewById(R.id.boutonAnnule);
        ajoutNom = (EditText) findViewById(R.id.ajoutNom);
        ajoutPrenom = (EditText) findViewById(R.id.ajoutPrenom);

        buttonConfirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Retour = new Intent(Formulaire.this, MainActivity.class);
                Retour.putExtra(NOM,ajoutNom.getText().toString());
                Retour.putExtra(PRENOM,ajoutPrenom.getText().toString());

                startActivity(Retour);
            }
        });

        buttonAnnule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Retour = new Intent(Formulaire.this, MainActivity.class);
                startActivity(Retour);
            }
        });

    }


}
