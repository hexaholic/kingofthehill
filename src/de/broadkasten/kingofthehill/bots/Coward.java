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
 * Coward does nothing but defend until his life reaches 500 or lower.
 * He then attacks whoever has the least power each turn.
 * @author Pedro Hoffmann Alves
 */
public class Coward extends Bot {

    @Override
    public int getAction(final Bot[] bots, final int[] actions, final int[] lives, final int[] powers) {
        int target = Bot.DEFEND;
        int me = -1;
        for(int i = 0; i < bots.length; i++) {
            if(bots[i] == this) {
                me = i;
            }
        }
        if(lives[me] <= 500) {
            int weakest = Integer.MAX_VALUE;
            for(int i = 0; i < powers.length; i++) {
                if(powers[i] <= weakest && bots[i] != this) {
                    weakest = powers[i];
                    target = i;
                }
            }
        }
        return target;
    }
    
}
