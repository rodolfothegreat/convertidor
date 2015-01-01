package dettorre.rodolfo.convertidor;

import java.util.LinkedHashMap;
import java.util.Set;

public class ProportionalConverter extends converty {

	LinkedHashMap<String, Double> fTheElements;
	public ProportionalConverter(String AnIdenty) {
		super(AnIdenty);
		fTheElements = new LinkedHashMap<String, Double>();
	}

	@Override
	public double convert(String FromUnit, String ToUnit, double FromValue) {
		double fromCoef, toCoef;
		fromCoef = fTheElements.get(FromUnit);
		toCoef = fTheElements.get(ToUnit);
		if ((fromCoef == 0) | (toCoef == 0))
			return 0.0;
		else if (FromUnit.equalsIgnoreCase(ToUnit))
			return FromValue;
		else
			return FromValue * fromCoef /toCoef ;
	}
	
	
	@Override
	public  String[] getItems() {
		Set <String> TheItems = fTheElements.keySet();	
		return (String[]) TheItems.toArray(new String[TheItems.size()]) ;
	}
	
	public void setConverter(String[] TheItems, double[] TheValues) {
		fTheElements.clear();
		int ALength = TheItems.length;
		for(int i = 0; i < ALength; i++) {
			fTheElements.put(TheItems[i], new Double(TheValues[i]));		
		}
	}

}
