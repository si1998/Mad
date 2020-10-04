package com.example.coolcutz;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Update extends AppCompatActivity {
    EditText _txtName, _txtPhoneNumber, _txtEmail, _txtPassword,_txtID;
    Button _delbtn,_updatedbtn;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        _delbtn = (Button) findViewById(R.id.button10);
        _updatedbtn = (Button) findViewById(R.id.button15);

        _txtName = (EditText) findViewById(R.id.editTextTextPersonName);
        _txtPhoneNumber = (EditText) findViewById(R.id.editTextNumber2);
        _txtEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        _txtPassword = (EditText) findViewById(R.id.editTextTextPassword2);
        _txtID = (EditText) findViewById(R.id.editTextNumber2);

        openHelper = new DBHandler(this);

        _delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String id = _txtID.getText().toString();
                deleteData(id);
                Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        _updatedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = _txtName.getText().toString();
                String phone = _txtPhoneNumber.getText().toString();
                String password = _txtPassword.getText().toString();
                String mail = _txtEmail.getText().toString();
                db = openHelper.getWritableDatabase();
                updateData(name,mail,phone,password);
                Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

        public boolean deleteData(String id){
            return db.delete(DBHandler.TABLE_NAME, DBHandler.COLS_1 + "=?",new String[]{id})>0;
        }

        public boolean updateData(String name, String phone, String mail,String password ){
            ContentValues contentValues = new ContentValues() ;
            contentValues.put(DBHandler.COLS_2,name);
            contentValues.put(DBHandler.COLS_3,mail);
            contentValues.put(DBHandler.COLS_4,phone);
            contentValues.put(DBHandler.COLS_5,password);
            String id = _txtID.getText().toString();
            return db.update(DBHandler.TABLE_NAME,contentValues,DBHandler.COLS_1 + "=?",new String[]{id})>0;
        }

}