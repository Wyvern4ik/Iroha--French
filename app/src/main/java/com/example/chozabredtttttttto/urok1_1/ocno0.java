package com.example.chozabredtttttttto.urok1_1;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chozabredtttttttto.MainActivity;
import com.example.chozabredtttttttto.R;

public class ocno0 extends AppCompatActivity {
    private ProgressBar progressBar;
    private ImageView backArrow;
    private Button playButton;
    private Button nextButton;
    private MediaPlayer mediaPlayer;
    private RadioGroup answerGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocno0);

        getWindow().setStatusBarColor(Color.WHITE);
        // Устанавливаем черный текст и иконки в статус-баре
        getWindow().getDecorView().setSystemUiVisibility(
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | // Контент под статус-баром
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR    // Черный текст и иконки
        );

        progressBar = findViewById(R.id.progress_bar);
        backArrow = findViewById(R.id.back_arrow);
        playButton = findViewById(R.id.play_button);
        nextButton = findViewById(R.id.next_button);

        // Назад
        backArrow.setOnClickListener(v -> showExitConfirmationDialog());


        // Инициализация медиаплеера для аудио
        mediaPlayer = MediaPlayer.create(this, R.raw.bonjour); // Укажите путь к аудио файлу в res/raw

        // Обработка кнопки "Назад"
        backArrow.setOnClickListener(v -> showExitConfirmationDialog());

        // Обработка кнопки "Прослушать"
        playButton.setOnClickListener(v ->{
            Toast.makeText(this, "Воспроизведение звука: Bonjour!", Toast.LENGTH_SHORT).show();
            mediaPlayer.start();
                });

        // Обработка кнопки "Далее"
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(ocno0.this, teoria1_1.class);
            startActivity(intent);
        });
    }
    public void finishActivity() {
        Intent retryIntent = new Intent(this, MainActivity.class); // Переход на основной экран
        startActivity(retryIntent);
        finish(); // Просто закрываем текущий Activity
    }
    /**
     * Диалог подтверждения выхода
     */
    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ты действительно хочешь выйти?")
                .setMessage("Твой прогресс будет потерян.")
                .setPositiveButton("Выйти", (dialog, which) -> finishActivity())
                .setNegativeButton("Продолжить", (dialog, which) -> dialog.dismiss())
                .create().show();
    }
}
