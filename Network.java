package com.bercoviciadrianpa2022lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Network
{
    private ArrayList<Node> nodes = new ArrayList<>();
    private HashMap<String,Integer> nodeNameIndexHashmap = new HashMap<>();
    private VertexMap vertexMap;

    Network (String inputFileName)
    {
        File inputFile = new File("src/" + inputFileName);
        try
        {
            Scanner inputScanner = new Scanner(inputFile);

            int nodesNumber = inputScanner.nextInt();

            for (int i = 1; i <= nodesNumber; i++)
            {
                String nodeName = inputScanner.next();
                String nodeType = inputScanner.next();

                //this ensures the node names are unique
                if ( nodeNameIndexHashmap.containsKey(nodeName))
                    continue;

                if (nodeType.equals("COMPUTER"))
                {
                    float storageCapacity = inputScanner.nextFloat();
                    nodes.add( new Computer(this,storageCapacity) );
                }
                else if (nodeType.equals("ROUTER"))
                {
                    nodes.add( new Router(this) );
                }
                else if (nodeType.equals("SWITCH"))
                {
                    nodes.add( new Switch(this) );
                }

                int index = nodes.size() - 1;

                nodes.get( index ).setNodeName(nodeName);
                nodeNameIndexHashmap.put(nodeName, index);
            }

            //read adjacency
            while (inputScanner.hasNext())
            {
                String nodeA = inputScanner.next();
                String nodeB = inputScanner.next();
                float edgeCost = inputScanner.nextFloat();
                float failureProbability = inputScanner.nextFloat();

                int indexA = nodeNameIndexHashmap.get(nodeA);
                int indexB = nodeNameIndexHashmap.get(nodeB);

                nodes.get(indexA).addNeighbor( indexB, edgeCost, failureProbability );
                nodes.get(indexB).addNeighbor( indexA, edgeCost, failureProbability);
            }

            vertexMap = new VertexMap(this, nodes);

            //vertexMap.printAdjacencyMatrix(true);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Node> getDijkstraSafestPath(Node startNode, Node endNode)
    {
        //since probability of failure cannot be negative, it is safe to implement Dijkstra's algorithm
        //Failure probability will be considered an edge cost, and we will try to minimize it
        ArrayList<Float> currentSafest = new ArrayList<>();
        ArrayList<Node> reachedFromNode = new ArrayList<>();
        HashMap<String, Node> visitationHashmap = new HashMap<String, Node>();

        int numberOfNodes = nodes.size();

        int startNodeIndex = getNodeIndex(startNode);

        for (int i = 0; i < numberOfNodes; i++ )
        {
            currentSafest.add(9999999f);
            reachedFromNode.add(null);
        }

        currentSafest.set(startNodeIndex, 0f);
        reachedFromNode.set(startNodeIndex, startNode);

        while (visitationHashmap.containsKey(endNode.getNodeName()) == false)//while the end destination has not yet been visited
        {
            Node currentNode;

            int minIndex = -1;
            float minCost = 0;

            for (int i = 0; i < currentSafest.size(); i++)
            {
                if ( visitationHashmap.containsKey( nodes.get(i).getNodeName() ) )
                {
                    //the node has already been visited
                    continue;
                }

                if (minIndex == -1 || minCost > currentSafest.get(i))
                {
                    minIndex = i;
                    minCost = currentSafest.get(i);
                }
            }
            if (minIndex == -1)
            {
                //no valid nodes were found
                break;
            }

            currentNode = nodes.get(minIndex);

            ArrayList<Integer> neighbors = currentNode.getNeighbourIndexes();

            float currentCostFromSource = currentSafest.get(minIndex);

            for (int i = 0; i < neighbors.size(); i++)
            {
                int adjacentNodeIndex = neighbors.get(i);

                if (visitationHashmap.containsKey( nodes.get( adjacentNodeIndex ).getNodeName() ))
                    continue;
                //check if adjacent node has been visited

                float newCost = currentCostFromSource + currentNode.getEdgeCostAtIndex(i);

                if (newCost < currentSafest.get(adjacentNodeIndex))
                {
                    currentSafest.set(adjacentNodeIndex, newCost);
                    reachedFromNode.set(adjacentNodeIndex, currentNode);
                }
            }

            visitationHashmap.put(currentNode.getNodeName(), currentNode);
        }

        ArrayList<Node> safestPath = new ArrayList<>();

        Node pathPoint = endNode;

        while (pathPoint != startNode)
        {
            safestPath.add(pathPoint);
            pathPoint = reachedFromNode.get( getNodeIndex(pathPoint) );
        }

        System.out.println("Minimal path cost: " + currentSafest.get( getNodeIndex( endNode ) ));

        safestPath.add(startNode);

        for (int i = safestPath.size() - 1; i >= 0; i--)
        {
            System.out.print( safestPath.get(i).nodeName );

            if (i != 0)
                System.out.print( " >> " );
        }

        return safestPath;
    }

    public int getNodeIndex(Node node)
    {
        return nodeNameIndexHashmap.get(node.getNodeName());
    }

    private void addNode( Node node )
    {
        nodes.add(node);
    }

    public ArrayList<Node> getIdentifiableNodes()
    {
        ArrayList<Node> identifiable = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++)
        {
            if (nodes.get(i).getIP() != null)
                identifiable.add(nodes.get(i));
        }

        return identifiable;
    }

    public void printNetwork()
    {
        System.out.println("Network nodes: ");
        for (int i = 0; i < nodes.size(); i++)
        {
            System.out.println( nodes.get(i) );
        }

        System.out.println("Vertex degrees: ");

        for (int i = 0; i < nodes.size(); i++)
        {
            System.out.print(nodes.get(i).getNodeDegree() + " ");
        }

        System.out.print("\n");

        System.out.println("Identifiable nodes: ");

        ArrayList<Node> identifiable = getIdentifiableNodes();

        for (int i = 0; i < identifiable.size(); i++)
        {
            System.out.println(identifiable.get(i));
        }

        System.out.print("\n");

        System.out.println("Simplified adjacency matrix: \n");

        vertexMap.printAdjacencyMatrix(true);

        System.out.print("\n");

        System.out.println("Edge costs: \n");

        vertexMap.printAdjacencyMatrix(false);

        System.out.print("\n");

        System.out.println("Failure probability: \n");

        vertexMap.printFailureProbability();

        System.out.print("\n");

        System.out.println("Safest path from first to last: \n");

        getDijkstraSafestPath( nodes.get(0), nodes.get( nodes.size() - 1 ) );
    }
}
