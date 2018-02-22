package com.example.hossam1.speechrecognition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Arrays;

public class Main2 extends AppCompatActivity {
    RadioGroup radioSexGroup, radioSexGroup2;
    Button btn;
    RadioButton r1, r2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        radioSexGroup = (RadioGroup) findViewById(R.id.g1);
        radioSexGroup2 = (RadioGroup) findViewById(R.id.g2);
       // btn = (Button) findViewById(R.id.Go);
    }

   public void GO(View view) {
        String d[] = {"الباب", "الشباك", "الانوار", "التكييف","السخان",  "البوتجاز" };
        int Select1 = radioSexGroup.getCheckedRadioButtonId();
        int Select2 = radioSexGroup2.getCheckedRadioButtonId();

        if (Select1 != -1 && Select2 != -1) {
            r1 = (RadioButton) findViewById(Select1);
            r2 = (RadioButton) findViewById(Select2);
            String S1, S2;
            S1 = "" + Arrays.asList(d).indexOf(r1.getText().toString());
            if (r2.getText().toString().equals("افتح")) {
                S2 = "A";
            } else {
                S2 = "B";
            }
            Toast.makeText(this, S2 + "  " + S1, Toast.LENGTH_LONG).show();

            Intent in = new Intent(this, MainActivity.class);
            in.putExtra("label", S2 + S1);
            in.putExtra("Name", r2.getText().toString() +"  "+ r1.getText().toString());
            startActivity(in);
        } else {
            Toast.makeText(this, "لازم تختار حاجة ", Toast.LENGTH_LONG).show();

        }

    }
}
