package com.encryption;


import com.encryption.strategies.EncryptionContext;
import com.encryption.strategies.MonoalphabeticCipher;
import com.encryption.strategies.TranspositionCipher;
import com.encryption.strategies.VigenereCipher;
import com.encryption.utilities.Utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        try
        {
            System.out.println("enter the file path");
            String path = keyboard.nextLine();

            String text = Files.readString(Paths.get(path));
            System.out.println("Choose encryption type:");
            System.out.println("1- Monoalphabetic");
            System.out.println("2- Vigenere");
            System.out.println("3- Transposition");

            int choice = keyboard.nextInt();
            keyboard.nextLine();

            text = Utilities.preprocessText(text,choice);
            String encryptedText ="";
            EncryptionContext encryptionContext = new EncryptionContext();

            switch (choice){
                case 1 :
                    encryptionContext.setEncryptionStrategy(new MonoalphabeticCipher());
                    encryptedText = encryptionContext.encrypt(text);
                    break;
                case 2 :
                    encryptionContext.setEncryptionStrategy(new VigenereCipher());
                    encryptedText = encryptionContext.encrypt(text);
                    break;
                case 3 :
                    encryptionContext.setEncryptionStrategy(new TranspositionCipher());
                    encryptedText = encryptionContext.encrypt(text);
                    break;

                default:
                    System.out.println("Wrong choice !!");
            }

            String outPath = path.replace(".txt","_encrypted.txt");
            Files.writeString(Paths.get(outPath),encryptedText);
            System.out.println("encrypted file saved to : "+outPath);

        }
        catch (IOException e)
        {
            System.out.println("Error reading File!");
        }


    }
}