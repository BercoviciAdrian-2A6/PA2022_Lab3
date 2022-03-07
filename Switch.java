package com.bercoviciadrianpa2022lab3;

public class Switch extends Node
{
    @Override
    public String getIP() {
        return null;
    }

    @Override
    public float getStorageCapacity() {
        return -1;
    }

    @Override
    public String toString() {
        return inNetworkNodeName + " - [switch]";
    }
}
