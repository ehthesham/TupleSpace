package tupleServer;
@SuppressWarnings("rawtypes")

public class Tuple   {
	
	@Override
	public String toString() {
		return "Tuple <" +(String) a.toString() + ", " + (String)b.toString() + ", " +(String) c.toString() + ">";
	}
	Object a,b,c;
	 
	public Tuple read(Object a, Object b, Object c) {
		// TODO Auto-generated method stub
		if(Comparator(a, this.a) && Comparator(b, this.b)&& Comparator(c, this.c)) return this;
		else return null;		
	}
	 
	public Tuple read(Object a, Class b, Object c) {
		// TODO Auto-generated method stub
		if(Comparator(a, this.a) && ComparatorC(this.b, b)&& Comparator(c, this.c)) return this;
		else return null;	
	}
	 
	public Tuple read(Object a, Object b, Class c) {
		// TODO Auto-generated method stub
		if(Comparator(a, this.a) && Comparator(b, this.b)&& ComparatorC(this.c, c)) return this;
		else return null;
	}

	public Tuple read(Object a, Class b, Class c) {
		// TODO Auto-generated method stub
		if(Comparator(a, this.a) && ComparatorC(this.b, b)&& ComparatorC(this.c, c)) return this;
		else return null;		
	}
	 
	public Tuple read(Class a, Object b, Class c) {
		// TODO Auto-generated method stub
		if(ComparatorC(this.a, a) && Comparator(this.b, b)&& ComparatorC(this.c, c)) return this;
		else return null;
	}
	 
	public Tuple read(Class a, Class b, Object c) {
		// TODO Auto-generated method stub
		if(ComparatorC(this.a, a) && ComparatorC(this.b, b)&& Comparator(this.c,c)) return this;
		else return null;
	}
	 
	public Tuple read(Class a, Class b, Class c) {
		// TODO Auto-generated method stub
		if(ComparatorC(this.a, a) && ComparatorC(this.b, b)&& ComparatorC(this.c, c)) return this;
		else return null;
	}
	
	public void write(Object a, Object b, Object c) {
		// TODO Auto-generated method stub	
		this.a=a;
		this.b=b;
		this.c=c;
	}
	 
	public void update(Object a, Object b, Object c) {
		// TODO Auto-generated method stub	
	}
	
	public boolean Comparator(Object a, Object b) {
		if(a==b||a.equals(b)) return true;
		else return false;	
	}  
	
	public boolean ComparatorC(Object a, Class b) {
		if(b.equals(Boolean.class)) {
			if( a instanceof Boolean) return true;
			else return false;
			}
		else if ( b.equals(String.class)) {
		if(	a instanceof String) return true ;
		else return false; 
		}
		else if (b.equals(Integer.class)) {
			if( a instanceof Integer) return true;
			else return false;
		
		}else return false;
	}

}
