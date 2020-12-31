package com.github.dynosaur;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandPrompt {
    private Scanner input;
    public CommandPrompt(Scanner input) {
        this.input = input;
    }
    public ArrayList<String> input(String question, boolean allowEmpty) {
        while (true) {
            if (question.equals("")) System.out.print("> ");
            else System.out.print(question + "\n> ");
            var commands = parseCommands(input.nextLine());
            if (!allowEmpty) {
                if (commands.size() == 0) continue;
            }
            return commands;
        }
    }
    public ArrayList<String> input() {
        return input("", true);
    }
    public ArrayList<String> input(boolean allowEmpty) {
        return input("", allowEmpty);
    }

    public static ArrayList<String> parseCommands(String commandString) {
        ArrayList<String> list = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        boolean insideString = false;
        for (char c : commandString.toCharArray()) {
            if (c == ' ' && !insideString) {
                if (builder.length() != 0) {
                    list.add(builder.toString());
                    builder = new StringBuilder();
                } else continue;
            } else if (c == '"') {
                insideString = insideString ? false : true;
                continue;
            } else {
                builder.append(c);
            }
        }
        if (builder.length() > 0) list.add(builder.toString());
        return list;
    }
}
