import java.util.ArrayList;

public class Bnet {
	
	Variable B;
	Variable E;
	Variable A;
	Variable J;
	Variable M;
	
	public Bnet() {
		B = new Variable('B', Variable.Type.INITIAL);
		E = new Variable('E', Variable.Type.INITIAL);
		A = new Variable('A', Variable.Type.INTERMEDIATE);
		J = new Variable('J', Variable.Type.END);
		M = new Variable('M', Variable.Type.END);
	}
	
	public void set_for_true(Variable v, boolean given) {
		v.s_end = 0;
		if (given == true) {
			v.g_end = 0;
		} 
	}
	
	public void set_for_false(Variable v, boolean given) {
		v.s_start = 1;
		if (given == true) {
			v.g_start = 1;
		} 
	}
	
	public void handle_input(String[] args) {
		boolean given = false;
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("given")) {
				given = true;
			} else if (args[i].equalsIgnoreCase("Bt")) {
				set_for_true(this.B, given);
			} else if (args[i].equalsIgnoreCase("Bf")) {
				set_for_false(this.B, given);
			} else if (args[i].equalsIgnoreCase("Et")) {
				set_for_true(this.E, given);
			} else if (args[i].equalsIgnoreCase("Ef")) {
				set_for_false(this.E, given);
			} else if (args[i].equalsIgnoreCase("At")) {
				set_for_true(this.A, given);
			} else if (args[i].equalsIgnoreCase("Af")) {
				set_for_false(this.A, given);
			} else if (args[i].equalsIgnoreCase("Jt")) {
				set_for_true(this.J, given);
			} else if (args[i].equalsIgnoreCase("Jf")) {
				set_for_false(this.J, given);
			} else if (args[i].equalsIgnoreCase("Mt")) {
				set_for_true(this.M, given);
			} else if (args[i].equalsIgnoreCase("Mf")) {
				set_for_false(this.M, given);
			} else {
				System.out.println("Error with input; please try again");
				System.exit(0);
			}
		}
		
		System.out.println("Our set values are: ");
		
		System.out.println("B: " + B.letter + " " + B.type);
		System.out.println(B.s_start + " " + B.s_end + " " + B.g_start + " " + B.g_end);
		
		System.out.println("E: " + E.letter + " " + E.type);
		System.out.println(E.s_start + " " + E.s_end + " " + E.g_start + " " + E.g_end);
		
		System.out.println("A: " + A.letter + " " + A.type);
		System.out.println(A.s_start + " " + A.s_end + " " + A.g_start + " " + A.g_end);
		
		System.out.println("J: " + J.letter + " " + J.type);
		System.out.println(J.s_start + " " + J.s_end + " " + J.g_start + " " + J.g_end);
		
		System.out.println("M: " + M.letter + " " + M.type);
		System.out.println(M.s_start + " " + M.s_end + " " + M.g_start + " " + M.g_end);
		
		System.out.println("\n");
	}
	
	public static double calculate (Variable B, Variable E, Variable A, Variable J, Variable M) {
			Variable v = new Variable('x', Variable.Type.NONE);
		
			//Our numerator is search
			double n = 0;
			for (int e = E.s_start; e <= E.s_end; e++) { //Earthquake
				for (int b = B.s_start; b <= B.s_end; b++) { //Burglary
					for (int a = A.s_start; a <= A.s_end; a++) { //Alarm
						for (int j = J.s_start; j <= J.s_end; j++) { //John calls
							for (int m = M.s_start; m <= M.s_end; m++) { //Mary calls
								n = n + (v.P(B, b, -1, -1) * v.P(E, e, -1, -1) * v.P(A, a, b, e) * v.P(J, j, a, -1) * v.P(M, m, a, -1));
								System.out.println("N: " + n);
							}
						}
					}
				}
			}

			//Our denominator is given 
			double d = 0;
			for (int e = E.g_start; e <= E.g_end; e++) { //Earthquake
				for (int b = B.g_start; b <= B.g_end; b++) { //Burglary
					for (int a = A.g_start; a <= A.g_end; a++) { //Alarm
						for (int j = J.g_start; j <= J.g_end; j++) { //John calls
							for (int m = M.g_start; m <= M.g_end; m++) { //Mary calls
								d = d + (v.P(B, b, -1, -1) * v.P(E, e, -1, -1) * v.P(A, a, b, e) * v.P(J, j, a, -1) * v.P(M, m, a, -1));
								System.out.println("D: " + d);
							}
						}
					}
				}
			}
			
			System.out.println("");
			System.out.println("Final values =====================");
			System.out.println("N: " + n);
			System.out.println("D: " + d);
			
			return (n/d);
	}
	
	
	public static void main(String[] args) {
		System.out.println("Welcome to our Bayesian Network!=========================");
		if (args.length == 0) {
			System.out.println("Please rerun the program with command line arguments");
			System.exit(0);
		}
		if (args.length > 6) {
			System.out.println("Please rerun the program with between 1 and 5 arguments on command line");
			System.exit(0);
		}
		
		Bnet b = new Bnet();
		b.handle_input(args);
		
		//All of these tests run correctly!
		/*System.out.println("My sanity checks: ==================");
		Variable v = new Variable('x', Variable.Type.NONE);
		
		double test1 = v.P(b.B, 1, -1, -1);
		System.out.println("P(B = t): " + test1);
		
		double test2 = v.P(b.B, 0, -1, -1);
		System.out.println("P(B = f): " + test2);
		
		double test3 = v.P(b.A, 1, 0, 1);
		System.out.println("P(A = t given B = f and E = t): " + test3);
		
		double test4 = v.P(b.A, 0, 0, 1);
		System.out.println("P(A = f given B = f and E = t): " + test4);
		
		double test5 = v.P(b.J, 1, 1, -1);
		System.out.println("P(J = t given A = t): " + test5);
		
		double test6 = v.P(b.J, 1, 0, -1);
		System.out.println("P(J = t given A = f): " + test6);
		
		double test7 = v.P(b.J, 0, 1, -1);
		System.out.println("P(J = f given A = t): " + test7);
		
		double test8 = v.P(b.J, 0, 0, -1);
		System.out.println("P(J = f given A = f): " + test8);
		
		double test9 = v.P(b.M, 1, 0, -1);
		System.out.println("P(M = t given A = f): " + test9);*/
		
		double answer = calculate(b.B, b.E, b.A, b.J, b.M);
		System.out.println("The probability is: " + answer);	
	}

	
	/*
	//Reads our input from command line and separates into query and evidence
	public void handle_input(String[] args) {
		boolean contains_given = false;
		int given_index = -1;
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("given")) {
				contains_given = true;
				given_index = i;
			}
		}
		
		if (contains_given == true) {
			for (int i = 0; i < given_index; i++) {
				Variable v = get_var(args, i);
				this.variables.add(v);
			}
			
			for (int i = given_index+1; i < args.length; i++) {
				Variable v = get_var(args, i);
				this.evidence.add(v);
			}
		} else {
			for (int i = 0; i < args.length; i++) {
				Variable v = get_var(args, i);
				this.variables.add(v);
			}
		}
		
	}
	
	//Given an input such as 'At' splits into variable Alarm and boolean true
	public static Variable get_var(String[] args, int i) {
		Variable v = new Variable();
		v.letter = args[i].charAt(0);
		
		if ((v.letter == 'b') || (v.letter == 'B')) {
			v.type = Variable.Type.INITIAL;
		} else if ((v.letter == 'e') || (v.letter == 'E')) {
			v.type = Variable.Type.INITIAL;
		} else if ((v.letter == 'a') || (v.letter == 'A')) {
			v.type = Variable.Type.INTERMEDIATE;
		} else if ((v.letter == 'j') || (v.letter == 'J')) {
			v.type = Variable.Type.END;
		} else if ((v.letter == 'm') || (v.letter == 'M')) {
			v.type = Variable.Type.END;
		} else {
			v.type = Variable.Type.NONE;
		}
		
		if (args[i].charAt(1) == 't') {
			v.bool = true;
		} else {
			v.bool = false;
		}
		
		return v;
	}
		
	//Given a variable, prints information and sets Bnet value apropriately
	public void handle_variable(Variable v) {
		if ((v.letter == 'a') || (v.letter == 'A')) {
			System.out.print("Alarm");
			if (v.bool == true) {
				this.a.iBool = 1;
			} else {
				this.a.iBool = 0; 
			}
		} else if ((v.letter == 'b') || (v.letter == 'B')) {
			System.out.print("Burglary");
			if (v.bool == true) {
				this.b.iBool = 1;
			} else {
				this.b.iBool = 0; 
			}
		} else if ((v.letter == 'e') || (v.letter == 'E')){
			System.out.print("Earthquake");
			if (v.bool == true) {
				this.e.iBool = 1;
			} else {
				this.e.iBool = 0; 
			}
		} else if ((v.letter == 'm') || (v.letter == 'M')) {
			System.out.print("Mary calls");
			if (v.bool == true) {
				this.m.iBool = 1;
			} else {
				this.m.iBool = 0; 
			}
		} else if ((v.letter == 'j') || (v.letter == 'J')) {
			System.out.print("John calls");
			if (v.bool == true) {
				this.j.iBool = 1;
			} else {
				this.j.iBool = 0; 
			}
		} else {
			System.out.print("Unknown ");
		}
		
		System.out.print(" is " + v.bool + "\n");
	}
	
	//Prints what we are looking for and what we know
	public void handle_information() {
		System.out.println("We are looking for the following information: ");
		for (int i = 0; i < this.variables.size(); i++) {
			handle_variable(variables.get(i));
		}
		
		System.out.println("We know the following information: ");
		for (int i = 0; i < this.evidence.size(); i++) {
			handle_variable(evidence.get(i));
		}
	}
	
	//Returns the joint probability of the five events
	public double computeProbability(boolean b, boolean e, boolean a, boolean j, boolean m) {
		
		return 0;
	}
	*/
}
