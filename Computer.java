package com.bercoviciadrianpa2022lab3;

import java.util.HashMap;
import java.util.Random;

public class Computer extends Node
{
    private static HashMap<String,Computer> globalIpList = new HashMap<String,Computer>();
    private String IP;
    private float storageCapacityGB;

    Computer (float storageCapacityGB)
    {
        generateIP();
        this.storageCapacityGB = storageCapacityGB;
    }

    private void generateIP()
    {
        Random random = new Random();

        do
        {
            IP = "";
            for (int i = 1; i <= 3; i++)
            {
                int num = random.nextInt(128);
                String piece = String.format("%03d", num);
                IP += piece;

                if (i < 3)
                    IP += ".";
            }
        }while (globalIpList.containsKey( IP ) );

        globalIpList.put(IP, this);
    }

    @Override
    public String getIP()
    {
        return IP;
    }


    @Override
    public float getStorageCapacity() {
        return storageCapacityGB;
    }

    @Override
    public String toString()
    {
        return inNetworkNodeName + " - [computer - IP: " + IP + " - storage capacity: " + storageCapacityGB + "GB ]";
    }
}
