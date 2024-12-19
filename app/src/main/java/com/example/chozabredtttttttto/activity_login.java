package com.example.chozabredtttttttto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class activity_login extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        getWindow().setStatusBarColor(Color.WHITE);
        // Устанавливаем черный текст и иконки в статус-баре
        getWindow().getDecorView().setSystemUiVisibility(
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | // Контент под статус-баром
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR    // Черный текст и иконки
        );

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(activity_login.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isValid = databaseHelper.checkUser(username, password);
                    if (isValid) {
                        String userLanguage = databaseHelper.getUserLanguage(username, password);
                        if (userLanguage != null) {
                            Toast.makeText(activity_login.this, "Добро пожаловать", Toast.LENGTH_SHORT).show();
                            // Здесь можно перейти на следующий экран
                            //Intent intent2 = new Intent(activity_login.this, LessonsActivity.class);
                            Intent intent = new Intent(activity_login.this, MainActivity.class);
                            //intent.putExtra("language", userLanguage);
                            //intent2.putExtra("language", userLanguage);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(activity_login.this, "Неверные данные", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}