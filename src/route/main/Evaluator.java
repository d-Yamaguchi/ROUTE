package route.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Evaluator extends CalcVisitor {
	
	Map<String, Object> record = new HashMap<String, Object>();
	
	public Object eval(CalcTree node){
		return node.accept(this);
	}

	@Override
	public Object visit(Source node) {
		return null;
	}
	
	@Override
	public Object visit(Funcdecl node) {
		String id = String.class.cast(node.child.get(0).accept(this));
		Map<String, Object> subrecord = new HashMap<String, Object>();
		
		//funcdecl.. not yet!
		record.put(id, subrecord);
		return null;
	}
	
	@Override
	public Object visit(Returnlist node) {
		//return value is a list data structure?
		return null;	
	}
	
	@Override
	public Object visit(Add node) {
		Integer left = Integer.class.cast(node.child.get(0).accept(this));
		Integer right = Integer.class.cast(node.child.get(1).accept(this));
		return left + right;
	}

	@Override
	public Object visit(Mul node) {
		Integer left = Integer.class.cast(node.child.get(0).accept(this));
		Integer right = Integer.class.cast(node.child.get(1).accept(this));
		return left * right;
	}

	@Override
	public Object visit(Int node) {
		return node.val;
	}

	@Override
	public Object visit(Equals node) {
		Object left = node.child.get(0).accept(this);
		Object right = node.child.get(1).accept(this);
		if (left == right) {
			return true;
		}
		return false;
	}
	
	public Object visit(NotEquals node) {
		Object left = node.child.get(0).accept(this);
		Object right = node.child.get(1).accept(this);
		if (left == right) {
			return false;
		}
		return true;
	}

	@Override
	public Object visit(GreaterThan node) {
		Integer left = Integer.class.cast(node.child.get(0).accept(this));
		Integer right = Integer.class.cast(node.child.get(1).accept(this));
		if (left > right) {
			return true;
		}
		return false;
	}

	@Override
	public Object visit(GreaterThanEquals node) {
		Integer left = Integer.class.cast(node.child.get(0).accept(this));
		Integer right = Integer.class.cast(node.child.get(1).accept(this));
		if (left < right) {
			return false;
		}
		return true;
	}

	@Override
	public Object visit(LessThan node) {
		Integer left = Integer.class.cast(node.child.get(0).accept(this));
		Integer right = Integer.class.cast(node.child.get(1).accept(this));
		if (left < right) {
			return true;
		}
		return false;
	}

	@Override
	public Object visit(LessThanEquals node) {
		Integer left = Integer.class.cast(node.child.get(0).accept(this));
		Integer right = Integer.class.cast(node.child.get(1).accept(this));
		if (left < right) {
			return false;
		}
		return true;
	}
	
	@Override
	public Object visit(And node){
		Boolean left = Boolean.class.cast(node.child.get(0).accept(this));
		Boolean right = Boolean.class.cast(node.child.get(1).accept(this));
		return left && right;
	}
	
	@Override
	public Object visit(Or node){
		Boolean left = Boolean.class.cast(node.child.get(0).accept(this));
		Boolean right = Boolean.class.cast(node.child.get(1).accept(this));
		return left || right;
	}
	
	@Override
	public Object visit(Vardecl node){
		String id = String.class.cast(node.child.get(0).accept(this));
		Integer val = Integer.class.cast(node.child.get(1).accept(this));
		record.put(id, val);
		return null;
	}

	@Override
	public Object visit(In node) {
		String id = String.class.cast(node.child.get(0).accept(this));
		Scanner scan = new Scanner(System.in);
		switch (scan.next()) {
		case "true":
			record.put(id, true);
			break;
		case "false":
			record.put(id, false);
			break;
		default:
			record.put(id, Integer.class.cast(scan));
			break;
		}
		return null;
	}

	@Override
	public Object visit(Out node){
		String str = String.class.cast(node.child.get(0).accept(this));
		System.out.println(str);
		return null;
	}
	
	@Override
	public Object visit(Name node) {
		return node.str;
	}
	
	@Override
	public Object visit(True node){
		return node.bool;
	}
	
	@Override
	public Object visit(False node){
		return node.bool;
	}
	
	@Override
	public Object visit(Unop node){
		Object leftnode = node.child.get(0).accept(this);
		Object rightnode = node.child.get(1).accept(this);
		//Not yet! Also, Minus and Not
		return rightnode;
	}
	
}
