package com.encryption;

import com.encryption.context.EncryptionContext;
import com.encryption.enums.CipherType;
import com.encryption.factories.EncryptionFactory;
import com.encryption.strategies.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        try {
            System.out.println("enter the file path");
            String inputPath = keyboard.nextLine();

            System.out.println("Enter output file path:");
            String outputPath = keyboard.nextLine();

            String text = Files.readString(Paths.get(inputPath));
            System.out.println("Choose function type:");
            System.out.println("1- encryption");
            System.out.println("2- decryption");

            int function = keyboard.nextInt();
            keyboard.nextLine();

            Path path = Paths.get(outputPath);
            if (function == 1) // encryption logic
            {
                System.out.println("Choose encryption type:");
                System.out.println("1- Monoalphabetic");
                System.out.println("2- Vigenere");
                System.out.println("3- Transposition");
                System.out.println("4- DES");

                int strategyChoise = keyboard.nextInt();
                keyboard.nextLine();

                CipherType cipherType = CipherType.fromChoice(strategyChoise);

                System.out.println("Enter key : ");
                String key = keyboard.nextLine();


                // encryption strategy
                EncryptionStrategy strategy = EncryptionFactory.createStrategy(cipherType , key);

                // Context which use the strategy
                EncryptionContext context = new EncryptionContext(strategy);

                text = text.toLowerCase();
                String encryptedText = context.encrypt(text);

                //style for decryption facility
                encryptedText = "METHOD:"+strategy.getName()+"\n"+encryptedText;

                Files.writeString(path, encryptedText);
                System.out.println("Encrypted file saved to: " + outputPath);
            }
            else if(function == 2)
            {
                String[] lines = text.split("\n",2); // for my program only
                String methodLine = lines[0];
                String encryptedText = lines[1];

                String method = methodLine.split(":")[1];

                CipherType cipherType = CipherType.valueOf(method);

                System.out.print("enter key : ");
                String key = keyboard.nextLine();

                // strategy
                EncryptionStrategy decryptionStrategy = EncryptionFactory.createStrategy(cipherType,key);

                // Context which use the strategy
                EncryptionContext decryptionContext = new EncryptionContext(decryptionStrategy);

                // decryption
                String decryptedText = decryptionContext.decrypt(encryptedText);

                Files.writeString(path, decryptedText);
                System.out.println("Decrypted file saved to: " + outputPath);

            }
            else {System.out.println("Invalid function choice!!");}

        } catch (IOException e) {
            System.out.println("Error reading File!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid cipher type or key! " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}