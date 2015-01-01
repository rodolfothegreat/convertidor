package dettorre.rodolfo.convertidor;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ConvertidorActivity extends Activity 
implements OnClickListener , OnItemSelectedListener{
    /** Called when the activity is first created. */
   
	private Button fBtnConvert;
    private Spinner fspinnerUnitType;
    private Spinner fspinnerFrom;
    private Spinner fspinnerTo;
    private TemperatureConverter fTemperatureConverter;
    private ProportionalConverter fWeightConverter;
    private ProportionalConverter fDistanceConverter;
    private ProportionalConverter fVolumeConverter;
    private ProportionalConverter fAreaConverter;
   
    private ArrayAdapter <String> fWeightAdapter;
    private ArrayAdapter <String> fTemperatureAdapter;
    private ArrayAdapter <String> fDistanceAdapter;
    private ArrayAdapter <String> fVolumeAdapter;
    private ArrayAdapter <String> fAreaAdapter;
    private converty[] fConvertyList;
    private EditText fEditValue;
    private TextView ftextViewResult;
    private void InitializeObjects() {
    	
    	String[] VolumeUnits = new String[] {"cubic meter(m3)", "cubic centimeter (cc, cm3)", "cubic millimeter (mm3",
    			"litre (l)","cubic foot (ft3)", "cubic inch (in3)", "gallon (UK) (gal)", "pint (UK) (pt)",
    			"fluid ounce (UK) (fl oz)", "Cup", "Tea Spoon", "Table Spoon"};
    	
    	double[] VolumeValues = new double[] {1000000, 1, 0.001, 1000, 28316.846592, 16.387064, 4546.09, 568.26125,
    			29.5735295625, 250, 5, 20}; 
    	
    	
    	String[] DistanceUnits = new String[] {"meter (m)", "centimeter (cm)", "millimeter (mm)",
    			"kilometer (Km)", "mile (mi)", "nautical mile (M)", "yard (yd)", "foot (ft)", "inch (in)"};
    	
    	double[] DistanceValues = new double[] {100, 1, 0.1, 100000, 160934.4, 185200, 91.44, 30.48, 2.54};  
    	
    	String[] WeightUnits = new String[] {"gram (g)", "milligram (mg)", "kilogram (kg)", "tonne", 
    				"pound", "ounce", "stone"};
    	double[] WeightValues = new double[] {1, 0.001, 1000, 1000000, 453.59237, 28.349523125, 6350.29318};

    	String[] AreaUnits = new String[] {"square kilomenter (Km2)", "square meter (m2)", "square centimeter (cm2)",
    				"square millimeter (mm2)", "hectare (ha)", "acre", "square foot (ft2)", "square inch (in2)",
    				"square yard (yd2)", "square mile", "Caballería (Guatemala)", "Caballería (Mexico)",
    				"Caballería (Cuba)", "vara2", "Manzana"};
    	
    	double[] AreaValues = new double[] {1000000, 1, 0.0001, 0.000001, 10000, 4046.8564224,
    			0.09290304, 0.00064516, 0.83612736, 2589988.110336, 451266.74, 427956.75, 134202.38, 0.698896, 6988.96 }; 

    	
    	
    	fWeightConverter.setConverter(WeightUnits, WeightValues);
    	fDistanceConverter.setConverter(DistanceUnits, DistanceValues);
    	fVolumeConverter.setConverter(VolumeUnits, VolumeValues);
    	fAreaConverter.setConverter(AreaUnits, AreaValues);
  	
    	fTemperatureAdapter = new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item );
    	fTemperatureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	 
    	for( int i1 = 0; i1 < fTemperatureConverter.getItems().length; i1++) {
    		String sItem = fTemperatureConverter.getItems()[i1];
    		fTemperatureAdapter.add(sItem);
    	}
    	
    	fWeightAdapter = new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item );
    	fWeightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    	fDistanceAdapter = new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item );
    	fDistanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    	fVolumeAdapter = new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item );
    	fVolumeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	
    	fAreaAdapter = new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item );
    	fAreaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	
    	for( int i1 = 0; i1 < fWeightConverter.getItems().length; i1++) {
    		String sItem = fWeightConverter.getItems()[i1];
    		fWeightAdapter.add(sItem);
    	}

    	for( int i1 = 0; i1 < fDistanceConverter.getItems().length; i1++) {
    		String sItem = fDistanceConverter.getItems()[i1];
    		fDistanceAdapter.add(sItem);
    	}



    	for( int i1 = 0; i1 < fVolumeConverter.getItems().length; i1++) {
    		String sItem = fVolumeConverter.getItems()[i1];
    		fVolumeAdapter.add(sItem);
    	}
    	
    	for( int i1 = 0; i1 < fAreaConverter.getItems().length; i1++) {
    		String sItem = fAreaConverter.getItems()[i1];
    		fAreaAdapter.add(sItem);
    	}
    	
    	fspinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
    	fspinnerTo = (Spinner) findViewById(R.id.spinnerTo);
    	fspinnerFrom.setAdapter(fWeightAdapter);
    	fspinnerTo.setAdapter(fWeightAdapter);
    	
    	fConvertyList = new converty[5];
    	fConvertyList[0] = fTemperatureConverter;
    	fConvertyList[1] = fWeightConverter;
    	fConvertyList[2] = fDistanceConverter;
    	fConvertyList[3] = fVolumeConverter;
    	fConvertyList[4] = fAreaConverter;

        fspinnerFrom.setOnItemSelectedListener(this);
        fspinnerTo.setOnItemSelectedListener(this);
    }	

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fspinnerUnitType = (Spinner) findViewById(R.id.spinnerUnitType);
        fspinnerUnitType.setOnItemSelectedListener(this);

        
        ArrayAdapter <CharSequence> adapter =
        	  new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("Weight");
        adapter.add("Temperature");	 
        adapter.add("Distance");
        adapter.add("Volume");
        adapter.add("Area");
        fspinnerUnitType.setAdapter(adapter);
        fTemperatureConverter = new TemperatureConverter("Temperature");
        fWeightConverter = new ProportionalConverter("Weight");
        fDistanceConverter = new ProportionalConverter("Distance");
        fVolumeConverter = new ProportionalConverter("Volume");
        fAreaConverter = new ProportionalConverter("Area");
              
        fBtnConvert = (Button) findViewById(R.id.btnConvert);
        fEditValue = (EditText) findViewById(R.id.editValue);
        ftextViewResult = (TextView) findViewById(R.id.textViewResult);
        fBtnConvert.setOnClickListener(this);
        ftextViewResult.setText( "" );
        InitializeObjects();
        
    }
    
    public void onClick(View v) {
    	
    	if (v==fBtnConvert){
    		converty AConverty;
    		AConverty = null;
    		for(int i = 0; i < fConvertyList.length; i++) {
    			if (fspinnerUnitType.getSelectedItem().toString().equalsIgnoreCase(fConvertyList[i].getIdentity())) {
    				AConverty = fConvertyList[i];
    			}
    			
    		}
    		if (AConverty == null) {
    			ftextViewResult.setText("here");
    			return;
    		}
    		
    		String UnitFrom = fspinnerFrom.getSelectedItem().toString();
    		String UnitTo = fspinnerTo.getSelectedItem().toString();
    	
    		String ValueFromSt = fEditValue.getText().toString(); 
    		double ValueFrom ;
    		try{
    			ValueFrom = Double.parseDouble(ValueFromSt);
    		}
    			catch(NumberFormatException e) {
    				// if the code gets to here, it was NOT recognizable as a double
    			ValueFrom = 0;
    		}   
    		
    		double aresult = AConverty.convert(UnitFrom, UnitTo, ValueFrom);
            BigDecimal bd =  BigDecimal.valueOf(aresult);
           // ftextViewResult.setText( " " + String.valueOf(aresult)  );
            ftextViewResult.setText(" " + bd.toPlainString());
    		
    	}
    	
    } 




	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {

        if (arg0.getChildCount() != 0){
        //    ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLUE);
            ((TextView) arg0.getChildAt(0)).setTextSize(20);
        }


        if (arg0 != fspinnerUnitType) {
            return;

        }

        String NewUnitType = fspinnerUnitType.getSelectedItem().toString();
		if (NewUnitType.equalsIgnoreCase("weight")) {
	    	fspinnerFrom.setAdapter(fWeightAdapter);
	    	fspinnerTo.setAdapter(fWeightAdapter);		
		}
		
		if (NewUnitType.equalsIgnoreCase("temperature")) {
	    	fspinnerFrom.setAdapter(fTemperatureAdapter);
	    	fspinnerTo.setAdapter(fTemperatureAdapter);		
			
		}

		if (NewUnitType.equalsIgnoreCase("distance")) {
	    	fspinnerFrom.setAdapter(fDistanceAdapter);
	    	fspinnerTo.setAdapter(fDistanceAdapter);		
			
		}

		if (NewUnitType.equalsIgnoreCase("volume")) {
	    	fspinnerFrom.setAdapter(fVolumeAdapter);
	    	fspinnerTo.setAdapter(fVolumeAdapter);		
			
		}
		
		if (NewUnitType.equalsIgnoreCase("area")) {
	    	fspinnerFrom.setAdapter(fAreaAdapter);
	    	fspinnerTo.setAdapter(fAreaAdapter);		
			
		}
		
	}


	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
    
    
    
}