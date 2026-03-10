package com.encryption.des;

public abstract class CipherAlgorithm {
    protected int bitSize;
    protected int rounds;
    protected int keySize;
    // Key key;

    abstract public String encrypt(String text) throws Exception;

    abstract public String decrypt(String cipher) throws Exception;

    abstract protected void validateText(String text) throws Exception;
}
