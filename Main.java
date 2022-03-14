package com.bercoviciadrianpa2022lab3;
//Bercovici Adrian 2A6
//PA 2022 Lab3

public class Main {

    public static void main(String[] args)
    {
        InputGenerator.generateInput();

        //Network network = new Network("GraphInput.txt");

        Network network = new Network("GeneratedInput.txt");

        network.printNetwork();
    }
}
