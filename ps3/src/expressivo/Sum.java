package expressivo;

import java.util.Map;

public class Sum implements Expression {
	private Expression left;
	private Expression right;
	
	public Sum(Expression l, Expression r) {
		left = l;
		right = r;
	}
	
	@Override
	public String toString() {
		return left.toString() + " + " + right.toString();
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Sum) {
			return left.equals(((Sum) obj).left)
					&& right.equals(((Sum) obj).right);
		}
		if (obj instanceof Group) {
			return this.equals(((Group) obj).getContain());
		}
		return false;
	}

	@Override
	public Expression diffrentiate(String variable) {
		return new Sum(left.diffrentiate(variable),
				right.diffrentiate(variable));
	}

	@Override
	public Expression simplify(Map<String, Double> environment) {
		Expression sLeft = left.simplify(environment);
		Expression sRight = right.simplify(environment);
		
		if (sLeft instanceof Value && sRight instanceof Value) {
			return new Value(((Value) sLeft).value + ((Value) sRight).value);
		}
		return new Sum(sLeft, sRight);
	}
}
