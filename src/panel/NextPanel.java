package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import tetris.Block;
import tetris.Data;

public class NextPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	Data data = new Data();
	
	public NextPanel(Data data1){
		setPreferredSize(null);
		setBackground(new Color(255, 0, 0, 0));
		data = data1;
	}
	
	/*
	 * s: ���� e: �� x: x��ǥ y: y��ǥ
	 * size : ������
	 * bs = ��Ͻ�����ġ x, y
	 */
	int sX = 100;
	int sY = 220;
	
	@Override
	protected void paintComponent(Graphics g){ 
		super.paintComponent(g);
		int bsX=50;
		int bsY=265;
		g.setColor(Color.BLACK);
		g.setFont(new Font("����ü", Font.CENTER_BASELINE, 30));
		g.drawString("Level : "+data.level, 20, bsY-100);
		g.setFont(new Font("����ü", Font.CENTER_BASELINE, 20));
		g.drawString("[next]", 50, bsY-30);
		if(data.status){
			for (int i = 0; i < 4; i++) {
				int x1 = bsX + (Block.getInstance().bX[data.nextIdx1][i] * data.BlockSize);
				int y1 = bsY + (Block.getInstance().bY[data.nextIdx1][i] * data.BlockSize);
				
				g.setColor(Color.red);
				g.fillRect(x1, y1, data.BlockSize, data.BlockSize);
				g.setColor(Color.white);
				g.drawRect(x1, y1, data.BlockSize, data.BlockSize);				
			}
		}
		g.setColor(Color.black);
		g.setFont(new Font("����ü", Font.CENTER_BASELINE, 20));
		g.drawString("���ӵ� : "+ data.levelSleep , sX-80, sY+260);
		g.setColor(Color.black);
		g.setFont(new Font("����ü", Font.CENTER_BASELINE, 20));
		g.drawString("SCORE : "+data.score , sX-80, sY+290);
		g.drawString("total : "+data.comboCount+" line clear",sX-80,sY+320);
		if(data.clearMsg!=null) {
			g.drawString(data.clearMsg, sX-80, sY+230);
		}
	}
}
