import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Iterator;

public class GraphExplore {
    public static void main(String args[]) {
        new GraphExplore();
        //System.out.print("Hello World");
    }

    public GraphExplore() {
        Graph graph = new SingleGraph("tutorial 1");

        graph.setStrict(false);
        graph.setAutoCreate(true);

        graph.addAttribute("ui.stylesheet", styleSheet + labelStyleSheet);
//        graph.addAttribute("ui.stylesheet", labelStyleSheet);

        graph.display();

        graph.addEdge("AB", "A", "B", true);
        graph.addEdge("BC", "B", "C", true);
        graph.addEdge("CA", "C", "A", true);
        graph.addEdge("AD", "A", "D", true);
        graph.addEdge("DE", "D", "E", true);
        graph.addEdge("DF", "D", "F", true);
        graph.addEdge("EF", "E", "F", true);
        graph.addEdge("EG", "E", "G", true);
        graph.addEdge("EH", "E", "H", true);
        graph.addEdge("EI", "E", "I", true);

        graph.getEdge("AB").setAttribute("distance", 50);
        graph.getEdge("BC").setAttribute("distance", 150);
        graph.getEdge("CA").setAttribute("distance", 250);
        graph.getEdge("AD").setAttribute("distance", 25);
        graph.getEdge("DE").setAttribute("distance", 5);
        graph.getEdge("DF").setAttribute("distance", 50);
        graph.getEdge("EF").setAttribute("distance", 150);

        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }

        for (Edge edge : graph.getEdgeSet())
        {
            edge.addAttribute("ui.label", edge.getAttribute("distance"));
        }


        explore(graph.getNode("A"));
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
                    " shape: line;" +
                    " size: 1px, 0px;" +
                    " fill-color: brown3." +
                    ";" +
                    " text-mode: normal;" +
                    " text-alignment: center;" +
                    "}";

    public void explore(Node source) {
        Iterator<? extends Node> k = source.getBreadthFirstIterator();

        while (k.hasNext()) {
            Node next = k.next();
            next.setAttribute("ui.class", "marked");
            sleep();
        }
    }

    protected void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }
}
