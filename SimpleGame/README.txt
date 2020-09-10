Ke Zhao
CIS195-202
Simple Game
10/13/2019

================================  Player Instruction  ===================================
To play this game, you need to move the green square on the screen and try your best to not
collide with any black blocks. You will get five points when each blocks disappear on the 
screen, and will die once you collide with any black blocks.

Note: Occasionally you may see white screen when you open the app. It is probably because 
your computer runs too slow. Try to reopen (most of time no need to reinstall) the app and 
wait for few seconds.
  
================================  Classes and Functions  =================================
This game includes three activities.

MainActivity: this activity shows the canvas, which is the Game View of the game.

SubmitActivity: this activity will appear once you end the game. It will show your score 
and allow you to put your name. It also includes menu that allows you to directly restart 
the game or go to leader board activity without storing your name and score.

BoardActivity: this activity will show up after you click the submit button in the SubmitActivity 
or after you click the icon button in the menu. This activity shows the TOP 5 players in the 
game and allow you to reset the leaderboard with confirmation. It also includes menu bar that 
allows you to restart the game. It uses sharePreference to store that past top 5 players’ data.
It uses a ListView with custom adapter to show the results of top five players. 

================================  Game View and Characters  ==============================
GameView: GameView is the main canvas of the game. It implemented SurfaceView, a custom View, 
and GameThread, a thread to control all animations/drawings. 

Sprite: the black rectangle in each line/bar.

SpriteList: this class that creates a list of black rectangle that share same characteristics. 
It also includes the collide function and scoring function.

Player: the class where created the characteristics of players

Constants: it scores some constants such as screen width and screen height for the game.

GameObject: an interface for player and sprites 

