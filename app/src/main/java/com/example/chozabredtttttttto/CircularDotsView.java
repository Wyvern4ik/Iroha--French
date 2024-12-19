package com.example.chozabredtttttttto;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircularDotsView extends View {

    private static final int DOT_COUNT = 8; // Количество точек
    private static final float DOT_RADIUS = 10f; // Радиус точки
    private static final float CIRCLE_RADIUS = 100f; // Радиус вращения точки
    private static final int ANIMATION_DURATION = 1000; // Длительность анимации (мс)

    private Paint dotPaint;
    private float rotationAngle = 0f;

    public CircularDotsView(Context context) {
        super(context);
        init();
    }

    public CircularDotsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularDotsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Настраиваем краску для точек
        dotPaint = new Paint();
        dotPaint.setAntiAlias(true);
        dotPaint.setColor(0xFFFFFFFF); // Голубой цвет точек
        dotPaint.setStyle(Paint.Style.FILL);

        // Запускаем анимацию
        startAnimation();
    }

    private void startAnimation() {
        // Анимация вращения точек
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "rotationAngle", 0f, 360f);
        animator.setDuration(ANIMATION_DURATION);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.start();
    }

    public void setRotationAngle(float rotationAngle) {
        this.rotationAngle = rotationAngle;
        invalidate(); // Перерисовываем экран
    }

    public float getRotationAngle() {
        return rotationAngle;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Центр Canvas
        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;

        // Рисуем точки по кругу
        for (int i = 0; i < DOT_COUNT; i++) {
            // Угол для текущей точки (в градусах)
            float angle = (float) (2 * Math.PI * i / DOT_COUNT + Math.toRadians(rotationAngle));
            float dotX = centerX + (float) (CIRCLE_RADIUS * Math.cos(angle));
            float dotY = centerY + (float) (CIRCLE_RADIUS * Math.sin(angle));

            // Рисуем точку
            canvas.drawCircle(dotX, dotY, DOT_RADIUS, dotPaint);
        }
    }
}

