package tupleServer;

import java.util.ArrayList;
@SuppressWarnings("rawtypes")

public class TupleSpace {
	
	ArrayList<Tuple> tupleSpace = new ArrayList<>();
	synchronized void writeToSpace(Object a, Object b, Object c) throws InterruptedException {
		Thread.sleep(10000);
		Tuple temp= new Tuple();
		temp.write(a, b, c);
		tupleSpace.add(temp);
	}
	 @Override
		public String toString() {
			String abc="-";
			for(int i=0;i< tupleSpace.size();i++) {
				Tuple t= tupleSpace.get(i);
				abc+=t.toString()+";";
			}
			abc+="-";
			
			return abc;
		}
	 synchronized Tuple takeFromSpace(Tuple T, TupleSpace space) throws InterruptedException {
			Thread.sleep(10000);

			Tuple temp=null;
		//	ArrayList<Tuple> listtosend = new ArrayList<>();
			for(int i= 0;i<space.tupleSpace.size();i++) {
				 temp= space.tupleSpace.get(i).read(T.a, T.b, T.c);
				 tupleSpace.remove(i);
			}
			return temp;
		}
	synchronized ArrayList<Tuple> readFromSpace(Object a, Object b, Object c) throws InterruptedException {
		Thread.sleep(10000);

		Tuple temp=null;
		ArrayList<Tuple> listtosend = new ArrayList<>();
		for(int i= 0;i<tupleSpace.size();i++) {
			 temp= tupleSpace.get(i).read(a, b, c);
			listtosend.add(temp);
		}
		return listtosend;
	}
	synchronized ArrayList<Tuple> readFromSpace(Class a, Object b, Object c) throws InterruptedException {
		Thread.sleep(10000);
		Tuple temp=null;
		ArrayList<Tuple> listtosend = new ArrayList<>();
		for(int i= 0;i<tupleSpace.size();i++) {
			 temp= tupleSpace.get(i).read(a, b, c);
			 listtosend.add(temp);
		}
		return listtosend;
	}
	synchronized ArrayList<Tuple> readFromSpace(Object a, Class b, Object c) throws InterruptedException {
		Thread.sleep(10000);
		Tuple temp=null;
		ArrayList<Tuple> listtosend = new ArrayList<>();
		for(int i= 0;i<tupleSpace.size();i++) {
			 temp= tupleSpace.get(i).read(a, b, c);
			 listtosend.add(temp);
		}
		return listtosend;
	}
	synchronized ArrayList<Tuple> readFromSpace(Object a, Object b, Class c) throws InterruptedException {
		Thread.sleep(10000);

		Tuple temp=null;
		ArrayList<Tuple> listtosend = new ArrayList<>();
		for(int i= 0;i<tupleSpace.size();i++) {
			 temp= tupleSpace.get(i).read(a, b, c);
			 listtosend.add(temp);
		}
		return listtosend;
	}
	synchronized ArrayList<Tuple> readFromSpace(Class a, Class b, Class c) throws InterruptedException {	
		Thread.sleep(10000);
		Tuple temp=null;
		ArrayList<Tuple> listtosend = new ArrayList<>();
		for(int i= 0;i<tupleSpace.size();i++) {
			 temp= tupleSpace.get(i).read(a, b, c);
			 listtosend.add(temp);
		}
		return listtosend;
	}
	synchronized ArrayList<Tuple> readFromSpace(Object a, Class b, Class c) throws InterruptedException {
		Thread.sleep(10000);
		Tuple temp=null;
		ArrayList<Tuple> listtosend = new ArrayList<>();
		for(int i= 0;i<tupleSpace.size();i++) {
			 temp= tupleSpace.get(i).read(a, b, c);
			 listtosend.add(temp);
		}
		return listtosend;
	}
	synchronized ArrayList<Tuple> readFromSpace(Class a, Object b, Class c) throws InterruptedException {
		Thread.sleep(10000);
		Tuple temp=null;
		ArrayList<Tuple> listtosend = new ArrayList<>();
		for(int i= 0;i<tupleSpace.size();i++) {
			 temp= tupleSpace.get(i).read(a, b, c);
			 listtosend.add(temp);
		}
		return listtosend;
	}
	synchronized ArrayList<Tuple> readFromSpace(Class a, Class b, Object c) throws InterruptedException {
		Thread.sleep(10000);
		Tuple temp=null;
		ArrayList<Tuple> listtosend = new ArrayList<>();
		for(int i= 0;i<tupleSpace.size();i++) {
			 temp= tupleSpace.get(i).read(a, b, c);
			 listtosend.add(temp);
		}
		return listtosend;
	}
}
