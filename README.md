[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/J_c8sizy)
# MassiveMotion
CS 245 Project 02

# Project 2 - Massive Motion

This project simulates celestial bodies moving across a canvas. There's a red star in the center and black comets that appear at the edges and move around.

## What I Built

I made four different list implementations from scratch:
- **ArrayList** - Uses an array that grows when it fills up
- **LinkedList** - Nodes connected with next pointers
- **DoublyLinkedList** - Nodes with next and prev pointers
- **DummyHeadLinkedList** - Like LinkedList but with a dummy node at the start

The simulation lets you switch between these lists to see how they all work the same.

## Files

- ArrayList.java
- LinkedList.java
- DoublyLinkedList.java
- DummyHeadLinkedList.java
- List.java (the interface we implement)
- Config.java (reads the config file)
- MassiveMotion.java (main program)
- MassiveMotion.txt (settings file)

## Running It

Go into the src folder and compile:
```
javac *.java
```

Then run:
```
java MassiveMotion
```

You should see a window open with the star and comets moving around.

## Changing Settings

Edit MassiveMotion.txt to change things:

- `list` - which list type to use (arraylist, single, double, or dummyhead)
- `timer_delay` - how fast it updates (lower = faster)
- `window_size_x` and `window_size_y` - size of the window
- `gen_x` and `gen_y` - how often comets appear
- `body_size` - how big the comets are
- `body_velocity` - how fast they move

The star properties can also be changed (position, size, velocity).

## How It Works

Every 75 milliseconds (by default), the timer fires and the program:
1. Moves all the bodies based on their velocity
2. Removes any that went off the screen
3. Maybe creates a new comet at a random edge
4. Redraws everything

The comets spawn at the edges with random velocities pointing inward. The star stays in the middle.

## Why ArrayList is Best Here

For this simulation, ArrayList works better than the linked lists because:
- We loop through every body to draw them each frame (need fast access)
- We add new comets to the end a lot (ArrayList is fast at this)
- We don't remove things from the middle very often

The linked lists would be better if we were doing lots of insertions in the middle of the list.

## What I Learned

The hardest part was making sure to iterate backwards when removing bodies. If you go forwards and remove something, all the indices shift and you skip elements. Going backwards avoids that problem.

I also learned that the dummy head node makes the code cleaner because you don't need special cases for adding/removing at the beginning of the list.

## Requirements

Just need Java installed. Uses standard Java libraries (Swing for the GUI).