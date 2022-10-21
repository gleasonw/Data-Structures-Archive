package willsGame;

import javax.swing.*;
import java.awt.*;

public class gamePanel extends JPanel{
	
	public gamePanel(LayoutManager layout, int thick, Color shade) {
		super(layout);
		setBorder(BorderFactory.createLineBorder(shade, thick));
	}
	
	public gamePanel(LayoutManager layout, int x, int y, int thick, Color shade) {
		super(layout);
		setPreferredSize(new Dimension(x,y));
		setBorder(BorderFactory.createLineBorder(shade, thick));
	}

}
