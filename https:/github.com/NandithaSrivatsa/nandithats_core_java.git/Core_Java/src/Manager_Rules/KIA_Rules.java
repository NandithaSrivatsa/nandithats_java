package Manager_Rules;

import Jr_Developer.*; // import Package.*; For all Class
import Jr_Developer.KIA_SpeedLimit; // import Package.Classname

public class KIA_Rules {

	public static void main(String[] args) {
		
		KIA_AirBags ob = new KIA_AirBags();
		ob.display_Air();
		
		KIA_SpeedLimit ob1 = new KIA_SpeedLimit();
		ob1.display_speed();
		
		//Jr_Developer.KIA_AirBags ob2 = new Jr_Developer.KIA_AirBags();
		//ob2.display_Air();
	}

}
