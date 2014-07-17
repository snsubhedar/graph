package com.ga.tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;

import java.util.*;

public class CrossoverAgent {

    static Graph inputMap;
    int chromosomeCount;

    public CrossoverAgent(Graph map, int organismCount){
        inputMap = map;
        chromosomeCount = organismCount; 
        
        
    }
    
    public TreeMap <Double, ArrayList <Path>> crossover(TreeMap <Double, ArrayList <Path>> workspace){
    	
		
		return null;
		

	}

    public TreeMap <Double, ArrayList <Path>> cleanWorkspace (TreeMap <Double, ArrayList <Path>> populationMap){
        final int topTenCount = chromosomeCount/10;
        TreeMap <Double, ArrayList <Path>> mapWorkspace = new TreeMap <Double, ArrayList <Path>>();

        int numberOfPathsAdded = 0;

         Iterator<Entry<Double, ArrayList<Path>>> it = populationMap.entrySet().iterator();
         while (it.hasNext() && numberOfPathsAdded < topTenCount) {
             Map.Entry pairs = (Map.Entry)it.next();

             if ( (numberOfPathsAdded + ((ArrayList <Path>)(pairs.getValue())).size() ) < topTenCount){
                 mapWorkspace.put((Double)pairs.getKey(), (ArrayList <Path>) pairs.getValue());
             }

         }


         return mapWorkspace;

    }
    
    private List <Path> crossoverPaths (Path pathOne, Path pathTwo){
    	List <Path> pathOneSplit = splitPath(pathOne);
    	List <Path> pathTwoSplit = splitPath (pathTwo);
    	
    	Path crossoverOne = crossoverPathHalves (pathOneSplit.get(0), pathTwoSplit.get(1));
    	Path crossoverTwo = crossoverPathHalves(pathTwoSplit.get(0), pathOneSplit.get(1));
    	
    	List <Path> crossedOverPaths = new ArrayList <Path>();
    	crossedOverPaths.add(0, crossoverOne);
    	crossedOverPaths.add(1, crossoverTwo);
    	
    	
		return crossedOverPaths;
    	
    }

    private Path crossoverPathHalves (Path halfOne, Path halfTwo){ // once we have seperated the paths in crossover paths we need to cross them over one at a time
        final int completedPathSize = inputMap.getNodeCount();
        PopulationMaker pm = new PopulationMaker(inputMap);
        Path crossoverPath = halfOne.getACopy();
        List <Node> halfTwoNodePath= halfTwo.getNodePath();
        
        while (crossoverPath.size() < completedPathSize){
        	Node thisNode = halfTwoNodePath.get(0);
        	if (crossoverPath.contains(thisNode)){
        		crossoverPath.add(pm.getEquivalentEdge(crossoverPath.peekNode(), pickRandomNodeNotInPath(crossoverPath, halfTwo)));
        	}else{
        		crossoverPath.add(pm.getEquivalentEdge(crossoverPath.peekNode(), thisNode));
        	}
        	halfTwoNodePath.remove(0);
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
        
        int numberOfEdgesInverse = halfTwoInverse.getEdgeCount(); //avoids concurrent modification error 
        while (numberOfEdgesInverse > 0){
            Edge edgeToAdd = halfTwoInverse.popEdge();
            if (!(halfTwo.empty())){
                halfTwo.add(pm.getEquivalentEdge(edgeToAdd.getNode0(), edgeToAdd.getNode1()));
            }else{
                halfTwo.setRoot(path.getNodePath().get(crossoverPoint++));
                halfTwo.add(pm.getEquivalentEdge(edgeToAdd.getNode0(), edgeToAdd.getNode1()));
            }
            
            numberOfEdgesInverse --;

        }
        
        halfOne.popEdge();

        List <Path> splitParent = new ArrayList<Path>();

        splitParent.add(0, halfOne);
        splitParent.add(1, halfTwo);

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
