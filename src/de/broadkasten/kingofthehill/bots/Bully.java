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
package de.broadkasten.kingofthehill.bots;

import de.broadkasten.kingofthehill.Bot;

/**
 * Bully attacks the bot that has the least life points.
 * @author Pedro Hoffmann Alves
 */
public class Bully extends Bot{

    @Override
    public int getAction(Bot[] bots, int[] actions, int[] lives, int[] powers) {
        int target = Bot.DEFEND;
        int leastLife = Integer.MAX_VALUE;
        for(int i = 0; i < bots.length; i++) {
            if(lives[i] <= leastLife && lives[i] >= 1 && bots[i] != this) {
                leastLife = lives[i];
                target = i;
            }
        }
        return target;
    }
    
}
