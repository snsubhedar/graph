package com.ga.tsp;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Path;

import java.util.ArrayList;
import java.util.TreeMap;

public class ExecuteTSP {

    static int numberOfChromosomes =1000;
    final static int numberOfGenerations = 1000;
    final static int fitnessPercentage = 50;

    public static void main(String args[]) {
    	executeGA();
        
    }


    private static void executeGA (){
        MapMaker mapMaker = new MapMaker();
        final Graph inputMap = mapMaker.makeRandomMap();
        PopulationMaker populationMaker = new PopulationMaker(inputMap);
        TreeMap <Double, ArrayList <Path>> population = populationMaker.generate(numberOfChromosomes);
        CrossoverAgent crossoverAgent = new CrossoverAgent(inputMap, numberOfChromosomes, fitnessPercentage);

        for (Integer i = numberOfGenerations; i>0; i--){
            population = crossoverAgent.cleanWorkspace(population);
            System.out.println("Generation:" + i.toString() + 
            		" Weight=" + population.firstKey().toString() + 
            		" Path:" + population.firstEntry().getValue().get(population.firstEntry().getValue().size()-1).toString());
            population = crossoverAgent.crossover(population);
        }

        populationMaker.showPopulation(population);


    }



}
