package com.example.asus.aplikasisqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DatabaseHelper extends SQLiteOpenHelper {
    //nama database
    public static final String DATABASE_NAME = "kuliah.db";
    //nama table
    public static final String TABLE_NAME = "table_krs";
    //versi database
    private static final int DATABASE_VERSION = 1;
    //table field
    public static final String COL_1 = "kode_barang";
    public static final String COL_2 = "nama_barang";
    public static final String COL_3 = "satuan";
    public static final String COL_4 = "jumlah";
    public static final String COL_5 = "harga";
    public static final String COL_6 = "gambar";


    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table table_krs(kode_barang integer primary key autoincrement," +

        db.execSQL("create table table_krs(kode_barang integer primary key," +
                "nama_barang text null," +
                "satuan text null," +
                "jumlah integer null," +
                "harga integer null," +
                "gambar blob not null);");

    }


    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }


    //metode untuk tambah data
    public boolean insertData(String kode_barang,String nama_barang, String satuan, String jumlah, String harga, String gambar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,kode_barang);
        contentValues.put(COL_2,nama_barang);
        contentValues.put(COL_3,satuan);
        contentValues.put(COL_4,jumlah);
        contentValues.put(COL_5,harga);
        contentValues.put(COL_6,gambar);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;     }



    //metode untuk mengambil data
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from table_krs", null);
        return res;
    }

    //metode untuk merubah data

    public boolean updateData(String kode_barang,String nama_barang, String satuan, String jumlah, String harga, String gambar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,kode_barang);
        contentValues.put(COL_2,nama_barang);
        contentValues.put(COL_3,satuan);
        contentValues.put(COL_4,jumlah);
        contentValues.put(COL_5,harga);
        contentValues.put(COL_6,gambar);
        db.update(TABLE_NAME,contentValues,"kode barang= ?",new String[] {kode_barang});
        return true;
    }


    //metode untuk menghapus data

    public int deleteData (String kode_barang) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "kode barang = ?", new String[] {kode_barang});
    } }
