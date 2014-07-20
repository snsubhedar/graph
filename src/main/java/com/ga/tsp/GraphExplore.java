package com.ga.tsp;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.Viewer;

import java.util.Collection;
import java.util.Iterator;

public class GraphExplore {

    Graph graph;

    public GraphExplore(Graph graph)
    {
        this.graph = graph;
    }

    public static void main(String args[]) {
        new com.ga.tsp.GraphExplore();
    }

    public static void show () {
        new com.ga.tsp.GraphExplore();
    }
    public GraphExplore() {
        Graph graph = new SingleGraph("inputMap");
        boolean isDirected = false;

        graph.setStrict(false);
        graph.setAutoCreate(true);

        graph.addAttribute("ui.stylesheet" , styleSheet + " " + labelStyleSheet );



        graph.addEdge("AB", "A", "B", isDirected);
        graph.addEdge("BC", "B", "C", isDirected);
        graph.addEdge("CD", "C", "D", isDirected);

        addAllEdgeInstances(graph, isDirected);
        /*graph.addEdge("AD", "A", "D", isDirected);
        graph.addEdge("DE", "D", "E", isDirected);
        graph.addEdge("DF", "D", "F", isDirected);
        graph.addEdge("EF", "E", "F", isDirected);
        graph.addEdge("EG", "E", "G", isDirected);
        graph.addEdge("EH", "E", "H", isDirected);
        graph.addEdge("EI", "E", "I", isDirected);*/

        graph.getEdge("AB").setAttribute("weight", 50.0);
        graph.getEdge("BC").setAttribute("weight", 150.0);
        graph.getEdge("CD").setAttribute("weight", 250.0);
        graph.getEdge("AC").setAttribute("weight", 250.0);
        graph.getEdge("AD").setAttribute("weight", 250.0);
        graph.getEdge("BD").setAttribute("weight", 250.0);
        /*graph.getEdge("AD").setAttribute("weight", 25);
        graph.getEdge("DE").setAttribute("weight", 5);
        graph.getEdge("DF").setAttribute("weight", 50);
        graph.getEdge("EF").setAttribute("weight", 150);*/

        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }

        for (Edge edge : graph.getEdgeSet())
        {
            edge.addAttribute("ui.label", edge.getAttribute("distance"));
        }
        Viewer viewer = graph.display();

        explore(graph);
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

    public void explore(Node source) {
        Iterator<? extends Node> k = source.getBreadthFirstIterator();

        while (k.hasNext()) {
            Node next = k.next();
            next.setAttribute("ui.class", "marked");
            sleep();
        }
    }

    public void explore(Graph graph) {

        Collection<Edge> edges = graph.getEdgeSet();

        for(Edge edge : edges)
        {
            edge.setAttribute("ui.class", "marked");
            sleep();
        }

        for(Edge edge : edges)
        {
            edge.setAttribute("ui.class", "unMarked");
            sleep();
        }
    }

    //waits for 1 second
    protected void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }


    private static void addAllEdgeInstances (Graph graph, boolean isDirected){
        Collection<Node> noteSet = graph.getNodeSet();

        for (Iterator iterator = noteSet.iterator(); iterator.hasNext();) {
            Node startNode = (Node) iterator.next();

            for (Iterator<Node> iterator2 = noteSet.iterator(); iterator2.hasNext();) {
                Node endNode = iterator2.next();

                if (!(startNode == endNode)){
                    graph.addEdge(startNode.toString().concat(endNode.toString()), startNode, endNode, isDirected);
                }

            }
        }
    }
}


