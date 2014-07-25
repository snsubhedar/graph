package com.ga.tsp;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;

import java.util.*;

public class MutationAgent {
	
	Graph inputMap;
	
	
	public MutationAgent (Graph map){
        inputMap = map;  
    }
	
	
	public void reverseSequenceMutation (TreeMap <Double, ArrayList <Path>> workspace){
		/**In the reverse sequence mutation operator, we take a sequence S limited by two
		positions i and j randomly chosen, such that i<j. The gene order in this sequence
		will be reversed by the same way as what has been covered in the previous
		operation. */
		
		Random r = new Random(); // get a random path in the workspace
		PopulationMaker pm = new PopulationMaker(inputMap);
//		List <Double> keys = new ArrayList <Double> (workspace.keySet());
//		ArrayList<Path> randomPathList = workspace.get(keys.get(r.nextInt(keys.size()-1)));
//		Path path = (randomPathList.get(r.nextInt(randomPathList.size()))).getACopy();
		Path path = workspace.firstEntry().getValue().get(workspace.firstEntry().getValue().size() - 1);
		
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
		 
		for (Iterator iterator = halfTwoInverseList.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
			path.add(pm.getEquivalentEdge(path.peekNode(), node));
		}
		
		path.add(pm.getEquivalentEdge(path.peekNode(), inputMap.getNode(0)));
		// at this point the mutated path is prepared 
		// now to add it to the appropriate place in the workspace
		 
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
	
    private int getMutationPoint (Path path){ // returns random mutation point in the middle 50% of the path 
//        Random r= new Random();
//        int pathSize = path.size();
//        if (pathSize < 16){
//        	int lowerBound = pathSize/4 +2; 			// lower 25%  
//            int upperBound = ((pathSize/4)*3) -1 ; 		// upper 75%
//            int range = upperBound - lowerBound;
//            int mutationPoint = (r.nextInt(range)) + lowerBound;
//            
//            return mutationPoint;
//        }else{
//        	int lowerBound = pathSize/4; 			// lower 25%  
//            int upperBound = ((pathSize/4)*3); 		// upper 75%
//            int range = upperBound - lowerBound;
//            int mutationPoint = (r.nextInt(range)) + lowerBound;
//            
//            return mutationPoint;
//        }
    	
    	int mutationPoint = (path.size()/2)+3;
    	return mutationPoint;
        
    }
	
	
	
	
	
	
	
}
