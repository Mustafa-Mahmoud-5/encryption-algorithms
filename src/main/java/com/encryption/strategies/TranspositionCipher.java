package com.encryption.strategies;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class TranspositionCipher implements EncryptionStrategy {

    private int[] key;

    public TranspositionCipher(String keyInput) {

        String[] parts = keyInput.trim().split("\\s+");
        key = new int[parts.length];

        Set<Integer> check = new HashSet<>();
        int[] temp = new int[parts.length];

        for(int i = 0; i < parts.length; i++) {

            int value = Integer.parseInt(parts[i]);

            if(value < 1 || value > parts.length)
                throw new IllegalArgumentException(
                        "Key values must be between 1 and " + parts.length);

            if(!check.add(value))
                throw new IllegalArgumentException(
                        "Duplicate values in key");

            temp[i] = value;
        }

        Arrays.sort(temp);

        for(int i = 0; i < temp.length; i++) {
            if(temp[i] != i + 1)
                throw new IllegalArgumentException(
                        "Key must contain numbers from 1..n exactly once");
        }

        for(int i = 0; i < parts.length; i++) {
            key[i] = Integer.parseInt(parts[i]) - 1;
        }
    }

    @Override
    public String encrypt(String text) {

        int blockSize = key.length;
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < text.length(); i += blockSize){

            char[] block = new char[blockSize];
            char[] cipher = new char[blockSize];

            for(int j = 0; j < blockSize; j++){
                if(i + j < text.length())
                    block[j] = text.charAt(i + j);
                else
                    block[j] = 'x';
            }

            for(int j = 0; j < blockSize; j++){
                cipher[key[j]] = block[j];
            }

            result.append(cipher);
        }

        return result.toString();
    }

    @Override
    public String decrypt(String text) {

        int blockSize = key.length;
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < text.length(); i += blockSize){

            char[] block = new char[blockSize];
            char[] original = new char[blockSize];

            for(int j = 0; j < blockSize; j++){
                block[j] = text.charAt(i + j);
            }

            for(int j = 0; j < blockSize; j++){
                original[j] = block[key[j]];
            }

            result.append(original);
        }

        return result.toString();
    }

    @Override
    public String getName() {
        return "TRANS";
    }
}
