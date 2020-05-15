package com.example.travelers_calculator.toolbar.history;

//this class will take care of getting the data from each fragment's result and storing it for further processing
//and setting the calculation to the textview when that gets called.
public class HistoryData
{
    //either a double or a string?
    String calculation;
    String subText;

    public HistoryData(String mCalculation, String msubText)
    {
        this.calculation = mCalculation;
        this.subText = msubText;

    }

    public String getCalculation() {
        return calculation;
    }
    public String getSubText(){ return subText;}

    public void setCalculation(String mCalculation)
    {
        this.calculation = mCalculation;
    }

    public void setSubText(String mSubtext)
    {
        this.subText = subText;
    }
}
