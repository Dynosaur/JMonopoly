package com.github.dynosaur;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dynosaur.io.IOHandler;
import com.github.dynosaur.variation.Variation;

import static com.github.dynosaur.Util.toArrayList;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static File workingDirectory = new File(System.getProperty("user.dir"));
    private static ObjectMapper mapper = new ObjectMapper();

    public static void listen() {
        System.out.print("> ");
        ArrayList<String> commands = toArrayList(input.nextLine().split(" "));
        switch (commands.get(0)) {
            case "x":
            case "exit":
                System.exit(0);
            case "g":
            case "generate":
                generate(commands.subList(1, commands.size()));
                break;
            case "h":
            case "help":
                printHelp();
                break;
            default:
                System.out.println("Command \"" + commands.get(0) + "\" is not recognized. Please use the help command to see possible commands.\n");
        }
    }

    public static void printHelp() {
        System.out.println("    e(x)it - exits the program\n".concat(
                           "    (h)elp - prints available commands\n").concat(
                           "(g)enerate - accepted params: [game, variation]\n"));
    }

    public static void generate(List<String> args) {
        File varConfigs = workingDirectory.toPath().resolve("variation-configs").toFile();
        if (args.size() == 0) {
            System.out.println("Generate requires at least 1 argument: [game, variation]\n");
            return;
        }
        switch (args.get(0)) {
            case "game":
                String[] variations = varConfigs.list();
                if (variations.length == 0) {
                    System.out.println("No variations found, please create one.");
                    return;
                }
                System.out.println("What variation of monopoly do you want to play?");
                File varConfigFile;
                while (true) {
                    for (short i = 0; i < variations.length; i++) System.out.println("\t" + (i + 1) + ": " + variations[i]);
                    System.out.print("> ");
                    String indexOrFileName = input.nextLine();
                    try {
                        short index = Short.parseShort(indexOrFileName);
                        try {
                            varConfigFile = varConfigs.toPath().resolve(variations[index - 1]).toFile();
                            break;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Index not within range, please enter a number between 1 and " + variations.length);
                        }
                    } catch (NumberFormatException e) {
                        varConfigFile = varConfigs.toPath().resolve(indexOrFileName + ".json").toFile();
                        if (!varConfigFile.exists()) {
                            System.out.println("No variation named " + varConfigFile.getName());
                        } else break;
                    }
                }
                Variation v;
                try {
                    v = mapper.readValue(varConfigFile, Variation.class);
                } catch (IOException e) {
                    System.out.println("An error occurred while reading " + varConfigFile.getAbsolutePath() + ": " + e.getMessage() + "\n");
                    return;
                }
                Game g = new Game(input, v);
                g.start();
                break;
            case "variation":
                String variationName;
                try {
                    variationName = args.get(1);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print("What is the name of the variation?\n> ");
                    variationName = input.nextLine();
                }
                File variationFile = workingDirectory.toPath().resolve("variation-configs").resolve(variationName.replace(' ', '-').toLowerCase() + ".json").toFile();
                if (variationFile.exists()) {
                    System.out.println(variationFile.getPath() + " already exists.\n");
                    return;
                }
                try {
                    variationFile.createNewFile();
                    mapper.writerWithDefaultPrettyPrinter().writeValue(variationFile, new Variation(1500));
                    System.out.println("Created new file " + variationFile.getAbsolutePath() + ".\n");
                } catch (IOException e) {
                    System.out.println("An error occurred while creating " + variationFile.getAbsolutePath() + ": " + e.getMessage());
                }
                break;
            default:
                System.out.println("\"" + args.get(0) + "\" is not a valid argument for generate.\n");
        }
    }

    public static void main(String[] args) {
        IOHandler ioHandler = new IOHandler(new String[]{"variation-configs", "saves"});
        try {
            ioHandler.createMissingFiles();
        } catch(IOException e) {
            System.out.println("An error occurred while creating required files: " + e.getMessage());
            System.exit(1);
        }
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        printHelp();
        while (true) listen();
    }
}
