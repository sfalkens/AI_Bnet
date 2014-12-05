
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
	Type type;
	
	int s_start;
	int s_end;
	int g_start;
	int g_end;
	
	//Default constructor
	public Variable(char l, Type t) {
		letter = l;
		type = t;
		
		s_start = 1;
		s_end = 2;
		g_start = 1; //Not sure
		g_end = 2; //Not sure
	}
	
	public static double P(Variable query, int value, int p1, int p2) {
		if (query.type == Type.INITIAL) { //No parents
			//If dealing with Burglary
			if (query.letter == 'B') { 
				if (value == 1) { //If want B = t
					return PB;
				}
				return (1 - PB); //Else if want B = f
			} 
			
			//Else if dealing with Earthquake
			if (value == 1) {
				return PE; //If want E = t
			}
			return (1 - PE); //Else if want E = f
			
			
		} else if (query.type == Type.INTERMEDIATE) { //Two parents (B = p1, E = p2)
			//All of the following is for Alarm 
			if ((p1 == 1) && (p2 == 1)) { //B = t and E = t
				if (value == 1) {
					return PA_BT_ET; //If want A = t
				}
				return (1 - PA_BT_ET); //Else if want A = f
			} else if ((p1 == 1) && (p2 == 0)) { //B = t and E = f
				if (value == 1) {
					return PA_BT_EF; //If want A = t
				}
				return (1 - PA_BT_EF); //Else if want A = f
			} else if ((p1 == 0) && (p2 == 1)) { //B = f and E = t
				if (value == 1) {
					return PA_BF_ET; //If want A = t
				}
				return (1 - PA_BF_ET); //Else if want A = f
			} else if ((p1 == 0) && (p2 == 0)) { //B = f and E = f
				if (value == 1) {
					return PA_BF_EF; //If want A = t
				}
				return (1 - PA_BF_EF); //Else if want A = f
			} 
		} else if (query.type == Type.END) { //One parent
			if (query.letter == 'J') {
				if (p1 == 1) { //A = t
					if (value == 1) {
						return PJ_AT; //If want J = t and A = t 
					}
					return (1 - PJ_AT); //Else if want J = f and A = t
				} else { //A = f
					if (value == 1) {
						return PJ_AF; //If want J = t and A = f
					}
					return (1 - PJ_AF); //Else if want J = f and A = f
				} 
			} else if (query.letter == 'M') {
				if (p1 == 1) { //A = t
					if (value == 1) {
						return PM_AT; //If want M = t and A = t 
					}
					return (1 - PM_AT); //If want M = f and A = t
				} else { //A = f
					if (value == 1) {
						return PM_AF; //If want M = f and A = t 
					}
					return (1 - PM_AF); //If want M = f adn A = f
				} 
			}
		}
		return -1000000; //Error
	}
	
}
