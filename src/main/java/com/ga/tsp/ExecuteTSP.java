package com.ga.tsp;

import java.util.ArrayList;
import java.util.TreeMap;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Path;

public class ExecuteTSP {

    static int numberOfChromosomes = 100;
    final static int numberOfGenerations = 5;

    public static void main(String args[]) {
        executeGA();
    }


    public static void executeGA (){
        MapMaker mapMaker = new MapMaker();
        final Graph inputMap = mapMaker.makeRandomMap();
        PopulationMaker populationMaker = new PopulationMaker(inputMap);
        TreeMap <Double, ArrayList <Path>> population = populationMaker.generate(numberOfChromosomes);
        CrossoverAgent crossoverAgent = new CrossoverAgent(inputMap, numberOfChromosomes);

        for (int i = numberOfGenerations; i>0; i--){
            population = crossoverAgent.cleanWorkspace(population);
            population = crossoverAgent.crossover(population);
        }

        populationMaker.showPopulation(population);


    }



}
