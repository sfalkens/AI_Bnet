
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
	
	char letter;
	boolean bool;
	
	int iBool;
	Type type;
	
	//Default constructor
	public Variable() {
		letter = 'x';
		boolean bool;
		
		int iBool = 0;
		type = Type.NONE;
	}
	
	public double P(Variable query, Variable p1, Variable p2) {
		if (type == Type.INITIAL) { //No parents
			if ((query.letter == 'B') || (query.letter == 'b')) { //If dealing with Burglary
				if (query.iBool == 1) {
					return PB;
				}
				return (1-PB);
			} 
			
			//Else if dealing with Earthquake
			if (query.iBool == 1) {
				return PE;
			}
			return (1-PE);
		} else if (type == Type.INTERMEDIATE) { //Two parents (B = p1, E = p2)
			if ((query.letter == 'A') || (query.letter == 'a')) {
				if ((p1.iBool == 1) && (p2.iBool == 1)) { //B = t and E = t
					if (query.iBool == 1) {
						return PA_BT_ET;
					}
					return (1 - PA_BT_ET);
				} else if ((p1.iBool == 1) && (p2.iBool == 0)) { //B = t and E = f
					if (query.iBool == 1) {
						return PA_BT_EF;
					}
					return (1 - PA_BT_EF);
				} else if ((p1.iBool == 0) && (p2.iBool == 1)) { //B = f and E = t
					if (query.iBool == 1) {
						return PA_BF_ET;
					}
					return (1 - PA_BF_ET);
				} else if ((p1.iBool == 0) && (p2.iBool == 0)) { //B = f and E = f
					if (query.iBool == 1) {
						return PA_BF_EF;
					}
					return (1 - PA_BF_EF);
				} else {
					return -1000000;
				}
			}
			return -1000000; //Error has occured! Only intermediate should be Alarm
		} else if (type == Type.END) { //One parent
			if ((query.letter == 'J') || (query.letter == 'j')) {
				if (p1.iBool == 1) { //If A = t
					if (query.iBool == 1) {
						return PJ_AT;
					}
					return (1 - PJ_AT);
				} else if (p1.iBool == 0) { //If A = f
					if (query.iBool == 1) {
						return PJ_AF;
					}
					return (1 - PJ_AF);
				} else {
					return -1000000;
				}
			} else if ((query.letter == 'M') || (query.letter == 'm')) {
				if (p1.iBool == 1) { //If A = t
					if (query.iBool == 1) {
						return PM_AT;
					}
					return (1 - PM_AT);
				} else if (p1.iBool == 0) { //If A = f
					if (query.iBool == 1) {
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
