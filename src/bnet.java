import java.util.ArrayList;

public class Bnet {
	
	Variable b;
	Variable e;
	Variable a;
	Variable j;
	Variable m;
	
	public Bnet() {
		b = new Variable(Variable.Type.INITIAL);
		e = new Variable(Variable.Type.INITIAL);
		a = new Variable(Variable.Type.INTERMEDIATE);
		j = new Variable(Variable.Type.END);
		m = new Variable(Variable.Type.END);
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
	
	public void handle_input(String[] args) {
		boolean given = false;
		
		for (int i = 0; i < args.length; i++) {
			System.out.println("I: " + i + " Args: " + args[i]);
			if (args[i].equalsIgnoreCase("given")) {
				given = true;
			} else if (args[i].equalsIgnoreCase("Bt")) {
				if (given == true) {
					b.s_start = 0;
					b.s_end = 0;
					b.g_start = 0;
					b.g_end = 0; 
				} else {
					b.s_start = 0;
					b.s_end = 0;
				}
			} else if (args[i].equalsIgnoreCase("Bf")) {
				if (given == true) {
					b.s_start = 1;
					b.s_end = 1;
					b.g_start = 1;
					b.g_end = 1; 
				} else {
					b.s_start = 1;
					b.s_end = 1;
				}
			} else if (args[i].equalsIgnoreCase("Et")) {
				if (given == true) {
					e.s_start = 0;
					e.s_end = 0;
					e.g_start = 0;
					e.g_end = 0; 
				} else {
					e.s_start = 0;
					e.s_end = 0;
				}
			} else if (args[i].equalsIgnoreCase("Ef")) {
				if (given == true) {
					e.s_start = 1;
					e.s_end = 1;
					e.g_start = 1;
					e.g_end = 1; 
				} else {
					e.s_start = 1;
					e.s_end = 1;
				}
			} else if (args[i].equalsIgnoreCase("At")) {
				if (given == true) {
					a.s_start = 0;
					a.s_end = 0;
					a.g_start = 0;
					a.g_end = 0; 
				} else {
					a.s_start = 0;
					a.s_end = 0;
				}
			} else if (args[i].equalsIgnoreCase("Af")) {
				if (given == true) {
					a.s_start = 1;
					a.s_end = 1;
					a.g_start = 1;
					a.g_end = 1; 
				} else {
					a.s_start = 1;
					a.s_end = 1;
				}
			} else if (args[i].equalsIgnoreCase("Jt")) {
				if (given == true) {
					j.s_start = 0;
					j.s_end = 0;
					j.g_start = 0;
					j.g_end = 0; 
				} else {
					j.s_start = 0;
					j.s_end = 0;
				}
			} else if (args[i].equalsIgnoreCase("Jf")) {
				if (given == true) {
					j.s_start = 1;
					j.s_end = 1;
					j.g_start = 1;
					j.g_end = 1; 
				} else {
					j.s_start = 1;
					j.s_end = 1;
				}
			} else if (args[i].equalsIgnoreCase("Mt")) {
				if (given == true) {
					m.s_start = 0;
					m.s_end = 0;
					m.g_start = 0;
					m.g_end = 0; 
				} else {
					m.s_start = 0;
					m.s_end = 0;
				}
			} else if (args[i].equalsIgnoreCase("Mf")) {
				if (given == true) {
					m.s_start = 1;
					m.s_end = 1;
					m.g_start = 1;
					m.g_end = 1; 
				} else {
					m.s_start = 1;
					m.s_end = 1;
				}
			} else {
				System.out.println("Error with input; please try again");
				System.exit(0);
			}
		}
	}
	
	public static double calculate (Variable b, Variable e, Variable a, Variable j, Variable m) {
			Variable var = new Variable(Variable.Type.NONE);
			
			//Our numerator is search
			double n = 0;
			for (int ei = e.s_start; ei < e.s_end; ei++) { //Earthquake
				for (int bi = b.s_start; bi < b.s_end; bi++) { //Burglary
					for (int ai = a.s_start; ai < a.s_end; ai++) { //Alarm
						for (int ji = j.s_start; ji < j.s_end; ji++) { //John calls
							for (int mi = m.s_start; mi < m.s_end; mi++) { //Mary calls
								n = var.P(b, bi, -1, -1) 
										* var.P(e, ei, -1, -1) 
										* var.P(a, ai, bi, ei) 
										* var.P(j, ji, ai, -1) 
										* var.P(m, mi, ai, -1);
							}
						}
					}
				}
			}

			//Our denomenator is given 
			double d = 0;
			for (int ei = e.g_start; ei < e.g_end; ei++) { //Earthquake
				for (int bi = b.g_start; bi < b.g_end; bi++) { //Burglary
					for (int ai = a.g_start; ai < a.g_end; ai++) { //Alarm
						for (int ji = j.g_start; ji < j.g_end; ji++) { //John calls
							for (int mi = m.g_start; mi < m.g_end; mi++) { //Mary calls
								d = var.P(b, bi, -1, -1) 
										* var.P(e, ei, -1, -1) 
										* var.P(a, ai, bi, ei) 
										* var.P(j, ji, ai, -1) 
										* var.P(m, mi, ai, -1);
							}
						}
					}
				}
			}
			
			return n/d;
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
		double answer = calculate(b.b, b.e, b.a, b.j, b.m);
		System.out.println("The probability is: " + answer);	
	}

}
