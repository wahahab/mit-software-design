package expressivo;

public class Sum implements Expression {
	private Expression left;
	private Expression right;
	
	public Sum(Expression l, Expression r) {
		left = l instanceof Sum ? new Group(l) : l;
		right = r instanceof Sum ? new Group(r) : r;
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
}
