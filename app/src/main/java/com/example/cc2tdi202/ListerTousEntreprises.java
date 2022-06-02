package com.example.cc2tdi202;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListerTousEntreprises extends AppCompatActivity {
    MyDatabase db;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lister_tous_entreprises );
        list=findViewById( R.id.lst );

        db = new MyDatabase(this);


        ArrayList<Entreprise> entr = MyDatabase.getAllEntreprise(db.getReadableDatabase());

        ArrayList<String> l = new ArrayList<>();
        for(Entreprise en: entr){
            l.add(en.getRaisonSociale());
            l.add( String.valueOf( en.getCapitale() ) );}


        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_list_item_1,l);

        list.setAdapter(ad);
    }
}