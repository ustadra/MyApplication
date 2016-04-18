package com.example.ustad.mapafinal;

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
    GestorBD db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lista = (ListView) findViewById(R.id.listView);
        showList();

    }
    private void showList(){
        // conectionDB conector de la base de datos
        db=new GestorBD(this);
        //metodo de conector de la base de datos donde muestra todos los registros
        Cursor c=db.getData();
        item=new ArrayList<String>();
        String tittle= "",content="";
        if(c.moveToFirst()){
            //recorremos la BD donde tittle es un campo de ella y los numeros de dentro de getString() son la columna de la BD que le inseramos a la String
            do {
                tittle=c.getString(0);
                content=c.getString(1);
                item.add(tittle+" "+content);
            }while(c.moveToNext());
        }
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,item);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
    }
}
