import java.util.*;
import java.io.*;

public class Graph implements GraphInterface<Town,Road> {
	
	private ArrayList<Town> townList = new ArrayList<>();
	private ArrayList<Road> roadList = new ArrayList<>();
	private int[] distance;
	private String[] previous;

	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		
		if (sourceVertex == null || destinationVertex == null)
			
			return null;
		
		Road temp = new Road(sourceVertex,destinationVertex,"");
	
		for(int i = 0; i < roadList.size(); i++) {
			
			if(roadList.get(i).equals(temp))
				
				return roadList.get(i);
			
		}
		
		return null;
		
	}

	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) throws NullPointerException, IllegalArgumentException{
		
		if (sourceVertex == null || destinationVertex == null)
			
			throw new NullPointerException();
		
		if(townList.contains(sourceVertex) == false || townList.contains(destinationVertex) == false)
			
			throw new IllegalArgumentException();
		
		Road toAdd = new Road(sourceVertex,destinationVertex,weight,description);
		
		for(int i = 0; i < roadList.size(); i++)
			
			if(roadList.get(i).equals(toAdd))
				
				return null;
		
		roadList.add(toAdd);
		return toAdd;
		
	}

	public boolean addVertex(Town v) throws NullPointerException{
		
		if(v == null)
			throw new NullPointerException();
		
		for(int i = 0; i < townList.size(); i++) {
			
			if(townList.get(i).equals(v))
				
				return false;
			
		}
		
		townList.add(v);
		return true;
		
	}

	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		
		if(sourceVertex == null || destinationVertex == null)
			
			return false;
		
		Road temp = new Road(sourceVertex,destinationVertex,"");
		
		for(int i = 0; i < roadList.size(); i++)
			
			if(roadList.get(i).equals(temp))
				
				return true;
		
		return false;
		
	}

	public boolean containsVertex(Town v) {
		
		if(v == null)
			
			return false;
		
		for(int i = 0; i < townList.size(); i++) {
			
			if(townList.get(i).equals(v))
				
				return true;
			
		}
		
		return false;
		
	}

	public Set<Road> edgeSet() {
		
		Set<Road> toReturn = new HashSet<>();
		
		for(int i = 0; i < roadList.size(); i++)
			
			toReturn.add(roadList.get(i));
		
		return toReturn;
		
	}

	public Set<Road> edgesOf(Town vertex) throws NullPointerException, IllegalArgumentException{
		
		if (vertex == null)
			
			throw new NullPointerException();
		
		if(townList.contains(vertex) == false)
			
			throw new IllegalArgumentException();
		
		Set<Road> toReturn = new HashSet<>();
		
		for(int i = 0; i < roadList.size(); i++)
			
			if(roadList.get(i).getSource().equals(vertex) || roadList.get(i).getDestination().equals(vertex))
				
				toReturn.add(roadList.get(i));
		
		return toReturn;
		
	}
	
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		Road toReturn = new Road(sourceVertex, destinationVertex, weight, description); 
		Road tempRoad = new Road(sourceVertex, destinationVertex, weight, description);
		
		if (weight > -1 && description != null) {
			
			for(int i = 0; i < roadList.size(); i++) {
				
				if(roadList.get(i).equals(tempRoad) && roadList.get(i).getWeight() == tempRoad.getWeight() && roadList.get(i).getName().equals(tempRoad.getName())) {
					
					toReturn = roadList.get(i);
					roadList.remove(i);
					return toReturn;
					
				}
				
			}
			
		}
		
		if (weight > -1 && description == null) {
			
			for(int i = 0; i < roadList.size(); i++) {
				
				if(roadList.get(i).equals(tempRoad) && roadList.get(i).getWeight() == toReturn.getWeight()) {
					
					toReturn = roadList.get(i);
					roadList.remove(i);
					return toReturn;
					
				}
				
			}
			
		}
		
		if (weight <= -1 && description != null) {
			
			for(int i = 0; i < roadList.size(); i++) {
				
				if(roadList.get(i).equals(tempRoad) && roadList.get(i).getName().equals(tempRoad.getName())) {
					
					toReturn = roadList.get(i);
					roadList.remove(i);
					return toReturn;
					
				}
				
			}
			
		}
		
		if (weight <= -1 && description == null) {
			
			for(int i = 0; i < roadList.size(); i++) {
				
				if(roadList.equals(tempRoad)) {
					
					toReturn = roadList.get(i);
					roadList.remove(i);
					return toReturn;
					
				}
				
			}
			
		}
		
		return null;
		
	}

	public boolean removeVertex(Town v) {
		
		if(v == null || townList.contains(v) == false)
			
			return false;
		
		roadList.removeAll(this.edgesOf(v));
		townList.remove(v);
		return true;
		
	}

	public Set<Town> vertexSet() {
		
		Set<Town> toReturn = new HashSet<Town>();
		
		for(int i = 0; i <townList.size(); i++)
			
			toReturn.add(townList.get(i));
		
		return toReturn;
		
	}

	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		
		dijkstraShortestPath(sourceVertex);
		ArrayList<String> pathway = new ArrayList<String>();
		ArrayList<Integer> degree = new ArrayList<Integer>();
		ArrayList<String> townNames = new ArrayList<>();
		
		for(Town town : this.vertexSet()) {
			
			townNames.add(town.getName());
			
		}
		
		int index = townNames.indexOf(destinationVertex.getName());
		pathway.add(destinationVertex.getName());
		
		while (previous[index] != null) {
			
			degree.add(distance[index]);
			pathway.add(previous[index]);
			index = townNames.indexOf(previous[index]);

		}
		
		Collections.reverse(degree);
		Collections.reverse(pathway);
		
		ArrayList<String> toReturn = new ArrayList<String>();
		int total = 0;
		
		for (int i = 0; i<pathway.size()-1; i++) {
			
			toReturn.add(pathway.get(i) + " via " + ((getEdge(new Town(pathway.get(i)), new Town(pathway.get(i+1)))).getName()) + " to " + pathway.get(i+1) + " " + (degree.get(i)-total) + " mi");
			total += degree.get(i)-total;
			
		}
		return toReturn;
	}

	public void dijkstraShortestPath(Town sourceVertex) {
		
		String sourceName = sourceVertex.getName();
		ArrayList<String> townNames = new ArrayList<String>();
		ArrayList<String> unvisited = new ArrayList<String>();
		
		for (Town town : this.vertexSet()) {
			
			townNames.add(town.getName());
			unvisited.add(town.getName());
			
		}
		
		distance = new int[townNames.size()];
		previous = new String[townNames.size()];

		Arrays.fill(distance, 999);
		distance[townNames.indexOf(sourceName)] = 0;

		while (unvisited.isEmpty() == false) {
			
			HashMap<String, Road> adjacentTowns = getAdjacentTowns(new Town(sourceName));
			
			for (String s : adjacentTowns.keySet()) {
				
				if (adjacentTowns.get(s) != null && unvisited.indexOf(s) != -1) {
					
					int weight = adjacentTowns.get(s).getWeight();
					int currentIndex = townNames.indexOf(sourceName);
					int index = townNames.indexOf(s);
					
					if (distance[index] > distance[currentIndex] + weight) {
						
						previous[index] = sourceName;
						distance[index] = weight + distance[currentIndex];
						
					}
					
				}
				
			}
			
			unvisited.remove(unvisited.indexOf(sourceName));
			
			if(unvisited.isEmpty())
				
				break;
			
			int shortestIndex = -1;
			int shortestElement = 999;
			
			for (int i = 0; i < unvisited.size(); i++) {
				
				int ind = townNames.indexOf(unvisited.get(i));
				
				if (shortestElement > distance[ind]) {
					
					shortestElement = distance[ind];
					shortestIndex = ind;
					
				}
				
			}
			
			if(shortestIndex == -1)
				
				break;
			
			sourceName = townNames.get(shortestIndex);
			
		}
		
	}
	
	public HashMap<String,Road> getAdjacentTowns(Town t){
		
		HashMap<String,Road> toReturn= new HashMap<String,Road>();
		
		for(Road i : this.edgesOf(t)) {
			
			if (i.getSource().equals(t))
				
				toReturn.put(i.getDestination().getName(), i);
			
			else 
				
				toReturn.put(i.getSource().getName(), i);
			
		}
		
		return toReturn;
		
	}
	
}
