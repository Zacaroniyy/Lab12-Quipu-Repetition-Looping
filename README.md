# Lab 12 - Quipu + Repetition/Looping:

## Create a Digital Quipu Program!

_Learning Objectives: Demonstrate an understanding of repetition through for loops. Demonstrate an understanding of repetition through while loops._


## Introduction:

Check out this video: [Threads That Speak](https://youtu.be/AmPyz1kCbOw)

The Inca Empire, which existed from 1438 - 1533, was vast and advanced. It spread over an
area which stretched from what is now the northern border of Ecuador to Chile. The Inca people
numbered around 12 million but they were from many different ethnic groups and spoke about 20
different languages. The civilization had reached a high level of sophistication with a remarkable
system of roads, agriculture, textile design, and administration. The Inca did not have a formalized
system of writing, but they developed an accurate system of counting and recording numerical
information. It involved an organized pattern of knots in strings called a quipu.

![Picture of a quipu](Inca_Quipu.jpeg)

A quipu was a storage device for numerical quantities. It consists of strings which were knotted
to represent numbers. A number was represented by knots in the string, using a positional base 10
representation. If the number 586 was to be recorded on the string then six touching knots were
placed near the free end of the string, a space was left, then eight touching knots for the 10s, another space, and finally 5 touching knots for the 100s.

![Example quipu showing 586](https://mathshistory.st-andrews.ac.uk/Diagrams/quipu586.gif)


## Lab Instructions:

You will be constructing an Inca Quipu in a Java program, such that a user may type any number from 0 to 999 (inclusive) and the value will be expressed in a visual quipu.

### Step 1
Write a static method that will take an integer input and print the appropriate number of vertical knots represented by asterisks. Use the method `printCentered()` from `UtilityBelt` class for the output. For example, invoking the method with the input 3 should output:

```
              *
              *
              *
```

*Note: The width of your Quipu should be 30. This will be the first parameter to `printCentered()`.*

### Step 2
Next, write another static method that will take the hundreds, tens and ones place of a number as inputs and print the quipu. Invoke your first method and `printCentered()` to help you format the output. For example, inputs 2,3,1 should produce:

```
Your quipu:
             ___
              |
              *
              *
              |
              *
              *
              *
              |
              *
              |
              ‾
```

*Note: The top is underscores and the bottom is an overline (`\u203E`).*

### Step 3
Figure out an algorithm to split up a number, for example 352, into the hundreds: 3, tens: 5, and ones: 2 places.

### Step 4
In the `main()` method, write the program for user interaction. First, print an Intro message. Then prompt the user to enter a number between 0 and 999. If the user types in a number < 0 or > 999, an error message should be displayed and they should be asked to enter a number again until they enter a valid input. After implementing the algorithm to find the hundreds, tens, and ones places from step 3, call your method to print the quipu. After quipu is displayed, allow the user to choose to enter another number to convert to a quipu, repeating as many times as they wish. The console interaction should look like:

    Hello! This program turns any whole number between 0 and 999 into a digital Quipu.

    Please enter a number between 0 and 999: 1345
    ERROR: please enter value between 0 - 999

    Please enter a number between 0 and 999: 234
    Hundreds   = 2
    Tens       = 3
    Ones       = 4

    Your quipu:
                ---
                 |
                 *
                 *
                 |
                 *
                 *
                 *
                 |
                 *
                 *
                 *
                 *
                 |
                 ‾

    Would you like to make another quipu? [Y/N]: y

    Please enter a number between 0 and 999: -12
    ERROR: please enter value between 0 - 999

    Please enter a number between 0 and 999: 12
    Hundreds   = 0
    Tens       = 1
    Ones       = 2

    Your quipu:
                ---
                 |
                 |
                 *
                 |
                 *
                 *
                 |
                 ‾

    Would you like to make another quipu? [Y/N]: N

    Goodbye!

_Credits: This lab is an adaptation of one created by Mike Paulding._
