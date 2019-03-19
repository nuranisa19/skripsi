package com.pusing.pusing;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FingerprintManager fingerprintManager;
    KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
        keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);


        findViewById(R.id.ic_fingerprint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startfingerprint();
            }
        });
    }

    private void startfingerprint() {
        if (checkfingerprint ()){
            Toast.makeText (MainActivity.this, "Place your finger on sensor", Toast.LENGTH_SHORT). show();
           FingerprintAuthenticator authenticator = FingerprintAuthenticator.getInstance();
           if (authenticator.cipherinit()){
               FingerprintManager.CryptoObject cryptObj = new FingerprintManager.CryptoObject(authenticator.getCipher());

               FingerprintHandler fingerprintHandler = new FingerprintHandler();
               fingerprintHandler.startAuthentication(cryptObj);
           }
        }
    }

    private class FingerprintHandler extends FingerprintManager.AuthenticationCallback{
        CancellationSignal signal;

        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            Toast.makeText (MainActivity.this, "Authentication Error", Toast.LENGTH_SHORT). show();
        }

        @Override
        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
            super.onAuthenticationHelp(helpCode, helpString);
            Toast.makeText (MainActivity.this, "Authentication Help", Toast.LENGTH_SHORT). show();
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            Toast.makeText (MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT). show();
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            Toast.makeText (MainActivity.this, "Fingerprint Authencitation Success", Toast.LENGTH_SHORT). show();

            startActivity(new Intent(MainActivity.this, layout2.class));
        }

        public void startAuthentication(FingerprintManager.CryptoObject crytObj) {

            CancellationSignal signal = new CancellationSignal();

            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.USE_FINGERPRINT)!= PackageManager.PERMISSION_GRANTED){
                return;
            }

            fingerprintManager.authenticate(crytObj, signal, 0, this, null);
        }

        void  cancelFingerprint(){
            signal.cancel();
        }
    }

    private boolean checkfingerprint() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){

            return false;
        }
        if (fingerprintManager.isHardwareDetected()){
            if(fingerprintManager.hasEnrolledFingerprints()){
                if(keyguardManager.isKeyguardSecure()){
                    return true;
                }
            }else{
                Toast.makeText (this, "Enrol Fingerprint !!!", Toast.LENGTH_SHORT). show();
                startActivity(new Intent(Settings.ACTION_SECURITY_SETTINGS));
            }

        }
        return true;
    }
}