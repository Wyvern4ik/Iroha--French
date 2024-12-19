package com.example.chozabredtttttttto.urok1_4;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chozabredtttttttto.MainActivity;
import com.example.chozabredtttttttto.R;
import com.example.chozabredtttttttto.urok1_3.test1_3;

public class teoria1_4 extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private Button nextButton;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    private int currentStep = 0; // Текущий шаг

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teoria1_4);

        getWindow().setStatusBarColor(Color.WHITE);
        // Устанавливаем черный текст и иконки в статус-баре
        getWindow().getDecorView().setSystemUiVisibility(
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | // Контент под статус-баром
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR    // Черный текст и иконки
        );

        viewFlipper = findViewById(R.id.view_flipper);
        nextButton = findViewById(R.id.next_button);
        progressBar = findViewById(R.id.progress_bar);

        ImageView backArrow = findViewById(R.id.back_arrow);
        // Назад
        backArrow.setOnClickListener(v -> showExitConfirmationDialog());

        // Аудио-файлы
        int[] audioFiles = {R.raw.mere, R.raw.pere, R.raw.frere, R.raw.saur, R.raw.enfant};

        // Обработка кнопки "Прослушать" для каждого этапа
        findViewById(R.id.play_button_1).setOnClickListener(v -> playAudio(audioFiles[0], "Mère"));
        findViewById(R.id.play_button_2).setOnClickListener(v -> playAudio(audioFiles[1], "Père"));
        findViewById(R.id.play_button_3).setOnClickListener(v -> playAudio(audioFiles[2], "Frère"));
        findViewById(R.id.play_button_3).setOnClickListener(v -> playAudio(audioFiles[3], "Sœur"));
        findViewById(R.id.play_button_3).setOnClickListener(v -> playAudio(audioFiles[4], "Enfant"));

        // Обработка кнопки "Далее"
        nextButton.setOnClickListener(v -> {
            if (currentStep < 4) {
                viewFlipper.showNext();
                currentStep++;
                updateProgressBar();
            } else {
                Intent intent = new Intent(teoria1_4.this, test1_4.class);
                startActivity(intent);
            }
        });
    }

    private void playAudio(int audioResource, String message) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, audioResource);
        Toast.makeText(this, "Воспроизведение: " + message, Toast.LENGTH_SHORT).show();
        mediaPlayer.start();
    }

    private void updateProgressBar() {
        progressBar.setProgress((currentStep + 1) * 3); // Обновляем прогресс
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
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
