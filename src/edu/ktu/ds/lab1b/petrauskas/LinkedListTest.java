package edu.ktu.ds.lab1b.petrauskas;

import edu.ktu.ds.lab1b.util.Ks;
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

        Ks.oun("");
        printList("Before sorting:");

        addElements();
        setElements();
        removeElements();

        intList.sortBubble();
        printList("After sorting:");
    }

    private void createList(int size)
    {
        for (int i = 0; i < size; i++)
        {
            Integer number = rnd.nextInt(2048);
            Ks.oun(String.format("Added %d to the list.", number));

            intList.add(number);
        }
    }

    private void addElements()
    {
        Ks.oun(intList.add(0, 16) ? "16 added successfully." : "16 not added.");
        Ks.oun(intList.add(3, 64) ? "64 added successfully." : "64 not added.");
        Ks.oun(intList.add(15, 32) ? "32 added successfully." : "32 not added.");
        Ks.oun(intList.add(-5, 8) ? "8 added successfully." : "8 not added.");

        int amount = rnd.nextInt(4);
        for (int i = 0; i < amount; i ++)
        {
            Integer number = rnd.nextInt(128);
            intList.addLast(number);
            Ks.oun(String.format("Added %d to the end of the list.", number));
        }

        Ks.oun("");
        printList("After adding the elements:");
    }

    private void setElements()
    {
        Integer oldVal = intList.set(0, 1);
        Ks.oun(String.format("%d set at index 0 set to 1.", oldVal));

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
                Ks.ern(String.format("Cannot set index %d. %s", position, ex.getMessage()));
                success = false;
            }

            if (success)
            {
                Ks.oun(String.format("%d at index %d set to %d.", oldVal, position, newVal));
            }
        }
        printList("After setting:");
    }

    private void removeElements()
    {
        for (int i = 0; i < 3; i++)
        {
            boolean success = true;
            int position = rnd.nextInt(16);

            Integer removedValue = null;
            try
            {
                removedValue = intList.remove(position);
            }
            catch (Exception ex)
            {
                Ks.ern(String.format("Cannot remove index %d. %s", position, ex.getMessage()));
                success = false;
            }

            if (success)
            {
                Ks.oun(String.format("Removed value %d at index %d.", removedValue, position));
            }
        }

        Ks.oun("");
        printList("After removing:");
    }

    private void printList(String header)
    {
        Ks.oun(header);
        var sb = new StringBuilder();
        for (Integer data : intList)
        {
            sb.append(String.format("%s ", data.toString()));
        }
        Ks.oun(sb.toString());
        Ks.oun(String.format("Total elements: %d.", intList.size()));
        Ks.oun("");
    }

    public static void main(String... args)
    {
        Locale.setDefault(new Locale("LT"));
        var test = new LinkedListTest();
        test.run();

        Ks.oun("Done!");
    }
}
