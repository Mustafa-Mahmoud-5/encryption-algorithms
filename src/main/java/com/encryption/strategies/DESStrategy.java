package com.encryption.strategies;

import com.encryption.des.DESAlgorithm;

public class DESStrategy implements EncryptionStrategy {
    private DESAlgorithm algorithm;

    public DESStrategy(String key) throws Exception {
        // Initialize DES algorithm with validated key
        this.algorithm = new DESAlgorithm(key);
    }

    @Override
    public String encrypt(String text) {
        try {
            return algorithm.encrypt(text);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed: " + e.getMessage(), e);
        }
    }

    @Override
    public String decrypt(String ciphertext) {
        try {
            return algorithm.decrypt(ciphertext);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed: " + e.getMessage(), e);
        }
    }

    @Override
    public String getName() {
        return "DES";
    }
}
