package com.bercoviciadrianpa2022lab3;

import java.util.ArrayList;

/**
 * This class serves as a dynamic adjacency matrix
 * Since travel time (edge cost) cannot be negative in this case -
 * if two nodes are not adjacent the travel time will be set to -1
 * else the travel time will be a non-null positive float
 */

public class VertexMap
{
    private class MatrixColumn
    {
        private ArrayList<Float> edgeCosts = new ArrayList<>();
        private ArrayList<Float> failureProbability = new ArrayList<>();

        MatrixColumn (int nodesNumber)
        {
            for (int i = 0; i < nodesNumber; i++)
            {
                edgeCosts.add( -1.0f );
                failureProbability.add( -1.0f );
            }
        }

        private float getEdgeCostAtLine(int lineIndex)
        {
            return edgeCosts.get(lineIndex);
        }

        private float getFailureProbabilityAtLine(int lineIndex)
        {
            return failureProbability.get(lineIndex);
        }

        private void setEdge(int lineIndex, float edgeValue, float failureProbability)
        {
            edgeCosts.set(lineIndex, edgeValue);
            this.failureProbability.set(lineIndex, failureProbability);
        }
    }

    private Network network;
    private ArrayList<MatrixColumn> adjacencyMatrix = new ArrayList<>();

    VertexMap(Network network, ArrayList<Node> nodes)
    {
        this.network = network;

        for (int i = 0; i < nodes.size(); i++)
        {
            adjacencyMatrix.add( new MatrixColumn( nodes.size() ) );
        }

        //since this is an undirected graph, we could get away with computing just half of the adjacency matrix
        //but for argument's sake the entire matrix will be generated normally

        for (int i = 0; i < nodes.size(); i++)
        {
            for (int iNeighbor = 0; iNeighbor < nodes.get(i).getNodeDegree(); iNeighbor++)
            {
                int neighborIndex = nodes.get(i).getNeighborAtIndex(iNeighbor);
                float neighborCost = nodes.get(i).getEdgeCostAtIndex(iNeighbor);
                Float failureProbability = nodes.get(i).getFailureProbabilityAtIndex(iNeighbor);
                setAdjacencyMatrixAt(i, neighborIndex, neighborCost, failureProbability);
            }
        }
    }

    public float getAdjacencyMatrixAt(int line, int column)
    {
        return adjacencyMatrix.get(column).getEdgeCostAtLine(line);
    }

    public float geFailureProbabilityAt(int line, int column)
    {
        return adjacencyMatrix.get(column).getFailureProbabilityAtLine(line);
    }

    public void setAdjacencyMatrixAt(int line, int column, float edgeCost, float failureProbability)
    {
        adjacencyMatrix.get(column).setEdge(line, edgeCost, failureProbability);
    }

    public void printAdjacencyMatrix(boolean simplifiedView)
    {

        int matrixSize = adjacencyMatrix.size();

        for (int i = 0; i < matrixSize; i++)
        {
            for (int j = 0; j < matrixSize; j++)
            {
                float edgeCost = getAdjacencyMatrixAt(i,j);

                if (simplifiedView) {
                    if (edgeCost > 0)
                        System.out.print(1);
                    else
                        System.out.print(0);
                }
                else
                {
                    if (edgeCost > 0)
                        System.out.print(edgeCost);
                    else
                        System.out.print("--");
                }

                System.out.print(" ");
            }

            System.out.print("\n");
        }
    }

    public void printFailureProbability()
    {
        for (int lines = 0; lines < adjacencyMatrix.size(); lines++)
        {
            for (int column = 0; column < adjacencyMatrix.size(); column++)
            {
                float fp = geFailureProbabilityAt(lines, column);

                if (fp > -1.0f)
                    System.out.print(fp);
                else
                    System.out.print("--");

                System.out.print(" ");
            }

            System.out.print("\n");
        }
    }
}
