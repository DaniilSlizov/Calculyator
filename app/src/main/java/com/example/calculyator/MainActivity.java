package com.example.calculyator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Объявление кнопок и элементов интерфейса
    private Button buttonAdd, buttonSubtract, buttonDivide, buttonMultiply, buttonClean, buttonStepen, buttonDeveloper;
    private TextView operation, result;
    private EditText number1, number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Установка макета для активности

        // Инициализация элементов интерфейса
        initializeViews();

        // Установка обработчиков нажатий для кнопок
        setButtonListeners();
    }

    // Метод для инициализации элементов интерфейса
    private void initializeViews() {
        buttonAdd = findViewById(R.id.buttonAdd); // Кнопка сложения
        buttonSubtract = findViewById(R.id.buttonSubtract); // Кнопка вычитания
        buttonDivide = findViewById(R.id.buttonDivide); // Кнопка деления
        buttonMultiply = findViewById(R.id.buttonMultiply); // Кнопка умножения
        buttonDeveloper = findViewById(R.id.buttonDeveloper); // Кнопка "О разработчике"
        buttonClean = findViewById(R.id.buttonClean); // Кнопка очистки
        buttonStepen = findViewById(R.id.buttonStepen); // Кнопка возведения в степень
        operation = findViewById(R.id.operation); // Текстовое поле для отображения операции
        number1 = findViewById(R.id.number1); // Поле ввода первого числа
        number2 = findViewById(R.id.number2); // Поле ввода второго числа
        result = findViewById(R.id.result); // Текстовое поле для отображения результата
    }

    // Метод для установки обработчиков событий нажатия на кнопки
    private void setButtonListeners() {
        buttonAdd.setOnClickListener(v -> calculate("+")); // Обработчик для сложения
        buttonSubtract.setOnClickListener(v -> calculate("-")); // Обработчик для вычитания
        buttonMultiply.setOnClickListener(v -> calculate("*")); // Обработчик для умножения
        buttonDivide.setOnClickListener(v -> calculate("/")); // Обработчик для деления
        buttonStepen.setOnClickListener(v -> calculate("^")); // Обработчик для возведения в степень
        buttonClean.setOnClickListener(v -> clearFields()); // Обработчик для очистки чисел и операций

        // Обработчик для кнопки "О разработчике"
        buttonDeveloper.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class); // Создание интента для перехода
            startActivity(intent); // Запуск новой активности
        });
    }

    // Остальной код остается без изменений...
    private void calculate(String op) {
        if (isInputValid()) { // Проверка на корректность ввода
            double num1 = Double.parseDouble(number1.getText().toString()); // Процесс анализа первого числа
            double num2 = Double.parseDouble(number2.getText().toString()); // Процесс анализа второго числа
            double res; // Переменная для хранения результата

            // Выполнение операции в зависимости от выбранного знака
            switch (op) {
                case "+":
                    operation.setText("+"); // Установка знака операции
                    res = num1 + num2; // Сложение
                    break;
                case "-":
                    operation.setText("-"); // Установка знака операции
                    res = num1 - num2; // Вычитание
                    break;
                case "*":
                    operation.setText("*"); // Установка знака операции
                    res = num1 * num2; // Умножение
                    break;
                case "/":
                    operation.setText("/"); // Установка знака операции
                    if (num2 != 0) { // Проверка на деление на ноль
                        res = num1 / num2; // Деление
                    } else {
                        showError("Ошибка: деление на ноль"); // Сообщение об ошибке
                        return; // Прерывание выполнения метода
                    }
                    break;
                case "^": // Обработка возведения в степень
                    operation.setText("^"); // Установка знака операции
                    res = Math.pow(num1, num2); // Возведение в степень
                    break;
                default:
                    showError("Ошибка: неверная операция"); // Сообщение об ошибке
                    return; // Прерывание выполнения метода
            }
            result.setText(String.valueOf(res)); // Отображение результата
        } else {
            showError("Ошибка: введите числа"); // Сообщение об ошибке ввода
        }
    }

    // Метод для проверки корректности ввода чисел
    private boolean isInputValid() {
        return !TextUtils.isEmpty(number1.getText()) && !TextUtils.isEmpty(number2.getText()); // Проверка на пустые поля
    }

    // Метод для очистки полей ввода и результата
    private void clearFields() {
        number1.setText(""); // Очистка первого числа
        operation.setText(""); // Очистка знака операции
        number2.setText(""); // Очистка второго числа
        result.setText(""); // Очистка результата
    }

    // Метод для отображения сообщений об ошибках
    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show(); // Показ сообщения
    }
}