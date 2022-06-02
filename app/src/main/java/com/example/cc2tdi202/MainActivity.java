package com.example.cc2tdi202;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

    }

    public void click(View view) {
        Intent i = null;
        switch (view.getId()){
            case R.id.ajoutE: i=new Intent(MainActivity.this, AjouterEntreprise.class); break;
            case R.id.EditE: i=new Intent(MainActivity.this, EditerEntreprise.class); break;
            case R.id.ListeE: i=new Intent(MainActivity.this, ListerTousEntreprises.class); break;
        }
        startActivity(i);
    }
}