package com.example.chozabredtttttttto.urok1_1;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chozabredtttttttto.MainActivity;
import com.example.chozabredtttttttto.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class test1_1 extends AppCompatActivity {
    private TextView answerPlaceholder;
    private Button option1, option2;
    private ProgressBar progressBar;
    private int currentQuestion = 0;
    private int correctAnswers = 0; // Количество правильных ответов
    private int stars = 0; // Количество звезд
    private int questionType; // 0 - правописание, 1 - перевод, 2 - аудио
    private MediaPlayer mediaPlayer;
    private Button playButton;

    // Вопросы: {"Тип вопроса", "Текст/аудио ресурс", "Правильный ответ", "Вариант 2"}
    private final String[][] questions = {
            {"0", "Bonjour", "Bonjour !", "Bonjou !"},  // Правописание
            {"1", "Cпасибо", "Merci", "Danke"},       // Перевод
            {"0", "Salut", "Salut !", "Salutt !"},      // Правописание
            {"1", "До свидания", "Au revoir", "Hola"},          // Перевод
            {"2", "bonjour", "Bonjour", "Hola"},        // Аудио вопрос 1
            {"2", "merci", "Merci", "Danke"},           // Аудио вопрос 2
            {"3", "Здравствуйте | Спасибо", "Здравствуйте | Спасибо  ", "Merci|Bonjour"}, // Перенос слов 1
            {"3", "До свидания | Привет", "До свидания | Привет", "Salut|Au revoir"} // Перенос слов 2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1_1);

        getWindow().setStatusBarColor(Color.WHITE);
        // Устанавливаем черный текст и иконки в статус-баре
        getWindow().getDecorView().setSystemUiVisibility(
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | // Контент под статус-баром
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR    // Черный текст и иконки
        );

        // Инициализация UI элементов
        ImageView backArrow = findViewById(R.id.back_arrow);
        progressBar = findViewById(R.id.progress_bar);
        answerPlaceholder = findViewById(R.id.answer_placeholder);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        playButton = findViewById(R.id.play_button);


        progressBar.setMax(questions.length);

        // Назад
        View.OnClickListener backClickListener = v -> showExitConfirmationDialog();
        backArrow.setOnClickListener(backClickListener);

        // Установка первого вопроса
        setQuestion();



        // Обработчики нажатий
        option1.setOnClickListener(v -> checkAnswer(option1.getText().toString()));
        option2.setOnClickListener(v -> checkAnswer(option2.getText().toString()));

        playButton.setOnClickListener(v ->{

            mediaPlayer.start();
        });
    }

    /**
     * Устанавливает текущий вопрос в зависимости от типа (правописание, перевод или аудио)
     */
    private void setQuestion() {
        questionType = Integer.parseInt(questions[currentQuestion][0]);
        answerPlaceholder.setTextColor(Color.BLACK);

        if (questionType == 0) {
            // Задание на правописание
            answerPlaceholder.setText("_____!");
            option1.setText(questions[currentQuestion][3]); // Вариант 1
            option2.setText(questions[currentQuestion][2]); // Вариант 2 (правильный)
        } else if (questionType == 1) {
            // Задание на перевод
            answerPlaceholder.setText(questions[currentQuestion][1]); // Слово/фраза для перевода
            option1.setText(questions[currentQuestion][2]); // Вариант перевода 1
            option2.setText(questions[currentQuestion][3]); // Вариант перевода 2
        } else if (questionType == 2) {
            // Задание на прослушивание
            playAudio(questions[currentQuestion][1]);
            answerPlaceholder.setText("Прослушайте и выберите фразу:");
            option1.setText(questions[currentQuestion][2]); // Вариант 1
            option2.setText(questions[currentQuestion][3]); // Вариант 2
            // Чтобы сделать кнопку видимой
            playButton.setVisibility(View.VISIBLE);
        }
        else if (questionType == 3) {
            // Новый тип задания - перенос слов
            setupDragAndDropTask();
        }
        updateProgressBar();
    }

    /**
     * Проверяет правильность ответа
     */
    private void checkAnswer(String selectedAnswer) {
        String correctAnswer = questions[currentQuestion][2];

        if (questionType == 0) {
            // Для задания на правописание меняем текст
            answerPlaceholder.setText(selectedAnswer);
        }

        if (selectedAnswer.equals(correctAnswer)) {
            handleCorrectAnswer();
        } else {
            handleIncorrectAnswer();
        }
    }

    /**
     * Воспроизводит аудио по названию ресурса
     */
    private void playAudio(String audioResourceName) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        int audioResId = getResources().getIdentifier(audioResourceName, "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, audioResId);
        //mediaPlayer.start();
    }

    /**
     * Обработка правильного ответа
     */
    private void handleCorrectAnswer() {
        answerPlaceholder.setTextColor(Color.parseColor("#4CAF50"));
        correctAnswers++;
        stars += 10;
        showBottomSheetDialog(true);
    }

    /**
     * Обработка неправильного ответа
     */
    private void handleIncorrectAnswer() {
        answerPlaceholder.setTextColor(Color.parseColor("#F44336"));
        showBottomSheetDialog(false);
    }

    /**
     * Показывает BottomSheetDialog с результатом ответа
     */
    private void showBottomSheetDialog(boolean isCorrect) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog, null);
        bottomSheetDialog.setContentView(sheetView);

        TextView resultText = sheetView.findViewById(R.id.result_text);
        TextView explanationText = sheetView.findViewById(R.id.explanation_text);
        Button nextQuestionButton = sheetView.findViewById(R.id.next_question_button);

        if (isCorrect) {
            resultText.setText("Правильно!");
            resultText.setTextColor(Color.parseColor("#4CAF50"));
        } else {
            resultText.setText("Неправильно!");
            resultText.setTextColor(Color.parseColor("#F44336"));
        }

        //explanationText.setText("Правильный ответ: " + questions[currentQuestion][2]);
        explanationText.setText( questions[currentQuestion][2]);

        nextQuestionButton.setOnClickListener(v -> {
            currentQuestion++;
            if (currentQuestion < questions.length) {
                setQuestion();
                updateProgressBar();
            } else {
                // Переход на итоговый экран
                Intent intent = new Intent(this, EndActivity1_1.class);

                // Передаём количество правильных ответов, звёзд и процент
                intent.putExtra("stars", 10); // Пример: передаём 10 звёзд
                intent.putExtra("score", calculateScorePercentage()); // Передаём процент завершения
                startActivity(intent);
                finish(); // Закрываем текущий Activity
                currentQuestion = 0; // Сбрасываем для повторного прохождения
                setQuestion();
            }
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }
    private int calculateScorePercentage() {
        return (int) (((double) correctAnswers / currentQuestion) * 100);
    }
    /**
     * Обновляет прогресс-бар
     */
    private void updateProgressBar() {
        progressBar.setProgress(currentQuestion + 1);
    }

    /**
     * Завершает тест и выводит результаты
     */
    private void finishTest() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Тест завершен!")
                .setMessage("Правильных ответов: " + correctAnswers + "\nЗвезды: " + stars)
                .setPositiveButton("ОК", (dialog, which) -> finish())
                .create().show();
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
    private void setupDragAndDropTask() {
        // Загружаем новый макет для drag-and-drop
        setContentView(R.layout.drag_and_drop_task1_1);

        // Инициализация элементов для перетаскивания
        TextView draggable1 = findViewById(R.id.draggable1);
        TextView draggable2 = findViewById(R.id.draggable2);
        TextView dropArea1 = findViewById(R.id.drop_area1);
        TextView dropArea2 = findViewById(R.id.drop_area2);
        ImageView BackDrop = findViewById(R.id.back_arroww);
        // Назад
        View.OnClickListener backClickListener = v -> showExitConfirmationDialog();
        BackDrop.setOnClickListener(backClickListener);


        // Установка слов для переноса
        String[] words = questions[currentQuestion][2].split("\\|");
        String[] dropWords = questions[currentQuestion][3].split("\\|");

        // Установка слов для переноса в области drop_area
        dropArea1.setText(dropWords[1]);
        dropArea2.setText(dropWords[0]);

        // Установка слов для перетаскивания
        draggable1.setText(words[1]);
        draggable2.setText(words[0]);

        // Добавляем обработчики для перетаскивания
        setupDragAndDrop(draggable1, dropArea1);
        setupDragAndDrop(draggable2, dropArea2);
    }

    private boolean isDropArea1Filled = false;
    private boolean isDropArea2Filled = false;

    private void setupDragAndDrop(TextView draggable, TextView dropArea) {
        draggable.setOnLongClickListener(v -> {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(draggable);
            v.startDragAndDrop(null, shadowBuilder, v, 0);
            return true;
        });

        dropArea.setOnDragListener((v, event) -> {
            switch (event.getAction()) {
                case DragEvent.ACTION_DROP:
                    TextView dropped = (TextView) event.getLocalState();
                    dropArea.setText(dropped.getText());
                    dropped.setVisibility(View.INVISIBLE);

                    // Устанавливаем флаги в зависимости от области
                    if (dropArea.getId() == R.id.drop_area1) {
                        isDropArea1Filled = true;
                    } else if (dropArea.getId() == R.id.drop_area2) {
                        isDropArea2Filled = true;
                    }

                    // Проверка только после того как оба поля заполнены
                    if (isDropArea1Filled && isDropArea2Filled) {
                        checkDragAndDropAnswers();
                    }
                    break;
            }
            return true;
        });
    }
    private void checkDragAndDropAnswers() {
        TextView dropArea1 = findViewById(R.id.drop_area1);
        TextView dropArea2 = findViewById(R.id.drop_area2);

        // Разделяем правильный ответ на два слова для проверки
        String[] correctAnswer = questions[currentQuestion][2].split("\\|"); // Правильные слова
        String userAnswer1 = dropArea1.getText().toString(); // Ответ пользователя для drop_area1
        String userAnswer2 = dropArea2.getText().toString(); // Ответ пользователя для drop_area2

        // Проверяем, что оба поля заполнены (если одно из полей пустое, то не засчитываем)
        if (!userAnswer1.isEmpty() && !userAnswer2.isEmpty()) {
            // Сравниваем ответ пользователя с правильным ответом
            if (userAnswer1.equals(correctAnswer[0]) && userAnswer2.equals(correctAnswer[1])) {
                // Если оба ответа правильные
                handleCorrectAnswer();
            } else {
                // Если хотя бы один ответ неправильный
                handleIncorrectAnswer();
            }
            // Сброс флагов после проверки
            isDropArea1Filled = false;
            isDropArea2Filled = false;
        }
    }
}
