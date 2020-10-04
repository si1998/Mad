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
import android.util.Patterns;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import static com.example.coolcutz.R.id.ed1Name;

public class Register extends AppCompatActivity {
    EditText _txtName, _txtPhoneNumber, _txtEmail, _txtPassword,_txtID;
    Button _regbtn, _nextbtn,_updatebtn;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    boolean isEmailValid, isPasswordValid,isNameValid,isPhoneValid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        _regbtn = (Button) findViewById(R.id.button11);
        _nextbtn = (Button) findViewById(R.id.button18);
        _updatebtn = (Button) findViewById(R.id.button19);

        _txtName = (EditText) findViewById(ed1Name);
        _txtPhoneNumber = (EditText) findViewById(R.id.ed2Phone2);
        _txtEmail = (EditText) findViewById(R.id.ed4EmailAddress);
        _txtPassword = (EditText) findViewById(R.id.ed3Password2);


        _regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
            }
        });
    }

    public void SetValidation() {
        // Check for a valid email address.
        if ( _txtEmail.getText().toString().isEmpty()) {
            _txtEmail.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher( _txtEmail.getText().toString()).matches()) {
            _txtEmail.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }

        //check for a valide name.
        if (_txtName.getText().toString().isEmpty()) {
            _txtName.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else  {
            isNameValid = true;
        }

        // Check for a valid phone number.
        if (_txtPhoneNumber.getText().toString().isEmpty()) {
            _txtPhoneNumber.setError(getResources().getString(R.string.phone_error));
            isPhoneValid = false;
        } else  {
            isPhoneValid = true;
        }

        // Check for a valid password.
        if (   _txtPassword.getText().toString().isEmpty()) {
            _txtPassword.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (_txtPassword.getText().length() < 6) {
            _txtPassword.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
        }

        if (isNameValid && isEmailValid && isPhoneValid && isPasswordValid) {
            Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }

        openHelper = new DBHandler(this);
        _regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = _txtName.getText().toString();
                String phone = _txtPhoneNumber.getText().toString();
                String password = _txtPassword.getText().toString();
                String mail = _txtEmail.getText().toString();
                db = openHelper.getWritableDatabase();
                insertData(name,mail,phone,password);
                Toast.makeText(getApplicationContext(), "Successfully Entered", Toast.LENGTH_SHORT).show();
            }
        });

        _nextbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });

        _updatebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Register.this,Update.class);
                startActivity(intent);
            }
        });
    }
    public void insertData(String name, String phone, String mail,String password ){
        ContentValues contentValues = new ContentValues() ;
        contentValues.put(DBHandler.COLS_2,name);
        contentValues.put(DBHandler.COLS_3,mail);
        contentValues.put(DBHandler.COLS_4,phone);
        contentValues.put(DBHandler.COLS_5,password);
        long id= db.insert(DBHandler.TABLE_NAME,null,contentValues);
    }


}

