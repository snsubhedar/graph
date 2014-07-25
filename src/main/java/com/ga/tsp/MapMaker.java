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

        graph.addAttribute("ui.stylesheet");

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("H");
        graph.addNode("I");
        graph.addNode("J");


        addAllEdgeInstances(graph, isDirected);

        addRandomWeights(graph, "weight");
//        graph.getEdge("AB").setAttribute("weight", 50.0);
//        graph.getEdge("BC").setAttribute("weight", 150.0);
//        graph.getEdge("CD").setAttribute("weight", 250.0);
//        graph.getEdge("AC").setAttribute("weight", 250.0);
//        graph.getEdge("AD").setAttribute("weight", 250.0);
//        graph.getEdge("BD").setAttribute("weight", 250.0);
//        graph.getEdge("AD").setAttribute("weight", 25);
//        graph.getEdge("DE").setAttribute("weight", 5);
//        graph.getEdge("DF").setAttribute("weight", 50);
//        graph.getEdge("EF").setAttribute("weight", 150);
        for (Edge edge : graph.getEdgeSet())
        {
            edge.addAttribute("ui.label", edge.getAttribute("distance"));
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

    protected String styleSheet =
            "node {" +
                    "       fill-color: black;" +
                    "}" +
                    "node.marked {" +
                    "       fill-color: red;" +
                    "}";

    protected String labelStyleSheet =
            "edge {" +
                    "       shape: line;" +
                    "       size: 1px, 0px;" +
                    "       fill-color: yellow;" +
                    "       text-mode: normal;" +
                    "       text-alignment: center;" +
                    "}" +
                    "edge.marked {" +
                    "       fill-color: red;" +
                    "}" +
                    "edge.unMarked {" +
                    "       fill-color: yellow;" +
                    "}";



}

