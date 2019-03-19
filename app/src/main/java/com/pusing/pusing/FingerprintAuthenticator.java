package com.pusing.pusing;

import android.annotation.TargetApi;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class FingerprintAuthenticator {

    private static final String KEY_NAME = "android.fingerprint" ;
    private static FingerprintAuthenticator fingerprintAuthenticator;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private Object setBlockModes;
    private Cipher cipher;
    public Signature getCipher;


    private FingerprintAuthenticator(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initAuthentication();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initAuthentication(){
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES,"AndroidKeyStore");
        } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }


        try {
            keyStore.load(null);

            keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (CertificateException e1) {
            e1.printStackTrace();
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InvalidAlgorithmParameterException e1) {
            e1.printStackTrace();
        }

    }

    public boolean cipherinit(){
        try {
           cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES+"/" +KeyProperties.BLOCK_MODE_CBC+"/"+KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (NoSuchPaddingException e1) {
            e1.printStackTrace();
        }

        try {
            keyStore.load(null);

            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME, null);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            return true;
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (CertificateException e1) {
            e1.printStackTrace();
        } catch (UnrecoverableKeyException e1) {
            e1.printStackTrace();
        } catch (KeyStoreException e1) {
            e1.printStackTrace();
        } catch (InvalidKeyException e1) {
            e1.printStackTrace();
        }
        return false;
    }


    private void setEncryptionPaddings(String encryptionPaddingPkcs7) {
    }

    private void setBlockModes(String blockModeCbc) {
    }

    public static FingerprintAuthenticator getInstance(){
        if(fingerprintAuthenticator == null)
            fingerprintAuthenticator = new FingerprintAuthenticator();

        return fingerprintAuthenticator;
    }

    public Cipher getCipher() {
        return cipher;
    }
}
