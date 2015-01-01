package dettorre.rodolfo.convertidor;

public abstract class converty {
	private String fIdentity;
	public String getIdentity() {
		return fIdentity; 
	}
	public converty(String AnIdenty)
	{
		fIdentity = AnIdenty;
	}
	public abstract double convert(String FromUnit, String ToUnit, double FromValue);
	public abstract String[] getItems();
	public String toString() {
		return fIdentity;
	}

	
}
