package Controller;

import java.awt.event.*;

public interface IModeController {
	public void MouseClicked(MouseEvent e);
	public void MouseDown(MouseEvent e);
	public void MouseUp(MouseEvent e);
	public void MouseMoved(MouseEvent e);
	public void MouseDragged(MouseEvent e);
	public void KeyDown(KeyEvent e);
	public void KeyUp(KeyEvent e);
	public void KeyTyped(KeyEvent e);
}
