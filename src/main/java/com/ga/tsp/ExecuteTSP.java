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
    static int typeOfSelection;
    static int typeOfMutation;
    static int crossoverPoint;

    public static void main(String args[]) {
    	Scanner userInputScanner = new Scanner(System.in);
    	System.out.println("Number Of Chromosomes: ");
        numberOfChromosomes = userInputScanner.nextInt();
        
    	System.out.println("Number Of Generations: ");
        numberOfGenerations = userInputScanner.nextInt();
        
        System.out.println("Fitness Percentage: ");
        fitnessPercentage = userInputScanner.nextInt();
        
        System.out.println("Type of Selection:");
        System.out.println("1: Standard Percentage Based	2: Inverse Linear Roulette");
        while (typeOfSelection != 1 && typeOfSelection != 2){
        	typeOfSelection = userInputScanner.nextInt();
        	if (typeOfSelection != 1 && typeOfSelection != 2 ){
        		System.out.println("I'm sorry I didn't get that");
        	}
        }
        
        System.out.println("Type Of Mutation:");
        System.out.println("1: Reverse Sequence Mutation (RSM)	  2: Center Inverse Mutation (CIM)");
        while (typeOfMutation != 1 && typeOfMutation != 2){
        	typeOfMutation = userInputScanner.nextInt();
        	if (typeOfMutation != 1 && typeOfMutation != 2 ){
        		System.out.println("I'm sorry I didn't get that");
        	}
        }
        
        System.out.println("Crossover Point:");
        System.out.println("1: Random (within middle 50%	  2: Center");
        while (crossoverPoint != 1 && crossoverPoint != 2){
        	crossoverPoint = userInputScanner.nextInt();
        	if (crossoverPoint != 1 && crossoverPoint != 2 ){
        		System.out.println("I'm sorry I didn't get that");
        	}
        }

    	executeGA();
    	
    }

 
    private static void executeGA (){
        MapMaker mapMaker = new MapMaker();
        final Graph inputMap = mapMaker.makePresetMap();
        PopulationMaker populationMaker = new PopulationMaker(inputMap);
        TreeMap <Double, ArrayList <Path>> population = populationMaker.generate(numberOfChromosomes);
        CrossoverAgent crossoverAgent = new CrossoverAgent(inputMap, numberOfChromosomes, fitnessPercentage, crossoverPoint);
        MutationAgent mutationAgent = new MutationAgent (inputMap);
        PopulationViewer viewer= new PopulationViewer(inputMap);
		inputMap.display();

        for (Integer i = numberOfGenerations; i>0; i--){
        	if (typeOfSelection == 1){
        		population = crossoverAgent.cleanWorkspaceWithPercentage(population);
        	}else {
        		population = crossoverAgent.inverseLinearCoefficientSelection(population);
        	}
        	
        	showCurrentStats (population, viewer, numberOfGenerations-i+1);
        	
            if (i < numberOfGenerations && i%5 == 0){
            	if (typeOfMutation == 1){
            		mutationAgent.reverseSequenceMutation(population);
            	}else{
            		mutationAgent.centerInverseMutation(population);
            	}     	
            	
            }
            crossoverAgent.crossover(population);
            sleep();
        }
        
        population = crossoverAgent.cleanWorkspaceWithPercentage(population);
        viewer.showTopPath(population);
//        viewer.showPopulation(population);


    }
    
    private static void showCurrentStats(TreeMap <Double, ArrayList <Path>> population, PopulationViewer viewer, int generationNumber){
    	viewer.showTopPath(population);
        System.out.println("Generation:" + (generationNumber) +
        		" Weight = " + population.firstKey().intValue() + 
        		" Path:" + population.firstEntry().getValue().get(population.firstEntry().getValue().size()-1).toString());
    }
    
    protected static void sleep() {
        try { Thread.sleep(100); } catch (Exception e) {}
    }



}
