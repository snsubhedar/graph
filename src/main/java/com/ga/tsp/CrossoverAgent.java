package com.ga.tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.math.genetics.Population;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import scala.util.Random;

import java.util.*;
import java.util.Map.Entry;

import org.graphstream.graph.Path;

public class CrossoverAgent {

    static Graph inputMap;

    public CrossoverAgent(Graph g){
        inputMap = g;
    }

    public TreeMap <Double, ArrayList <Path>> makeWorkspace (TreeMap <Double, ArrayList <Path>> populationMap, int chromosomeCount){
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

//
//    private Collection<Path> crossover(){
//        final int crossoverPoint = selectCrossoverPoint(pathOne);
//        Path crossedOverPathOne = new Path();
//        Path crossedOverPathTwo = new Path();
//        List<Edge> pathOneEdgeSet = pathOne.getEdgePath();
//        List<Edge> pathTwoEdgeSet = pathTwo.getEdgePath();
//
//
//
//
//        return
//    }

    private Path crossoverPathHalves (Path halfOne, Path halfTwo){ // once we have seperated the paths in crossover paths we need to cross them over one at a time
        final int completedPathSize = inputMap.getNodeCount();
        PopulationMaker pm = new PopulationMaker(inputMap);
        Path crossoverPath = halfOne;

        while (completedPathSize > crossoverPath.size()){
            List <Edge> halfTwoEdgeList = halfTwo.getEdgePath();
            List <Node> halfTwoNodeList = halfTwo.getNodePath();
            Node pathTwoCurrentNode = halfTwoNodeList.get(0);

            Node lastNodeInCrossoverPath = crossoverPath.getNodePath().get(crossoverPath.size()-1);

            if (!(crossoverPath.contains(pathTwoCurrentNode))){
                Edge edgeToAdd = pm.getEquivalentEdge(lastNodeInCrossoverPath, pathTwoCurrentNode);

                crossoverPath.add(edgeToAdd);

            }else{

                Edge edgeToAdd = pm.getEquivalentEdge(lastNodeInCrossoverPath, pm.pickRandomNode());

                crossoverPath.add(edgeToAdd);
            }

            halfTwo.popNode();


        }

        Edge finalEdge = pm.getEquivalentEdge(crossoverPath.getNodePath().get(crossoverPath.size()), crossoverPath.getRoot());
        crossoverPath.add(finalEdge);

        return crossoverPath;
    }

    public List <Path> splitPath (Path path){
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
        
        int numberOfEdgesInverse = halfTwoInverse.getEdgeCount();

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
}
