package Menu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import rank.RankData;
import tetris.Block;
import tetris.Data;
import tetris.GameFrame;

public class TetrisMenuBar extends JMenuBar{
	private static final long serialVersionUID = 1L;
	
	public static JMenu menu1 = new JMenu("Menu");
	
	public TetrisMenuBar(Data data1, Data data2) {
		menu1.setMnemonic('m');

		menu1.add(new Item1(data1, data2));	
		menu1.add(new Item2(data1, data2));
		menu1.add(new Item4());
		menu1.add(new Item3());
		
		add(menu1);
	}
}

class Item1 extends JMenuItem{
	private static final long serialVersionUID = 1L;

	public Item1(Data a, Data b) {
		super("replay");
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.gameOver = true;
				a.status = false;
				b.gameOver = true;
				b.status = false;
				Block.getInstance().gameOver(a);
				Block.getInstance().gameOver(b);
				GameFrame.bgp.repaint();
			}
		});
	}
}

class Item2 extends JMenuItem{
	private static final long serialVersionUID = 1L;
	
	protected Component popupMenu;
	
	String str1 = ("순위   승수   이름 \n===============");
	
	public Item2(Data a, Data b) {
		super("show ranking");
				
		if(a.winner == 1) {
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(popupMenu, str1+RankData.returnPopup(RankData.personData));	//고쳐야되는데 모르겠다
				}
			});
		}else {
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(popupMenu, str1+RankData.calRank(b));
				}
			});
		}
		
	}
}

class Item3 extends JMenuItem{
	private static final long serialVersionUID = 1L;

	public Item3() {
		super("Exit");
		setMnemonic('x');
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}	
}
class Item4 extends JMenuItem{
	private static final long serialVersionUID = 1L;
	
	protected Component popupMenu;
	
	public Item4() {
		super("도움말");
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(popupMenu, "날짜 : 2018-04-23\n< 키 안내 >\n1p : w/a/s/d capslock\n2p : 키보드화살표 spacebar");	
			}
		});
	}
}
