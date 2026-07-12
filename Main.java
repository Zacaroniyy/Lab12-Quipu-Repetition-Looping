/********************************************
*	AUTHOR:	Sean Leblanc
* COLLABORATORS:
*	LAST MODIFIED:
********************************************/

/********************************************
*	Quipu
*********************************************
*	PROGRAM DESCRIPTION:
*	A user will enter a number between 0 and 999
*	and this program will display the number
*	as a quipu.
*********************************************/

import java.util.Scanner;

public class Main
{
    /***** CONSTANT SECTION *****/

    // Prints the specified number of knots
    public static void printKnots(int knots)
    {
        for(int i = 0; i < knots; i++)
        {
            UtilityBelt.printCentered(30, "*");
        }
    }

    // Prints the complete quipu
    public static void printQuipu(int hundreds, int tens, int ones)
    {
        System.out.println("\nYour quipu:");

        UtilityBelt.printCentered(30, "___");
        UtilityBelt.printCentered(30, "|");

        printKnots(hundreds);

        UtilityBelt.printCentered(30, "|");

        printKnots(tens);

        UtilityBelt.printCentered(30, "|");

        printKnots(ones);

        UtilityBelt.printCentered(30, "|");
        UtilityBelt.printCentered(30, "\u203E");
    }

    public static void main(String[] args)
    {
        /***** DECLARATION SECTION *****/
        Scanner input = new Scanner(System.in);

        int number;
        int hundreds;
        int tens;
        int ones;

        char again = 'Y';

        /***** INTRO SECTION *****/
        System.out.println("Hello! This program turns any whole number between 0 and 999 into a digital Quipu.");

        /***** PROCESSING SECTION *****/
        while(again == 'Y' || again == 'y')
        {
            // Get a valid number
            while(true)
            {
                System.out.print("\nPlease enter a number between 0 and 999: ");
                number = input.nextInt();

                if(number >= 0 && number <= 999)
                {
                    break;
                }

                System.out.println("ERROR: please enter value between 0 - 999");
            }

            // Find each digit
            hundreds = number / 100;
            tens = (number % 100) / 10;
            ones = number % 10;

            /***** OUTPUT SECTION *****/
            System.out.println("Hundreds = " + hundreds);
            System.out.println("Tens     = " + tens);
            System.out.println("Ones     = " + ones);

            // Print the quipu
            printQuipu(hundreds, tens, ones);

            // Ask if user wants another
            System.out.print("\nWould you like to make another quipu? [Y/N]: ");
            again = input.next().charAt(0);
        }

        System.out.println("\nGoodbye!");

        input.close();
    }
}
