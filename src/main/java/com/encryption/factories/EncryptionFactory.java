package com.encryption.factories;
import com.encryption.enums.CipherType;
import com.encryption.strategies.*;

public class EncryptionFactory {
    public static EncryptionStrategy createStrategy(CipherType type, String key) throws Exception {
        return switch (type) {
            case MONO -> new MonoalphabeticCipher(key);
            case VIGENERE -> new VigenereCipher(key);
            case TRANS -> new TranspositionCipher(key);
            case DES -> new DESStrategy(key);
            default -> throw new IllegalArgumentException("Invalid  Type");
        };
    }
}
