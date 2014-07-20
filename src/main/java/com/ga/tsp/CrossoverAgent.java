package com.ga.tsp;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;

import java.util.*;
import java.util.Map.Entry;

public class CrossoverAgent {

    static Graph inputMap;
    int chromosomeCount;
    int cutoffPercentage;

    public CrossoverAgent(Graph map, int organismCount, int fitnessPercentage){
        inputMap = map;
        chromosomeCount = organismCount; 
        cutoffPercentage = fitnessPercentage;
        
        
    }
    
    public TreeMap <Double, ArrayList <Path>> crossover(TreeMap <Double, ArrayList <Path>> workspace){
    	Random random = new Random();
    	int numberOfPathsInWorkspace = chromosomeCount/(100/cutoffPercentage);
    	TreeMap <Double, ArrayList <Path>> newGeneration = workspace;
    	List <Double> keys = new ArrayList <Double> (workspace.keySet());
    	
    	while (numberOfPathsInWorkspace < chromosomeCount){
    		ArrayList<Path> one = workspace.get(keys.get(random.nextInt(keys.size()-1)));
    		ArrayList<Path> two = workspace.get(keys.get(random.nextInt(keys.size()-1)));
    		
    		Path parentOne = one.get(random.nextInt(one.size()));
    		Path parentTwo = two.get(random.nextInt(two.size()));
    		List <Path> pathsToAdd= crossoverPaths(parentOne, parentTwo);
    		
    		for (Iterator<Path> iterator = pathsToAdd.iterator(); iterator.hasNext();) {
				Path currentPath = (Path) iterator.next();
				Double pathWeight = currentPath.getPathWeight("weight");
				ArrayList <Path> currentPathArray = newGeneration.get(pathWeight);
				 if(currentPathArray != null){
		         // Weight is already present in TreeMap and add this Path to the respective array list.
					 boolean isDuplicate = false;
					 for (Path pathInArray: currentPathArray){
						 if (pathInArray.equals(currentPath)){
		                        isDuplicate = true;
						 }
					 }
					 
					 if (isDuplicate == false){
						 currentPathArray.add(currentPath);
						 numberOfPathsInWorkspace++;
					 }

				 } else {
		                // This weight has never been seen before so make a new key and array list to add to the LinkedHashMap.
					 currentPathArray=new ArrayList <Path>();
					 currentPathArray.add(currentPath);
					 numberOfPathsInWorkspace++;
					 newGeneration.put(pathWeight, currentPathArray);
				 }
			}
    	}
    	
    	
		return newGeneration;
		

	}

    public TreeMap <Double, ArrayList <Path>> cleanWorkspace (TreeMap <Double, ArrayList <Path>> populationMap){
        final int numberOfFitChromosomeCount = chromosomeCount/(100/cutoffPercentage);
        TreeMap <Double, ArrayList <Path>> mapWorkspace = new TreeMap <Double, ArrayList <Path>>();

        int numberOfPathsAdded = 0;

         Iterator<Entry<Double, ArrayList<Path>>> it = populationMap.entrySet().iterator();
         while (it.hasNext() && numberOfPathsAdded < numberOfFitChromosomeCount) {
             Map.Entry pairs = (Map.Entry)it.next();

             if ( (numberOfPathsAdded + ((ArrayList <Path>)(pairs.getValue())).size() ) <= numberOfFitChromosomeCount){
                 mapWorkspace.put((Double)pairs.getKey(), (ArrayList <Path>) pairs.getValue());
                 numberOfPathsAdded++;
             }

         }


         return mapWorkspace;

    }
    
    private List <Path> crossoverPaths (Path pathOne, Path pathTwo){
    	List <Path> pathOneSplit = splitPath(pathOne);  //SS: use apache.commons.lang3.tuple.Pair instead of list

    	List <Path> pathTwoSplit = splitPath (pathTwo);
    	
    	Path crossoverOne = crossoverPathHalves (pathOneSplit.get(0), pathTwoSplit.get(1));
    	Path crossoverTwo = crossoverPathHalves(pathTwoSplit.get(0), pathOneSplit.get(1));
    	
    	List <Path> crossedOverPaths = new ArrayList <Path>();
    	crossedOverPaths.add(0, crossoverOne);
    	crossedOverPaths.add(1, crossoverTwo);
    	
    	
		return crossedOverPaths;
    	
    }

    //SS: if Ch1 and Ch2 are two chromosomes with p1 and p2 being divided parts,
    // you have to pick Ch1p1--Ch2p2 .. Instead of searching for random node in entire graph (expensive if graph is big)
    // simply pick a random node from Ch1p2. Conversely, simply pick a random node from Ch2p2 to complete path for Ch2p1

    private Path crossoverPathHalves (Path halfOne, Path halfTwo){ // once we have separated the paths in crossover paths we need to cross them over one at a time
        final int completedPathSize = inputMap.getNodeCount();
        PopulationMaker pm = new PopulationMaker(inputMap);
        Path crossoverPath = halfOne.getACopy();
        List <Node> halfTwoNodePath= halfTwo.getNodePath();
        
        while (crossoverPath.size() < completedPathSize){
        	Node thisNode = halfTwoNodePath.get(halfTwoNodePath.size() - 1);
        	if (crossoverPath.contains(thisNode)){
        		crossoverPath.add(pm.getEquivalentEdge(crossoverPath.peekNode(), pickRandomNodeNotInPath(crossoverPath, halfTwo)));
        	}else{
        		crossoverPath.add(pm.getEquivalentEdge(crossoverPath.peekNode(), thisNode));
        	}
        	halfTwoNodePath.remove(halfTwoNodePath.size() - 1);
        }
        
        

        Edge finalEdge = pm.getEquivalentEdge(crossoverPath.peekNode(), crossoverPath.getRoot());
        crossoverPath.add(finalEdge);

        return crossoverPath;
    }

    private List <Path> splitPath (Path path){
        int crossoverPoint = getCrossoverPoint(path);
        PopulationMaker pm = new PopulationMaker(inputMap);
        Path halfTwo = new Path();
        Path halfOne = path.getACopy();

        Path halfTwoInverse = new Path();
        for (int i = crossoverPoint; i>1; i--){ // will return the second half in its inverse form
            if (!(halfTwoInverse.empty())){
            	halfTwoInverse.add(halfOne.popEdge());
            }else{
            	Edge edgeToAdd = halfOne.popEdge();
            	halfTwoInverse.setRoot(edgeToAdd.getNode0());
            	halfTwoInverse.add(edgeToAdd);
            }
        }

        //why do you need this? Second half is used to check for missing nodes in second crossover
        // and they will be picked in random anyway.
       // I think this is unnecessary.

//        int numberOfEdgesInverse = halfTwoInverse.getEdgeCount(); //avoids concurrent modification error
//        while (numberOfEdgesInverse > 0){
//            Edge edgeToAdd = halfTwoInverse.popEdge();
//            if (!(halfTwo.empty())){
//                halfTwo.add(pm.getEquivalentEdge(edgeToAdd.getNode0(), edgeToAdd.getNode1()));
//            }else{
//                halfTwo.setRoot(path.getNodePath().get(crossoverPoint++));
//                halfTwo.add(pm.getEquivalentEdge(edgeToAdd.getNode0(), edgeToAdd.getNode1()));
//            }
//
//            numberOfEdgesInverse --;
//
//        }
        
        halfOne.popEdge();   // SS: why?

        List <Path> splitParent = new ArrayList<Path>();

        splitParent.add(0, halfOne);
        splitParent.add(1, halfTwoInverse);

        return splitParent;


    }


    private int getCrossoverPoint(Path path){
        int crossoverPoint = path.size()/2; //currently in the middle
        //here we can determine the crossover point logic (randomly if necessary)
        return crossoverPoint;
    }
    
    private Node pickRandomNodeNotInPath (Path path, Path pathTwo){
    	PopulationMaker pm = new PopulationMaker(inputMap);	
    	
    	while (true){
    		Node randomNode = pm.pickRandomNode();
			if ((!(path.contains(randomNode))) && (!(pathTwo.contains(randomNode)))){
				
    			return randomNode;
    		}
    	}
    	
    	
    	
    }
}
