# LPOO - 2020 - G32 - BRICKBREAKER

The player is in control of a sliding platform that can bounce the ball into the bricks above. The player must use angles and rebounds to control the direction the balls move. If the balls fall into the abyss below, you’ll lose a life. Once a player manages to destroy all the bricks, he passes the level and the difficulty increases.  
This project was developed by Ricardo Cardoso (up201604686@fe.up.pt) and Marta Lobo (up201604530@fe.up.pt) for LPOO 2019⁄20.


## Implemented Features
- [x] **Arena** - with insurmountable barrier
- [x] **Platform** - the player must be able to move the platform side to side when arrow left or arrow right are pressed.

## Planned Features
- [ ] **Bouncing ball** - must collide with brick and barriers
- [ ] **Normal bricks** - must disappear once they are hit by the ball
- [ ] **Special bricks** - must take more hits till they disappear / increase platform size / decrease platform size
- [ ] **Collision detection** 
- [ ] **Enemies** - must throw bombs; must disappear once it is hit 3 times by the bouncing ball
- [ ] **Score** - must increase differently depending on the type of bricks that are hit 
- [ ] **Lifes** - how many lifes the player has, must decrease whenever the player can't catch the ball
- [ ] **Levels** - 1st level must start with normals bricks only; difficulty increases throughout the levels -> more special bricks, less normal bricks, more enemies 
- [ ] **Menu and Game Over Screen** - main menu must have the title of the game and its instructions, game over screen must have options to quit or play again

## Design 
### 1. Separating the Game's Modules
#### 1.1) Problem in Context
The first challenge was finding a correct way to differentiate the physical aspects of our program: 
We soon understood that we should separate, for example, the **general logic** of the game (such as the movements of the ship, ball and bricks and other mechanic aspects of the game) from the **"drawing"** aspects, which should display that general game logic into a screen, so that the game could be understandable and user friendly. 
This separation is useful by allowing changes on one component, without having to change the other parts; and this way we can easily prevent the violation of the **Single Responsability Principle**.

#### 1.2) The Pattern
To maintain some form of organization, we followed the Model-View-Controller (MVC) architectural pattern.
In short, the MVC design pattern specifies that an application consists of a data model (it directly manages the data, logic and rules of the application),
the view of the information, and the control of the information (accepts inputs and converts them to commands). 
The pattern requires that each of these be separated into different objects. 

#### 1.3) Implementation
Here's how we decided to implement the pattern:

[_UML aqui_]

#### 1.4) Consequences
As said in _**Problem in Context**_, besides the organization it makes it easier to change only one component of the game, and to keep all the others intact.






## Know Code Smells and Refactoring Suggestions 
### Speculative Generality
For now, class Enemy is a speculative generality because it was created to support anticipated future features, and currently has no use.



## Testing

[_screenshot of coverage aqui_]
[_link to mutation testing aqui_]

