package com.encryption.enums;

public enum CipherType {
    MONO ,
    VIGENERE ,
    TRANS ,
    DES ;
    public static CipherType fromChoice(int choice)
    {
        return switch (choice)
        {
            case 1 -> MONO ;
            case 2 -> VIGENERE;
            case 3 -> TRANS;
            case 4 -> DES;
            default -> throw new IllegalArgumentException("WRONG CHOICE!!");
        };
    }
}
