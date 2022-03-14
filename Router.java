package com.bercoviciadrianpa2022lab3;

public class Router extends Node
{

    Router(Network network) {
        super(network);
    }

    @Override
    public String toString() {
        return nodeName + " - [router - " + getStorageCapacity("GB") + "]";
    }
}
