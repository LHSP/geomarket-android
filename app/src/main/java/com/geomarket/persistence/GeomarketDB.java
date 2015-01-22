package com.geomarket.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by carambola-acer on 04/09/2014.
 */
public class GeomarketDB extends SQLiteOpenHelper {
    private static GeomarketDB mInstance = null;

    public static GeomarketDB getInstance(Context context) {
        if(mInstance == null){
            mInstance = new GeomarketDB(context.getApplicationContext());
        }
        return mInstance;
    }

    private GeomarketDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String DATABASE_NAME = "Geomarket.db";
    public static final int DATABASE_VERSION = 1;

    // PROPAGANDAS
    public static final String TABLE_PROPAGANDAS = "Propagandas";
    public static final String PROPAGANDAS_COLUMN_ID = "ID";
    public static final String PROPAGANDAS_COLUMN_TITULO = "TITULO";
    public static final String PROPAGANDAS_COLUMN_CORPO = "CORPO";
    public static final String PROPAGANDAS_COLUMN_LINK = "LINK";
    public static final String PROPAGANDAS_COLUMN_TIPO_ID = "TIPO_ID";
    public static final String PROPAGANDAS_COLUMN_ESTAB_ID = "ESTAB_ID";
    public static final String PROPAGANDAS_COLUMN_DATA_INICIO = "DATA_INICIO";
    public static final String PROPAGANDAS_COLUMN_DATA_FIM = "DATA_FIM";

    // Database creation sql statement
    private static  final String CREATE_PROPAGANDAS = "create table "
            + TABLE_PROPAGANDAS + "("
            + PROPAGANDAS_COLUMN_ID + " integer primary key, "
            + PROPAGANDAS_COLUMN_TITULO + " text, "
            + PROPAGANDAS_COLUMN_CORPO + " text, "
            + PROPAGANDAS_COLUMN_LINK + " text, "
            + PROPAGANDAS_COLUMN_TIPO_ID + " integer, "
            + PROPAGANDAS_COLUMN_ESTAB_ID + " integer, "
            + PROPAGANDAS_COLUMN_DATA_INICIO + " bigint not null, "
            + PROPAGANDAS_COLUMN_DATA_FIM + " bigint not null "
            + ");";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROPAGANDAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(GeomarketDB.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROPAGANDAS);
        onCreate(db);
    }
}
