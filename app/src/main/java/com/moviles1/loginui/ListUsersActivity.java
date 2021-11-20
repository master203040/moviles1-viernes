package com.moviles1.loginui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.moviles1.loginui.adapters.UserAdapter;
import com.moviles1.loginui.databinding.ActivityListUsersBinding;
import com.moviles1.loginui.entities.UserEntity;

import java.util.ArrayList;

public class ListUsersActivity extends AppCompatActivity {
    private ActivityListUsersBinding listUsersBinding;
    private DbHelper dbHelper;
    private int idUser;
    private ArrayList<UserEntity> usersArrayList;
    private UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listUsersBinding = ActivityListUsersBinding.inflate(getLayoutInflater());
        View v = listUsersBinding.getRoot();
        setContentView(v);
        usersArrayList = new ArrayList<>();
        dbHelper = new DbHelper(this);
        userAdapter = new UserAdapter(this,usersArrayList);
        listUsersBinding.rvListUsers.setHasFixedSize(true);
        listUsersBinding.rvListUsers.setLayoutManager(new LinearLayoutManager(this));
        listUsersBinding.rvListUsers.setAdapter(userAdapter);
        listUsers();
    }

    public void listUsers(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM users",
                null);
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                idUser = Integer.parseInt(cursor.getString(0));
                int indexNameRow = cursor.getColumnIndex("name");
                int indexEmailRow = cursor.getColumnIndex("email");
                int indexIdentificationRow = cursor.getColumnIndex("identification");
                int indexPasswordRow = cursor.getColumnIndex("password");
                String name = cursor.getString(indexNameRow).toString();
                String email = cursor.getString(indexEmailRow).toString();
                long identification = Long.parseLong(cursor.getString(indexIdentificationRow));
                String password = cursor.getString(indexPasswordRow).toString();
                UserEntity userEntity = new UserEntity();
                userEntity.setId(idUser);
                userEntity.setEmail(email);
                userEntity.setName(name);
                userEntity.setIdentification(identification);
                userEntity.setPassword(password);
                usersArrayList.add(userEntity);
            }
            userAdapter.notifyDataSetChanged();
        }
    }
}