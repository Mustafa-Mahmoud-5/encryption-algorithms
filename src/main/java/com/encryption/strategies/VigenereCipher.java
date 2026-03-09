package com.encryption.strategies;

public class VigenereCipher implements EncryptionStrategy
{
    private String keyword;

    public VigenereCipher(String keyword) {

        if(!keyword.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException("Key must contain only letters");

        this.keyword = keyword.toLowerCase();
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
    public String getName() {return "VIGNERE";}
}
