package darw;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Horse extends JPanel {
	String imageName;
	int x, y;
	
	public Horse(String imageName) {
		this.imageName = imageName;
	}	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Toolkit tool = Toolkit.getDefaultToolkit();
		
		Image img = tool.getImage("images/"+imageName+".jpg");
		g.drawImage(img, 0, 0, 60, 40, this);
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}