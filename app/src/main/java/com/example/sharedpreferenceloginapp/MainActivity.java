package com.example.sharedpreferenceloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sEditor;

    private EditText Name, Password;
    private Button login;
    private CheckBox CheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = findViewById(R.id.editTxtName);
        Password = findViewById(R.id.editTxtPassword);
        login =  findViewById(R.id.btnLogin);
        CheckBox = findViewById(R.id.checkBox);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sEditor = sharedPreferences.edit();

        checkSharedPreferences();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckBox.isChecked()){
                    sEditor.putString(CheckBox.getText().toString(), "True");
                    sEditor.commit();

                    String name  = Name.getText().toString();
                    sEditor.putString("NAME", name);
                    sEditor.commit();

                    String password  = Password.getText().toString();
                    sEditor.putString("PASSWORD", password);
                    sEditor.commit();
                }
                else {
                    sEditor.putString(CheckBox.getText().toString(), "False");
                    sEditor.commit();

                    sEditor.putString("NAME", "");
                    sEditor.commit();

                    sEditor.putString("PASSWORD", "");
                    sEditor.commit();
                }
            }
        });
    }

    public void checkSharedPreferences() {
        String checkbox = sharedPreferences.getString(CheckBox.getText().toString(), "False");
        String name = sharedPreferences.getString("NAME", "");
        String password = sharedPreferences.getString("PASSWORD", "");

        Name.setText(name);
        Password.setText(password);

        if (checkbox.equals("True")) {
            CheckBox.setChecked(true);
        }
        else{
            CheckBox.setChecked(false);
        }

    }
}