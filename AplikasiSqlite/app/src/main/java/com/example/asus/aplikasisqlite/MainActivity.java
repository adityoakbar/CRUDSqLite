package com.example.asus.aplikasisqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.database.Cursor; import android.support.v7.app.AlertDialog; import android.support.v7.app.AppCompatActivity; import android.os.Bundle; import android.view.View; import android.widget.Button; import android.widget.EditText; import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper myDb;
    EditText editHarga,editGambar,editSatuan,editJumlah,editKode,editNama;
    Button btnAddData;
    Button btnViewAll;
    Button btnUpdate;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editNama = (EditText)findViewById(R.id.editText_nama);
        editKode = (EditText)findViewById(R.id.editText_kode);
        editHarga = (EditText)findViewById(R.id.editText_harga);
        editSatuan = (EditText)findViewById(R.id.editText_satuan);
        editJumlah = (EditText)findViewById(R.id.editText_jumlah);
        editGambar = (EditText)findViewById(R.id.editText_gambar);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnViewAll = (Button)findViewById(R.id.button_view);
        btnUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);

        AddData();
        viewAll();
        UpdateData();
        deleteData();

    }
    //fungsi hapus

    public void deleteData() {

        btnDelete.setOnClickListener(new View.OnClickListener() {

                                         @Override

                                         public void onClick(View v) {

                                             Integer deletedRows = myDb.deleteData(editKode.getText().toString());

                                             if (deletedRows > 0)

                                                 Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();

                                             else

                                                 Toast.makeText(MainActivity.this,"Data Failed to Deleted!",Toast.LENGTH_LONG).show();

                                         }

                                     }

        );

    }


    //fungsi update

    public void UpdateData() {
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editKode.getText().toString(),
                                editNama.getText().toString(),
                                editSatuan.getText().toString(),
                                editJumlah.getText().toString(),
                                editHarga.getText().toString(),
                                editGambar.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data Failed to Update",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }



    //fungsi tambah

    public void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              boolean isInserted = myDb.insertData(editKode.getText().toString(),editNama.getText().toString (),
                                                      editSatuan.getText().toString(),editJumlah.getText().toString (),
                                                      editHarga.getText().toString(),
                                                      editGambar.getText().toString() );
                                              if(isInserted == true)
                                                  Toast.makeText(MainActivity.this,"Data Terimpan",Toast.LENGTH_LONG).show();
                                              else
                                                  Toast.makeText(MainActivity.this,"Data Gagal Tersimpan",Toast.LENGTH_LONG).show();
                                          }
                                      }
        );
    }



    //fungsi menampilkan data

    public void viewAll() {

        btnViewAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Noting Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext() ) {
                            buffer.append("Kode Barang:"+ res.getString(0)+"\n");
                            buffer.append("Nama Barang :"+ res.getString(1)+"\n");
                            buffer.append("Satuan :"+ res.getString(2)+"\n");
                            buffer.append("Jumlah :"+ res.getString(3)+"\n");
                            buffer.append("Harga :"+ res.getString(4)+"\n");
                            buffer.append("Gambar :"+ res.getString(5)+"\n\n");
                        }
                        // show all data
                        showMessage("Barang :",buffer.toString());
                    }
                }
        );
    }

    //membuat alert dialog

    public void showMessage(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);

        builder.setTitle(title);

        builder.setMessage(Message);

        builder.show();

    }

}

