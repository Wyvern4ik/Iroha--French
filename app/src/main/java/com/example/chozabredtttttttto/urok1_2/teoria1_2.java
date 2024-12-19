package com.example.chozabredtttttttto.urok1_2;

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
import com.example.chozabredtttttttto.urok1_1.test1_1;

public class teoria1_2 extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private Button nextButton;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    private int currentStep = 0; // Текущий шаг

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teoria1_2);

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
        int[] audioFiles = {R.raw.merci, R.raw.ilvousplait, R.raw.aurevoir, R.raw.aurevoir, R.raw.pardon};

        // Обработка кнопки "Прослушать" для каждого этапа
        findViewById(R.id.play_button_1).setOnClickListener(v -> playAudio(audioFiles[0], "Merci"));
        findViewById(R.id.play_button_2).setOnClickListener(v -> playAudio(audioFiles[1], "S'il vous plaît"));
        findViewById(R.id.play_button_3).setOnClickListener(v -> playAudio(audioFiles[2], "Au revoir!"));
        findViewById(R.id.play_button_3).setOnClickListener(v -> playAudio(audioFiles[3], "De rien"));
        findViewById(R.id.play_button_3).setOnClickListener(v -> playAudio(audioFiles[4], "Pardon"));

        // Обработка кнопки "Далее"
        nextButton.setOnClickListener(v -> {
            if (currentStep < 4) {
                viewFlipper.showNext();
                currentStep++;
                updateProgressBar();
            } else {
                Intent intent = new Intent(teoria1_2.this, test1_2.class);
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
