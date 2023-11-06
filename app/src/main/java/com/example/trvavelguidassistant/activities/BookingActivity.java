package com.example.trvavelguidassistant.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trvavelguidassistant.R;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        findViewById(R.id.cardAirBooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExternalBrowser("https://www.skyscanner.co.in/");
            }
        });

        findViewById(R.id.cardTrainBooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExternalBrowser("https://www.irctc.co.in/nget/train-search");
            }
        });

        findViewById(R.id.cardBusBooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExternalBrowser("https://www.redbus.in/");
            }
        });

        findViewById(R.id.cardHotelBooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExternalBrowser("https://www.goibibo.com/hotels/");
            }
        });
    }

    private void openExternalBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
