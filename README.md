#  Deck Builder RPG Clone

Final project for the COMP 1406Z course delivered Winter 2021 at Carleton University.

A text-based Java clone of the popular deckbuilder video game, [Slay the Spire](https://store.steampowered.com/app/646570/Slay_the_Spire/), which aims to demonstrate object-oriented programming capabilities with a simple, but extensible demo. 

## Installation

Clone the repository to a local directory with `git clone https://github.com/willKip/Deck-Builder-RPG-Clone/`, and compile the project with a Java compiler.
Java 17 or higher is needed. The entirety of the project runs on the command line.

## Running Example
```
|==============================Game Start==============================|
| You are an adventurer climbing the Spire.
| Play the cards you are given each turn to defeat enemies.

|===============Floor 1===============|
| You encounter a Cultist!
| Turn 1 |
| Cultist HP: 50/50, Block: 0
| Effects: []
| Player HP: 80/80, Block: 0, Energy: <3/3>
| Effects: []

| [C]ards
| [S]tatus
| [E]nd Turn
> c

| Select an option.
| [C]urrent Hand
| dr[A]w Pile
| [D]iscard Pile
| [B]ack
> c
  | Current Energy: <3/3>
  | Current Hand
    | [1] Strike: <1> Deal 6 damage.
    | [2] Strike: <1> Deal 6 damage.
    | [3] Bash: <2> Deal 8 damage. Then apply 2 vulnerable.
    | [4] Strike: <1> Deal 6 damage.
    | [5] Defend: <1> Gain 5 block.
    | [B]ack
Select a card to play. > 1
  | Using Strike!

| Cultist HP: 44/50, Block: 0
| Effects: []
| Player HP: 80/80, Block: 0, Energy: <2/3>
| Effects: []
  | Current Energy: <2/3>
  | Current Hand
    | [1] Strike: <1> Deal 6 damage.
    | [2] Bash: <2> Deal 8 damage. Then apply 2 vulnerable.
    | [3] Strike: <1> Deal 6 damage.
    | [4] Defend: <1> Gain 5 block.
    | [B]ack
Select a card to play. >
```
