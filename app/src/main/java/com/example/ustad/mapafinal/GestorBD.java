package com.example.ustad.mapafinal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class GestorBD extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UstaIker.db";
    public static final String RUTAS_TABLE_NAME = "rutas";
    public static final String RUTAS_COLUMN_ID = "id"; // identificativo de la ruta (contador)
    public static final String RUTAS_COLUMN_FECHA = "fecini"; // fecha-hora de creación de la ruta
    public static final String RUTAS_COLUMN_FECFIN = "fecfin"; // fecha-hora del último marked

    public static final String POSICIONES_TABLE_NAME2 = "posiciones";
    public static final String POSICIONES_COLUMN_ID = "id"; //identificativo de la posición AUTOINCREMENT
    public static final String POSICIONES_COLUMN_ID_RUTA = "id_ruta"; // identificativo de la ruta
    public static final String POSICIONES_COLUMN_LAT = "lat"; // latitud
    public static final String POSICIONES_COLUMN_LON = "lon"; // longitud
    public static final String POSICIONES_COLUMN_FECHA = "fecha"; // fecha-hora



    private HashMap hp;

    public GestorBD(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table rutas " +
                        "(id integer primary key, fecini datetime default current_timestamp, fecfin datetime)"
        );
        db.execSQL(
                "create table posiciones " +
                        "(id integer primary key autoincrement, id_ruta integer, lat double, lon double, fecha datetime default current_timestamp)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS posiciones");
        db.execSQL("DROP TABLE IF EXISTS rutas");
        onCreate(db);
    }

    public int creaRuta  ()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Integer id = obtenerId();
        contentValues.put("id", id);
        db.insert("rutas", null, contentValues);
        return id;
    }

    public boolean creaPosicion  (int id, double lat, double lon)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_ruta", id);
        contentValues.put("lat", lat);
        contentValues.put("lon", lon);
        db.insert("posiciones", null, contentValues);
        return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from rutas order by id", null );
        return res;
    }
    public Cursor getData2(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from posiciones where id_ruta = "+id+" order by id", null );
        return res;
    }

    public int obtenerId(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, RUTAS_TABLE_NAME);

            return numRows + 1;

    }

    public boolean finRuta (Integer id)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fecfin", dateFormat.format(date));
        db.update("rutas", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public int borraRuta (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int res;
        res = db.delete("posiciones",
                "id_ruta = ? ",
                new String[] { Integer.toString(id) });
        res = db.delete("rutas",
                "id = ? ",
                new String[] { Integer.toString(id) });
        return res; // nº de registros borrados
    }

    public ArrayList<String> verRutas()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select LPAD(ID,5)||'   '||to_char(fecini,'dd/mm/yyyy hh24:mi:ss') RESUL from rutas", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(RUTAS_COLUMN_ID)));
            res.moveToNext();
        }
        return array_list;
    }
}