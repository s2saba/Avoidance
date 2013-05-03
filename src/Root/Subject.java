package Root;

import java.util.LinkedList;
import java.util.Iterator;

public class Subject {
	public Subject() {
		observers = new LinkedList<IObserver>();
	}
	
	public void Subscribe(IObserver observer) {
		observers.add(observer);
	}
	
	public void Unsubscribe(IObserver observer) {
		observers.remove(observer);
	}
	
	protected void Notify() {
		Iterator<IObserver> iter = observers.iterator();
		while (iter.hasNext()) {
			iter.next().Update();
		}
	}
	
	LinkedList<IObserver> observers;
}
