package com.example.labctr.ferreteriasqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edx_codigo, edx_descripcion, edx_precio;
    Button btn_grabar, btn_consulCodigo, btn_consulDesc, btn_eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edx_codigo=findViewById(R.id.ed_codigo);
        edx_descripcion=findViewById(R.id.ed_descripcion);
        edx_precio=findViewById(R.id.ed_precio);
        btn_grabar=findViewById(R.id.bt_grabar);
        btn_consulCodigo=findViewById(R.id.bt_consulCodigo);
        btn_consulDesc=findViewById(R.id.bt_consulDesc);
        btn_eliminar=findViewById(R.id.bt_eliminar);


        btn_grabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grabarDatos();
            }
        });

        btn_consulCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarCodigo();
            }
        });

        btn_consulDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarDescripcion();
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarDato();
                consultarCodigo();
            }
        });

    }

    private void grabarDatos() {
        AdminSqlHelper admin = new AdminSqlHelper(getApplicationContext(),
                "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String cod = edx_codigo.getText().toString();
        String des= edx_descripcion.getText().toString();
        String pre = edx_precio.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("codigo_art",cod);
        registro.put("descripcion_art",des);
        registro.put("precio_art", pre);

        db.insert("articulo",null,registro);
        Toast.makeText(getApplicationContext(),"Registro exitoso.",Toast.LENGTH_LONG).show();
        db.close();
    }



    private void consultarCodigo(){
        AdminSqlHelper admin = new AdminSqlHelper(getApplicationContext(),
                "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String cod = edx_codigo.getText().toString();
        Cursor fila = db.rawQuery("select descripcion_art, precio_art " +
                "from articulo where codigo_art='"+cod+"'",null);

        if (fila.moveToFirst()){
            edx_descripcion.setText(fila.getString(0).toString());
            edx_precio.setText(fila.getString(1).toString());
            Toast.makeText(getApplicationContext(),"Encuentra.",
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"No se encontró ese artículo.",
                    Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    private void consultarDescripcion(){
        AdminSqlHelper admin = new AdminSqlHelper(getApplicationContext(),
                "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String des = edx_descripcion.getText().toString();
        Cursor fila = db.rawQuery("select codigo_art, precio_art " +
                "from articulo where descripcion_art='"+des+"'",null);

        if (fila.moveToFirst()){
            edx_codigo.setText(fila.getString(0).toString());
            edx_precio.setText(fila.getString(1).toString());
            Toast.makeText(getApplicationContext(),"Encuentra.",
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"No se encontró ese artículo.",
                    Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    private void eliminarDato() {
        AdminSqlHelper admin = new AdminSqlHelper(getApplicationContext(),
                "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String cod = edx_codigo.getText().toString();

        int delete = db.delete("articulo","codigo_art='"+cod+"'",null);

        if (delete==1)
        {
            Toast.makeText(getApplicationContext(),"Eliminado.",
                    Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(getApplicationContext(),"No se eliminó ese artículo.",
                    Toast.LENGTH_LONG).show();
        }

    }
}
