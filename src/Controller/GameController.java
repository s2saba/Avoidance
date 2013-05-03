package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Model.GameEngine;

public class GameController implements IModeController {

	private GameEngine engine;
	
	public GameController(GameEngine engine) {
		this.engine = engine;
	}
	
	@Override
	public void MouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void MouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void MouseUp(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void MouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void MouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void KeyDown(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void KeyUp(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void KeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void TimerTick(ActionEvent e) {
		engine.UpdateGameState();
	}
	
}
