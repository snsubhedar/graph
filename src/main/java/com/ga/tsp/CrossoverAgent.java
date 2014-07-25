package com.ga.tsp;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
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
					 newGeneration.put(pathWeight, currentPathArray);
					 numberOfPathsInWorkspace++;
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
    	Pair <Path, Path> pathOneSplit = splitPath(pathOne);
    	Pair <Path, Path> pathTwoSplit = splitPath (pathTwo);
    	
    	Path crossoverOne = crossoverPathHalves (pathOneSplit.getLeft().getACopy(), pathTwoSplit.getRight().getACopy(), pathOneSplit.getRight().getACopy());
    	Path crossoverTwo = crossoverPathHalves(pathTwoSplit.getLeft().getACopy(), pathOneSplit.getRight().getACopy(), pathTwoSplit.getRight().getACopy());
    	
    	List <Path> crossedOverPaths = new ArrayList <Path>();
    	crossedOverPaths.add(0, crossoverOne);
    	crossedOverPaths.add(1, crossoverTwo);
    	
    	
		return crossedOverPaths;
    	
    }

    private Path crossoverPathHalves (Path halfOne, Path halfTwo, Path pathOneHalfTwo){ // once we have separated the paths in crossover paths we need to cross them over one at a time
        final int completedPathSize = inputMap.getNodeCount();
        PopulationMaker pm = new PopulationMaker(inputMap);
        Path crossoverPath = halfOne.getACopy();
        List <Node> halfTwoNodePath= halfTwo.getNodePath();
        List <Node> nodesToPickFrom = pathOneHalfTwo.getNodePath();
        nodesToPickFrom.remove(inputMap.getNode(0));
        
        
        while (crossoverPath.size() < completedPathSize){
        	Node thisNode = halfTwoNodePath.get(halfTwoNodePath.size()-1);
        	if (crossoverPath.contains(thisNode)){
        		crossoverPath.add(pm.getEquivalentEdge(crossoverPath.peekNode(), pickRandomNodeNotInPath(crossoverPath, halfTwo, nodesToPickFrom)));
        	}else{
        		crossoverPath.add(pm.getEquivalentEdge(crossoverPath.peekNode(), thisNode));
        	}
        		halfTwoNodePath.remove(halfTwoNodePath.size()-1);
        }
        
        

        Edge finalEdge = pm.getEquivalentEdge(crossoverPath.peekNode(), crossoverPath.getRoot());
        crossoverPath.add(finalEdge);

        return crossoverPath;
    }

    private Pair <Path, Path> splitPath (Path path){
        int crossoverPoint = getCrossoverPoint(path);
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
        
        halfOne.popEdge();

        Pair <Path, Path> splitParent = new ImmutablePair<Path, Path>(halfOne, halfTwoInverse);

        return splitParent;


    }


    private int getCrossoverPoint(Path path){ // returns random crossover point in the middle 50% of the path 
//        Random r= new Random();
//        int pathSize = path.size();
//        if (pathSize < 11){
//        	int lowerBound = pathSize/4 +2; 			// lower 25%  
//            int upperBound = ((pathSize/4)*3) -1 ; 		// upper 75%
//            int range = upperBound - lowerBound;
//            int crossoverPoint = (r.nextInt(range)) + lowerBound;
//            
//            return crossoverPoint;
//        }else{
//        	int lowerBound = pathSize/3; 			// lower 25%  
//            int upperBound = ((pathSize/4)*3); 		// upper 75%
//            int range = upperBound - lowerBound;
//            int crossoverPoint = (r.nextInt(range)) + lowerBound;
//            return crossoverPoint;
//        }
    	
    	int crossoverPoint = (path.size()/2)+3;
    	return crossoverPoint;
    
    
        
    }
    
    private Node pickRandomNodeNotInPath (Path path, Path pathTwo, List <Node> nodesToPickFrom){
    	Random r = new Random();
    	while (true){
    		int randomNumber = r.nextInt(nodesToPickFrom.size());
    		Node randomNode = nodesToPickFrom.get(randomNumber);
			if ((!(path.contains(randomNode))) && (!(pathTwo.contains(randomNode)))){
				nodesToPickFrom.remove(randomNumber);
    			return randomNode;
    		}else{
    			nodesToPickFrom.remove(randomNumber);
    		}
    	}
    	
    }
    
}
