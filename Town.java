public class Town implements Comparable<Town>{
	
	private String townName;
	
	public Town (String name) {
		
		this.townName = name;
		
	}
	
	public Town (Town templateTown) {
		
		townName = templateTown.getName();
		
	}
	
	public String getName() {
		
		return townName;
		
	}
	
	public int compareTo(Town otherTown) {
		
		return townName.compareTo(otherTown.getName());
		
	}
	
	public String toString() {
		
		return townName;
		
	}
	
	public int hashCode() {
		
		return townName.hashCode();
		
	}
	
	public boolean equals(Object obj) {
		
		Town other = (Town)obj;
		return townName.equals(other.getName());
		
	}

}