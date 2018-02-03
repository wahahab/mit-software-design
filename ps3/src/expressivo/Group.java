package expressivo;

import java.awt.Container;
import java.awt.ContainerOrderFocusTraversalPolicy;
import java.util.Map;

import org.hamcrest.core.IsInstanceOf;

public class Group implements Expression {
	private Expression contain;
	
	public Group(Expression c) {
		contain = c;
	}
	
	@Override
	public String toString() {
		return "(" + contain.toString() + ")";
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object object) {
		return contain.equals(object);
	}
	
	public Expression getContain() {
		return contain;
	}

	@Override
	public Expression diffrentiate(String variable) {
		return contain.diffrentiate(variable);
	}

	@Override
	public Expression simplify(Map<String, Double> environment) {
		return contain.simplify(environment);
	}
}
