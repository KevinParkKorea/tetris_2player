package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;


import javax.swing.JButton;
import javax.swing.JPanel;

import tetris.Data;

public class CenterPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	Data data = new Data();
	
	
	/*
	 * s: Ω√¿€ e: ≥° x: x¡¬«• y: y¡¬«•
	 * size : ¡ı∞®∞™
	 * x y : block start
	 */
	int sX = 80;
	int sY = 140;
	int eX = 280;
	int eY = 540;

	public CenterPanel(Data a) {		
		data = a;
		
		setPreferredSize(null);
		setBackground(new Color(255, 0, 0, 0));
		setLayout(null);
	}
	
 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);		
		
		g.fillRect(sX, sY, eX - sX, eY - sY);
		g.setColor(Color.gray);
		for (int i = 1; i <= 19; i++) {
			g.drawLine(sX, sY + (data.BlockSize * i), eX, sY + (data.BlockSize * i));
		}

		for (int i = 1; i <= 9; i++) {
			g.drawLine(sX + (data.BlockSize * i), sY, sX + (data.BlockSize * i), eY);
		}
		
		//M A P block x¡¬«•¥¬ -20 
		for(int i=0;i<12;i++){
			for(int j=0;j<21;j++){
				if(data.map[0][i][j]&&data.map[1][i][j]){
					int mx = sX-data.BlockSize+(data.BlockSize*i);
					int my = sY+(data.BlockSize*j);
					g.setColor(Color.gray);
					g.fillRect(mx, my, data.BlockSize, data.BlockSize);
					g.setColor(Color.white);
					g.drawRect(mx, my, data.BlockSize, data.BlockSize);
				}
		}}
		
		//count
		if(data.startCount!=0) {
			g.setColor(Color.red);
			g.setFont(new Font("µ∏øÚ√º", Font.CENTER_BASELINE, 260));
			g.drawString(String.valueOf(data.startCount), 110, 400);
		}else if(data.overMsg!=null) {
			g.setColor(Color.red);
			g.setFont(new Font("µ∏øÚ√º", Font.CENTER_BASELINE, 60));
			g.drawString(data.overMsg, 45, 280);
		}
		
		//status
		if(data.status) {
			for(int i=0;i<4;i++) {
				int bx = sX+(data.BlockSize*data.nowBlock[0][i]);
				int by = sY+(data.BlockSize*data.nowBlock[1][i]);
				g.setColor(Color.red);
				g.fillRect(bx, by, data.BlockSize, data.BlockSize);
				g.setColor(Color.white);
				g.drawRect(bx, by, data.BlockSize, data.BlockSize);
			}
		}
		
		//Score
		g.setFont(new Font("µ∏øÚ√º", Font.ITALIC, 20));		
		g.setColor(Color.blue);
		if(data.lineTemp>-1) {		
			g.drawString("+"+data.lineCount, 310, sY+(data.lineTemp*data.BlockSize));
			g.drawString("+"+data.comboCount, 320, sY+(data.lineTemp*data.BlockSize)+20);		
		}
	}//paint end
}
