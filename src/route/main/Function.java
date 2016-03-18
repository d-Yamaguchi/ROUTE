package route.main;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

public class Function {
	Map<String, Object> funcrecord = new HashMap<String, Object>();
	LinkedList<SimpleEntry<Object, Boolean>> returnList = new LinkedList<AbstractMap.SimpleEntry<Object,Boolean>>();
	LinkedList<SimpleEntry<String,Object>> argumentList = new LinkedList<SimpleEntry<String,Object>>();
	
	public Function(LinkedList<SimpleEntry<String, Object>> arguments, LinkedList<SimpleEntry<Object, Boolean>> returnList, Map<String,Object> funcrecord){
		this.argumentList = arguments;
		this.returnList = returnList;
		this.funcrecord = funcrecord;
	}
	
}
