package com.encryption.strategies;

public class TranspositionCipher implements EncryptionStrategy{

    private int[] key;

    public TranspositionCipher(String key) {

        String[] parts = key.split(" ");
        this.key = new int[parts.length];

        for(int i=0;i<parts.length;i++)
            this.key[i] = Integer.parseInt(parts[i]);
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
    public String getName() {return "TRANS";}
}
