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

/**
 * Describes an abstract bot that can join the fight.
 * All bots have to extend this class.
 * @author Pedro Hoffmann Alves
 */
public abstract class Bot {
    
    public static final int DEFEND = -1, DEAD = -2;
    
    private static final int NAME_LENGTH = 6;
    
    private int life, power;
    
    /**
     * Creates a new Bot using the default values set in the Controller class.
     */
    public Bot() {
        life = Controller.DEFAULT_LIFE;
        power = Controller.DEFAULT_POWER;
    }
    
    /**
     * Called when someone attacks the bot.
     * If not defending: damage is subtracted from life, power raises by 1.
     * If defending: power lowered by 1, damage/2 is subtracted from life, gain 2 power for each attacker
     * @param damage The (possible) damage dealt to this bot.
     * @param defending Determines if the bot is defending
     */
    void onAttack(final int damage, boolean defending) {
        life -= defending ? (damage / 2) : damage;
        power += defending ? 2 : 1;
    }
    
    /**
     * Called to reduce the power if the bot is defending.
     */
    void onDefend() {
        power--;
    }
    
    int getLife() {
        return life;
    }
    
    int getPower() {
        return power;
    }
    
    /**
     * Contains the logic of the bot.
     * Determines which action should be taken this round.
     * @param bots An array of all bots in the game
     * @param actions The last action each bot took
     * @param lives The life of each bot
     * @param powers The power of each bot
     * @return The index of the bot to attack or Bot.DEFEND to defend
     */
    public abstract int getAction(final Bot[] bots, final int[] actions, final int[] lives, final int[] powers);
    
    @Override
    public String toString() {
        String padding = "";
        for(int i = 0; i < NAME_LENGTH; i++) {
            padding += "_";
        }
        return (this.getClass().getSimpleName() + padding).substring(0, NAME_LENGTH);
    }
    
}
