package com.ga.tsp;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;

import java.util.*;

public class MutationAgent {
	
	Graph inputMap;
	PopulationMaker pm;
	
	
	public MutationAgent (Graph map){
        inputMap = map;  
		pm = new PopulationMaker(inputMap);
    }
	
	
	public void reverseSequenceMutation (TreeMap <Double, ArrayList <Path>> workspace){
		/**In the reverse sequence mutation operator, we take a sequence S limited by two
		positions i and j randomly chosen, such that i<j. The gene order in this sequence
		will be reversed by the same way as what has been covered in the previous
		operation. */
		
		Path path = getTopPathInWorkspace(workspace);
		
		int mutationPoint = getMutationPoint(path);
		Path halfTwoInverse = new Path();
		
		 for (int i = mutationPoint; i>1; i--){ // will return the second half in its inverse form
			 if (!(halfTwoInverse.empty())){
				 halfTwoInverse.add(path.popEdge());
			 }else{
				 Edge edgeToAdd = path.popEdge();
				 halfTwoInverse.setRoot(edgeToAdd.getNode0());
				 halfTwoInverse.add(edgeToAdd);
			 }
		 }
		 path.popEdge(); // remove the last redundant edge of the path
		 
		 List<Node> halfTwoInverseList = halfTwoInverse.getNodePath();
		 halfTwoInverseList.remove(inputMap.getNode(0));
		 
		for (Iterator<Node> iterator = halfTwoInverseList.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
			path.add(pm.getEquivalentEdge(path.peekNode(), node));
		}
		
		path.add(pm.getEquivalentEdge(path.peekNode(), inputMap.getNode(0)));
		// at this point the mutated path is prepared 
		addPathToWorkspace(path, workspace);
	}
	
	public void centerInverseMutation (TreeMap <Double, ArrayList <Path>> workspace){
		/** The chromosome is divided into two sections. All genes in each section are copied
		and then inversely placed in the same section of a child.*/
		
		Path path = getTopPathInWorkspace(workspace);
		
		Pair <Path, Path> pathSplit= splitPathInHalf (path);
		Path halfOne = pathSplit.getLeft();
		List <Node> halfTwoInverseList = pathSplit.getRight().getNodePath();
		halfTwoInverseList.remove(0);
		List <Node> pathOneNodeList = halfOne.getNodePath();
		List <Node> halfOneInverseList = new ArrayList <Node>();
		
		for (int i = pathOneNodeList.size(); i>1; i--){
			Node lastNode = pathOneNodeList.get(pathOneNodeList.size()-1);
			halfOneInverseList.add(lastNode);
			pathOneNodeList.remove(pathOneNodeList.size()-1);
		}
		
		
		Path mutatedPath = new Path();
		mutatedPath.setRoot(inputMap.getNode(0));
		for (Node node : halfTwoInverseList){
			mutatedPath.add(pm.getEquivalentEdge(mutatedPath.peekNode(), node));
		}
		for (Node node : halfOneInverseList){
			mutatedPath.add(pm.getEquivalentEdge(mutatedPath.peekNode(), node));
		}
		mutatedPath.add(pm.getEquivalentEdge(mutatedPath.peekNode(), inputMap.getNode(0)));
		
		addPathToWorkspace(mutatedPath, workspace);
		
	}
	
	
    private Pair <Path, Path> splitPathInHalf (Path path){
        Path halfOne = path.getACopy();

        Path halfTwoInverse = new Path();
        for (int i = path.size()/2; i>1; i--){ // will return the second half in its inverse form
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
	
	
	
    private int getMutationPoint (Path path){ // returns random mutation point in the middle 50% of the path 
        Random r= new Random();
        int pathSize = path.size();
        int lowerBound = pathSize/3; 			// lower 25%  
        int upperBound = ((pathSize/4)*3); 		// upper 75%
        int range = upperBound - lowerBound;
        int mutationPoint = (r.nextInt(range)) + lowerBound;
        
        return mutationPoint;
        
    }
    
    private Path getRandomPathInWorkspace(TreeMap <Double, ArrayList <Path>> workspace){
    	Random r = new Random(); // get a random path in the workspace
		List <Double> keys = new ArrayList <Double> (workspace.keySet());
		ArrayList<Path> randomPathList = workspace.get(keys.get(r.nextInt(keys.size()-1)));
		Path path = (randomPathList.get(r.nextInt(randomPathList.size()))).getACopy();
		
		return path;
    }
    
    private Path getTopPathInWorkspace(TreeMap <Double, ArrayList <Path>> workspace){
    	Path path = workspace.firstEntry().getValue().get(workspace.firstEntry().getValue().size() - 1);
    	
    	return path;
    }
    
    private void addPathToWorkspace (Path path, TreeMap <Double, ArrayList <Path>> workspace){
    	 Double currentPathWeight = new Double(path.getPathWeight("weight"));

         ArrayList <Path> currentPathArray = workspace.get(currentPathWeight);

         if(currentPathArray != null){
             // Weight is already present in LinkedHashMap and add this Path to the respective array list.
             boolean isDuplicate = false;
             for (Path pathInArray: currentPathArray){
                 if (pathInArray.equals(path)){
                     isDuplicate = true;
                 }
             }
             if (isDuplicate == false){
                 currentPathArray.add(path);
             }

         } else {
             // This weight has never been seen before so make a new key and array list to add to the LinkedHashMap.
             currentPathArray=new ArrayList <Path>();
             currentPathArray.add(path);
             workspace.put(currentPathWeight, currentPathArray);
         }
    }
	
	
	
	
	
	
	
}
