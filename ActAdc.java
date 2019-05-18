package com.example.pedri.elavamonos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ActAdc  extends AppCompatActivity {

    final int REQUEST_CODE_GALLERY = 999;
    private EditText nomeReceita;
    private EditText tempoReceita;
    private EditText rendimeto;
    private EditText preparo;
    private EditText ingredientes;
    private ImageView foto;
    public static SQLiteHelper sqLiteHelper;
    private Button btnAdd;
    private Receita receita = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        init();
        sqLiteHelper = new SQLiteHelper(this, "FoodDB.sqlite", null, 2);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS RECEITA(Id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, tempo VARCHAR,rendimento VARCHAR,preparo VARCHAR,ingredientes , foto BLOB)");


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insertData(
                            nomeReceita.getText().toString().trim(),
                            tempoReceita.getText().toString().trim(),
                            rendimeto.getText().toString().trim(),
                            preparo.getText().toString().trim(),
                            ingredientes.getText().toString().trim(),
                            imageViewToByte(foto)
                    );
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    nomeReceita.setText("");
                    tempoReceita.setText("");
                    rendimeto.setText("");
                    preparo.setText("");
                    ingredientes.setText("");
                    foto.setImageResource(R.mipmap.ic_launcher);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });






    }



    public void onClick(View view) {
        ActivityCompat.requestPermissions(
                ActAdc.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY
        );
    }



        public void golist(View view) {
            Intent intentl = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intentl);
        }



    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                foto.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    private void init(){
        nomeReceita = findViewById(R.id.nome);
        tempoReceita = findViewById(R.id.tempo);
        rendimeto = findViewById(R.id.rendimento);
        ingredientes = findViewById(R.id.ingredientes);
        preparo = findViewById(R.id.praparo);
        foto = findViewById(R.id.imageView);
        btnAdd = (Button) findViewById(R.id.btnAdd);
    }
}
