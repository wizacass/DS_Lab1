package edu.ktu.ds.lab1b.petrauskas;

import edu.ktu.ds.lab1b.util.Ks;
import edu.ktu.ds.lab1b.util.Parsable;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Phone implements Parsable<Phone>
{
    final static private int minYear = 2014;

    final static private double minPrice = 100.0;
    final static private double maxPrice = 1500.0;

    private String _manufacturer;
    private String _model;
    private int _batteryCapacity;
    private double _makeYear;
    private double _price;
    private double _diagonalScreenSize;
    private Boolean _hasHeadphoneJack;

    public Phone() { }

    public Phone(
            String manufacturer, String model, int batteryCapacity,
            double makeYear, double price, double diagonalScreenSize,
            Boolean hasHeadphoneJack)
    {
        this._manufacturer = manufacturer;
        this._model = model;
        this._batteryCapacity = batteryCapacity;
        this._makeYear = makeYear;
        this._price = price;
        this._diagonalScreenSize = diagonalScreenSize;
        this._hasHeadphoneJack = hasHeadphoneJack;
    }

    public static int getMinYear()
    {
        return minYear;
    }

    public static double getMinPrice()
    {
        return minPrice;
    }

    public static double getMaxPrice()
    {
        return maxPrice;
    }

    public String getManufacturer()
    {
        return _manufacturer;
    }

    public String getModel()
    {
        return _model;
    }

    public int getBatteryCapacity()
    {
        return _batteryCapacity;
    }

    public double getMakeYear()
    {
        return _makeYear;
    }

    public double getPrice()
    {
        return _price;
    }

    public double getDiagonalScreenSize()
    {
        return _diagonalScreenSize;
    }

    public Boolean getHasHeadphoneJack()
    {
        return _hasHeadphoneJack;
    }

    public void setPrice(double price)
    {
        this._price = price;
    }

    @Override
    public void parse(String dataString)
    {
        try
        {
            var data = new Scanner(dataString);
            _manufacturer = data.next();
            _model = data.next();
            _batteryCapacity = data.nextInt();
            _makeYear = data.nextDouble();
            _price = data.nextDouble();
            _diagonalScreenSize = data.nextDouble();
            _hasHeadphoneJack = data.nextBoolean();
            setPrice(data.nextDouble());
        }
        catch (InputMismatchException e)
        {
            Ks.ern(String.format("Invalid Phone data string -> %s", dataString));
        }
        catch (NoSuchElementException e)
        {
            Ks.ern(String.format("Missing data on Phone -> %s", dataString));
        }
    }

    @Override
    public int compareTo(Phone other)
    {
        if (this._price == other._price)
        {
            return Double.compare(this._makeYear, other._makeYear);
        }
        else if (this._price < other._price) { return 1; }
        else { return -1; }
    }
}
