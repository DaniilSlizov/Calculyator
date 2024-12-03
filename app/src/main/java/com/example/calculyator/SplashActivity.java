package com.example.calculyator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DISPLAY_LENGTH = 3000; // Время отображения заставки в миллисекундах

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen); // Убедитесь, что этот файл существует

        // Использование Handler для задержки перехода к MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Переход к MainActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class); // Убедитесь, что MainActivity существует
                startActivity(intent);
                finish(); // Закрытие SplashActivity
            }
        }, SPLASH_DISPLAY_LENGTH); // Задержка в 3000 миллисекунд
    }
}
