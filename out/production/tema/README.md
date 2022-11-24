#### Dumitru Bianca-Andreea

## GwentStone

#### Description
 * Being a combination between the popular games Gwent and Heartstone, _GwentStone_ is a new
online strategy card game of choices and consequences.
 * The game can be played only by 2 players and based on the input tests made by
an AI, the implemented code can simulate possible matches.

#### Implementation
 * The code is structured in 4 main packages:
    * **Cards** - contains all the cards for the game
    * **Decks** - has all the decks for the players
    * **Game Preps** - are included:
      * a class for preparing the beginning of the game (GameInitialize)
      * a class for storing the game's statistics
    * **Players** - with, obviously, the 2 players
 * Besides the packages, there are 2 more classes:
   * **MyMain** - the "root" of all the classes
   * **MyPrinter** - has all the messages for output

 * Additional information about some classes:
   * _Abilities_ contains the powers of the cards
   * _ChosenDeck_ represents the deck chosen by the player at the beginning of a game
   * _DecksForPLayer_ has all the decks allocated to a player in a game
   * _Player_ is a class that has both players

#### What I think I can improve

* Using interfaces and abstract classes in my code would organise better the code. I think
I could make "Player" an abstract class (PlayerOne and PlayerTwo would extend Player). Also, making
an abstract class or interface for all the types of the cards can be a good idea.

#### Bibliography
   * https://attacomsian.com/blog/jackson-create-json-array
   * https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/tutorial-json-jackson
   * https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/static-final


