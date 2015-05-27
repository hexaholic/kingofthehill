/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Pedro Hoffmann Alves
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package de.broadkasten.kingofthehill;

import de.broadkasten.kingofthehill.bots.*;

/**
 * Handles bot battles and their logic.
 * @author Pedro Hoffmann Alves
 */
public class Controller {
    
    /**
     * Defines the number of rounds to simulate before the game ends.
     */
    private static final int ROUNDS_PER_GAME = 1000;
    
    /**
     * Used to set the initial life and power of each bot.
     */
    public static final int DEFAULT_LIFE = 1000, DEFAULT_POWER = 10;
    
    /**
     * Contains all bots that joined the battle.
     */
    private final Bot[] bots =  {
        
        // Add your bots here
        new Hero(),
        new Bully(),
        new Coward(),
        
    };
    
    /**
     * Contains the last action of each bot.
     */
    private int[] lastActions = new int[bots.length];

    /**
     * Gets called when the program is started.
     * Sets up the controller and starts it.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller controller =  new Controller();
        controller.init();
        controller.run();
    }

    /**
     * Initializes the controller.
     */
    private void init() {
        for(int action : lastActions) {
            action = Bot.DEFEND;
        }
        System.out.println("Welcome to the battle! We have " + bots.length + " bots in the arena.");
        System.out.println("All bots are starting with " + DEFAULT_LIFE + " life and " + DEFAULT_POWER + " power.");
        System.out.println("We will be playing " + ROUNDS_PER_GAME + " rounds.");
        System.out.println("Let the game begin...");
        separator();
    }
    
    private void separator() {
        System.out.println("---------------------------------------------");
    }
    
    private void run() {
        for(int round = 0; round < ROUNDS_PER_GAME; round++) {
            // check for a winner
            int alive = 0;
            for(int i = 0; i < bots.length; i++) {
                if(bots[i].getLife() >= 1) {
                    alive++;
                }
            }
            if(alive == 1) {
                System.out.println("We have a winner!");
                break;
            }
            else {
                if(alive == 0) {
                    System.out.println("No bot won.");
                    break;
                }
            }
            
            
            // gather data from each bot
            int[] lives = new int[bots.length];
            int[] powers = new int[bots.length];
            int[] actions = new int[lastActions.length];
            for(int i = 0; i < bots.length; i++) {
                lives[i] = bots[i].getLife();
                powers[i] = bots[i].getPower();
            }
            
            // ask for actions
            for(int i = 0; i < bots.length; i++) {
                if(bots[i].getLife() >= 1) {
                    actions[i] = bots[i].getAction(bots, lastActions, lives, powers);
                }
                else {
                    actions[i] = Bot.DEAD;
                }
            }
            
            // perform actions
            for(int i = 0; i < actions.length; i++) {
                if(actions[i] != Bot.DEAD) {
                    System.out.print("> " + bots[i]);
                    if(actions[i] == Bot.DEFEND) {
                        bots[i].onDefend();
                        System.out.println(" is defending");
                    }
                    else {
                        Bot target = bots[actions[i]];
                        target.onAttack(powers[i], (actions[actions[i]] == Bot.DEFEND));
                        System.out.println(" attacks " + target);
                    }
                }
            }
            
            // print results
            for(int i = 0; i < bots.length; i++) {
                Bot bot = bots[i];
                if(bot.getLife() >= 1) {
                    System.out.println(bot + " has " + bot.getLife() + " life and " + bot.getPower() + " power");
                }
                else {
                    if(lastActions[i] != Bot.DEAD) {
                        System.out.println(bots[i] + " died!");
                    }
                }
            }
            
            lastActions = actions;
            separator();
        }
    }
}
