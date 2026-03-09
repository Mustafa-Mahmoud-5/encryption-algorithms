package com.encryption.enums;

public enum CipherType {
    MONO ,
    VIGENERE ,
    TRANS ;
    public static CipherType fromChoice(int choice)
    {
        return switch (choice)
        {
            case 1 -> MONO ;
            case 2 -> VIGENERE;
            case 3 -> TRANS;
            default -> throw new IllegalArgumentException("WRONG CHOICE!!");
        };
    }
}
