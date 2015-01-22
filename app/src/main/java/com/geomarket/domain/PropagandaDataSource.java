package com.geomarket.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.geomarket.model.Propaganda;
import com.geomarket.persistence.GeomarketDB;

import java.util.Date;
import java.util.List;

/**
 * Created by Luiz on 11/11/2014.
 */
public class PropagandaDataSource {

    // Database fields
    private SQLiteDatabase database;
    private GeomarketDB dbHelper;

    public PropagandaDataSource(Context context) {
        dbHelper = GeomarketDB.getInstance(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<Propaganda> getPropagandas(){
        return null;
    }
/*
    public List<Integer> getProdutosId(int lojaId) {
        List<Integer> produtosId = new ArrayList<Integer>();
        Cursor cursor = database.query(InfopriceDB.TABLE_LOJAS_PRODUTOS, allColumns,
                InfopriceDB.LOJAS_PRODS_COLUMN_LOJA_ID + " = " + lojaId, null, null, null, null);

        if(cursor != null && cursor.getCount() >= 1) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                produtosId.add(cursor.getInt(cursor.getColumnIndex(InfopriceDB.LOJAS_PRODS_COLUMN_PROD_ID)));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return produtosId;
    }

    public List<Integer> getIdsProdutosNaoColetados(int lojaId) {
        List<Integer> produtosId = new ArrayList<Integer>();
        Cursor cursor = database.query(InfopriceDB.TABLE_LOJAS_PRODUTOS, allColumns,
                InfopriceDB.LOJAS_PRODS_COLUMN_LOJA_ID + " = " + lojaId + " AND " +
                        InfopriceDB.LOJAS_PRODS_COLUMN_COLETADO + " = 0", null, null, null, null);

        if(cursor != null && cursor.getCount() >= 1) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                produtosId.add(cursor.getInt(cursor.getColumnIndex(InfopriceDB.LOJAS_PRODS_COLUMN_PROD_ID)));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return produtosId;
    }

    public int coletaProduto(int produtoId, int lojaId) {
        ContentValues values = new ContentValues();
        values.put(InfopriceDB.LOJAS_PRODS_COLUMN_COLETADO, 1);
        int rowsUpdated = database.update(InfopriceDB.TABLE_LOJAS_PRODUTOS,
                values,
                InfopriceDB.LOJAS_PRODS_COLUMN_LOJA_ID + " = " + lojaId
                        + " AND " + InfopriceDB.LOJAS_PRODS_COLUMN_PROD_ID + " = " + produtoId,
                null);

        return rowsUpdated;
    }

    public void deleteAllLojasProdutos() {
        database.delete(InfopriceDB.TABLE_LOJAS_PRODUTOS, null, null);
    }

    public void descoletaProdutosLoja(int lojaId) {
        ContentValues values = new ContentValues();
        values.put(InfopriceDB.LOJAS_PRODS_COLUMN_COLETADO, 0);
        database.update(InfopriceDB.TABLE_LOJAS_PRODUTOS, values,
                InfopriceDB.LOJAS_PRODS_COLUMN_LOJA_ID + " = " + lojaId, null);
    }
    */

    private Propaganda cursotToPropaganda(Cursor cursor){
        Propaganda propaganda = new Propaganda();
        propaganda.setId(cursor.getInt(cursor.getColumnIndex(GeomarketDB.PROPAGANDAS_COLUMN_ID)));
        propaganda.setTitulo(cursor.getString(cursor.getColumnIndex(GeomarketDB.PROPAGANDAS_COLUMN_TITULO)));
        propaganda.setCorpo(cursor.getString(cursor.getColumnIndex(GeomarketDB.PROPAGANDAS_COLUMN_CORPO)));
        propaganda.setLink(cursor.getString(cursor.getColumnIndex(GeomarketDB.PROPAGANDAS_COLUMN_LINK)));
        propaganda.setTipoId(cursor.getInt(cursor.getColumnIndex(GeomarketDB.PROPAGANDAS_COLUMN_TIPO_ID)));
        propaganda.setEstabId(cursor.getInt(cursor.getColumnIndex(GeomarketDB.PROPAGANDAS_COLUMN_ESTAB_ID)));
        propaganda.setDataInicio(new Date(cursor.getLong(cursor.getColumnIndex(GeomarketDB.PROPAGANDAS_COLUMN_DATA_INICIO))));
        propaganda.setDataFim(new Date(cursor.getLong(cursor.getColumnIndex(GeomarketDB.PROPAGANDAS_COLUMN_DATA_FIM))));

        return propaganda;
    }

    private ContentValues getValues(Propaganda propaganda){
        ContentValues values = new ContentValues();
        values.put(GeomarketDB.PROPAGANDAS_COLUMN_ID, propaganda.getId());
        values.put(GeomarketDB.PROPAGANDAS_COLUMN_TITULO, propaganda.getTitulo());
        values.put(GeomarketDB.PROPAGANDAS_COLUMN_CORPO, propaganda.getCorpo());
        values.put(GeomarketDB.PROPAGANDAS_COLUMN_LINK, propaganda.getLink());
        values.put(GeomarketDB.PROPAGANDAS_COLUMN_TIPO_ID, propaganda.getTipoId());
        values.put(GeomarketDB.PROPAGANDAS_COLUMN_ESTAB_ID, propaganda.getEstabId());
        values.put(GeomarketDB.PROPAGANDAS_COLUMN_DATA_INICIO, propaganda.getDataInicio().getTime());
        values.put(GeomarketDB.PROPAGANDAS_COLUMN_DATA_FIM, propaganda.getDataFim().getTime());

        return values;
    }
}
