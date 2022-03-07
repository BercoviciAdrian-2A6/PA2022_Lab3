package com.bercoviciadrianpa2022lab3;

import java.util.ArrayList;

public class Network
{
    private ArrayList<Node> nodes = new ArrayList<>();

    public void addNode( Node node )
    {
        nodes.add(node);
        node.setInNetworkNodeName( "Vert " + nodes.size() );
    }

    public void printNetwork()
    {
        for (int i = 0; i < nodes.size(); i++)
        {
            System.out.println( nodes.get(i) );
        }
    }
}
