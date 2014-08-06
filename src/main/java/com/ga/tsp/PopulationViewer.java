package com.ga.tsp;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created and mantained by Animesh.
 * <animesh . koratana @ gmail . com>
 */
public class PopulationViewer {
	
	Graph graph;
	
	public PopulationViewer(Graph inputMap){
		graph = inputMap;
        cleanGraph(true);  // clean and add default label attributes
        graph.addAttribute("ui.stylesheet" , styleSheet + " " + labelStyleSheet );
	}
	
	public void showTopPath (TreeMap <Double, ArrayList <Path>> population){
		cleanGraph(false);  //just clean the edge styles
		
		
		Path topPath = population.firstEntry().getValue().get(population.firstEntry().getValue().size()-1);
		markPathOne(topPath);
	}
	
	private void markPathOne(Path path) {
		PopulationMaker pm = new PopulationMaker(graph);
        Collection<Edge> edges = path.getEdgeSet();

        for(Edge edge : edges){
            (pm.getEquivalentEdge(edge.getNode0(), edge.getNode1())).setAttribute("ui.class", "marked");
        }
    }
	
	private void cleanGraph (Boolean addLabel){
        Collection<Edge> edges = graph.getEdgeSet();

        for(Edge edge : edges)
        {
            if(addLabel)
            {
                edge.addAttribute("ui.label", edge.getAttribute("weight"));
                edge.addAttribute("layout.weight", (Double) edge.getAttribute("weight") * 1000);
            }
            edge.setAttribute("ui.class", "unMarked");
        }

        if(addLabel)
        {
            for (Node node : graph) {
                node.addAttribute("ui.label", node.getId());
            }
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
            "edge   {" +
                    "       shape: line;" +
                    "       size: 1px, 0px;" +
                    "       fill-color: black;" +
                    "       text-mode: normal;" +
                    "       text-style: bold;" +
                    "       text-alignment: along;" +
                    "}" +
                    "edge.marked {" +
                    "       fill-color: red;" +
                    "       size: 3px, 0px;" +
                    "}" +
                    "edge.unMarked {" +
                    "       fill-color: black;" +
                    "       size: 1px, 0px;" +
                    "}";
}
