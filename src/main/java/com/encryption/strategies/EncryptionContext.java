package com.encryption.strategies;

public class EncryptionContext {

    private EncryptionStrategy encryptionStrategy;

    //default
    public EncryptionContext(){}

    //par
    public EncryptionContext(EncryptionStrategy encryptionStrategy)
    {
        setEncryptionStrategy(encryptionStrategy);
    }

    //setter
    public void setEncryptionStrategy(EncryptionStrategy encryptionStrategy)
    {
        this.encryptionStrategy = encryptionStrategy;
    }

    //perform strategy
    public String encrypt(String text)
    {
        return encryptionStrategy.encrypt(text);
    }
    public String decrypt(String decryptedText)
    {
        return encryptionStrategy.decrypt(decryptedText);
    }

}
