package expressivo;

import java.util.Map;

public class Value implements Expression {
	double value;
	
	public Value(double v) {
		value = v;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Value) {
			return value == ((Value) obj).value;
		}
		if (obj instanceof Group) {
			return this.equals(((Group) obj).getContain());
		}
		return false;
	}
	@Override
	public Expression diffrentiate(String variable) {
		return new Value(0);
	}

	@Override
	public Expression simplify(Map<String, Double> environment) {
		return this;
	}
}
