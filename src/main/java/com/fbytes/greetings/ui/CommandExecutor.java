package com.fbytes.greetings.ui;

/**
 * Created by S on 17.12.2016.
 */
public class CommandExecutor {

    protected CommandExecutor next;

    public void execute(String command){
        if (next!=null)
            next.execute(command);
        else
            System.out.println("Unknown command");
    };

    public void showHelpMsg(){
        if (next!=null)
            next.showHelpMsg();
    }

    public CommandExecutor addNext(CommandExecutor commandExecutor){
        if (next!=null){
            next.addNext(commandExecutor);
        }
        else{
            next=commandExecutor;
        }
        return this;
    };

}
