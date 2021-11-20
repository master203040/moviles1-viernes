package com.moviles1.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.moviles1.loginui.databinding.ActivityMainBinding;
import com.moviles1.loginui.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding registerBinding;
    private DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = registerBinding.getRoot();
        setContentView(view);
        dbHelper = new DbHelper(this);
    }

    public void registerUser(View view){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues userData = new ContentValues();
        String name = registerBinding.etName.getText().toString();
        String email = registerBinding.etEmail.getText().toString();
        String identification = registerBinding.etIdentification.getText().toString();
        String password = registerBinding.etPassword.getText().toString();
        userData.put("name", name);
        userData.put("email", email);
        userData.put("identification", identification);
        userData.put("password", password);
        long newUser = db.insert("users",null,userData);
        Toast.makeText(this, "" + newUser, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void goTolistUsersActivity(View view){
        Intent intent = new Intent(this, ListUsersActivity.class);
        startActivity(intent);
    }
}