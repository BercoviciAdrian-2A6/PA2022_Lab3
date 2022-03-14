package com.bercoviciadrianpa2022lab3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class InputGenerator
{
    static final float NO_COST = 0.0f;

    public static void generateInput()
    {
        try
        {
            BufferedWriter inputWriter = new BufferedWriter( new FileWriter("src/GeneratedInput.txt") );

            Random random = new Random();

            int numberOfNodes = random.nextInt(128) + 128;//generate between 128 and 256 nodes;

            inputWriter.write(numberOfNodes + "\n");

            ArrayList<String> generatedNodes = new ArrayList<>();

            for (int i = 0; i < numberOfNodes; i++)
            {
                String newNode = "Vertex_" + (i + 1) + " ";
                String type = "";

                int rnd = random.nextInt(3) ;

                switch (rnd)
                {
                    case 0: { newNode += "COMPUTER " + (random.nextInt(9750) + 250) + "\n"; break;}
                    case 1: { newNode += "ROUTER\n"; break;}
                    case 2: { newNode += "SWITCH\n"; break;}
                }

                inputWriter.write(newNode);
                generatedNodes.add("Vertex_" + (i + 1));
            }

            int numberOfEdges = random.nextInt( numberOfNodes * (numberOfNodes - 1) / 2 );
            //n * (n - 1) / 2 is the max number of edges in a non-directed graph

            for (int i = 0; i < numberOfEdges; i++)
            {
                //generating the same edge multiple times is fine
                //since it will just overwrite the previous values
                int nodeAIndex = random.nextInt(numberOfNodes);
                int nodeBIndex;
                do {
                    nodeBIndex = random.nextInt(numberOfNodes);
                }while (nodeAIndex == nodeBIndex);//an edge between the same node is invalid

                String edge = generatedNodes.get(nodeAIndex) + " "
                        + generatedNodes.get(nodeBIndex) + " " + random.nextInt(256) + " " + random.nextInt(100) + "\n";

                inputWriter.write(edge);
            }

            //generated the safest known path

            int fibA = 1;
            int fibB = 2;

            while (fibB < numberOfNodes)
            {
                String edge = generatedNodes.get(fibA - 1) + " "
                        + generatedNodes.get(fibB - 1) + " " + NO_COST + " " + NO_COST+ "\n";

                int currentFibA = fibA;
                fibA = fibB;
                fibB = currentFibA + fibB;

                inputWriter.write(edge);
            }

            if (fibA != (numberOfNodes - 1) )
            {
                String edge = generatedNodes.get(fibA - 1) + " "
                        + generatedNodes.get(numberOfNodes - 1) + " " + NO_COST + " " + NO_COST + "\n";
                inputWriter.write(edge);
            }

            inputWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
