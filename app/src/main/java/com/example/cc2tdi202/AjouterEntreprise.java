package com.example.cc2tdi202;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AjouterEntreprise extends AppCompatActivity {

    EditText e1,e2,e3;
    MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ajouter_entreprise );
        e1=findViewById( R.id.Rsocial );
        e2=findViewById( R.id.adresse );
        e3=findViewById( R.id.capitale );
        db=new MyDatabase(this );
    }

    public void annuler(View view) {
        Intent i = null;

        i=new Intent(AjouterEntreprise.this, MainActivity.class);
        startActivity(i);

    }

    public void enregistrer(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(AjouterEntreprise.this);
        alert.setTitle("Enregistre entreprise");
        alert.setMessage("Voulez-vous vraiment Enregistrer cette entreprise ?");
        alert.setPositiveButton("Enregistrer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Entreprise e = new Entreprise();

                e.setRaisonSociale(e1.getText().toString());
                e.setAdresse(e2.getText().toString());
                e.setCapitale(Double.parseDouble(e3.getText().toString()));

                if(MyDatabase.AddEntreprise(db.getWritableDatabase(),e)==-1)
                    Toast.makeText(AjouterEntreprise.this, "Enregistrement echoue", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AjouterEntreprise.this, "Enregistrement reussie", Toast.LENGTH_SHORT).show();

            }
        });
        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(AjouterEntreprise.this, "Operation annulee", Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();


    }
}