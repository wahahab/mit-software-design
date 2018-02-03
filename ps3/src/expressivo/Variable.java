package expressivo;

import java.util.Map;

public class Variable implements Expression {
	String symbol;
	
	public Variable(String s) {
		symbol = s;
	}
	
	@Override
	public String toString() {
		return symbol;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Group) {
			return this.equals(((Group) obj).getContain());
		}
		if (obj instanceof Variable) {
			return symbol.equals(((Variable) obj).symbol);
		}
		return false;
	}
	
	@Override
	public Expression diffrentiate(String variable) {
		return variable.equals(symbol) ? new Value(1) : new Value(0); 
	}

	@Override
	public Expression simplify(Map<String, Double> environment) {
		Double value = environment.get(symbol);
		
		if (value == null) {
			return this;
		}
		return new Value(value);
	}
	
}
