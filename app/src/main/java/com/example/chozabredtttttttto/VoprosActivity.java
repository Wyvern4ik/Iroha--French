package com.example.chozabredtttttttto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VoprosActivity extends AppCompatActivity {

    private Button nextButton;
    private String selectedGreeting;
    private String selectedLanguage = null;
    private int selectedFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vopros);

        getWindow().setStatusBarColor(Color.WHITE);
        // Устанавливаем черный текст и иконки в статус-баре
        getWindow().getDecorView().setSystemUiVisibility(
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | // Контент под статус-баром
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR    // Черный текст и иконки
        );
        // Элементы интерфейса

        TextView questionText = findViewById(R.id.questionText);
        RecyclerView languagesRecyclerView = findViewById(R.id.languagesRecyclerView);
        nextButton = findViewById(R.id.nextButton);



        // Список языков
        List<Language> languages = new ArrayList<>();
        //languages.add(new Language("Английский", R.drawable.english, "Hello!"));
        languages.add(new Language("Французский", R.drawable.french, "Bonjour!"));
        //languages.add(new Language("Немецкий", R.drawable.german, "Hallo!"));
        //languages.add(new Language("Испанский", R.drawable.spanish, "¡Hola!"));
        //languages.add(new Language("Итальянский", R.drawable.italian, "Ciao!"));
        //languages.add(new Language("Русский", R.drawable.russian, "Привет!"));
        //languages.add(new Language("Китайский", R.drawable.chinese, "你好!"));
        //languages.add(new Language("Японский", R.drawable.japanese, "こんにちは!"));
        //languages.add(new Language("Корейский", R.drawable.korean, "안녕하세요!"));
        //languages.add(new Language("Арабский", R.drawable.arabic, "مرحبا!"));
        //languages.add(new Language("Португальский", R.drawable.portuguese, "Olá!"));
        //languages.add(new Language("Хинди", R.drawable.hindi, "नमस्ते!"));


        // Установка адаптера с GridLayoutManager
        LanguageAdapter adapter = new LanguageAdapter(languages, this::onLanguageSelected); //
        languagesRecyclerView.setAdapter(adapter);

        // GridLayoutManager с двумя столбцами
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        languagesRecyclerView.setLayoutManager(gridLayoutManager);

        ImageView backButton = findViewById(R.id.backButton);
        // Обработка кнопки "Назад"
        backButton.setOnClickListener(v -> {
            // Действие для кнопки "Назад"
            Toast.makeText(this, "Возврат назад", Toast.LENGTH_SHORT).show();
            finish(); // Завершить Activity
        });

        // Переход к следующему шагу
        nextButton.setOnClickListener(v -> {
            if (selectedLanguage != null) {
                Intent intent = new Intent(this, activity_register.class);
                intent.putExtra("selectedLanguage", selectedLanguage); // Имя языка
                intent.putExtra("selectedFlag", selectedFlag); // Ресурс флага
                intent.putExtra("languageGreeting", selectedGreeting); // Приветствие
                startActivity(intent);
            } else {
                Toast.makeText(this, "Пожалуйста, выберите язык", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Обработка выбора языка
    private void onLanguageSelected(Language language) {
        selectedLanguage = language.getName(); // Сохранение имени языка
        selectedFlag = language.getFlagResId(); // Сохранение ресурса флага
        selectedGreeting = language.getGreeting(); // Сохранение приветствия
        nextButton.setVisibility(Button.VISIBLE); // Показываем кнопку "Далее"
    }
}