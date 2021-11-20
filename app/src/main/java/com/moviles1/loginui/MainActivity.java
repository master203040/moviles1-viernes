
package com.moviles1.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moviles1.loginui.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        mainBinding.btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                String email = mainBinding.etEmail.getText().toString();
                String password = mainBinding.etPassword.getText().toString();
                Toast.makeText(this, email + password, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}