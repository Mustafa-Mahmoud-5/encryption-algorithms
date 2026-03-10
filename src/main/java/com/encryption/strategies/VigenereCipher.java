package com.encryption.strategies;

public class VigenereCipher implements EncryptionStrategy
{
    private String key;

    public VigenereCipher(String keyword) {

        if(!keyword.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException("Key must contain only letters");
        this.key = keyword.toLowerCase();
    }
    @Override
    public String encrypt(String text) {
        StringBuilder sb  = new StringBuilder();
        int keyLen = key.length();
        int keyindex = 0;
        for(char c : text.toCharArray())
        {
            if(c>='a'&&c<='z')
            {
                int shift = key.charAt(keyindex%keyLen)-'a';
                char enc = (char)((c-'a'+shift) % 26 +'a');
                sb.append(enc);
                keyindex++;
            }
            else
                sb.append(c);
        }
        return sb.toString();
    }
    @Override
    public String decrypt(String decryptedText)
    {
        StringBuilder sb = new StringBuilder();
        int keyLen = key.length();
        int keyindex = 0;
        for(char c :decryptedText.toCharArray())
        {
            if(c>='a'&&c<='z')
            {
                int shift = key.charAt(keyindex%keyLen)-'a';
                char dec = (char)((c-'a'-shift +26)% 26 +'a');
                sb.append(dec);
                keyindex++;
            }
            else
                sb.append(c);
        }
        return sb.toString();
    }


    @Override
    public String getName() {return "VIGENERE";}
}
