package edu.ktu.ds.lab1b.petrauskas;

import edu.ktu.ds.lab1b.util.LinkedList;

import java.util.Locale;
import java.util.Random;

public class LinkedListTest
{
    private LinkedList<Integer> intList;
    private Random rnd;

    private LinkedListTest()
    {
        intList = new LinkedList<>();
        rnd = new Random();
        rnd.setSeed(2017);
    }

    private void run()
    {
        createList(rnd.nextInt(20));

        System.out.println();
        printList("Before sorting:");

        // - Adding
        System.out.println(intList.add(0, 16) ? "16 added successfully." : "16 not added.");
        System.out.println(intList.add(3, 64) ? "64 added successfully." : "64 not added.");
        System.out.println(intList.add(15, 32) ? "32 added successfully." : "32 not added.");
        System.out.println(intList.add(-5, 8) ? "8 added successfully." : "8 not added.");
        printList("After adding the elements:");

        // - Setting
        Integer oldVal = intList.set(0, 1);
        System.out.printf("%d set at index 0 set to 1.%n", oldVal);

        for (int i = 0; i < 5; i ++)
        {
            boolean success = true;
            int position = rnd.nextInt(16);
            int newVal = rnd.nextInt(1024);

            try
            {
                oldVal = intList.set(position, newVal);
            }
            catch (Exception ex)
            {
                System.out.printf("Cannot set index %d. %s%n", position, ex.getMessage());
                success = false;
            }

            if (success)
            {
                System.out.printf("%d at index %d set to %d.%n", oldVal, position, newVal);
            }
        }
        printList("After setting:");

        // - Removing
        for (int i = 0; i < 3; i++)
        {
            boolean success = true;
            int position = rnd.nextInt(20);

            Integer removedValue = null;
            try
            {
                removedValue = intList.remove(position);
            }
            catch (Exception ex)
            {
                System.out.printf("Cannot remove index %d. %s%n", position, ex.getMessage());
                success = false;
            }

            if (success)
            {
                System.out.printf("Removed value %d at index %d.%n", removedValue, position);
            }
        }
        printList("After removing:");

        intList.sortBubble();
        printList("After sorting:");
    }

    private void createList(int size)
    {
        for (int i = 0; i < size; i++)
        {
            Integer number = rnd.nextInt(2048);
            System.out.printf("Added %d to the list.%n", number);

            intList.add(number);
        }
    }

    private void printList(String header)
    {
        System.out.printf("%n%s%n", header);
        for (Integer data : intList)
        {
            System.out.printf("%s ", data.toString());
        }
        System.out.printf("%nTotal elements: %d.%n%n", intList.size());
    }

    public static void main(String... args)
    {
        Locale.setDefault(new Locale("LT"));
        var test = new LinkedListTest();
        test.run();

        System.out.println("\nDone!");
    }
}
