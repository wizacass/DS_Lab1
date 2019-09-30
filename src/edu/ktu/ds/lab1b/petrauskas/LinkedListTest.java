package edu.ktu.ds.lab1b.petrauskas;

import edu.ktu.ds.lab1b.util.LinkedList;

import java.util.Locale;
import java.util.Random;

public class LinkedListTest
{
    private LinkedList<Integer> intList;

    private LinkedListTest()
    {
        intList = new LinkedList<Integer>();
    }

    private void run()
    {
        createList(10);
        System.out.println();
        printList("Before sorting: ");
        intList.sortBubble();
        printList("After sorting:  ");

        // - Adding
        System.out.println(intList.add(0, 16) ? "16 added successfully" : "16 not added");
        System.out.println(intList.add(3, 64) ? "64 added successfully" : "64 not added");
        System.out.println(intList.add(15, 32) ? "32 added successfully" : "32 not added");
        System.out.println(intList.add(-5, 8) ? "8 added successfully" : "8 not added");
        printList("After adding the elements: ");

        // - Setting
        // TODO: Implement setting test

        // - Removing
        // TODO: Implement removing test
    }

    private void createList(int size)
    {
        Random rnd = new Random();
        rnd.setSeed(2017);
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
