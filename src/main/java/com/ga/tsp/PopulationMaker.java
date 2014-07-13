package com.ga.tsp;

//testing

import java.util.*;
import java.util.Map.Entry;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;

import scala.util.Random;

public class PopulationMaker {

    static Graph inputMap;

    public static void main(String[] args) {
    }

    public PopulationMaker(Graph g){
        inputMap = g;
    }



    public TreeMap <Double, ArrayList <Path>> generate(int numberOfOrganisms) {

        TreeMap <Double, ArrayList <Path>> population = new TreeMap<Double, ArrayList <Path>>();

        for (int i = 0; i < numberOfOrganisms; i++){
            Path currentPath = getValidPath();

            Double currentPathWeight = new Double(currentPath.getPathWeight("weight"));

            ArrayList <Path> currentPathArray = population.get(currentPathWeight);

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
                population.put(currentPathWeight, currentPathArray);
            }
        }

        return population;



    }

    public Path getValidPath(){
        Path randomPath = new Path ();
        randomPath.setRoot(inputMap.getNode(0));
        Node previousNode = randomPath.getRoot();
        Random r = new Random();

        final int nodeCount = inputMap.getNodeCount();
        int randomNumber = r.nextInt(nodeCount);

        while (nodeCount >  randomPath.getNodeCount()){

            Node thisRandomNode = inputMap.getNode(randomNumber);

            if (!randomPath.contains(thisRandomNode)){ // If the random path does not contains the node

                String randomEdgeName = previousNode.toString().concat(thisRandomNode.toString());
                String inverseRandomEdgeName = thisRandomNode.toString().concat(previousNode.toString());

                if (!((inputMap.getEdge(randomEdgeName))== null)){
                    randomPath.add(inputMap.getEdge(randomEdgeName));
                }else{
                    randomPath.add(inputMap.getEdge(inverseRandomEdgeName));
                }
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


    public void showPopulation(TreeMap<Double, ArrayList<Path>> population){

        Iterator<Entry<Double, ArrayList<Path>>> it = population.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            System.out.println(pairs.getKey() + " : " + showPaths((ArrayList<Path>) pairs.getValue()));
            it.remove(); // avoids a ConcurrentModificationException

        }

    }

    private String showPaths(ArrayList <Path> paths){
        StringBuilder builder = new StringBuilder();

        for (Path path : paths){
            builder.append(" [").append(path.toString()).append("] ");
        }

        return builder.toString();

    }


    public void showPathGraph (Path path){
        Graph graph = inputMap;

        final String labelStyleSheet =
                "edge {" +
                        " shape: line;" +
                        " size: 1px, 0px;" +
                        " fill-color: brown3" +
                        ";" +
                        " text-mode: normal;" +
                        " text-alignment: center;" +
                        "}"+
                        "edge::selected {" +
                        "       fill-color: green;" +
                        "}";
        graph.addAttribute("ui.stylesheet" , labelStyleSheet);
        Collection <Edge> pathEdgeSet = path.getEdgeSet();

        for (Iterator<Edge> iterator = pathEdgeSet.iterator(); iterator.hasNext();) {
            Edge pathEdge = (Edge) iterator.next();

            Edge graphEdge = graph.getEdge(pathEdge.toString());

            graphEdge.setAttribute("ui.class", "selected");


        }

        graph.display();


    }




}
