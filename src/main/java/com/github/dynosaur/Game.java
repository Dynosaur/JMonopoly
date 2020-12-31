package com.github.dynosaur;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.github.dynosaur.variation.Variation;

public class Game {
    // private int turn = 0;
    private Scanner input;
    private CommandPrompt prompt;
    private HashMap<String, Player> players = new HashMap<String, Player>();
    private String[] playerNames;
    private int currentPlayerIndex = 0;
    private short turnCounter = 0;
    private Variation gameVariation;
    private boolean ongoingGame = true;
    private DecimalFormat moneyFormat = new DecimalFormat("#,##0.##");
    private Player bank = new Player(-1, "bank");
    public Game(Scanner input, Variation variation) {
        this.input = input;
        prompt = new CommandPrompt(input);
        gameVariation = variation;
        System.out.println("Enter the names of the players one at a time. Enter nothing when you are finished.");
        short index = 0;
        ArrayList<String> tempPlayerNames = new ArrayList<String>();
        while (true) {
            System.out.print("> ");
            String playerName = input.nextLine();
            if (playerName.equals("")) {
                if (players.size() == 0) System.out.println("Please enter at least one name.");
                else break;
            } else {
                if (players.containsKey(playerName)) System.out.println("A player named " + playerName + " already exists");
                else {
                    Player player = new Player(index, playerName);
                    tempPlayerNames.add(playerName);
                    player.funds += variation.startingFunds;
                    players.put(playerName, player);
                    index++;
                }
            }
        }
        playerNames = tempPlayerNames.toArray(new String[0]);
    }

    public void start() {
        while (true) {
            System.out.print("Who's turn is it?\n> ");
            String player = input.nextLine();
            if (players.containsKey(player)) {
                currentPlayerIndex = players.get(player).index;
                while (ongoingGame) doTurn();
                return;
            } else System.out.println("No player named " + player);
        }
    }

    private void doTurn() {
        Player currentPlayer = players.get(playerNames[currentPlayerIndex]);
        System.out.println("============ Turn " + (turnCounter + 1) + " (" + currentPlayer.name + ") ============");
        turnCommandPrompt();
        if (currentPlayerIndex == playerNames.length - 1) currentPlayerIndex = 0;
        else currentPlayerIndex++;
        turnCounter++;
    }

    private void turnCommandPrompt() {
        turnLoop: while (true) {
            ArrayList<String> commands = prompt.input(false);
            String command = commands.remove(0);
            switch (command) {
                case "x":
                case "exit":
                    while (true) {
                        var response = prompt.input("Are you sure you wish to end the game?", false);
                        switch (response.remove(0)) {
                            case "y":
                            case "yes":
                            case "ok":
                                ongoingGame = false;
                                return;
                            case "n":
                            case "no":
                                break;
                        }
                        break;
                    }
                    break;
                case "h":
                case "help":
                    System.out.println("                           e(x)it - exits the game, back to the main menu\n".concat(
                                       "                           (h)elp - shows commands that can be executed\n").concat(
                                       "                             next - advances to the next player's turn\n".concat(
                                       "            (s)tatus [playerName] - shows current info about a player\n".concat(
                                       "(p)ay [target] [source] [amount] - the source player loses the given amount, and the target (if one is provided) is given that amount"))));
                    break;
                case "p":
                case "pay":
                    try {
                        String targetName = commands.remove(0);
                        Player target;
                        if (!targetName.equals("bank") && !players.containsKey(targetName)) {
                            System.out.println("Could not locate target because no player is named " + targetName + ".\n");
                            break;
                        }
                        if (targetName.equals("bank")) target = bank;
                        else target = players.get(targetName);
                        try {
                            String sourceName = commands.remove(0);
                            Player source;
                            if (!sourceName.equals("bank") && !players.containsKey(sourceName)) {
                                System.out.println("Could not locate source because no player is named " + sourceName + ".\n");
                                break;
                            }
                            if (sourceName.equals("bank")) source = bank;
                            else source = players.get(sourceName);
                            try {
                                String amountString = commands.remove(0);
                                try {
                                    double amount = Double.parseDouble(amountString);
                                    target.funds += amount;
                                    source.funds -= amount;
                                    System.out.println("Transferred $" + moneyFormat.format(amount) + " from " + sourceName + " to " + targetName + ".\n");
                                } catch (NumberFormatException ignored) {
                                    System.out.println("Argument amount must be a number.");
                                }
                            } catch (IndexOutOfBoundsException ignored) {
                                System.out.println("Missing argument amount - the amount of money transferred from the source to target.\n");
                            }
                        } catch (IndexOutOfBoundsException ignored) {
                            System.out.println("Missing argument source - the player who will lose the amount.\n");
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                        System.out.println("Missing argument target - the player who will receive the amount.\n");
                    }
                    break;
                case "next":
                    break turnLoop;
                case "s":
                case "save":
                    break;
                case "status":
                    try {
                        gameStatus(commands.remove(0));
                    } catch (IndexOutOfBoundsException ignore) {
                        gameStatus(playerNames[currentPlayerIndex]);
                    }
                    break;
                default:
                    System.out.println("\"" + command + "\" is not a supported command.\n");
            }
        }
    }

    private void gameStatus(String playerName) {
        if (!players.containsKey(playerName)) {
            System.out.println("No player named " + playerName + ".\n");
            return;
        }
        Player player = players.get(playerName);
        System.out.println("Summary for " + playerName + "\n".concat(
                           "Funds: " + moneyFormat.format(player.funds) + "\n"));
    }
}
