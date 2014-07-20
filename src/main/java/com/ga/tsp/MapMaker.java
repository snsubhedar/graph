package com.ga.tsp;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class MapMaker{

	public static void main(String args[]) {
		new MapMaker();
    }


    public Graph makeRandomMap() {
    	Graph graph = new SingleGraph ("inputMap");
        boolean isDirected = false;

        graph.setStrict(false);
        graph.setAutoCreate(true);

        graph.addAttribute("ui.stylesheet" , labelStyleSheet, styleSheet );



        graph.addEdge("AB", "A", "B", isDirected);
        graph.addEdge("BC", "B", "C", isDirected);
        graph.addEdge("CD", "C", "D", isDirected);
        graph.addEdge("DE", "D", "E", isDirected);
        graph.addEdge("DF", "D", "F", isDirected);
        graph.addEdge("EF", "E", "F", isDirected);
        graph.addEdge("EG", "E", "G", isDirected);
        graph.addEdge("EH", "E", "H", isDirected);
        graph.addEdge("EI", "E", "I", isDirected);


        addAllEdgeInstances(graph, isDirected);

        addRandomWeights(graph, "weight");

        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }

        for (Edge edge : graph.getEdgeSet())
        {
            edge.addAttribute("ui.label", edge.getAttribute("weight"));
        }
        return graph;
    }


	private static void addAllEdgeInstances (Graph graph, boolean isDirected){ //run this function s
        Collection<Node> noteSet = graph.getNodeSet();

        for (Iterator<Node> iterator = noteSet.iterator(); iterator.hasNext();) {
            Node startNode = (Node) iterator.next();

            for (Iterator<Node> iterator2 = noteSet.iterator(); iterator2.hasNext();) {
                Node endNode = (Node) iterator2.next();

                if (!(startNode == endNode)){
                    graph.addEdge(startNode.toString().concat(endNode.toString()), startNode, endNode, isDirected);
                }

            }
        }
    }

    private static void addRandomWeights(Graph graph, String typeOfAttributeToAdd){
        Random r = new Random();
        for (Edge edge : graph.getEdgeSet()){
            Integer randomInt = r.nextInt(100);
            double randomDouble = randomInt.doubleValue();
            edge.addAttribute(typeOfAttributeToAdd, randomDouble );
        }
    }

    protected static String labelStyleSheet =
            "edge {" +
                    " shape: line;" +
                    " size: 1px, 0px;" +
                    " fill-color: brown3" +
                    ";" +
                    " text-mode: normal;" +
                    " text-alignment: center;" +
                    "}";

    protected static String styleSheet =
            "node {" +
                    "       fill-color: black;" +
                    "}" +
                    "node.marked {" +
                    "       fill-color: red;" +
                    "}";



}

