import java.io.*;
import java.util.*;

public class TownGraphManager implements TownGraphManagerInterface {

	private Graph townGraph = new Graph();
	
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		
		return (townGraph.addEdge(new Town(town1), new Town(town2), weight, roadName) != null);	
		
	}

	public String getRoad(String town1, String town2) {
		
		return townGraph.getEdge(new Town(town1), new Town(town2)).getName();
		
	}

	public boolean addTown(String v) {
		
		return townGraph.addVertex(new Town(v));
		
	}
	
	public Town getTown(String name) {
		
		if(townGraph.containsVertex(new Town(name)))
			
			return new Town(name);
		
		else 
			
			return null;
		
	}

	public boolean containsTown(String v) {
		
		return townGraph.containsVertex(new Town(v));
		
	}

	public boolean containsRoadConnection(String town1, String town2) {
		
		return townGraph.containsEdge(new Town(town1), new Town(town2));
		
	}

	public ArrayList<String> allRoads() {
		
		ArrayList<Road> roads = new ArrayList<Road>(townGraph.edgeSet());
		ArrayList<String> toReturn = new ArrayList<String>();
		
		for(int i = 0; i < roads.size(); i++) {
			
			toReturn.add(roads.get(i).getName());
			
		}
		
		for(int i = 0; i < toReturn.size(); i++) {
			
			int minIndex = i;
			
			for(int n = i; n < toReturn.size(); n++) {
				
				if(toReturn.get(minIndex).compareTo(toReturn.get(n)) > 0)
					
					minIndex = n;
				
			}
			
			String temp = toReturn.get(i);
			toReturn.set(i, toReturn.get(minIndex));
			toReturn.set(minIndex, temp);
			
		}
		
		return toReturn;
		
	}

	public boolean deleteRoadConnection(String town1, String town2, String road) {
		
		return (townGraph.removeEdge(new Town(town1), new Town(town2), -1, road) != null);
		
	}

	public boolean deleteTown(String v) {
		
		return townGraph.removeVertex(new Town(v));
		
	}

	public ArrayList<String> allTowns() {
		
		ArrayList<Town> towns = new ArrayList<Town>(townGraph.vertexSet());
		ArrayList<String> toReturn = new ArrayList<String>();
		
		for(int i = 0; i < towns.size(); i++) {
			
			toReturn.add(towns.get(i).getName());
			
		}
		
		for(int i = 0; i < toReturn.size(); i++) {
			
			int minIndex = i;
			
			for(int n = i; n < toReturn.size(); n++) {
				
				if(toReturn.get(minIndex).compareTo(toReturn.get(n)) > 0)
					
					minIndex = n;
				
			}
			
			String temp = toReturn.get(i);
			toReturn.set(i, toReturn.get(minIndex));
			toReturn.set(minIndex, temp);
			
		}
		
		return toReturn;
		
	}

	public ArrayList<String> getPath(String town1, String town2) {
		
		return townGraph.shortestPath(new Town(town1), new Town(town2));
		
	}

	public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException {
		
		Scanner inFile = new Scanner(selectedFile);
		
		while (inFile.hasNextLine()) {
			
			try {
				
				String[] nextLine = inFile.nextLine().split(",|\\;");
				townGraph.addVertex(new Town(nextLine[2]));
				townGraph.addVertex(new Town(nextLine[3]));
				townGraph.addEdge(new Town(nextLine[2]), new Town(nextLine[3]), Integer.parseInt(nextLine[1]), nextLine[0]);
			
			}
			
			catch (Exception e) {
				
				inFile.close();
				throw new java.io.IOException();
				
			}
			
		}
		
		inFile.close();
		
	}
	
}
