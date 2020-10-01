package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    private static class Statistics {
        int words;
        int characters;
        int syllables;
        int sentences;
        int polysyllables;
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(args[0]);
        String text = Files.readString(path);

        int countSentences = text.split("[.!?]").length;
        String[] words = text.split("\\s");
        int countWords = words.length;
        int countCharacters = Arrays.stream(words)
                .mapToInt(String::length)
                .sum();
        int[] syllables = Arrays.stream(words)
                .mapToInt(Main::countSyllables)
                .toArray();
        int countSyllables = Arrays.stream(syllables)
                .sum();
        int countPolysyllables = (int) Arrays.stream(syllables)
                .filter(x -> x > 2)
                .count();

        System.out.format("The text is:%n%s%n%n", text);
        System.out.format("Words: %d%n", countWords);
        System.out.format("Sentences: %d%n", countSentences);
        System.out.format("Characters: %d%n", countCharacters);
        System.out.format("Syllables: %d%n", countSyllables);
        System.out.format("Polysyllables: %d%n", countPolysyllables);

        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        switch (choice) {
            case "ARI":
                double index = ari(countWords, countSentences, countCharacters);
                printARI(index, assignAge(index));
                break;
            case "FK":
                index = fleschKincaid(countWords, countSentences, countSyllables);
                printFK(index, assignAge(index));
                break;
            case "SMOG":
                index = smog(countPolysyllables, countSentences);
                printSMOG(index, assignAge(index));
                break;
            case "CL":
                index = colemanLiau(countCharacters, countWords, countSentences);
                printCL(index, assignAge(index));
                break;
            case "all":
                LinkedList<Integer> ages = new LinkedList<>();
                index = ari(countWords, countSentences, countCharacters);
                int age = assignAge(index);
                ages.add(age);
                printARI(index, age);

                index = fleschKincaid(countWords, countSentences, countSyllables);
                age = assignAge(index);
                ages.add(age);
                printFK(index, age);

                index = smog(countPolysyllables, countSentences);
                age = assignAge(index);
                ages.add(age);
                printSMOG(index, age);

                index = colemanLiau(countCharacters, countWords, countSentences);
                age = assignAge(index);
                ages.add(age);
                printCL(index, age);

                System.out.println();
                System.out.format("This text should be understood in average by %.2f year olds.%n",
                        ages.stream().mapToInt(x -> x).average().orElse(0));
                break;
            default:
                break;
        }
    }

    private static int assignAge(double index) {
        int intScore = (int) Math.ceil(index);
        int result;
        if (intScore <= 2) {
            result = intScore + 4;
        } else if (intScore == 3) {
            result = 7;
        } else if (intScore <= 12) {
            result = intScore + 5;
        } else if (intScore == 13) {
            result = 18;
        } else {
            result = 24;
        }
        return result;
    }

    private static void printARI(double index, int age) {
        System.out.format("Automated Readability Index: %.2f (about %d year olds).%n", index, age);
    }

    private static void printFK(double index, int age) {
        System.out.format("Flesch–Kincaid readability tests: %.2f (about %d year olds).%n", index, age);
    }

    private static void printSMOG(double index, int age) {
        System.out.format("Simple Measure of Gobbledygook: %.2f (about %d year olds).%n", index, age);
    }

    private static void printCL(double index, int age) {
        System.out.format("Coleman–Liau index: %.2f (about %d year olds).%n", index, age);
    }

    public static double colemanLiau(int characters, int words, int sentences) {
        double L = characters / (double) words * 100;
        double S = sentences / (double) words * 100;
        return 0.0588 * L - 0.296 * S - 15.8;
    }

    public static double smog(int polysyllables, int sentences) {
        return 1.043 * Math.sqrt(polysyllables * 30.0 / sentences) + 3.1291;
    }

    public static double fleschKincaid(int words, int sentences, int syllables) {
        return 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
    }

    public static double ari(int words, int sentences, int characters) {
        return 4.71 * characters / words + 0.5 * words / sentences - 21.43;
    }

    public static int countSyllables(String word) {
        int count = 0;
        char lastVowel = 0;
        boolean previousCharWasVowel = false;
        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'y');
        for (char c :
                word.toCharArray()) {
            if (vowels.contains(c) && !previousCharWasVowel) {
                lastVowel = c;
                count++;
                previousCharWasVowel = true;
            } else {
                previousCharWasVowel = false;
            }
        }
        if (word.endsWith("e")) {
            count--;
        }
        if (count == 0) {
            return 1;
        }
        return count;
    }

    public static void task2() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
//        String line = "This text is hard to read. It contains a lot of sentenses as well as a lot of words in each sentence";
//        String line = "12, 12 13 14 14 14, 12 21 23 89! 75 12, 241 123, 123 123 123, 123 123 123? 123, 123 123 123 23 123 213 123 123 123.";

        String[] sentences = line.split("[.!?]");
        double average = Arrays.stream(sentences)
                .map(sentence -> sentence.trim().split("\\s"))
//                .peek(words -> System.out.println(Arrays.toString(words)))
                .mapToInt(words -> words.length)
                .average().orElse(0);

        if (average <= 10) {
            System.out.println("EASY");
        } else {
            System.out.println("HARD");
        }
    }

    public static void task1() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (line.length() <= 100) {
            System.out.println("EASY");
        } else {
            System.out.println("HARD");
        }
    }
}
