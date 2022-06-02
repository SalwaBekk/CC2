package com.example.cc2tdi202;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditerEntreprise extends AppCompatActivity {

    Spinner sp;
    EditText e1,e2,e3;
    MyDatabase db;
    ArrayList<Entreprise> entr;
    ArrayAdapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_editer_entreprise );
        e1=findViewById( R.id.Rsocial );
        e2=findViewById( R.id.adresse );
        e3=findViewById( R.id.capitale );
        db=new MyDatabase(this );
        sp=findViewById( R.id.spn );
        entr = MyDatabase.getAllEntreprise(db.getReadableDatabase());

        ArrayList<String> IDEntreprise = new ArrayList<>();
        for(Entreprise pp: entr)
            IDEntreprise.add(String.valueOf(pp.getId()));

        ad = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,IDEntreprise);
        sp.setAdapter(ad);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Entreprise e = entr.get( i );

                e.setRaisonSociale(e1.getText().toString());
                e.setAdresse(e2.getText().toString());
                e.setCapitale(Double.parseDouble(e3.getText().toString()));
                e1.setText(e.getRaisonSociale());
                e2.setText(e.getAdresse());
                e3.setText(String.format("%f",e.getCapitale()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void modifier(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(EditerEntreprise.this);
        alert.setTitle("Modifier Entreprise");
        alert.setMessage("Voulez-vous modifier cette entreprise ?");

        alert.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Entreprise e = entr.get(sp.getSelectedItemPosition());


                e.setRaisonSociale(e1.getText().toString());
                e.setAdresse(e2.getText().toString());
                e.setCapitale(Double.parseDouble(e3.getText().toString()));

                if(MyDatabase.UpdateEntreprise(db.getWritableDatabase(),e)==-1)
                    Toast.makeText(EditerEntreprise.this, "Modification echoue", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(EditerEntreprise.this, "Modification reussie", Toast.LENGTH_SHORT).show();

            }
        });
        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(EditerEntreprise.this, "Operation annulee", Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();
    }

    public void Supp(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(EditerEntreprise.this);
        alert.setTitle("Suppression Entreprise");
        alert.setMessage("Voulez-vous supprimer cette Entreprise ?");
        alert.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Entreprise e = entr.get(sp.getSelectedItemPosition());

                if(MyDatabase.DeleteEntreprise(db.getWritableDatabase(),e.getId())==-1)
                    Toast.makeText(EditerEntreprise.this, "suppression echoue", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(EditerEntreprise.this, "Suppression reussie", Toast.LENGTH_SHORT).show();
                    ad.remove(e.getId());
                }
            }
        });

        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(EditerEntreprise.this, "Operation annulee", Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();

    }
}