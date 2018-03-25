package com.example.bregasaria.bregasaria_1202150052_studycase5.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bregasaria.bregasaria_1202150052_studycase5.R;

public class AddActivity extends AppCompatActivity {

    // Mendeklarasikan variabel
    private EditText Eactivity, Edescription, Epriority;
    private Button Simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Mendefinisikan tiap-tiap id dari xml activity_add
        Eactivity = findViewById(R.id.Etodo);
        Edescription = findViewById(R.id.Edesc);
        Epriority = findViewById(R.id.Eprio);
        Simpan = findViewById(R.id.save);

        // Menyimpan data yang telah diinputkan
        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("activity",Eactivity.getText().toString());
                intent.putExtra("desc",Edescription.getText().toString());
                intent.putExtra("priority",Epriority.getText().toString());
                setResult(1,intent);
                finish();//finishing activity
            }
        });
    }
}
