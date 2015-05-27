# King Of The Hill
**King Of The Hill** is a simple robot battle simulator. It is based on the challenge [What Doesn't Kill Me](http://codegolf.stackexchange.com/questions/50172/what-doesnt-kill-me) on 
 *Programming Puzzles & Code Golf*, initially asked by StackExchange user [Geobits](http://codegolf.stackexchange.com/users/14215/geobits).

##Description
*Shamelessly copied from the linked challenge description, with some edits:*

This is a bot battle to see who can survive the longest. These bots increase their power by being attacked, though, so you need to think carefully before you shoot.

Each round, you can choose a bot to attack, or defend. Attacking will lower its life and increase its power. Last bot standing wins.

For more information on how the battles are fought, see the *How it works* section below.

##Download
If you want to edit the code and/or add your own bots, clone the repo to your machine.

    $ git clone https://github.com/hexaholic/kingofthehill.git

To just download a runnable version, see the [dist](https://github.com/hexaholic/kingofthehill/dist) directory and download the latest JAR file. Run it like this:

    $ java -jar KingOfTheHill.jar

Java 1.7 is required.

##How it works
####Bots
Each bot starts with 1000 life and 10 power.

**When attacked:**
- your attacker's power is subtracted from your life
- your power raises by 1.

So, if on the first round, you are attacked by two bots, you will have 980 life and 12 power.

**If you choose to defend:**
- your power will be lowered by 1
- all attacks against you this round will be reduced by half
- if you are attacked, you will gain 2 power for each attacker instead of 1

So, if you defend on the first round and are attacked by two bots, you will have 990 life and 13 power. If you defend and are not attacked, you will have 1000 life, but 9 power.

If your life is below 1, you die.

####Each round
Bots are called once per round using the `getAction` method. Each round, your bot is given information about all bots in the game as arrays. 

- `bots` is an array of Bot objects. It can be used to determine one's own index.
- `actions` is an array of integer values. It contains the indices of attacked bots or `Bot.DEFEND` for all defending bots.
- `lives` is an array of integer values. It contains the life points of each bot at the start of the current round.
- `powers` is an array of integer values. It contains the power of each bot at the start of the current round.

Array indices are constistent through the whole game, i.e. `actions[i]` was the action that `bots[i]` took last round, while he currently has `lives[i]` life points (for `0 <= i < bots.length`).

####Output
All output is written to `System.out`. It looks similar to this:

    Welcome to the battle! We have 3 bots in the arena.
    All bots are starting with 1000 life and 10 power.
    We will be playing 1000 rounds.
    Let the game begin...
    ---------------------------------------------
    > Hero__ attacks Coward
    > Bully_ attacks Coward
    > Coward is defending
    Hero__ has 1000 life and 10 power
    Bully_ has 1000 life and 10 power
    Coward has 990 life and 13 power
    ---------------------------------------------
    > Hero__ attacks Bully_
    > Bully_ attacks Coward
    > Coward is defending
    Hero__ has 1000 life and 10 power
    Bully_ has 990 life and 11 power
    Coward has 985 life and 14 power
    ---------------------------------------------

        ...

    ---------------------------------------------
    > Hero__ attacks Bully_
    > Bully_ attacks Hero__
    Hero__ died!
    Bully_ has 334 life and 63 power
    ---------------------------------------------
    We have a winner!

##Examples
The `bots` package contains three example bots:

- **Hero** simply attacks whoever has the most life in each round.
- **Bully** attacks the bot that has the least life points each round.
- **Coward** does nothing but defend until his life reaches 500 or lower. He then attacks whoever has the least power each round.

This is Hero's code, that you can use as a base for your own bots:

    package de.broadkasten.kingofthehill.bots;

    import de.broadkasten.kingofthehill.Bot;
    
    public class Hero extends Bot {
        @Override
        public int getAction(final Bot[] bots, final int[] actions, final int[] lives, final int[] powers) {
            int target = Bot.DEFEND;
            int mostLife = 0;
            for(int i = 0; i < bots.length; i++) {
                if(lives[i] >= mostLife && bots[i] != this) {
                    mostLife = lives[i];
                    target = i;
                }
            }
            return target;
        }
    }

When your bot is ready for action, add it to `Controller.java`.

    private final Bot[] bots =  {
        // Add your bots here
        new Hero(),
        new Bully(),
        new Coward(),
        //new MyBot(),
    };

##License
This software is published under MIT License. For more information, see the [LICENSE](https://github.com/hexaholic/kingofthehill/blob/master/LICENSE) file.