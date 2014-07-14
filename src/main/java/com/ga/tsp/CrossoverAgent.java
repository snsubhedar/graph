package com.ga.tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.graphstream.graph.Path;

public class CrossoverAgent {

public TreeMap <Double, ArrayList <Path>> makeWorkspace (TreeMap <Double, ArrayList <Path>> populationMap, int chromosomeCount){
	final int topTenCount = chromosomeCount/10;
	TreeMap <Double, ArrayList <Path>> mapWorkspace = new TreeMap <Double, ArrayList <Path>>();
	
	int numberofPathsAdded = 0; 
	
	 Iterator<Entry<Double, ArrayList<Path>>> it = populationMap.entrySet().iterator();
     while (it.hasNext() && numberofPathsAdded < topTenCount) {
         Map.Entry pairs = (Map.Entry)it.next();
         
         if ( (numberofPathsAdded + ((ArrayList <Path>)(pairs.getValue())).size() ) < topTenCount){
        	 mapWorkspace.put((Double)pairs.getKey(), (ArrayList <Path>) pairs.getValue());
         }

     }
     
     
     return mapWorkspace;
	
}

}
