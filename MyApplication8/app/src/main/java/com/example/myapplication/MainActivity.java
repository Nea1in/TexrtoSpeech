package com.example.myapplication;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;




import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private EditText editText;
    private Button buttonSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize TextToSpeech
        tts = new TextToSpeech(this, this);

        tts.setLanguage(new Locale("uk"));

        // Get references to EditText and Button widgets
        editText = findViewById(R.id.txtSpeak);
        buttonSpeak = findViewById(R.id.btnSpeech);

        // Set click listener for Speak button
        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get text from EditText widget
                String text = editText.getText().toString();

                // Speak the text using TextToSpeech
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                
            }
        });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // TTS is ready to use
        } else {
            // Error initializing TTS
        }
    }

    @Override
    protected void onDestroy() {
        // Release TTS resources
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}