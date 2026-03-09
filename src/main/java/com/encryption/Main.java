package com.encryption;


import com.encryption.factories.EncryptionFactory;
import com.encryption.strategies.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        try {
            System.out.println("enter the file path");
            String path = keyboard.nextLine();

            String text = Files.readString(Paths.get(path));
            System.out.println("Choose function type:");
            System.out.println("1- encryption");
            System.out.println("2- decryption");

            int function = keyboard.nextInt();
            keyboard.nextLine();

            if (function == 1) // encryption logic
            {
                System.out.println("Choose encryption type:");
                System.out.println("1- Monoalphabetic");
                System.out.println("2- Vigenere");
                System.out.println("3- Transposition");

                int strategyChoise = keyboard.nextInt();
                keyboard.nextLine();

                // encryption strategy
                EncryptionStrategy strategy = EncryptionFactory.createStrategy(strategyChoise);

                // Context which use the strategy
                EncryptionContext context = new EncryptionContext(strategy);

                text = text.toLowerCase();
                String encryptedText = context.encrypt(text);

                //style for decryption facility
                encryptedText = "METHOD:"+strategy.getName()+encryptedText;

                String outPath = path.replace(".txt", "_encrypted.txt");
                Files.writeString(Paths.get(outPath), encryptedText);
                System.out.println("encrypted file saved to : " + outPath);
            }
            else if(function == 2)
            {
                String[] lines = text.split("\n",2);
                String methodLine = lines[0];
                String encryptedText = lines[1];

                String method = methodLine.split(":")[1];

                // strategy
                EncryptionStrategy decryptionStrategy = EncryptionFactory.createStrategy(method);

                // Context which use the strategy
                EncryptionContext decryptionContext = new EncryptionContext(decryptionStrategy);

                // decryption
                String decryptedText = decryptionContext.decrypt(encryptedText);

                String outPath = path.replace("_encrypted.txt", "_decrypted.txt");
                Files.writeString(Paths.get(outPath), decryptedText);
                System.out.println("decrypted file saved to : " + outPath);

            }
            else {System.out.println("Invalid function choice!!");}

        } catch (IOException e) {
            System.out.println("Error reading File!");
        }
    }
}