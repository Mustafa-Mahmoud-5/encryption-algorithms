package com.encryption.factories;
import com.encryption.strategies.*;

public class EncryptionFactory {
    public static EncryptionStrategy createStrategy(int choice)
    {
        return switch (choice) {
            case 1 -> new MonoalphabeticCipher();
            case 2 -> new VigenereCipher();
            case 3 -> new TranspositionCipher();
            default -> throw new IllegalArgumentException("Invalid encryption Type");
        };
    }
    public static EncryptionStrategy createStrategy(String method)
    {
        return switch (method) {
            case "MONO" -> new MonoalphabeticCipher();
            case "VIGENERE" -> new VigenereCipher();
            case "TRANS" -> new TranspositionCipher();
            default -> throw new IllegalArgumentException("Unknown encrypted text");
        };
    }

}
