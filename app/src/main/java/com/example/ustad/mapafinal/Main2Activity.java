package com.example.ustad.mapafinal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    ListView lista;
    List<String> item = null;
    List<Number> item2 = null;
    GestorBD db;
    private  Intent intent;
    private  Intent intent2;
    private  int NUMERO1;
    private  int startbutton;
    private int distancia;
    private int tiempo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent=new Intent(Main2Activity.this, MapsActivity.class);
        intent2=new Intent(Main2Activity.this, MostrarDatos.class);
        lista = (ListView) findViewById(R.id.listView);
        showList();

    }
    private void showList(){
        // conectionDB conector de la base de datos
        db=new GestorBD(this);
        //metodo de conector de la base de datos donde muestra todos los registros
        Cursor c=db.getData();
        item=new ArrayList<String>();
        item2=new ArrayList<Number>();
        String tittle= "",content="";
        int id;
        if(c.moveToFirst()){
            //recorremos la BD donde tittle es un campo de ella y los numeros de dentro de getString() son la columna de la BD que le inseramos a la String
            do {
                tittle=c.getString(0);
                id=c.getInt(0);
                content=c.getString(1);
                item.add(tittle+" "+content);
                item2.add(id);
            }while(c.moveToNext());
        }
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,item);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NUMERO1=item2.get(position).intValue();
                intent.putExtra("numeroid", NUMERO1);
                startbutton=1;
                intent.putExtra("startbutton", startbutton);
                startActivity(intent);
            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                NUMERO1=item2.get(position).intValue();
                intent2.putExtra("numeroid", NUMERO1);
                startActivity(intent2);
                return false;
            }
        });

    }
}
