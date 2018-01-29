package expressivo;

import javax.naming.spi.DirStateFactory.Result;

import org.antlr.v4.tool.LeftRecursionCyclesMessage;

public class Multiply implements Expression {
	private Expression left;
	private Expression right;
	
	public Multiply(Expression l, Expression r) {
		left = l;
		right = r;
	}
	
	@Override
	public String toString() {
		return left.toString() + "*" + right.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Multiply) {
			return left.equals(((Multiply) obj).left)
					&& right.equals(((Multiply) obj).right);
		}
		if (obj instanceof Group) {
			return this.equals(((Group) obj).getContain());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public Expression diffrentiate(String variable) {
		return new Sum(new Multiply(left, new Group(right.diffrentiate(variable))),
				new Multiply(right, new Group(left.diffrentiate(variable))));
	}
}
