package com.fbytes.greetings.ui;

/**
 * Created by S on 17.12.2016.
 */
public class ConcreteCommand extends CommandExecutor {

    RunWithHelp crux;
    String commandName;

    public ConcreteCommand(String commandName, RunWithHelp crux){
        this.crux=crux;
        this.commandName=commandName;
    }

    @Override
    public void execute(String command){
        int param;
        try{
            if (!crux.run(command))
                super.execute(command);
        }
        catch (Exception e){
            System.out.println("Error acquired executing "+commandName);
        }

    };

    @Override
    public void showHelpMsg(){
        if (next!=null)
            super.showHelpMsg();

        crux.showHelp();
    }
}
