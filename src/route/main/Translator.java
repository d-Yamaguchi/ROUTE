package route.main;
import nez.ast.CommonTree;


public class Translator {
	public static CalcTree translate(CommonTree node){
		switch (node.getTag().getSymbol()) {
		case "Source":
			return new Source(node);
		case "Funcdecl":
			return new Funcdecl(node);
		case "Arglist":
			return new Arglist(translate(node.get(0)), translate(node.get(1)));
		case "Returnlist":
			return new Returnlist(translate(node.get(0)), translate(node.get(1)));
		case "Vardecl":
			return new Vardecl(translate(node.get(0)), translate(node.get(1)));
		case "Unop":
			return new Unop(translate(node.get(0)), translate(node.get(1)));
		case "Add":
			return new Add(translate(node.get(0)), translate(node.get(1)));
		case "Mul":
			return new Mul(translate(node.get(0)), translate(node.get(1)));
		case "LessThan":
			return new LessThan(translate(node.get(0)), translate(node.get(1)));
		case "GreaterThan":
			return new GreaterThan(translate(node.get(0)), translate(node.get(1)));
		case "LessThanEquals":
			return new LessThanEquals(translate(node.get(0)), translate(node.get(1)));
		case "GreaterThanEquals":
			return new GreaterThanEquals(translate(node.get(0)), translate(node.get(1)));
		case "Equals":
			return new Equals(translate(node.get(0)), translate(node.get(1)));
		case "NotEquals":
			return new NotEquals(translate(node.get(0)), translate(node.get(1)));
		case "Or":
			return new Or(translate(node.get(0)),translate(node.get(1)));
		case "In":
			return new In(translate(node.get(0)));
		case "Out":
			return new Out(translate(node.get(0)));
		case "Integer":
			return new Int(Integer.parseInt(node.toText()));
		case "Name":
			return new Name(node.toString());//これで良いのか??toStrとtoTexの違いは?
		case "True":
			return new True(Boolean.parseBoolean(node.toText()));
		case "False":
			return new True(Boolean.parseBoolean(node.toText()));
		default:
			break;
		}
		return null;
	}
}
