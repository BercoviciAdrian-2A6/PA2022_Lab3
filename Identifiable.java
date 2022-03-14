package com.bercoviciadrianpa2022lab3;

public interface Identifiable
{
    default String getIP()
    {
        return null;
    }
}
