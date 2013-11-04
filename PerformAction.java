package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 10/31/13
 * Time: 2:01 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class PerformAction {
    private String prompt;

    public PerformAction(String prompt){
        this.prompt = prompt;

    }

    public String getPrompt() {
        return prompt;
    }

    public abstract void execute_Action();

}
