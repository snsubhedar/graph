package com.ga.tsp;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by Animesh on 7/25/14.
 */
public class PopulationViewer {
	
	Graph graph;
	
	public PopulationViewer(Graph inputMap){
		graph = inputMap;
	}
	
	public void showTopPaths (TreeMap <Double, ArrayList <Path>> population){
		cleanGraph();
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }
		graph.addAttribute("ui.stylesheet" , styleSheet + " " + labelStyleSheet );
		
		Path path = population.firstEntry().getValue().get(population.firstEntry().getValue().size() - 1);
		markPathOne(path);
		
		
	}
	
	public void markPathOne(Path path) {
		PopulationMaker pm = new PopulationMaker(graph);
        Collection<Edge> edges = path.getEdgeSet();

        for(Edge edge : edges){
            (pm.getEquivalentEdge(edge.getNode0(), edge.getNode1())).setAttribute("ui.class", "marked");
//            sleep();
        }
    }
	
	public void cleanGraph (){
        Collection<Edge> edges = graph.getEdgeSet();

        for(Edge edge : edges)
        {
            edge.setAttribute("ui.class", "unMarked");
        }
	}
	
	
	
    protected void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }
	
	
	
	
    public void showPopulation(TreeMap<Double, ArrayList<Path>> population){

        Iterator<Entry<Double, ArrayList<Path>>> it = population.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            System.out.println(pairs.getKey() + " : " + showPaths((ArrayList<Path>) pairs.getValue()));
        }

    }
    private String showPaths(ArrayList <Path> paths){
        StringBuilder builder = new StringBuilder();

        for (Path path : paths){
            builder.append(path.toString());
        }

        return builder.toString();

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
                    "       fill-color: black;" +
                    "       text-mode: normal;" +
                    "       text-alignment: center;" +
                    "}" +
                    "edge.marked {" +
                    "       fill-color: red;" +
                    "}" +
                    "edge.unMarked {" +
                    "       fill-color: black;" +
                    "}";
}
