package com.example.hossam1.speechrecognition;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

=======
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
>>>>>>> 077f180f1017a4cc38dc3724994b3a8bd8c477c2
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
    Intent mSpeechRecognizerIntent;
    EditText input;
    ImageView click_to_speak;
    TextView show;
    Button btn_save, btn_reset;
    //ListView listview ;
    private DatabaseReference mDatabase;
    ArrayList<String> Sent;
    String Label,txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Sent = new ArrayList<String>();
        Intent data = getIntent();
        Label = data.getStringExtra("label");
        txt =data.getStringExtra("Name");
        TextView t = findViewById(R.id.tex);
        t.setText(txt);
        input = (EditText) findViewById(R.id.editText);
        show = (TextView) findViewById(R.id.show);
        // listview=(ListView)findViewById(R.id.list);
        click_to_speak = (ImageView) findViewById(R.id.imageView);

        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-EG");
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        click_to_speak.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_UP:
                        input.setHint("input show here");

                        mSpeechRecognizer.stopListening();

                        break;

                    case MotionEvent.ACTION_DOWN:
                        checkPermission();
                        input.setText("");
                        input.setHint("listening ");
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);

                        break;
                }
                return false;
            }
        });


        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
                Toast.makeText(MainActivity.this, "Ready For Speech", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onBeginningOfSpeech() {
                Toast.makeText(MainActivity.this, "Beginning Of Speech", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {
                Toast.makeText(MainActivity.this, "Speech Received", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onEndOfSpeech() {
                Toast.makeText(MainActivity.this, "End Of Speech", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(int i) {
                Toast.makeText(MainActivity.this, "Error Speech", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onResults(Bundle bundle) {

                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                if (matches != null) {

                    input.setText(matches.get(0));
<<<<<<< HEAD
                    show.append(matches.get(0) + "\n");
                    Sent.add(matches.get(0));
=======
                    tojson();
                    show.append(matches.get(0) + "\n");

>>>>>>> 077f180f1017a4cc38dc3724994b3a8bd8c477c2
//
//
//

                }

            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });




    }


    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);

        } else {

        }

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

<<<<<<< HEAD
    void save(View view) {
        for (int i = 0; i < Sent.size(); i++) {
            System.out.println(Sent.get(i) + Label);
            String id = mDatabase.push().getKey();
            mDatabase.child(id).child("Sentence").setValue(Sent.get(i));
            mDatabase.child(id).child("Label").setValue(Label);
        }
        Sent.clear();
        show.setText("");
    }
=======
    private void tojson (){

        // get the text from edittext and convert to json

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("command",input.getText());
            Log.i("jsonobject",jsonObject.toString());
            Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

>>>>>>> 077f180f1017a4cc38dc3724994b3a8bd8c477c2

    void reset(View view) {
        Sent.clear();
        show.setText("");
    }
}