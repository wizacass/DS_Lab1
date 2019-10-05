package edu.ktu.ds.lab1b.petrauskas;

import edu.ktu.ds.lab1b.util.LinkedList;

import java.util.Locale;

public class PhoneTest
{
    private LinkedList<Phone> phones;

    private void createPhones()
    {

    }

    private void run()
    {
        System.out.println("Hello world!");
        createPhones();
    }

    public static void main(String... args)
    {
        Locale.setDefault(new Locale("LT"));
        new PhoneTest().run();
        System.out.println("Done!");
    }
}
