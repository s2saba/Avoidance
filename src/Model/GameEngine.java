package Model;

import Root.Subject;

public class GameEngine extends Subject {
	public GameEngine() {
		super();
	}
	
	public void UpdateGameState() {
		Notify();
	}
}
