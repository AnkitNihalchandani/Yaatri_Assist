package com.example.trvavelguidassistant.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trvavelguidassistant.R;
import com.example.trvavelguidassistant.utilities.Constants;
import com.example.trvavelguidassistant.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferenceManager = new PreferenceManager(getApplicationContext());

        TextView textTitle = findViewById(R.id.textTitle);
        textTitle.setText(String.format(
                "%s %s",
                preferenceManager.getString(Constants.KEY_FIRST_NAME),
                preferenceManager.getString(Constants.KEY_LAST_NAME)
        ));

        // Map
        findViewById(R.id.cardMap).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MapDisplayActivity.class)));

        // TravelMate
        findViewById(R.id.cardTravelMate).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SocialMediaActivity.class)));

        // Weather
        findViewById(R.id.cardWeather).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), WeatherMenuActivity.class)));

        // Scheduler
        findViewById(R.id.cardScheduler).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SchedulerActivity.class)));

        // Identify Language
        findViewById(R.id.cardIdentifier).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), IdentifyLanguageActivity.class)));

        // News
        findViewById(R.id.cardNews).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), NewsActivity.class)));

        // OCR
        findViewById(R.id.cardOCR).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), TextRecognitionActivity.class)));

        // Translator
        findViewById(R.id.cardTranslator).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), TranslatorActivity.class)));

        // Travel Log
        findViewById(R.id.cardTravelLog).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), TravelLogMenuActivity.class)));

        // Settings
        findViewById(R.id.cardSettings).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SettingsActivity.class)));

        // Booking Option
        findViewById(R.id.cardBooking).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), BookingActivity.class)));

        findViewById(R.id.textSignOut).setOnClickListener(view -> signOut());
    }

    private void signOut() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );
        HashMap<String, Object> updates = new HashMap<>();
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    preferenceManager.clearPreferences();
                    Toast.makeText(this, "Signing out", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), SelectSignActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Unable to sign out", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("EXIT");
        builder.setMessage("Are you sure you want to exit?");

        builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.show();
    }
}
