import java.util.ArrayList;

public class bnet {

	//======================================================================
	static final double PB = 0.001;
	static final double PE = 0.002;
	
	static final double PA_BT_ET = 0.95;
	static final double PA_BT_EF = 0.94;
	static final double PA_BF_ET = 0.29;
	static final double PA_BF_EF = 0.001;
	
	static final double PJ_AT = 0.90;
	static final double PJ_AF = 0.05;
	
	static final double PM_AT = 0.70;
	static final double PM_AF = 0.01;
	//=====================================================================
	
	// B = burglary
	// E = earthquake
	// A = alarm
	// J = John calls
	// M = Mary calls
	
	
	public boolean contains_given;
	public int given_index;
	
	public boolean b;
	public boolean e;
	public boolean a;
	public boolean j;
	public boolean m;
	public boolean joint;
	
	public bnet() {
		contains_given = false;
		given_index = -1;
	}
	
	public void handle_input(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("given")) {
				this.contains_given = true;
				this.given_index = i;
			}
		}
		
		
	}
	
	public double enumerationAsk(variable x, ArrayList<variable> e) {
		return 0;
	}
	
	//Returns the joint probability of the five events
	public boolean computeProbability(boolean b, boolean e, boolean a, boolean j, boolean m) {
		
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to our Bayesian Network!");
		if (args.length == 0) {
			System.out.println("Please rerun the program with command line arguments");
			System.exit(0);
		}
		if (args.length > 6) {
			System.out.println("Please rerun the program with between 1 and 5 arguments on command line");
			System.exit(0);
		}
		
		bnet b = new bnet();
		b.handle_input(args);
		b.computeProbability(b.b, b.e, b.a, b.j, b.m);
		
		
	}

}
