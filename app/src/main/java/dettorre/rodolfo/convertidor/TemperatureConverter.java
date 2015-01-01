/**
 * 
 */
package dettorre.rodolfo.convertidor;

/**
 * @author Rodolfo d'Ettorre
 *
 */
public class TemperatureConverter extends converty {

	
	private final static String Celcius = "Celcius";
	private final static String Fahrenheit = "Fahrenheit";
	/**
	 * @param AnIdenty
	 */
	public TemperatureConverter(String AnIdenty) {
		super(AnIdenty);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dettorre.rodolfo.convertidor.converty#convert(java.lang.String, java.lang.String, float)
	 */
	@Override
	public double convert(String FromUnit, String ToUnit, double FromValue) {
		if (FromUnit.equalsIgnoreCase(ToUnit))
			return FromValue;
		else if (FromUnit.equalsIgnoreCase(Celcius))
			return (FromValue * 9 / 5) + 32;
		else if (FromUnit.equalsIgnoreCase(Fahrenheit))
			return (FromValue - 32) * 5 / 9;
		else
			return 0.0;
		
	}
	
	@Override
	public  String[] getItems() {
		String[] MyItems = new String[2];
		MyItems[0] =  Celcius;
		MyItems[1] = Fahrenheit ;
		return MyItems;
	}

}
