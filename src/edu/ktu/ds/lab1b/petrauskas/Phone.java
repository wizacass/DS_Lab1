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
    private double _cameraResolution;
    private Boolean _hasHeadphoneJack;
    private Boolean _hasUsbC;

    public Phone() { }

    public Phone(
            String manufacturer, String model, int batteryCapacity,
            double makeYear, double price, double diagonalScreenSize,
            double cameraResolution, Boolean hasHeadphoneJack, Boolean hasUsbC)
    {
        this._manufacturer = manufacturer;
        this._model = model;
        this._batteryCapacity = batteryCapacity;
        this._makeYear = makeYear;
        this._price = price;
        this._diagonalScreenSize = diagonalScreenSize;
        this._cameraResolution = cameraResolution;
        this._hasHeadphoneJack = hasHeadphoneJack;
        this._hasUsbC = hasUsbC;
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

    public double getCameraResolution()
    {
        return _cameraResolution;
    }

    public Boolean getHasHeadphoneJack()
    {
        return _hasHeadphoneJack;
    }

    public Boolean getHasUsbC()
    {
        return _hasUsbC;
    }

    public void setPrice(double price)
    {
        this._price = price;
    }

    @Override
    public void parse(String dataString)
    {
        try
        {   // ed - tai elementarūs duomenys, atskirti tarpais
            var ed = new Scanner(dataString);
            _manufacturer = ed.next();
            _model = ed.next();
            _batteryCapacity = ed.nextInt();
            _makeYear = ed.nextDouble();
            _price = ed.nextDouble();
            _diagonalScreenSize = ed.nextDouble();
            _cameraResolution = ed.nextDouble();
            _hasHeadphoneJack = ed.nextBoolean();
            _hasUsbC = ed.nextBoolean();
            setPrice(ed.nextDouble());
        }
        catch (InputMismatchException e)
        {
            Ks.ern("Blogas duomenų formatas apie auto -> " + dataString);
        }
        catch (NoSuchElementException e)
        {
            Ks.ern("Trūksta duomenų apie auto -> " + dataString);
        }
    }

    @Override
    public int compareTo(Phone o)
    {
        return 0;
    }
}
