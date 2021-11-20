package com.moviles1.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.moviles1.loginui.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding profileBinding;
    private DbHelper dbHelper;
    private int idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = profileBinding.getRoot();
        setContentView(view);
        dbHelper = new DbHelper(this);
        getUser();
    }

    public void getUser(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE identification=123456",
                null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            idUser = Integer.parseInt(cursor.getString(0));
            int indexNameRow = cursor.getColumnIndex("name");
            int indexEmailRow = cursor.getColumnIndex("email");
            int indexIdentificationRow = cursor.getColumnIndex("identification");
            int indexPasswordRow = cursor.getColumnIndex("password");
            profileBinding.etName.setText(cursor.getString(indexNameRow).toString());
            profileBinding.etEmail.setText(cursor.getString(indexEmailRow).toString());
            profileBinding.etIdentification.setText(
                    cursor.getString(indexIdentificationRow).toString());
            profileBinding.etPassword.setText(cursor.getString(indexPasswordRow).toString());

        }
        else{
            Toast.makeText(this,
                    "No hay registros",
                    Toast.LENGTH_SHORT).show();
        }

    }
    public void updateUser(View view){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String name = profileBinding.etName.getText().toString();
        String sql = String.format("UPDATE users set name='%s' WHERE id=%s",name,idUser);
        db.execSQL(sql);
    }
}