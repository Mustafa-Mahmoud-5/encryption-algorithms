package com.encryption.strategies;

public class MonoalphabeticCipher implements EncryptionStrategy{

    private String key;

    public MonoalphabeticCipher(String key)
    {
        //key validation
        if(key.length() != 26)
                throw new IllegalArgumentException("key  must contain 26 letters");
        this.key = key;
    }
    @Override
    public String encrypt(String text) {
            // bokra b3on allah
        return "";
    }
    @Override
    public String decrypt(String decryptedText)
    {
        return "";
    }
    @Override
    public String getName() {return "MONO";}
}
