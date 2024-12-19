package com.example.chozabredtttttttto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    private LinearLayout dotsContainer;
    private List<Integer> imageList;
    private int currentDotIndex = 0;

    private ViewPager2 viewPager;

    private TextView text_text;

    private List<String> descriptionList; // Список описаний


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        getWindow().setStatusBarColor(Color.WHITE);
        // Устанавливаем черный текст и иконки в статус-баре
        getWindow().getDecorView().setSystemUiVisibility(
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | // Контент под статус-баром
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR    // Черный текст и иконки
        );

        viewPager = findViewById(R.id.viewPager);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        dotsContainer = findViewById(R.id.dotsContainer);
        text_text = findViewById(R.id.text_text);

        // Рекламные изображения
        imageList = new ArrayList<>();
        imageList.add(R.drawable.image5); // Преимущество 1
        imageList.add(R.drawable.image1); // Преимущество 2
        imageList.add(R.drawable.image2); // Преимущество 3

        // Список описаний для каждого изображения
        descriptionList = new ArrayList<>();
        descriptionList.add("Добро пожаловать в наше приложение! Учитесь эффективно и легко с нашей платформой.");
        descriptionList.add("Исследуйте новые возможности с помощью наших интерактивных уроков.");
        descriptionList.add("Следите за своим прогрессом и достигайте новых высот каждый день.");


        // Устанавливаем адаптер
        ImageAdapter adapter = new ImageAdapter(imageList);
        viewPager.setAdapter(adapter);

        // Добавляем индикаторы точек
        addDotsIndicator(imageList.size());

        // Обрабатываем изменение страницы ViewPager
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Обновляем описание в TextView
                text_text.setText(descriptionList.get(position));

                updateDotsIndicator(position);
            }
        });

        // Обработка кнопки "Начать"
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> {
            // Переход на следующий экран (например, регистрацию)
            Intent intent = new Intent(StartActivity.this, VoprosActivity.class);
            startActivity(intent);
        });
        TextView registr = findViewById(R.id.registr_text);
        registr.setOnClickListener(y -> {
            Intent intent2 = new Intent(StartActivity.this, activity_login.class);
            startActivity(intent2);
        });
    }

    // Добавление точек-индикаторов
    private void addDotsIndicator(int count) {
        dotsContainer.removeAllViews();
        for (int i = 0; i < count; i++) {
            ImageView dot = new ImageView(this);
            dot.setImageResource(i == 0 ? R.drawable.dot_active : R.drawable.dot_inactive);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            dotsContainer.addView(dot, params);
        }
    }

    // Обновление точек-индикаторов
    private void updateDotsIndicator(int selectedIndex) {
        int childCount = dotsContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            android.widget.ImageView dot = (ImageView) dotsContainer.getChildAt(i);
            dot.setImageResource(i == selectedIndex ? R.drawable.dot_active : R.drawable.dot_inactive);
        }
    }
}