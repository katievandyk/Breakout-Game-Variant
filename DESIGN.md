# High Level Design Goals
The overall design features objects for the Paddle, Block, Bouncer, PowerUp, ToolBar, and Life. The Block object is a super class for two different types of blocks single/multi-hit blocks, and blocks that do not change when hit. The driver creates an instance of the Scene Controller, which holds all instances of these objects for the current game. The Scene Controller utilizes the Level Controller to control block configurations for specific levels. The Scene Controller handles the interface between PowerUps (which are defined to be the objects that fall from blocks) and actual changes seen on the game screen (the paddle growing, an extra bouncer being added, etc.). 

The Scene Controller can also create text screens when appropriate, such as for the start of the game, or when the user wins/loses. The SceneController calls the toolbar class to instantiate the toolbar at the bottom of each level scene. Finally, the Scene Controller also handles keys being pressed, such as the cheat keys or 'enter' key to signal the start of the game.

# Adding New Features
## Level
### 1. Add Block Configuration to Levels.java
Add the algorithm for returning an array of hit/bounce block coordinates in Levels.java. 
### 2. Add new level to Level Controller (LevelCtrl.java)
Add the current level to the changeLevel() method in the level controller, so that the coordinate arrays are set appropriately when the level = 'X'. 
### 3. Update handleWin() function
The handleWin() function keeps updating levels, unless the current level has updated to a number greater than the total number of levels. Update this function so that the total number of levels is incremented.
## Power Up
### 1. Update the PowerUp object
Under the PowerUp 'TYPE' attribute, add a new type of power up. Reference an image from the driver class to serve as the image for this type of power up. 
### 2. Update the SceneCtrl Class
In the SceneCtrl class, under the createPowerUp method, add a conditional statement to account for the paddle intersecting the new type of powerUp. Use the current objects/methods, or add a new method to the appropriate object, to represent your power up.
### 3. Place power up in a block
In the Block class, there is an instance variable titled availablePowerUps. Add the new power up type to this list, and the random number generator will automatically select your power up to be included in some blocks. 

## Block Type
### 1. Create a class for new block
Create a new class extending the Block class that contains specific methods/attributes specific to the new block.
### 2. Have new block appear in levels
To have the new block appear in levels, add block coordinates to the Levels class. Have these coordinates return to a block-specific array in the LevelCtrl class. Finally, replicate the loops for creating bounce/hit blocks in the SceneCtrl class.

# Major Design Choices

One of my major design choices was to create objects for the  Bouncer, Paddle, Life, Power Up, and a Block superclass/HitBlock and BounceBlock subclasses. These classes organize methods for getting/changing the display of the aforementioned objects, as well as methods for determining intersections, and in the case of blocks, whether a block can be removed. However, with my design as is,  I struggled in connecting these components in a cohesive, simple way. In the end, my SceneCtrl class ended up doing too much of the heavy-lifting by handling all interactions between Blocks/Bouncers and PowerUps/the Paddle. The order in which methods are called also creates a lot of unnecessary dependencies in my SceneCtrl() method that could have been eliminated with a more straightforward design. 

One design choice that I could have fleshed out better was how I organized my levels. Creating a level takes methods from 3 separate classes, and determining level features, such as power ups contained in blocks, come from a method embedded within the createBlocks() class in the SceneCtrl() class.  Ideally, to foster readability and flexibility, the Level Controller (LevelCtrl)  would create blocks and assign features such as power ups. 

Another design choice that could create more flexibility in adding new features is by creating a PowerUp superclass, and extending this class for the implementation of different power ups. Currently, there is one PowerUp class, which uses a conditional to determine which PowerUp image to use. This hinders readability and flexibility. 

# Assumptions
It is assumed that the user will follow instructions and hit the 'ENTER' key directly after the start screen. Hitting any other key will trigger a Null Pointer Exception, since my code assumes that a new level has been created (without ever having been initiated). 

It is also assumed that there are two-hit or less blocks. Blocks are determined randomly using the modulus operator, hence a block with more hits would need a different algorithm to determine what type of block is placed at a specific set of coordinates.

