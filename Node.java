package com.bercoviciadrianpa2022lab3;

import java.util.ArrayList;

public abstract class Node implements Identifiable, Storage
{
    protected String inNetworkNodeName;
    protected ArrayList<Node> neighbours = new ArrayList<>();
    protected ArrayList<Integer> edgeCost = new ArrayList<>();

    public String getInNetworkNodeName() {
        return inNetworkNodeName;
    }

    public void setInNetworkNodeName(String inNetworkNodeName) {
        this.inNetworkNodeName = inNetworkNodeName;
    }

    @Override
    public abstract String toString();
}
