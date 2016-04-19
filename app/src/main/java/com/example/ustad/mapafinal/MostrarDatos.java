package com.example.ustad.mapafinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MostrarDatos extends AppCompatActivity {
    private int id;
    private int tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final GestorBD db;
        db = new GestorBD(this);

        final Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id = extras.getInt("numeroid");

        TextView textView4 = (TextView) findViewById(R.id.textView4);
        TextView textView5 = (TextView) findViewById(R.id.textView5);
        TextView textView6 = (TextView) findViewById(R.id.textView6);


        textView4.setText("");
        textView5.setText("");
        textView6.setText("");
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.borraRuta(id);


            }
        });

    }

}
