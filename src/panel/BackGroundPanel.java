package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackGroundPanel extends JPanel{
	private static final long serialVersionUID = 1L;
		
	public BackGroundPanel() {
		setPreferredSize(new Dimension(1200, 560));
		setLayout(new GridLayout(0, 4));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.black);
     	g.setFont(new Font("돋움체", Font.CENTER_BASELINE, 90));
     	g.drawString("<< T E T R I S >>", 200, 80);
        
        setOpaque(false); //그림을 표시하게 설정,투명하게 조절
        
    }
}
