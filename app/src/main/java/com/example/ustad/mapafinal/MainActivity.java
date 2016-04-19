package com.example.ustad.mapafinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
 private GestorBD bd;
    private int idruta;
    private int startbutton;
    private int NUMERO1;
    private  Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setBd(new GestorBD(this));
        intent=new Intent(MainActivity.this, MapsActivity.class);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 NUMERO1 = bd.creaRuta();
                setStartbutton(0);

                    intent.putExtra("numeroid", NUMERO1);
                    intent.putExtra("startbutton", startbutton);
                startActivity(intent);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.finRuta(idruta);
                startbutton=1;

                intent.putExtra("numeroid", NUMERO1);
                intent.putExtra("startbutton", startbutton);
                startActivity(intent);
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public GestorBD getBd() {
        return bd;
    }

    public void setBd(GestorBD bd) {
        this.bd = bd;
    }

    public int getIdruta() {
        return idruta;
    }

    public void setIdruta(int idruta) {
        this.idruta = idruta;
    }

    public int getStartbutton() {
        return startbutton;
    }

    public void setStartbutton(int startbutton) {
        this.startbutton = startbutton;
    }
}
