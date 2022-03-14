package com.bercoviciadrianpa2022lab3;

import java.util.ArrayList;

public abstract class Node implements Identifiable, Storage
{
    /**
     * nodeName must always be unique
     */
    protected Network network;
    protected String nodeName;
    protected ArrayList<Integer> neighbourIndexes = new ArrayList<>();
    protected ArrayList<Float> edgeCost = new ArrayList<>();
    protected ArrayList<Float> failureProbability = new ArrayList<>();

    Node ( Network network )
    {
        this.network = network;
    }

    public void addNeighbor(int index, float cost, float failureProbability)
    {
        for (int i = 0; i < neighbourIndexes.size(); i++)
        {
            //since this is not a multi-graph, no repetitions can be found in the adjacency list
            if (neighbourIndexes.get(i) == index)
                return;
        }

        neighbourIndexes.add(index);
        edgeCost.add(cost);
        this.failureProbability.add(failureProbability);
    }

    public int getNodeDegree()
    {
        return neighbourIndexes.size();
    }

    public int getNeighborAtIndex(int index)
    {
        return neighbourIndexes.get(index);
    }

    public float getEdgeCostAtIndex(int index)
    {
        return edgeCost.get(index);
    }

    public float getFailureProbabilityAtIndex(int index)
    {
        return failureProbability.get(index);
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public ArrayList<Integer> getNeighbourIndexes() {
        return neighbourIndexes;
    }

    @Override
    public abstract String toString();
}
