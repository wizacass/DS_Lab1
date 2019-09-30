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
        System.out.println(intList.add(0, 16) ? "16 added successfully" : "16 not added");
        System.out.println(intList.add(3, 64) ? "64 added successfully" : "64 not added");
        System.out.println(intList.add(15, 32) ? "32 added successfully" : "32 not added");
        System.out.println(intList.add(-5, 8) ? "8 added successfully" : "8 not added");
        printList("After adding the elements:");

        // - Setting
        Integer oldVal = intList.set(0, 1);
        System.out.println(oldVal + " set at index 0 set to 1");

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
                System.out.println("Cannot set index " + position + ". " + ex.getMessage());
                success = false;
            }

            if (success)
            {
                System.out.println(oldVal + " set at index " + position + " set to " + newVal);
            }
        }
        printList("After setting:");

        // - Removing
        // TODO: Implement removing test

        intList.sortBubble();
        printList("After sorting:");
    }

    private void createList(int size)
    {
        for (int i = 0; i < size; i++)
        {
            Integer number = rnd.nextInt(2048);
            System.out.println("Adding " + number + " to the list");

            intList.add(number);
        }
    }

    private void printList(String header)
    {
        System.out.println();
        System.out.println(header);
        for (Integer data : intList)
        {
            System.out.print(data.toString() + ' ');
        }
        System.out.println();
        System.out.println("Total: " + intList.size());
        System.out.println();
    }

    public static void main(String... args)
    {
        Locale.setDefault(new Locale("LT"));
        var test = new LinkedListTest();
        test.run();

        System.out.println("\nDone!");
    }
}
