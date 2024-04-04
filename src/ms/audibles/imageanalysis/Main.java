//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE.md file in the project root for full license information.
//
package ms.audibles.imageanalysis;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This sample application shows how to use features of the Azure AI Vision Image Analysis SDK for Java
 */
public class Main {

    @SuppressWarnings("resource") // Scanner
    public static void main(String[] args){

        try {
            System.out.println("\n MS-Audibles: lets hear whats written");

            if (!Secrets.loadSucceeded(args)) {
                System.out.println("Something went wrong with keys");
                return;
            }

            boolean continueRunning = true;
            do {
                System.out.println("\nPlease choose one of the following samples:");
                System.out.println(" 1. Analyze image from PDF to see text");
                System.out.println(" 2. Analyze an image to see text");
                System.out.println(" 3. Hear the audio from PDF");

                System.out.print("\nYour choice 1,2,3 OR 0 to exit): ");
    
                String input = getInputArgument(args);
                if (input == null || input.isEmpty()) {
                    input = new Scanner(System.in).nextLine();
                } else {
                    continueRunning = false; // Input argument provided, exit after running once
                }

                int choice = Integer.parseInt(input);


                switch (choice)
                {
                case 1:
                {
                    System.out.print("\n Please enter the PDF file name. ");
                    String fileName = getInputArgument(args);
                    if (fileName == null || fileName.isEmpty()) {
                        fileName = new Scanner(System.in).nextLine();
                    } else {
                        continueRunning = false; // Input argument provided, exit after running once
                    }
                    ContentParser.extractPDF(Secrets.getEndpoint(), Secrets.getKey(), fileName);
                    break;
                }
                case 2: {

                    System.out.print("\n Please enter the image file name. ");
                    String fileName = getInputArgument(args);
                    if (fileName == null || fileName.isEmpty()) {
                        fileName = new Scanner(System.in).nextLine();
                    } else {
                        continueRunning = false; // Input argument provided, exit after running once
                    }
                    ContentParser.extractImage(Secrets.getEndpoint(), Secrets.getKey(), fileName);
                    break;
                }
                case 3:
                    System.out.print("\n Please enter the PDF file name to convert into audio. ");
                    String fileName = getInputArgument(args);
                    if (fileName == null || fileName.isEmpty()) {
                        fileName = new Scanner(System.in).nextLine();
                    } else {
                        continueRunning = false; // Input argument provided, exit after running once
                    }
                    TextToSpeech.dospeak(ContentParser.extractPDF(Secrets.getEndpoint(), Secrets.getKey(), fileName));
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
                }
            } while (continueRunning);
        }
        catch (Exception e) {
            System.err.println("Exception caught: " + e.toString());
            System.exit(2);
        }

        System.exit(0);
    }

    private static boolean containsHelpOption(String[] args) {
        return Arrays.asList(args).contains("--help") || Arrays.asList(args).contains("-h");
    }

    private static String getInputArgument(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("--input")) {
                return args[i + 1];
            }
        }
        return null;
    }

}