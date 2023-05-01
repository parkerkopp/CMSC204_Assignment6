
public class Road implements Comparable<Road>{
	
	private Town source;
	private Town destination;
	private int weight;
	private String name;
	
	public Road(Town source, Town destination, int weight, String name) {
		
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.name = name;
		
	}
	
	public Road(Town source, Town destination, String name) {
		
		this.source = source;
		this.destination = destination;
		weight = 1;
		this.name = name;
		
	}
	
	public Road(Road otherRoad) {
		
		this.source = otherRoad.source;
		this.destination = otherRoad.destination;
		this.weight = otherRoad.weight;
		this.name = otherRoad.name;
		
	}
	
	public int compareTo(Road otherRoad) {
		
		return name.compareTo(otherRoad.getName());
		
	}
	
	public boolean equals(Object r) {
		
		Road otherRoad = (Road)r;
		
		if(source.equals(otherRoad.getSource()) && destination.equals(otherRoad.getDestination()))
			
			return true;
		
		else if(source.equals(otherRoad.getDestination()) && destination.equals(otherRoad.getSource()))
			
			return true;
		
		else
			
			return false;
		
		
	}
	
	public Town getDestination() {
		
		return destination;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public Town getSource() {
		
		return source;
		
	}
	
	public int getWeight() {
		
		return weight;
		
	}
	
	public String toString() {
		
		return source.toString() + ", " + destination.toString() + ", " + weight + ", " + name;
		
	}

}
