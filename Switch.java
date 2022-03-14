package com.bercoviciadrianpa2022lab3;

public class Switch extends Node
{
    Switch(Network network) {
        super(network);
    }

    @Override
    public String toString() {
        return nodeName + " - [switch - " + getStorageCapacity("GB") + "]";
    }
}
