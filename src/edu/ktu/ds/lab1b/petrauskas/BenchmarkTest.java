package edu.ktu.ds.lab1b.petrauskas;

import edu.ktu.ds.lab1b.demo.SimpleBenchmark;
import edu.ktu.ds.lab1b.util.Ks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class BenchmarkTest
{
    private int[] counts = { 16, 64, 1024, 4096, 24_000, 100_000, 1_000_000 };
    private ArrayList<Double> powList = new ArrayList<Double>();
    private ArrayList<Double> productList = new ArrayList<Double>();
    private Random rnd = new Random(2017);

    private void run()
    {
        for(int count: counts)
        {
            powList = new ArrayList<>();
            productList = new ArrayList<>();
            testPower(count);
            testIndex(count);
        }

        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("Total memory: = " + memTotal);
        Ks.oun("");
    }

    private void testPower(int count)
    {
        Ks.oun(String.format("Test with %d elements.", count));

        long loopStartTime = System.nanoTime();
        for (int i = 0; i < count; i++) { }
        long loopEndTime = System.nanoTime();
        double loopTime = (loopEndTime - loopStartTime) / 1e9;

        long powStartTime = System.nanoTime();
        for (int i = 0; i < count; i++)
        {
            var product = Math.pow(i, 2);
            powList.add(product);
        }
        long powEndTime = System.nanoTime();
        double powTime = (loopEndTime - loopStartTime) / 1e9;

        long productStartTime = System.nanoTime();
        for (int i = 0; i < count; i++)
        {
            double product = i * i;
            productList.add(product);
        }
        long productEndTime = System.nanoTime();
        double productTime = (loopEndTime - loopStartTime) / 1e9;

        Ks.oun(String.format("Empty loop time:      %f", loopTime));
        Ks.oun(String.format("Power time:           %f", powTime));
        Ks.oun(String.format("Multiplication time:  %f", productTime));

        Ks.oun("");
    }

    private void testIndex(int count)
    {
        var list = new LinkedList<Integer>();

        for (int i = 0; i < count; i++)
        {
            list.add(i);
        }

        long indexTimeBefore = System.nanoTime();
        int index1 = list.indexOf(rnd.nextInt(count));
        long indexTimeAfter = System.nanoTime();

        long lastIndexTimeBefore = System.nanoTime();
        int index2 = list.lastIndexOf(rnd.nextInt(count));
        long lastIndexTimeAfter = System.nanoTime();

        double elapsedIndex = (indexTimeAfter - indexTimeBefore) / 1e9;
        double elapsedLastIndex = (lastIndexTimeAfter - lastIndexTimeBefore) / 1e9;

        Ks.oun(String.format("Time for indexOf:     %f", elapsedIndex));
        Ks.oun(String.format("Time for lastIndexOf: %f", elapsedLastIndex));
        Ks.oun("");
    }

    public static void main(String[] args)
    {
        new BenchmarkTest().run();
        Ks.oun("Benchmarking done!");
    }
}
