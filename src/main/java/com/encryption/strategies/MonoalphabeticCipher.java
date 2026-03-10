package com.encryption.strategies;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MonoalphabeticCipher implements EncryptionStrategy{

    private String key;
    private Map<Character , Character> decryptMap;

    public MonoalphabeticCipher(String key)
    {
        //key validation
        if(key.length() != 26)
                throw new IllegalArgumentException("key  must contain 26 letters");
        key = key.toLowerCase();
        Set<Character> set = new HashSet<>();
        for(char c : key.toCharArray())
        {
            if(c<'a' || c>'z')
                throw new IllegalArgumentException("Key must contain only letters from a to z");
            if(!set.add(c))
                throw new IllegalArgumentException("Key contains duplicate characters");
        }
        this.key = key;

        // create a map for decryption
        decryptMap = new HashMap<>();
        for(int i=0 ; i<26 ; i++)
            decryptMap.put(key.charAt(i) , (char)('a'+i));

    }
    @Override
    public String encrypt(String text) {
        StringBuilder sb = new StringBuilder();
        for( char c : text.toCharArray())
        {
            if(c>='a' && c<='z')
                sb.append(key.charAt(c-'a'));
            else
                sb.append(c);
        }
        return sb.toString();
    }
    @Override
    public String decrypt(String decryptedText)
    {
        StringBuilder sb = new StringBuilder();
        for (char c: decryptedText.toCharArray())
        {
            if(c>='a' && c<='z')
                sb.append(decryptMap.get(c));
            else
                sb.append(c);
        }
        return sb.toString();
    }
    @Override
    public String getName() {return "MONO";}
}
