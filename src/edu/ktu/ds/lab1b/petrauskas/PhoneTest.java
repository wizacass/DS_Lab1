package edu.ktu.ds.lab1b.petrauskas;

import edu.ktu.ds.lab1b.util.Ks;
import edu.ktu.ds.lab1b.util.LinkedList;

import java.util.Locale;

public class PhoneTest
{
    private LinkedList<Phone> phones;

    private PhoneTest()
    {
        phones = new LinkedList<>();
    }

    private void createPhones()
    {
        phones.add(new Phone(
                "Samsung",
                "Galaxy S8",
                3000,
                2017,
                400,
                5.8,
                true
        ));

        phones.add(new Phone(
                "Samsung",
                "Galaxy S10",
                3400,
                2019,
                570,
                6.1,
                true
        ));

        phones.add(new Phone(
                "Apple",
                "iPhone 11 Pro",
                3046,
                2019,
                1000,
                5.8,
                false
        ));

        String[] phoneInfos = {
            "Xiaomi Redmi Note 8 Pro 4500 2019 220.0 6.53 true",
            "OnePlus 7T 3800 2019 600.0 6.55 false"
        };

        for (String phoneData:phoneInfos)
        {
            var ph = new Phone(phoneData);
            if(ph.get_status() == true)
            {
                phones.add(ph);
            }
        }
    }

    private void listPhones()
    {
        for (Phone phone:phones)
        {
            Ks.oun(phone.toString());
        }
    }

    private void run()
    {
        createPhones();
        listPhones();
    }

    public static void main(String... args)
    {
        new PhoneTest().run();
        Ks.oun("Phone test complete!");
    }
}
