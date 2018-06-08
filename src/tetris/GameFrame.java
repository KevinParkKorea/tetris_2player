package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Menu.TetrisMenuBar;
import panel.BackGroundPanel;
import panel.CenterPanel;
import panel.NextPanel;
import tetris.Data;

public class GameFrame extends JFrame implements KeyListener, Runnable{
	private static final long serialVersionUID = 1L;

	public static BackGroundPanel bgp=new BackGroundPanel();
	NextPanel npL=new NextPanel(null);
	NextPanel npR=new NextPanel(null);
	CenterPanel cpL=new CenterPanel(null);
	CenterPanel cpR=new CenterPanel(null);
	public static JButton btn;
	
	Data data1 = new Data();
	Data data2 = new Data();
	
	public static int flag = 0;
		
	
	public GameFrame(Data a, Data b){

		super("testris");
		
		data1 = a;
		data2 = b;		
		
		TetrisMenuBar menu = new TetrisMenuBar(data1, data2);
		setJMenuBar(menu);
		
		npL = new NextPanel(data1);
		npR = new NextPanel(data2);
		cpL = new CenterPanel(data1);
		cpR = new CenterPanel(data2);
		
		bgp.add(npR);
		bgp.add(cpR);
		bgp.add(cpL);
		bgp.add(npL);
		
		btn=new JButton("restart");
		GameFrame.btn.setVisible(false);
		btn.setLocation(585,320);
		btn.setSize(100,30);
				
		add(btn);
		add(bgp);
		
		pack();
		cpL.requestFocus();
		cpL.setFocusable(true);
		cpR.requestFocus();
		cpR.setFocusable(true);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {bgp.repaint();}
			@Override
			public void mousePressed(MouseEvent e) {bgp.repaint();}
			
			@Override
			public void mouseExited(MouseEvent e) {bgp.repaint();}
			
			@Override
			public void mouseEntered(MouseEvent e) {bgp.repaint();}
			
			@Override
			public void mouseClicked(MouseEvent e) {bgp.repaint();}
		});
	}
	
	@Override
	public void run() {		
		cpL.addKeyListener(this);
		cpR.addKeyListener(this);
		GameFrame.btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameFrame.btn.setText("RESTART");
				GameFrame.btn.setVisible(false);
				
				Block.getInstance().startCount(data1);
				Block.getInstance().startCount(data2);
				
				GameFrame.bgp.repaint();				
			}
		});
	}
	
	@Override
	public void keyPressed(KeyEvent e) {		
		if(data1.status){
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				Block.getInstance().left(data2);
				break;
			case KeyEvent.VK_D:
				Block.getInstance().right(data2);
				break;
			case KeyEvent.VK_W:
				Block.getInstance().spin(data2);
				break;
			case KeyEvent.VK_S:
				Block.getInstance().down(data2);
				break;
			case KeyEvent.VK_CAPS_LOCK:
				//스페이스바로 해놓으면 안되서 숫자로 표기
				data2.spacestatus = true;
				int i=0;
				while (data2.spacestatus) {
					if(i>1) {
						data2.tSpin = false;
					}
					Block.getInstance().down(data2);
					i++;
				}
				break;
			default:
				break;
			}
			bgp.repaint();
		}
		
		if(data2.status){
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				Block.getInstance().left(data1);
				break;
			case KeyEvent.VK_RIGHT:
				Block.getInstance().right(data1);
				break;
			case KeyEvent.VK_UP:
				Block.getInstance().spin(data1);
				break;
			case KeyEvent.VK_DOWN:
				Block.getInstance().down(data1);
				break;
			case 32:
				//스페이스바로 해놓으면 안되서 숫자로 표기
				data1.spacestatus = true;
				int i=0;
				while (data1.spacestatus) {
					if(i>1) {
						data1.tSpin = false;
					}
					Block.getInstance().down(data1);
					i++;
				}
				break;
			default:
				break;
			}
			bgp.repaint();
		}				
	}
	
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	
	
}
