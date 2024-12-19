package com.example.chozabredtttttttto.urok1_2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chozabredtttttttto.MainActivity;
import com.example.chozabredtttttttto.R;
import com.example.chozabredtttttttto.urok1_1.teoria1_1;


public class EndActivity1_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_end1_2);

        getWindow().setStatusBarColor(Color.WHITE);
        // Устанавливаем черный текст и иконки в статус-баре
        getWindow().getDecorView().setSystemUiVisibility(
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | // Контент под статус-баром
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR    // Черный текст и иконки
        );

        // Получаем элементы интерфейса
        TextView starsCount = findViewById(R.id.stars_count);
        TextView scorePercentage = findViewById(R.id.score_percentage);
        Button retryButton = findViewById(R.id.retry_button);
        Button finishButton = findViewById(R.id.finish_button);

        // Получаем данные из Intent
        Intent intent = getIntent();
        int stars = intent.getIntExtra("stars", 0);
        int score = intent.getIntExtra("score", 0);

        // Устанавливаем значения звёзд и процента
        starsCount.setText("+" + stars);
        scorePercentage.setText(score + "%");

        // Обработчик для кнопки "Повторить"
        retryButton.setOnClickListener(v -> {
            Intent retryIntent = new Intent(this, teoria1_2.class); // Переход на основной экран
            startActivity(retryIntent);
            finish(); // Закрываем текущий Activity
        });

        // Обработчик для кнопки "Завершить"
        finishButton.setOnClickListener(v -> {
            Intent retryIntent = new Intent(this, MainActivity.class); // Переход на основной экран
            startActivity(retryIntent);
            finish(); // Просто закрываем текущий Activity
        });
    }
}