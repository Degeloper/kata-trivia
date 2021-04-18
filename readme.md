Once upon a time, there was an ugly implementation of the Trivia Game (Game.java).

Someone once noticed that as long as the same input is provided to the system, 
it will print the same output at the console. So that guy then had to idea to 
Copy-Paste the old implementation (to GameBetter.java), and write a test
that using a LOT of random inputs would call both the old system and the NEW system with the same inputs.
Many-many times. And then the test would then just verifies that the output 
remained the same despite your refactorings.

This is called the "Golden Master Method", and it was used to build the GameTest. 

***
Your job is to refactor BetterGame.java, continuously running GameTest to make sure you don't break things

Goal: apply OOP, SRP, DRY, and other good software practices! 

Warnings:
- There are some bugs hidden in the legacy code.
- Some names are wrong.
- Plus, there are missing abstractions (classes).

Do you best until you're *proud* of this code!

PS: you ARE allowed to make assumptions regarding the code to simplify it. 
Just list those assumptions in this file, below:
- 