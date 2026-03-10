package com.encryption.des;

public abstract class CipherAlgorithm {
    protected int bitSize;
    protected int rounds;
    protected int keySize;
    protected Key key;

    public void setKey(String key) throws Exception {
        validateText(key);
        this.key = new Key(key);
        this.key.generate();
    }

    abstract public String encrypt(String text) throws Exception;

    abstract public String decrypt(String cipher) throws Exception;

    abstract protected void validateText(String text) throws Exception;
}
