
public class Variable {

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
	
	public enum Type {
		INITIAL, INTERMEDIATE, END, NONE
	}
		
	Type type;
	
	char letter;
	int iBool; //Get rid of me!
	
	int s_start;
	int s_end;
	int g_start;
	int g_end;
	
	//double[] probabilities;
	
	//Default constructor
	public Variable(Type t) {		
		int iBool = 0;
		type = t;
		
		s_start = 0;
		s_end = 1;
		g_start = 0;
		g_end = 1;
	}
	
	public double P(Variable query, int value, int p1, int p2) {
		if (query.type == Type.INITIAL) { //No parents
			if ((query.letter == 'B') || (query.letter == 'b')) { //If dealing with Burglary
				if (value == 1) {
					return PB;
				}
				return (1-PB);
			} 
			
			//Else if dealing with Earthquake
			if (value == 1) {
				return PE;
			}
			return (1-PE);
		} else if (query.type == Type.INTERMEDIATE) { //Two parents (B = p1, E = p2)
			if ((query.letter == 'A') || (query.letter == 'a')) {
				if ((p1 == 1) && (p2 == 1)) { //B = t and E = t
					if (value == 1) {
						return PA_BT_ET;
					}
					return (1 - PA_BT_ET);
				} else if ((p1 == 1) && (p2 == 0)) { //B = t and E = f
					if (value == 1) {
						return PA_BT_EF;
					}
					return (1 - PA_BT_EF);
				} else if ((p1 == 0) && (p2 == 1)) { //B = f and E = t
					if (value == 1) {
						return PA_BF_ET;
					}
					return (1 - PA_BF_ET);
				} else if ((p1 == 0) && (p2 == 0)) { //B = f and E = f
					if (value == 1) {
						return PA_BF_EF;
					}
					return (1 - PA_BF_EF);
				} else {
					return -1000000;
				}
			}
			return -1000000; //Error has occured! Only intermediate should be Alarm
		} else if (query.type == Type.END) { //One parent
			if ((query.letter == 'J') || (query.letter == 'j')) {
				if (p1 == 1) { //If A = t
					if (value == 1) {
						return PJ_AT;
					}
					return (1 - PJ_AT);
				} else if (p1 == 0) { //If A = f
					if (value == 1) {
						return PJ_AF;
					}
					return (1 - PJ_AF);
				} else {
					return -1000000;
				}
			} else if ((query.letter == 'M') || (query.letter == 'm')) {
				if (p1 == 1) { //If A = t
					if (value == 1) {
						return PM_AT;
					}
					return (1 - PM_AT);
				} else if (p1 == 0) { //If A = f
					if (value == 1) {
						return PM_AF;
					}
					return (1 - PM_AF);
				} else {
					return -1000000;
				}
			}
			return -10000;
		}
		return -10000;
	}
	
}
