package com.ga.tsp;

// testing animesh 2

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import scala.util.Random;

import java.util.ArrayList;
import java.util.TreeMap;

public class PopulationMaker {

    static Graph inputMap;

    public PopulationMaker(Graph g){
        inputMap = g;
    }



    public TreeMap <Double, ArrayList <Path>> generate(int numberOfOrganisms) {

        TreeMap <Double, ArrayList <Path>> populationMap = new TreeMap<Double, ArrayList <Path>>();


        for (int i = 0; i < numberOfOrganisms; i++){
            Path currentPath = getValidPath();

            Double currentPathWeight = new Double(currentPath.getPathWeight("weight"));

            ArrayList <Path> currentPathArray = populationMap.get(currentPathWeight);

            if(currentPathArray != null){
                // Weight is already present in LinkedHashMap and add this Path to the respective array list.
                boolean isDuplicate = false;
                for (Path path: currentPathArray){
                    if (path.equals(currentPath)){
                        isDuplicate = true;
                    }
                }
                if (isDuplicate == false){
                    currentPathArray.add(currentPath);
                }

            } else {
                // This weight has never been seen before so make a new key and array list to add to the LinkedHashMap.
                currentPathArray=new ArrayList <Path>();
                currentPathArray.add(currentPath);
                populationMap.put(currentPathWeight, currentPathArray);
            }
        }

        return populationMap;



    }

    private Path getValidPath(){
        Path randomPath = new Path ();
        randomPath.setRoot(inputMap.getNode(0));
        Node previousNode = randomPath.getRoot();
        Random r = new Random();

        final int nodeCount = inputMap.getNodeCount();
        int randomNumber = r.nextInt(nodeCount);

        while (nodeCount >  randomPath.getNodeCount()){

            Node thisRandomNode = inputMap.getNode(randomNumber);

            if (!randomPath.contains(thisRandomNode)){ // If the random path does not contains the node

                randomPath.add(getEquivalentEdge(previousNode,thisRandomNode));
                previousNode = thisRandomNode;

            }//end of if
            randomNumber = r.nextInt(nodeCount);

        }//end of while

        if (!(inputMap.getEdge(previousNode.toString().concat(randomPath.getRoot().toString())) == null)){
            randomPath.add(inputMap.getEdge(previousNode.toString().concat(randomPath.getRoot().toString())));
        }else {
            randomPath.add(inputMap.getEdge(randomPath.getRoot().toString().concat(previousNode.toString())));
        }



        return randomPath;
    }


    public Node pickRandomNode (){
        Random r = new Random();
        int randomNumber = r.nextInt(inputMap.getNodeCount());
        Node randomNode = inputMap.getNode(randomNumber);

        return randomNode;
    }

    public Edge getEquivalentEdge (Node nodeOne, Node nodeTwo){
        StringBuilder builder = new StringBuilder();

        if (((inputMap.getEdge(builder.append(nodeOne.toString()).append(nodeTwo.toString()).toString()) != null))){
 
            return inputMap.getEdge(builder.toString());

        }else{

            StringBuilder buildertwo = new StringBuilder();
            return inputMap.getEdge(buildertwo.append(nodeTwo.toString()).append(nodeOne.toString()).toString());
        }

    }




}
