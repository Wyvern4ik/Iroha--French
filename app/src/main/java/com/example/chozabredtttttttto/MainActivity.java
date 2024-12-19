package com.example.chozabredtttttttto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.chozabredtttttttto.urok1_1.ocno0;
import com.example.chozabredtttttttto.urok1_1.teoria1_1;
import com.example.chozabredtttttttto.urok1_2.teoria1_2;
import com.example.chozabredtttttttto.urok1_3.teoria1_3;
import com.example.chozabredtttttttto.urok1_4.teoria1_4;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView progressText, glava1Kol;
    private ImageView learnTab, statsTab, profileTab;
    private boolean isPressed = false; // Состояние нажатия
    private int completedLessons = 0; // Количество завершенных уроков
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(Color.WHITE);
        // Устанавливаем черный текст и иконки в статус-баре
        getWindow().getDecorView().setSystemUiVisibility(
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | // Контент под статус-баром
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR    // Черный текст и иконки
        );

        // Initialize views
        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_text);
        learnTab = findViewById(R.id.learn_tab);
        statsTab = findViewById(R.id.stats_tab);
        profileTab = findViewById(R.id.profile_tab);
        glava1Kol = findViewById(R.id.glava1_kol); // Текст "пройдено уроков"

        LinearLayout lesson1 = findViewById(R.id.lesson_1);
        LinearLayout lesson2 = findViewById(R.id.lesson_2);
        LinearLayout lesson3 = findViewById(R.id.lesson_3);
        LinearLayout lesson4 = findViewById(R.id.lesson_4);
        LinearLayout lesson5 = findViewById(R.id.lesson_5);
        LinearLayout lesson6 = findViewById(R.id.lesson_6);
        LinearLayout lesson7 = findViewById(R.id.lesson_7);
        LinearLayout lesson8 = findViewById(R.id.lesson_8);


        setProgress(75); // Пример установки прогресса на 75%
        // Example: Set progress bar value
        int progress = 20;
        progressBar.setProgress(progress);
        progressText.setText(progress + "%");

        // Example: Set tab listeners
        learnTab.setOnClickListener(v -> {
            // Handle Learn tab click
        });

        statsTab.setOnClickListener(v -> {
            // Handle Stats tab click
        });

        profileTab.setOnClickListener(v -> {
            // Handle Profile tab click
        });

        lesson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Меняем фон на нажатый
                lesson1.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.lesson_background_selector));

                isPressed = !isPressed; // Переключаем состояние

                // Создание BottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_custom1_1, null);
                bottomSheetDialog.setContentView(bottomSheetView);

                // Находим кнопку внутри BottomSheet
                Button startLessonButton = bottomSheetView.findViewById(R.id.bottom_sheet_button_skills);
                startLessonButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Обработчик клика кнопки "Начать урок"
                        Toast.makeText(MainActivity.this, "Удачи!", Toast.LENGTH_SHORT).show();

                        // Переход на новую активность (например, Ocno1)
                        Intent intent = new Intent(MainActivity.this, teoria1_1.class);
                        startActivity(intent);

                        // Закрываем BottomSheetDialog
                        bottomSheetDialog.dismiss();
                    }
                });
                // Отображаем BottomSheetDialog
                bottomSheetDialog.show();
                // Обновляем текст завершенных уроков
                updateCompletedLessonsText();

            }
        });

        lesson2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
          // Меняем фон на нажатый
          lesson2.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.lesson_background_selector));

          isPressed = !isPressed; // Переключаем состояние

          // Создание BottomSheetDialog
          BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
          View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_custom1_2, null);
          bottomSheetDialog.setContentView(bottomSheetView);

          // Находим кнопку внутри BottomSheet
          Button startLessonButton = bottomSheetView.findViewById(R.id.bottom_sheet_button_skills);
          startLessonButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
              // Обработчик клика кнопки "Начать урок"
              Toast.makeText(MainActivity.this, "Удачи!", Toast.LENGTH_SHORT).show();

              // Переход на новую активность (например, Ocno1)
              Intent intent = new Intent(MainActivity.this, teoria1_2.class);
              startActivity(intent);

              // Закрываем BottomSheetDialog
               bottomSheetDialog.dismiss();
                }
             });
              // Отображаем BottomSheetDialog
              bottomSheetDialog.show();
              // Обновляем текст завершенных уроков
              updateCompletedLessonsText();
             }
            });
        lesson3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Меняем фон на нажатый
                lesson3.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.lesson_background_selector));

                isPressed = !isPressed; // Переключаем состояние

                // Создание BottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_custom1_3, null);
                bottomSheetDialog.setContentView(bottomSheetView);

                // Находим кнопку внутри BottomSheet
                Button startLessonButton = bottomSheetView.findViewById(R.id.bottom_sheet_button_skills);
                startLessonButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Обработчик клика кнопки "Начать урок"
                        Toast.makeText(MainActivity.this, "Удачи!", Toast.LENGTH_SHORT).show();

                        // Переход на новую активность (например, Ocno1)
                        Intent intent = new Intent(MainActivity.this, teoria1_3.class);
                        startActivity(intent);

                        // Закрываем BottomSheetDialog
                        bottomSheetDialog.dismiss();
                    }
                });
                // Отображаем BottomSheetDialog
                bottomSheetDialog.show();
                // Обновляем текст завершенных уроков
                updateCompletedLessonsText();
            }
        });
        lesson4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Меняем фон на нажатый
                lesson4.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.lesson_background_selector));

                isPressed = !isPressed; // Переключаем состояние

                // Создание BottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_custom1_4, null);
                bottomSheetDialog.setContentView(bottomSheetView);

                // Находим кнопку внутри BottomSheet
                Button startLessonButton = bottomSheetView.findViewById(R.id.bottom_sheet_button_skills);
                startLessonButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Обработчик клика кнопки "Начать урок"
                        Toast.makeText(MainActivity.this, "Удачи!", Toast.LENGTH_SHORT).show();

                        // Переход на новую активность (например, Ocno1)
                        Intent intent = new Intent(MainActivity.this, teoria1_4.class);
                        startActivity(intent);

                        // Закрываем BottomSheetDialog
                        bottomSheetDialog.dismiss();
                    }
                });
                // Отображаем BottomSheetDialog
                bottomSheetDialog.show();
                // Обновляем текст завершенных уроков
                updateCompletedLessonsText();
            }
        });
        lesson5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Меняем фон на нажатый
                lesson2.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.lesson_background_selector));

                isPressed = !isPressed; // Переключаем состояние

                // Создание BottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_custom1_1, null);
                bottomSheetDialog.setContentView(bottomSheetView);

                // Находим кнопку внутри BottomSheet
                Button startLessonButton = bottomSheetView.findViewById(R.id.bottom_sheet_button_skills);
                startLessonButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Обработчик клика кнопки "Начать урок"
                        Toast.makeText(MainActivity.this, "Урок начался!", Toast.LENGTH_SHORT).show();

                        // Переход на новую активность (например, Ocno1)
                        Intent intent = new Intent(MainActivity.this, ocno0.class);
                        startActivity(intent);

                        // Закрываем BottomSheetDialog
                        bottomSheetDialog.dismiss();
                    }
                });
                // Отображаем BottomSheetDialog
                bottomSheetDialog.show();
            }
        });
        lesson6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Меняем фон на нажатый
                lesson2.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.lesson_background_selector));

                isPressed = !isPressed; // Переключаем состояние

                // Создание BottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_custom1_1, null);
                bottomSheetDialog.setContentView(bottomSheetView);

                // Находим кнопку внутри BottomSheet
                Button startLessonButton = bottomSheetView.findViewById(R.id.bottom_sheet_button_skills);
                startLessonButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Обработчик клика кнопки "Начать урок"
                        Toast.makeText(MainActivity.this, "Урок начался!", Toast.LENGTH_SHORT).show();

                        // Переход на новую активность (например, Ocno1)
                        Intent intent = new Intent(MainActivity.this, ocno0.class);
                        startActivity(intent);

                        // Закрываем BottomSheetDialog
                        bottomSheetDialog.dismiss();
                    }
                });
                // Отображаем BottomSheetDialog
                bottomSheetDialog.show();
            }
        });
        lesson7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Меняем фон на нажатый
                lesson2.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.lesson_background_selector));

                isPressed = !isPressed; // Переключаем состояние

                // Создание BottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_custom1_1, null);
                bottomSheetDialog.setContentView(bottomSheetView);

                // Находим кнопку внутри BottomSheet
                Button startLessonButton = bottomSheetView.findViewById(R.id.bottom_sheet_button_skills);
                startLessonButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Обработчик клика кнопки "Начать урок"
                        Toast.makeText(MainActivity.this, "Урок начался!", Toast.LENGTH_SHORT).show();

                        // Переход на новую активность (например, Ocno1)
                        Intent intent = new Intent(MainActivity.this, ocno0.class);
                        startActivity(intent);

                        // Закрываем BottomSheetDialog
                        bottomSheetDialog.dismiss();
                    }
                });
                // Отображаем BottomSheetDialog
                bottomSheetDialog.show();
            }
        });
        lesson8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Меняем фон на нажатый
                lesson2.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.lesson_background_selector));

                isPressed = !isPressed; // Переключаем состояние

                // Создание BottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_custom1_1, null);
                bottomSheetDialog.setContentView(bottomSheetView);

                // Находим кнопку внутри BottomSheet
                Button startLessonButton = bottomSheetView.findViewById(R.id.bottom_sheet_button_skills);
                startLessonButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Обработчик клика кнопки "Начать урок"
                        Toast.makeText(MainActivity.this, "Урок начался!", Toast.LENGTH_SHORT).show();

                        // Переход на новую активность (например, Ocno1)
                        Intent intent = new Intent(MainActivity.this, ocno0.class);
                        startActivity(intent);

                        // Закрываем BottomSheetDialog
                        bottomSheetDialog.dismiss();
                    }
                });
                // Отображаем BottomSheetDialog
                bottomSheetDialog.show();
            }
        });
    }
    // Обновляем текст с количеством завершенных уроков
    private void updateCompletedLessonsText() {
        String text = "Пройдено уроков " + completedLessons + "/4";
        glava1Kol.setText(text);
    }
}