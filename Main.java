package com.bercoviciadrianpa2022lab3;
//Bercovici Adrian 2A6
//PA 2022 Lab3

public class Main {

    public static void main(String[] args)
    {
        Network network = new Network();

        network.addNode( new Computer( 1000 ) );

        network.addNode( new Computer( 2000 ) );

        network.addNode( new Router() );

        network.addNode( new Switch() );

        network.addNode( new Computer( 5000 ) );

        network.printNetwork();
    }
}
