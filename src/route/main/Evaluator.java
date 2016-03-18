package route.main;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.LinkedList;
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
		@SuppressWarnings("unchecked")
		LinkedList<SimpleEntry<String, Object>> arglist = (LinkedList<SimpleEntry<String, Object>>) node.child.get(1).accept(this);
		@SuppressWarnings("unchecked")
		LinkedList<SimpleEntry<Object, Boolean>> returnList = (LinkedList<SimpleEntry<Object, Boolean>>) node.child.get(2).accept(this);
		returnList.addLast(new SimpleEntry<Object, Boolean>(node.child.get(3).accept(this), true));
		
		@SuppressWarnings("unchecked")
		Map<String,Object> funcrecord = (Map<String, Object>) node.child.get(4).accept(this);
		Function func = new Function(arglist, returnList, funcrecord);
		
		record.put(id, func);
		return null;
	}
	
	@Override
	public Object visit(Arglist node){
		String leftside = String.class.cast(node.child.get(0).accept(this));
		Object rightside = node.child.get(1).accept(this);
		if (rightside==null) {
			LinkedList<SimpleEntry<String, Object>> argList = new LinkedList<SimpleEntry<String,Object>>();
			argList.add(new SimpleEntry<String, Object>(leftside,null));
			return argList;
		} else if (rightside instanceof String) {
			LinkedList<SimpleEntry<String, Object>> argList = new LinkedList<SimpleEntry<String,Object>>();
			argList.add(new SimpleEntry<String, Object>(leftside,null));
			argList.add(new SimpleEntry<String, Object>(String.class.cast(rightside),null));
			return argList;
		} else if (rightside instanceof LinkedList) {
			@SuppressWarnings("unchecked")
			LinkedList<SimpleEntry<String, Object>> argList = (LinkedList<SimpleEntry<String, Object>>) rightside;
			argList.addFirst(new SimpleEntry<String, Object>(leftside,null));
			return argList;
		}else {
			return null;
		}
	}
	
	@Override
	public Object visit(Returnlist node) {
		//return value is a list data structure?--Yes!!
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
		if(Boolean.class.cast(leftnode) ){
			Integer value =Integer.class.cast(rightnode) * (-1);
			return value;
		}else {
			Boolean value = Boolean.class.cast(rightnode);
			return !value;
		}
	}
	
	@Override
	public Object visit(Minus node) {
		return true;
	}
	
	@Override
	public Object visit(Not node) {
		return false;
	}
}
