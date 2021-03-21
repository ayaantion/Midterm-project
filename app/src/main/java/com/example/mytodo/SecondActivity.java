package com.example.mytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private EditText editTextTittle;
    private EditText editTextDesc;
    private Button buttonSave;
    private Button buttonShare;
    private Button buttonDelete;
    String tittle;
    String t;

    public static final String EXTRA_T = "tittle";
    public static final String EXTRA_D = "desc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editTextTittle = findViewById(R.id.edit_tittle);
        editTextDesc = findViewById(R.id.edit_Desc);
        buttonSave = findViewById(R.id.btn_save);
        buttonShare = findViewById(R.id.btn_share);
        buttonDelete = findViewById(R.id.btn_delete);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 tittle = editTextTittle.getText().toString();
                //  String desc = editTextDesc.getText().toString();
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.putExtra(EXTRA_T, tittle);
                //      intent.putExtra(EXTRA_D, desc);
                setResult(RESULT_OK, intent);
                finish();
                //  startActivity(intent);
            }
        });

        if (getIntent() != null) {
            t = getIntent().getStringExtra("list");
            editTextTittle.setText(t , TextView.BufferType.EDITABLE);
        }


        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,t );
                Intent chosenIntent = Intent.createChooser(intent, "Заголовок в диалоговом окне");
                startActivity(chosenIntent);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               editTextTittle.setText("");
               editTextDesc.setText("");
            }
        });

    }

}
