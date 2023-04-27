import java.io.*;
import java.util.Scanner;

public class aplikace {
    public static void main(String[] args) {

        String input = args[0];
        String output = null;

        if (args.length == 2) {
            output = args[1];
        }

        //určím jestli input je cesta k souboru, pokud je složka nalezena,
        // scanner bude scanovat ze složky, pokud ne tak ze standartního vstupu
        // v zadání bylo, že první parametr může být jen sudé číslo, to je zde také ošetřeno
        Scanner scanner = null;
        try {
            File inputFile = new File(input);
            scanner = new Scanner(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            try {
                int temp = Integer.parseInt(input);
                if (temp % 2 != 0) {
                    System.out.println("První parametr musí být sudé číslo, nebo cesta ke složce se vstupem");
                    System.exit(0);
                }
            }catch (NumberFormatException n) {
                System.out.println("První parametr musí být sudé číslo, nebo cesta ke složce se vstupem");
                System.exit(0);
            }

            System.out.println("Zadejte číselnou řadu:");
            scanner = new Scanner(System.in);
        }

        //určíme si Array zadaných čísel, nejsem si jistý jestli mají být zadávány s mezerou nebo bez,
        // raději ošetřím obě možnosti

        String[] arrayOfNumbers;
        String inputNumbers = scanner.nextLine();
        if (inputNumbers.contains(" ")) {
            arrayOfNumbers = inputNumbers.split(" ");
        } else {
            arrayOfNumbers = inputNumbers.split("");
        }

        // pokud output file není zadaný, printuju do standartního vstupu
        // tady se pokusím i ošetřit jestli zadaná čísla nejsou např. Stringy

        if (output == null) {
            printNumbersStandart(arrayOfNumbers);
        } else {
            File outputFile = new File(output);
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(outputFile));
                if (arrayOfNumbers.length % 2 == 0) {
                    for (String number : arrayOfNumbers) {
                        int temp;
                        try {
                            temp = Integer.parseInt(number);
                            if (temp % 2 == 0) {
                                writer.write(temp + " ");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("číselná řada nebyla zadána ve správném formátu, zadejte prosím jen čísla");
                            System.exit(0);
                        }
                    }
                }else {
                    for (String number : arrayOfNumbers) {
                        int temp;
                        try {
                            temp = Integer.parseInt(number);
                            if (temp % 2 != 0) {
                                writer.write(temp + " ");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("číselná řada nebyla zadána ve správném formátu, zadejte prosím jen čísla");
                            System.exit(0);
                        }
                    }
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void printNumbersStandart(String[] arrayOfNumbers) {

        if (arrayOfNumbers.length % 2 == 0) {
            for (String number : arrayOfNumbers) {
                int temp;
                try {
                    temp = Integer.parseInt(number);
                    if (temp % 2 == 0) {
                        System.out.print(temp + " ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("číselná řada nebyla zadána ve správném formátu, zadejte prosím jen čísla");
                    System.exit(0);
                }
            }
        } else {
            for (String number : arrayOfNumbers) {
                int temp;
                try {
                    temp = Integer.parseInt(number);
                    if (temp % 2 != 0) {
                        System.out.print(temp + " ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("číselná řada nebyla zadána ve správném formátu, zadejte prosím jen čísla");
                    System.exit(0);
                }
            }
        }
    }
}