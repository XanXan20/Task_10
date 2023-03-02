package com.example.task_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final String FILE_NAME = "context.txt";

    EditText param1;
    EditText param2;
    Button calculate;
    TextView answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        param1 = findViewById(R.id.param1);
        param2 = findViewById(R.id.param2);
        calculate = findViewById(R.id.calculate);
        answer = findViewById(R.id.answer);

        param1.setText("0");
        param2 .setText("0");

        FileInputStream fin = null;
        try{
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String file = new String(bytes);
            String[] params = file.split(" ");
            param1.setText(params[0]);
            param2.setText(params[1]);
            Toast.makeText(this, "Файл прочитан", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void click(View view) {
        FileOutputStream fos = null;

        try{

            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write((param1.getText().toString() + " " + param2.getText().toString()).getBytes());
            Toast.makeText(MainActivity.this, "Файл сохранен", Toast.LENGTH_SHORT).show();

        }catch(IOException e){
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }catch(IOException e){
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        Integer answ = Integer.parseInt(param1.getText().toString()) + Integer.parseInt(param2.getText().toString());
        answer.setText("Ответ: " + answ.toString());
    }
}