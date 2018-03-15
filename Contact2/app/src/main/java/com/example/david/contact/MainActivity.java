package com.example.david.contact;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListView maListe;
    private Button buttonAjout;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        maListe = (ListView) findViewById(R.id.listView);
        buttonAjout = (Button)findViewById(R.id.boutonAjout);
        buttonSave = (Button) findViewById(R.id.boutonSave);

        final ArrayList<HashMap<String, String>> listContact = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> contact;

        contact = new HashMap<String, String>();
        contact.put("nom", "Nguyen");
        contact.put("prenom", "David");
        contact.put("img", String.valueOf(R.drawable.img));
        listContact.add(contact);


        contact = new HashMap<String, String>();
        contact.put("nom", "Test");
        contact.put("prenom", "Test");
        contact.put("img", String.valueOf(R.drawable.img));
        listContact.add(contact);


        contact = new HashMap<String, String>();
        contact.put("nom", "Test2");
        contact.put("prenom", "Test2");
        contact.put("img", String.valueOf(R.drawable.img));
        listContact.add(contact);

        Intent i = getIntent();

        if(i.getStringExtra(Formulaire.NOM )!= null){
            contact = new HashMap<String, String>();
            contact.put("nom", i.getStringExtra(Formulaire.NOM));
            contact.put("prenom",  i.getStringExtra(Formulaire.PRENOM));
            contact.put("img", String.valueOf(R.drawable.img));
            listContact.add(contact);
        }

        final SimpleAdapter adapter = new SimpleAdapter(this.getBaseContext(), listContact, R.layout.contact,
                new String[]{"img", "nom", "prenom"}, new int[]{R.id.img, R.id.nom, R.id.prenom});

        maListe.setAdapter(adapter);

        maListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) maListe.getItemAtPosition(position);
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Contact");
                adb.setMessage("Nom : " + map.get("nom"));
                adb.setPositiveButton("Ok", null);
                adb.show();
            }
        });

        maListe.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, String> map = (HashMap<String, String>) maListe.getItemAtPosition(i);
                AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Supprimer ?");
                adb.setMessage("Voulez-vous vraiment supprimer : " + maListe.getItemAtPosition(i));
                final int positionToRemove = i;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        listContact.remove(positionToRemove);
                        adapter.notifyDataSetChanged();
                    }});
                adb.show();
                return true;
            }
        });

        buttonAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent formulaire = new Intent(MainActivity.this, Formulaire.class);
                startActivity(formulaire);
            }
        });


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = "testfile.txt";

                File file = new File(filename);

                FileOutputStream fos = null;
                ObjectOutputStream out = null;
                try {
                    fos = new FileOutputStream(file);
                    out = new ObjectOutputStream(fos);
                    out.writeObject(maListe);

                    out.close();
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Sauvegarde");
                    adb.setMessage("Votre fichier a été sauvegarder");
                    adb.setPositiveButton("Ok", null);
                    adb.show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Sauvegarde");
                    adb.setMessage("ECHEC");
                    adb.setPositiveButton("Ok", null);
                    adb.show();
                }
            }
        });


    }


}
