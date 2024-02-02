package com.assignment.messenger;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private EditText editTextPhoneNumber, editTextMessage;
    private Button sendButton;
    LinearLayout developerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        developerLayout = findViewById(R.id.developerLayout);

        developerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String portfolioUrl = "https://utkarsh140503.github.io/Portfolio/#";
                Uri uri = Uri.parse(portfolioUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextMessage = findViewById(R.id.editTextMessage);
        sendButton = findViewById(R.id.buttonSend);

        // Disable the Send button initially
        sendButton.setEnabled(false);

        // Add text change listeners to validate input length
        editTextPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInput();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        editTextMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInput();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void sendMessage(View view) {
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        if (phoneNumber.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST_CODE);
        } else {
            sendSMS(phoneNumber, message);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();
                String message = editTextMessage.getText().toString().trim();
                sendSMS(phoneNumber, message);
            } else {
                Toast.makeText(this, "Permission denied. SMS not sent.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "SMS sent successfully to "+phoneNumber, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send SMS", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void validateInput() {
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        boolean isPhoneNumberValid = phoneNumber.length() == 10;
        boolean isMessageValid = message.length() <= 160;

        sendButton.setEnabled(isPhoneNumberValid && isMessageValid);
    }
}