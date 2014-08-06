package com.ga.tsp;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
/**
 * Created and mantained by Animesh.
 * <animesh . koratana @ gmail . com>
 */
public class MapMaker{

	public static void main(String args[]) {
		new MapMaker();
    }


    public Graph makePresetLargeMap() {
    	Graph graph = new SingleGraph ("inputMap");
        boolean isDirected = false;

        graph.setStrict(false);
        graph.setAutoCreate(true);

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
        graph.addNode("K");
        graph.addNode("L");
        graph.addNode("M");
        graph.addNode("N");
        graph.addNode("O");
        graph.addNode("P");
        graph.addNode("Q");
        graph.addNode("R");
        graph.addNode("S");
        graph.addNode("T");
        graph.addNode("U");
        graph.addNode("V");
        graph.addNode("W");
        graph.addNode("X");
        graph.addNode("Y");
        graph.addNode("Z");
        


        addAllEdgeInstances(graph, isDirected);

        graph.getEdge("AB").setAttribute("weight" , 37.0);
        graph.getEdge("AC").setAttribute("weight" , 87.0);
        graph.getEdge("AD").setAttribute("weight" , 32.0);
        graph.getEdge("AE").setAttribute("weight" , 48.0);
        graph.getEdge("AF").setAttribute("weight" , 0.0);
        graph.getEdge("AG").setAttribute("weight" , 1.0);
        graph.getEdge("AH").setAttribute("weight" , 93.0);
        graph.getEdge("AI").setAttribute("weight" , 45.0);
        graph.getEdge("AJ").setAttribute("weight" , 7.0);
        graph.getEdge("AK").setAttribute("weight" , 28.0);
        graph.getEdge("AL").setAttribute("weight" , 39.0);
        graph.getEdge("AM").setAttribute("weight" , 99.0);
        graph.getEdge("AN").setAttribute("weight" , 54.0);
        graph.getEdge("AO").setAttribute("weight" , 36.0);
        graph.getEdge("AP").setAttribute("weight" , 14.0);
        graph.getEdge("AQ").setAttribute("weight" , 62.0);
        graph.getEdge("AR").setAttribute("weight" , 48.0);
        graph.getEdge("AS").setAttribute("weight" , 66.0);
        graph.getEdge("AT").setAttribute("weight" , 28.0);
        graph.getEdge("AU").setAttribute("weight" , 86.0);
        graph.getEdge("AV").setAttribute("weight" , 86.0);
        graph.getEdge("AW").setAttribute("weight" , 26.0);
        graph.getEdge("AX").setAttribute("weight" , 26.0);
        graph.getEdge("AY").setAttribute("weight" , 65.0);
        graph.getEdge("AZ").setAttribute("weight" , 63.0);
        graph.getEdge("BC").setAttribute("weight" , 92.0);
        graph.getEdge("BD").setAttribute("weight" , 47.0);
        graph.getEdge("BE").setAttribute("weight" , 9.0);
        graph.getEdge("BF").setAttribute("weight" , 58.0);
        graph.getEdge("BG").setAttribute("weight" , 64.0);
        graph.getEdge("BH").setAttribute("weight" , 83.0);
        graph.getEdge("BI").setAttribute("weight" , 19.0);
        graph.getEdge("BJ").setAttribute("weight" , 78.0);
        graph.getEdge("BK").setAttribute("weight" , 79.0);
        graph.getEdge("BL").setAttribute("weight" , 50.0);
        graph.getEdge("BM").setAttribute("weight" , 29.0);
        graph.getEdge("BN").setAttribute("weight" , 92.0);
        graph.getEdge("BO").setAttribute("weight" , 53.0);
        graph.getEdge("BP").setAttribute("weight" , 33.0);
        graph.getEdge("BQ").setAttribute("weight" , 97.0);
        graph.getEdge("BR").setAttribute("weight" , 91.0);
        graph.getEdge("BS").setAttribute("weight" , 85.0);
        graph.getEdge("BT").setAttribute("weight" , 98.0);
        graph.getEdge("BU").setAttribute("weight" , 35.0);
        graph.getEdge("BV").setAttribute("weight" , 1.0);
        graph.getEdge("BW").setAttribute("weight" , 48.0);
        graph.getEdge("BX").setAttribute("weight" , 91.0);
        graph.getEdge("BY").setAttribute("weight" , 3.0);
        graph.getEdge("BZ").setAttribute("weight" , 66.0);
        graph.getEdge("CD").setAttribute("weight" , 58.0);
        graph.getEdge("CE").setAttribute("weight" , 24.0);
        graph.getEdge("CF").setAttribute("weight" , 1.0);
        graph.getEdge("CG").setAttribute("weight" , 82.0);
        graph.getEdge("CH").setAttribute("weight" , 62.0);
        graph.getEdge("CI").setAttribute("weight" , 66.0);
        graph.getEdge("CJ").setAttribute("weight" , 8.0);
        graph.getEdge("CK").setAttribute("weight" , 81.0);
        graph.getEdge("CL").setAttribute("weight" , 21.0);
        graph.getEdge("CM").setAttribute("weight" , 13.0);
        graph.getEdge("CN").setAttribute("weight" , 41.0);
        graph.getEdge("CO").setAttribute("weight" , 98.0);
        graph.getEdge("CP").setAttribute("weight" , 2.0);
        graph.getEdge("CQ").setAttribute("weight" , 62.0);
        graph.getEdge("CR").setAttribute("weight" , 90.0);
        graph.getEdge("CS").setAttribute("weight" , 94.0);
        graph.getEdge("CT").setAttribute("weight" , 81.0);
        graph.getEdge("CU").setAttribute("weight" , 78.0);
        graph.getEdge("CV").setAttribute("weight" , 25.0);
        graph.getEdge("CW").setAttribute("weight" , 34.0);
        graph.getEdge("CX").setAttribute("weight" , 10.0);
        graph.getEdge("CY").setAttribute("weight" , 22.0);
        graph.getEdge("CZ").setAttribute("weight" , 71.0);
        graph.getEdge("DE").setAttribute("weight" , 63.0);
        graph.getEdge("DF").setAttribute("weight" , 53.0);
        graph.getEdge("DG").setAttribute("weight" , 91.0);
        graph.getEdge("DH").setAttribute("weight" , 91.0);
        graph.getEdge("DI").setAttribute("weight" , 3.0);
        graph.getEdge("DJ").setAttribute("weight" , 21.0);
        graph.getEdge("DK").setAttribute("weight" , 56.0);
        graph.getEdge("DL").setAttribute("weight" , 32.0);
        graph.getEdge("DM").setAttribute("weight" , 81.0);
        graph.getEdge("DN").setAttribute("weight" , 46.0);
        graph.getEdge("DO").setAttribute("weight" , 0.0);
        graph.getEdge("DP").setAttribute("weight" , 25.0);
        graph.getEdge("DQ").setAttribute("weight" , 11.0);
        graph.getEdge("DR").setAttribute("weight" , 0.0);
        graph.getEdge("DS").setAttribute("weight" , 73.0);
        graph.getEdge("DT").setAttribute("weight" , 2.0);
        graph.getEdge("DU").setAttribute("weight" , 87.0);
        graph.getEdge("DV").setAttribute("weight" , 41.0);
        graph.getEdge("DW").setAttribute("weight" , 90.0);
        graph.getEdge("DX").setAttribute("weight" , 92.0);
        graph.getEdge("DY").setAttribute("weight" , 56.0);
        graph.getEdge("DZ").setAttribute("weight" , 35.0);
        graph.getEdge("EF").setAttribute("weight" , 33.0);
        graph.getEdge("EG").setAttribute("weight" , 2.0);
        graph.getEdge("EH").setAttribute("weight" , 43.0);
        graph.getEdge("EI").setAttribute("weight" , 96.0);
        graph.getEdge("EJ").setAttribute("weight" , 42.0);
        graph.getEdge("EK").setAttribute("weight" , 30.0);
        graph.getEdge("EL").setAttribute("weight" , 10.0);
        graph.getEdge("EM").setAttribute("weight" , 42.0);
        graph.getEdge("EN").setAttribute("weight" , 73.0);
        graph.getEdge("EO").setAttribute("weight" , 40.0);
        graph.getEdge("EP").setAttribute("weight" , 54.0);
        graph.getEdge("EQ").setAttribute("weight" , 28.0);
        graph.getEdge("ER").setAttribute("weight" , 39.0);
        graph.getEdge("ES").setAttribute("weight" , 41.0);
        graph.getEdge("ET").setAttribute("weight" , 53.0);
        graph.getEdge("EU").setAttribute("weight" , 30.0);
        graph.getEdge("EV").setAttribute("weight" , 51.0);
        graph.getEdge("EW").setAttribute("weight" , 80.0);
        graph.getEdge("EX").setAttribute("weight" , 87.0);
        graph.getEdge("EY").setAttribute("weight" , 29.0);
        graph.getEdge("EZ").setAttribute("weight" , 54.0);
        graph.getEdge("FG").setAttribute("weight" , 30.0);
        graph.getEdge("FH").setAttribute("weight" , 16.0);
        graph.getEdge("FI").setAttribute("weight" , 0.0);
        graph.getEdge("FJ").setAttribute("weight" , 5.0);
        graph.getEdge("FK").setAttribute("weight" , 73.0);
        graph.getEdge("FL").setAttribute("weight" , 87.0);
        graph.getEdge("FM").setAttribute("weight" , 14.0);
        graph.getEdge("FN").setAttribute("weight" , 57.0);
        graph.getEdge("FO").setAttribute("weight" , 37.0);
        graph.getEdge("FP").setAttribute("weight" , 19.0);
        graph.getEdge("FQ").setAttribute("weight" , 2.0);
        graph.getEdge("FR").setAttribute("weight" , 81.0);
        graph.getEdge("FS").setAttribute("weight" , 27.0);
        graph.getEdge("FT").setAttribute("weight" , 43.0);
        graph.getEdge("FU").setAttribute("weight" , 4.0);
        graph.getEdge("FV").setAttribute("weight" , 76.0);
        graph.getEdge("FW").setAttribute("weight" , 18.0);
        graph.getEdge("FX").setAttribute("weight" , 13.0);
        graph.getEdge("FY").setAttribute("weight" , 39.0);
        graph.getEdge("FZ").setAttribute("weight" , 10.0);
        graph.getEdge("GH").setAttribute("weight" , 98.0);
        graph.getEdge("GI").setAttribute("weight" , 80.0);
        graph.getEdge("GJ").setAttribute("weight" , 76.0);
        graph.getEdge("GK").setAttribute("weight" , 21.0);
        graph.getEdge("GL").setAttribute("weight" , 3.0);
        graph.getEdge("GM").setAttribute("weight" , 42.0);
        graph.getEdge("GN").setAttribute("weight" , 97.0);
        graph.getEdge("GO").setAttribute("weight" , 20.0);
        graph.getEdge("GP").setAttribute("weight" , 80.0);
        graph.getEdge("GQ").setAttribute("weight" , 33.0);
        graph.getEdge("GR").setAttribute("weight" , 19.0);
        graph.getEdge("GS").setAttribute("weight" , 4.0);
        graph.getEdge("GT").setAttribute("weight" , 58.0);
        graph.getEdge("GU").setAttribute("weight" , 62.0);
        graph.getEdge("GV").setAttribute("weight" , 54.0);
        graph.getEdge("GW").setAttribute("weight" , 84.0);
        graph.getEdge("GX").setAttribute("weight" , 65.0);
        graph.getEdge("GY").setAttribute("weight" , 96.0);
        graph.getEdge("GZ").setAttribute("weight" , 44.0);
        graph.getEdge("HI").setAttribute("weight" , 39.0);
        graph.getEdge("HJ").setAttribute("weight" , 69.0);
        graph.getEdge("HK").setAttribute("weight" , 75.0);
        graph.getEdge("HL").setAttribute("weight" , 29.0);
        graph.getEdge("HM").setAttribute("weight" , 58.0);
        graph.getEdge("HN").setAttribute("weight" , 45.0);
        graph.getEdge("HO").setAttribute("weight" , 45.0);
        graph.getEdge("HP").setAttribute("weight" , 38.0);
        graph.getEdge("HQ").setAttribute("weight" , 67.0);
        graph.getEdge("HR").setAttribute("weight" , 53.0);
        graph.getEdge("HS").setAttribute("weight" , 47.0);
        graph.getEdge("HT").setAttribute("weight" , 71.0);
        graph.getEdge("HU").setAttribute("weight" , 88.0);
        graph.getEdge("HV").setAttribute("weight" , 47.0);
        graph.getEdge("HW").setAttribute("weight" , 77.0);
        graph.getEdge("HX").setAttribute("weight" , 55.0);
        graph.getEdge("HY").setAttribute("weight" , 97.0);
        graph.getEdge("HZ").setAttribute("weight" , 21.0);
        graph.getEdge("IJ").setAttribute("weight" , 27.0);
        graph.getEdge("IK").setAttribute("weight" , 48.0);
        graph.getEdge("IL").setAttribute("weight" , 89.0);
        graph.getEdge("IM").setAttribute("weight" , 66.0);
        graph.getEdge("IN").setAttribute("weight" , 31.0);
        graph.getEdge("IO").setAttribute("weight" , 53.0);
        graph.getEdge("IP").setAttribute("weight" , 65.0);
        graph.getEdge("IQ").setAttribute("weight" , 39.0);
        graph.getEdge("IR").setAttribute("weight" , 44.0);
        graph.getEdge("IS").setAttribute("weight" , 69.0);
        graph.getEdge("IT").setAttribute("weight" , 23.0);
        graph.getEdge("IU").setAttribute("weight" , 96.0);
        graph.getEdge("IV").setAttribute("weight" , 46.0);
        graph.getEdge("IW").setAttribute("weight" , 14.0);
        graph.getEdge("IX").setAttribute("weight" , 98.0);
        graph.getEdge("IY").setAttribute("weight" , 43.0);
        graph.getEdge("IZ").setAttribute("weight" , 37.0);
        graph.getEdge("JK").setAttribute("weight" , 6.0);
        graph.getEdge("JL").setAttribute("weight" , 39.0);
        graph.getEdge("JM").setAttribute("weight" , 57.0);
        graph.getEdge("JN").setAttribute("weight" , 28.0);
        graph.getEdge("JO").setAttribute("weight" , 73.0);
        graph.getEdge("JP").setAttribute("weight" , 28.0);
        graph.getEdge("JQ").setAttribute("weight" , 32.0);
        graph.getEdge("JR").setAttribute("weight" , 39.0);
        graph.getEdge("JS").setAttribute("weight" , 91.0);
        graph.getEdge("JT").setAttribute("weight" , 49.0);
        graph.getEdge("JU").setAttribute("weight" , 38.0);
        graph.getEdge("JV").setAttribute("weight" , 20.0);
        graph.getEdge("JW").setAttribute("weight" , 17.0);
        graph.getEdge("JX").setAttribute("weight" , 7.0);
        graph.getEdge("JY").setAttribute("weight" , 89.0);
        graph.getEdge("JZ").setAttribute("weight" , 87.0);
        graph.getEdge("KL").setAttribute("weight" , 12.0);
        graph.getEdge("KM").setAttribute("weight" , 28.0);
        graph.getEdge("KN").setAttribute("weight" , 80.0);
        graph.getEdge("KO").setAttribute("weight" , 57.0);
        graph.getEdge("KP").setAttribute("weight" , 11.0);
        graph.getEdge("KQ").setAttribute("weight" , 14.0);
        graph.getEdge("KR").setAttribute("weight" , 57.0);
        graph.getEdge("KS").setAttribute("weight" , 11.0);
        graph.getEdge("KT").setAttribute("weight" , 63.0);
        graph.getEdge("KU").setAttribute("weight" , 98.0);
        graph.getEdge("KV").setAttribute("weight" , 99.0);
        graph.getEdge("KW").setAttribute("weight" , 73.0);
        graph.getEdge("KX").setAttribute("weight" , 64.0);
        graph.getEdge("KY").setAttribute("weight" , 41.0);
        graph.getEdge("KZ").setAttribute("weight" , 76.0);
        graph.getEdge("LM").setAttribute("weight" , 5.0);
        graph.getEdge("LN").setAttribute("weight" , 58.0);
        graph.getEdge("LO").setAttribute("weight" , 89.0);
        graph.getEdge("LP").setAttribute("weight" , 21.0);
        graph.getEdge("LQ").setAttribute("weight" , 62.0);
        graph.getEdge("LR").setAttribute("weight" , 37.0);
        graph.getEdge("LS").setAttribute("weight" , 97.0);
        graph.getEdge("LT").setAttribute("weight" , 19.0);
        graph.getEdge("LU").setAttribute("weight" , 85.0);
        graph.getEdge("LV").setAttribute("weight" , 39.0);
        graph.getEdge("LW").setAttribute("weight" , 3.0);
        graph.getEdge("LX").setAttribute("weight" , 47.0);
        graph.getEdge("LY").setAttribute("weight" , 15.0);
        graph.getEdge("LZ").setAttribute("weight" , 13.0);
        graph.getEdge("MN").setAttribute("weight" , 55.0);
        graph.getEdge("MO").setAttribute("weight" , 33.0);
        graph.getEdge("MP").setAttribute("weight" , 31.0);
        graph.getEdge("MQ").setAttribute("weight" , 34.0);
        graph.getEdge("MR").setAttribute("weight" , 7.0);
        graph.getEdge("MS").setAttribute("weight" , 24.0);
        graph.getEdge("MT").setAttribute("weight" , 60.0);
        graph.getEdge("MU").setAttribute("weight" , 95.0);
        graph.getEdge("MV").setAttribute("weight" , 82.0);
        graph.getEdge("MW").setAttribute("weight" , 13.0);
        graph.getEdge("MX").setAttribute("weight" , 22.0);
        graph.getEdge("MY").setAttribute("weight" , 22.0);
        graph.getEdge("MZ").setAttribute("weight" , 0.0);
        graph.getEdge("NO").setAttribute("weight" , 95.0);
        graph.getEdge("NP").setAttribute("weight" , 5.0);
        graph.getEdge("NQ").setAttribute("weight" , 23.0);
        graph.getEdge("NR").setAttribute("weight" , 24.0);
        graph.getEdge("NS").setAttribute("weight" , 44.0);
        graph.getEdge("NT").setAttribute("weight" , 6.0);
        graph.getEdge("NU").setAttribute("weight" , 49.0);
        graph.getEdge("NV").setAttribute("weight" , 8.0);
        graph.getEdge("NW").setAttribute("weight" , 95.0);
        graph.getEdge("NX").setAttribute("weight" , 66.0);
        graph.getEdge("NY").setAttribute("weight" , 55.0);
        graph.getEdge("NZ").setAttribute("weight" , 52.0);
        graph.getEdge("OP").setAttribute("weight" , 94.0);
        graph.getEdge("OQ").setAttribute("weight" , 34.0);
        graph.getEdge("OR").setAttribute("weight" , 39.0);
        graph.getEdge("OS").setAttribute("weight" , 27.0);
        graph.getEdge("OT").setAttribute("weight" , 70.0);
        graph.getEdge("OU").setAttribute("weight" , 49.0);
        graph.getEdge("OV").setAttribute("weight" , 71.0);
        graph.getEdge("OW").setAttribute("weight" , 98.0);
        graph.getEdge("OX").setAttribute("weight" , 57.0);
        graph.getEdge("OY").setAttribute("weight" , 54.0);
        graph.getEdge("OZ").setAttribute("weight" , 8.0);
        graph.getEdge("PQ").setAttribute("weight" , 63.0);
        graph.getEdge("PR").setAttribute("weight" , 20.0);
        graph.getEdge("PS").setAttribute("weight" , 10.0);
        graph.getEdge("PT").setAttribute("weight" , 63.0);
        graph.getEdge("PU").setAttribute("weight" , 89.0);
        graph.getEdge("PV").setAttribute("weight" , 63.0);
        graph.getEdge("PW").setAttribute("weight" , 38.0);
        graph.getEdge("PX").setAttribute("weight" , 44.0);
        graph.getEdge("PY").setAttribute("weight" , 34.0);
        graph.getEdge("PZ").setAttribute("weight" , 51.0);
        graph.getEdge("QR").setAttribute("weight" , 54.0);
        graph.getEdge("QS").setAttribute("weight" , 86.0);
        graph.getEdge("QT").setAttribute("weight" , 7.0);
        graph.getEdge("QU").setAttribute("weight" , 23.0);
        graph.getEdge("QV").setAttribute("weight" , 44.0);
        graph.getEdge("QW").setAttribute("weight" , 83.0);
        graph.getEdge("QX").setAttribute("weight" , 88.0);
        graph.getEdge("QY").setAttribute("weight" , 6.0);
        graph.getEdge("QZ").setAttribute("weight" , 60.0);
        graph.getEdge("RS").setAttribute("weight" , 47.0);
        graph.getEdge("RT").setAttribute("weight" , 96.0);
        graph.getEdge("RU").setAttribute("weight" , 88.0);
        graph.getEdge("RV").setAttribute("weight" , 74.0);
        graph.getEdge("RW").setAttribute("weight" , 83.0);
        graph.getEdge("RX").setAttribute("weight" , 96.0);
        graph.getEdge("RY").setAttribute("weight" , 31.0);
        graph.getEdge("RZ").setAttribute("weight" , 22.0);
        graph.getEdge("ST").setAttribute("weight" , 60.0);
        graph.getEdge("SU").setAttribute("weight" , 92.0);
        graph.getEdge("SV").setAttribute("weight" , 71.0);
        graph.getEdge("SW").setAttribute("weight" , 93.0);
        graph.getEdge("SX").setAttribute("weight" , 55.0);
        graph.getEdge("SY").setAttribute("weight" , 27.0);
        graph.getEdge("SZ").setAttribute("weight" , 75.0);
        graph.getEdge("TU").setAttribute("weight" , 16.0);
        graph.getEdge("TV").setAttribute("weight" , 27.0);
        graph.getEdge("TW").setAttribute("weight" , 11.0);
        graph.getEdge("TX").setAttribute("weight" , 71.0);
        graph.getEdge("TY").setAttribute("weight" , 94.0);
        graph.getEdge("TZ").setAttribute("weight" , 19.0);
        graph.getEdge("UV").setAttribute("weight" , 40.0);
        graph.getEdge("UW").setAttribute("weight" , 63.0);
        graph.getEdge("UX").setAttribute("weight" , 72.0);
        graph.getEdge("UY").setAttribute("weight" , 89.0);
        graph.getEdge("UZ").setAttribute("weight" , 87.0);
        graph.getEdge("VW").setAttribute("weight" , 92.0);
        graph.getEdge("VX").setAttribute("weight" , 53.0);
        graph.getEdge("VY").setAttribute("weight" , 32.0);
        graph.getEdge("VZ").setAttribute("weight" , 49.0);
        graph.getEdge("WX").setAttribute("weight" , 70.0);
        graph.getEdge("WY").setAttribute("weight" , 48.0);
        graph.getEdge("WZ").setAttribute("weight" , 3.0);
        graph.getEdge("XY").setAttribute("weight" , 82.0);
        graph.getEdge("XZ").setAttribute("weight" , 66.0);
        graph.getEdge("YZ").setAttribute("weight" , 56.0);
        
        for (Edge edge : graph.getEdgeSet())
        {
            edge.addAttribute("ui.label", edge.getAttribute("distance"));
        }
        return graph;
    }
    
    public Graph makePresetSmallMap(){
    	Graph graph = new SingleGraph ("inputMap");
        boolean isDirected = false;

        graph.setStrict(false);
        graph.setAutoCreate(true);

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");

        addAllEdgeInstances(graph, isDirected);
        
        graph.getEdge("AB").setAttribute("weight" , 37.0);
        graph.getEdge("AC").setAttribute("weight" , 87.0);
        graph.getEdge("AD").setAttribute("weight" , 32.0);
        graph.getEdge("AE").setAttribute("weight" , 48.0);
        graph.getEdge("AF").setAttribute("weight" , 46.0);
        graph.getEdge("AG").setAttribute("weight" , 72.0);
        graph.getEdge("BC").setAttribute("weight" , 92.0);
        graph.getEdge("BD").setAttribute("weight" , 47.0);
        graph.getEdge("BE").setAttribute("weight" , 43.0);
        graph.getEdge("BF").setAttribute("weight" , 62.0);
        graph.getEdge("BG").setAttribute("weight" , 64.0);
        graph.getEdge("CD").setAttribute("weight" , 58.0);
        graph.getEdge("CE").setAttribute("weight" , 24.0);
        graph.getEdge("CF").setAttribute("weight" , 40.0);
        graph.getEdge("CG").setAttribute("weight" , 61.0);
        graph.getEdge("DE").setAttribute("weight" , 63.0);
        graph.getEdge("DF").setAttribute("weight" , 53.0);
        graph.getEdge("DG").setAttribute("weight" , 42.0);
        graph.getEdge("EF").setAttribute("weight" , 33.0);
        graph.getEdge("EG").setAttribute("weight" , 38.0);
        graph.getEdge("FG").setAttribute("weight" , 30.0);

        addRandomWeights(graph, "weight");
        
        for (Edge edge : graph.getEdgeSet())
        {
            edge.addAttribute("ui.label", edge.getAttribute("distance"));
        }
        return graph;
    }
    
    
    public Graph makeRandomLargeMap(){
    	Graph graph = new SingleGraph ("inputMap");
        boolean isDirected = false;

        graph.setStrict(false);
        graph.setAutoCreate(true);

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
        graph.addNode("K");
        graph.addNode("L");
        graph.addNode("M");
        graph.addNode("N");
        graph.addNode("O");
        graph.addNode("P");
        graph.addNode("Q");
        graph.addNode("R");
        graph.addNode("S");
        graph.addNode("T");
        graph.addNode("U");
        graph.addNode("V");
        graph.addNode("W");
        graph.addNode("X");
        graph.addNode("Y");
        graph.addNode("Z");
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addNode("d");
        graph.addNode("e");
        graph.addNode("f");
        graph.addNode("g");
        graph.addNode("h");
        graph.addNode("i");
        graph.addNode("j");
        graph.addNode("k");
        graph.addNode("l");
        graph.addNode("m");
        graph.addNode("n");
        graph.addNode("o");
        graph.addNode("p");
        graph.addNode("q");
        graph.addNode("r");
        graph.addNode("s");
        graph.addNode("t");
        graph.addNode("u");
        graph.addNode("v");
        graph.addNode("w");
        graph.addNode("x");
        graph.addNode("y");
        graph.addNode("z");
        


        addAllEdgeInstances(graph, isDirected);

        addRandomWeights(graph, "weight");
        
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
            Double randomDouble = randomInt.doubleValue();
            edge.addAttribute(typeOfAttributeToAdd, randomDouble );
        }
    }
}

