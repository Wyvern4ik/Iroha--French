package com.example.chozabredtttttttto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class activity_register extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private Button buttonRegister;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        getWindow().setStatusBarColor(Color.WHITE);
        // Устанавливаем черный текст и иконки в статус-баре
        getWindow().getDecorView().setSystemUiVisibility(
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | // Контент под статус-баром
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR    // Черный текст и иконки
        );

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        databaseHelper = new DatabaseHelper(this);

        // Элементы интерфейса
        ImageView selectedLanguageFlag = findViewById(R.id.selectedLanguageFlag);
        TextView languageGreeting = findViewById(R.id.languageGreeting);
        TextView selectedLanguage = findViewById(R.id.text_logo);


        // Получение данных из Intent
        String name = getIntent().getStringExtra("selectedLanguage");
        int selectedFlag = getIntent().getIntExtra("selectedFlag", -1); // Флаг
        String greeting = getIntent().getStringExtra("languageGreeting"); // Приветствие

        // Установка данных в элементы интерфейса
        if (selectedFlag != -1) {
            selectedLanguageFlag.setImageResource(selectedFlag); // Установка изображения флага
        }

        if (greeting != null) {
            languageGreeting.setText(greeting); // Установка текста приветствия
        }
        if (name != null) {
            selectedLanguage.setText("Зарегестрируйся и начни изучать "+ name); // Установка текста приветствия
        }



        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String language =name.trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(activity_register.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                } else {
                    boolean success = databaseHelper.addUser(username, password, language);
                    if (success) {
                        Toast.makeText(activity_register.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity_register.this, activity_login.class);
                       // Intent intent2 = new Intent(activity_register.this, HomeActivity.class);
                       // intent2.putExtra("name", name);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(activity_register.this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        TextView startButton = findViewById(R.id.text_login);
        startButton.setOnClickListener(v -> {
            // Переход на следующий экран (например, регистрацию)
            Intent intent = new Intent(activity_register.this, activity_login.class);
            startActivity(intent);
        });
    }
}