package com.ga.tsp;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Path;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class ExecuteTSP {

    static int numberOfChromosomes;
    static int numberOfGenerations ;
    static int fitnessPercentage;

    public static void main(String args[]) {
    	Scanner userInputScanner = new Scanner(System.in);
    	System.out.println("Number Of Chromosomes: ");
        numberOfChromosomes = userInputScanner.nextInt();
        
    	System.out.println("Number Of Generations: ");
        numberOfGenerations = userInputScanner.nextInt();
        
        System.out.println("Fitness Percentage: ");
        fitnessPercentage = userInputScanner.nextInt();
         
    	executeGA();
    }

 
    private static void executeGA (){
        MapMaker mapMaker = new MapMaker();
        final Graph inputMap = mapMaker.makeRandomMap();
        PopulationMaker populationMaker = new PopulationMaker(inputMap);
        TreeMap <Double, ArrayList <Path>> population = populationMaker.generate(numberOfChromosomes);
        CrossoverAgent crossoverAgent = new CrossoverAgent(inputMap, numberOfChromosomes, fitnessPercentage);
        MutationAgent mutationAgent = new MutationAgent (inputMap);
        PopulationViewer viewer= new PopulationViewer(inputMap);
		inputMap.display();

        for (Integer i = numberOfGenerations; i>0; i--){
            population = crossoverAgent.cleanWorkspace(population);
            viewer.showTopPaths(population);
            System.out.println("Generation:" + (numberOfGenerations-i+1) +
            		" Weight = " + population.firstKey().intValue() + 
            		" Path:" + population.firstEntry().getValue().get(population.firstEntry().getValue().size()-1).toString());
            if (i < numberOfGenerations && i%5 == 0){
            	mutationAgent.reverseSequenceMutation(population);
            }
            crossoverAgent.crossover(population);
        }
        
        population = crossoverAgent.cleanWorkspace(population);
        viewer.showTopPaths(population);
//        viewer.showPopulation(population);


    }
    
    protected static void sleep() {
        try { Thread.sleep(100); } catch (Exception e) {}
    }



}
