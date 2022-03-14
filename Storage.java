package com.bercoviciadrianpa2022lab3;

public interface Storage
{
    default float translateCapacity(float input, String measurementUnit)
    {
        float output = 0;

        switch (measurementUnit)
        {
            case "TB":{ output = input * 0.001F; break;}
            case "GB":{  output =  input;}
            case "MB":{  output =  input * 1000; break;}
            case "KB":{  output =  input * 1000000; break;}
            case "B":{  output =  input * 1000000000; break;}
        }

        return output;
    }

    default String getStorageCapacity(String measurementUnit)
    {
        return "No storage capacity";
    }
}
