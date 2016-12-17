package com.fbytes.greetings;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fbytes.greetings.model.Card;
import com.fbytes.greetings.service.CardService;
import com.fbytes.greetings.service.CardTemplateService;
import com.fbytes.greetings.ui.CommandExecutor;
import com.fbytes.greetings.ui.RunWithHelp;
import com.fbytes.greetings.ui.ConcreteCommand;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CardTemplateService cardTemplateService = CardTemplateService.getInstance();
        CardService cardService = CardService.getInstance();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        RunWithHelp listCards = new RunWithHelp() {
            @Override
            public boolean run(String inCommand) {
                if (inCommand.equals("l")) {
                    cardService.getCards().forEach(System.out::println);
                    return true;
                } else
                    return false;
            }

            @Override
            public void showHelp() {
                System.out.println("l - list created cards");
            }
        };

        RunWithHelp exit = new RunWithHelp() {
            @Override
            public boolean run(String inCommand) {
                if (inCommand.equals("x")) {
                    System.exit(0);
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void showHelp() {
                System.out.println("x - exit from application");
            }
        };


        RunWithHelp createNewCard = new RunWithHelp() {
            @Override
            public boolean run(String inCommand) {
                int templateId;
                try {
                    templateId = Integer.valueOf(inCommand);
                } catch (Exception e) {
                    return false;
                }

                Card newCard = cardService.createCardFromTemplate(cardTemplateService.getTemplateById(templateId));
                executorService.submit(new Sender(newCard));
                String cardText = cardService.createCardText(cardTemplateService.getTemplateById(templateId), newCard);
                System.out.println(cardText);
                return true;
            }

            @Override
            public void showHelp() {
                System.out.println("Select greeting card you wish to send:");
                cardTemplateService.getTemplates().forEach((s) -> {
                    System.out.println("\t" + s);
                });
                System.out.println();
            }
        };

        CommandExecutor commandExecutor = new CommandExecutor()
                .addNext(new ConcreteCommand("list created cards", listCards))
                .addNext(new ConcreteCommand("exit application", exit))
                .addNext(new ConcreteCommand("create new card",createNewCard));


        while (true) {
            commandExecutor.showHelpMsg();
            String input = scanner.nextLine();
            commandExecutor.execute(input);
            System.out.println();
            System.out.println();
        }
    }
}
