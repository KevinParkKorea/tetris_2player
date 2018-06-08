package tetris;

import java.util.Random;

import javax.swing.JOptionPane;

import panel.CenterPanel;
import tetris.Data;

public class Block {
	Random r = new Random();
		public final int[][] bX ={
				{0,1,0,1},{-1,0,1,2},{0,0,1,2},{2,2,1,0},{0,1,1,2},{0,1,1,2},{2,1,1,0}, //0-6
				{1,1,1,1},{-1,0,1,2},{0,0,0,0}, //7~9
				{1,2,1,1},{0,1,2,2},{1,1,1,0}, //10~12
				{1,1,1,2},{0,1,2,0},{0,1,1,1}, //13~15
				{1,1,2,1},{0,1,2,1},{0,1,1,1}, //16~18
				{1,0,1,0},{0,1,1,2},{0,-1,0,-1}, //19~21
				{1,1,2,2},{2,1,1,0},{0,0,1,1} //22~24
		};
		public final int[][] bY={
				{0,0,1,1},{0,0,0,0},{0,1,1,1},{0,1,1,1},{1,1,0,1},{0,0,1,1},{0,0,1,1}, //0~6
				{0,1,2,3},{1,1,1,1},{0,1,2,3}, //7~9
				{0,0,1,2},{1,1,1,2},{0,1,2,2}, //8~10
				{0,1,2,2},{1,1,1,2},{0,0,1,2}, //11~13
				{0,1,1,2},{1,1,1,2},{1,0,1,2}, //14~16
				{0,1,1,2},{1,1,2,2},{0,1,1,2}, //19~21
				{0,1,1,2},{1,1,2,2},{0,1,1,2} //22~24
		};
		
		/*���ư��°�*/
		public final int[] bSpinX= {0,1,-1,0,1,-1,0,1,-1};
		public final int[] bSpinY= {0,0,0,1,1,1,2,2,2
				
		};
		
		
	static Data data = new Data(); 
	Data data1 = new Data();
	Data data2 = new Data();
		
	private Block(Data a){
		data = a;
	}
	
	private Block(Data a, Data b){
		data1 = a;
		data2 = b;
	}
	
	private static class Singleton{ //�����ڰ� ���� ���� ȣ��Ǵ��� ������ �����Ǵ� ��ü�� �ϳ�, ���� ���� ���Ŀ� ȣ��� �����ڴ� ������ �����ڰ� ������ ��ü�� ����
		private static final Block instance = new Block(data);
	}

	public static Block getInstance(){
		return Singleton.instance;
	}
	
	private int random(Data data) { //�⺻��� 7����
		int rNum = r.nextInt(7);		
		return rNum;		
	}
	
	private int spincheck(Data data){
		int j=0;
		for (j = 0; j < bSpinX.length; j++) {
			int i = 0;
			for (i = 0; i < 4; i++) {
				try {
					if (data.map[0][bX[data.spinIdx][i] + data.lineX + 1 + bSpinX[j]][bY[data.spinIdx][i] + data.lineY + bSpinY[j]]) //data.map[][x�� ���� ���?][y�� ���ο� ���?]
						break;
				} catch (ArrayIndexOutOfBoundsException e) {
					break;
				}
			}
			if (i == 4) { //�ȴ��?
				data.idx = data.spinIdx;
				data.lineX=data.lineX+bSpinX[j];
				data.lineY=data.lineY+bSpinY[j];
				switch(data.idx) { //�Ǹ�絹����
				case 4:
				case 16:
				case 17:
				case 18:
					data.tSpin = true;
				}
				break;
			}
		}
		return j;
	}
	
	public void spin(Data data){
		switch(data.idx) {
		case 0: data.spinIdx=0;break;
		case 1:	data.spinIdx=7;break; //1->7
		case 2:	data.spinIdx=10;break;
		case 3: data.spinIdx=13;break;
		case 4: data.spinIdx=16;break;
		case 5: data.spinIdx=19;break;
		case 6: data.spinIdx=22;break;
		case 7: data.spinIdx=8;break; //7->8
		case 8: data.spinIdx=9;break; //8->9
		case 9: data.spinIdx=1;break; //9->1
		case 10: data.spinIdx=11;break;
		case 11: data.spinIdx=12;break;
		case 12: data.spinIdx=2;break;
		case 13: data.spinIdx=14;break;
		case 14: data.spinIdx=15;break;
		case 15: data.spinIdx=3;break;
		case 16: data.spinIdx=17;break;
		case 17: data.spinIdx=18;break;
		case 18: data.spinIdx=4;break;
		case 19: data.spinIdx=20;break;
		case 20: data.spinIdx=21;break;
		case 21: data.spinIdx=5;break;
		case 22: data.spinIdx=23;break;
		case 23: data.spinIdx=24;break;
		case 24: data.spinIdx=6;break;
		default:break;
		}
		int idx = spincheck(data);
		if(idx<bSpinX.length) blockSetting(data);
	}
	
	//�� ����
	private void blockSetting(Data data) { //�� ������� ��� ���� ��ġ ���ϰ�
		for(int i=0;i<4;i++) {
			data.nowBlock[0][i]=bX[data.idx][i]+data.lineX; //nowblock[0][i](=x��ǥ4���б�) = bx[����ε���][i]+��� (= ��x��ǥ) 
			data.nowBlock[1][i]=bY[data.idx][i]+data.lineY; //nowblock[1][i](=y��ǥ4���б�) = by[����ε���][i]+���� (= ��y��ǥ)  
		}
	}

	//�� �� ����
	private void newBlock(Data data){ //�� �� ����
		data.idx=data.nextIdx1;
		data.nextIdx1=random(data);
		data.lineX=4; //�� ���� ����� �����ǰ� ������ġ
		data.lineY=0; //�� ���� �� ������ �����ǰ� ������ġ
		blockSetting(data);
	}

	public void startCount(Data data) {
		data.startCount=3;
	}
	
	//�ʱ�ȭ
	public void gameStart(Data data) {
		data.status = true;
		data.comboCount=0;
		data.lineCount = 0;
		data.lineTemp = -1;
		data.level=1;
		data.levelSleep=1500;
		data.score=0;
		data.idx=random(data);
		data.nextIdx1=random(data);		
		data.lineX=4;
		data.lineY=0;
		data.clearMsg=null;
		data.overMsg = null;
		data.winner = 2;
		data.gameOverFlag = false;
		
		blockSetting(data);
		//�� �ʱ�ȭ
		//�߰� �� ���°�
		for(int i=0;i<12;i++){
			for(int j=0;j<21;j++){
				data.map[0][i][j]=false; //���� ���� ���� �κ� = false // ���� �̹� �׿��� �ִ� �κ� = true ȸ����
				data.map[1][i][j]=false;
		}}
		//�Ʒ� �����
		for(int i=0;i<12;i++){
			data.map[0][i][20]=true;//
			data.map[1][i][20]=true;
		}
		//�� �� �����
		for(int i=0;i<21;i++){
			data.map[0][0][i]=true;
			data.map[0][11][i]=true;
			data.map[1][0][i]=true;//
			data.map[1][11][i]=true;//
		}
	}	
	
	public void gameOver(Data data) {
		data.gameOver = false; 
		data.status = false;
		data.winner = 2;
		data.gameOverFlag = false;		
		
		GameFrame.btn.setVisible(true);
		GameFrame.bgp.repaint();
	}
	
	public void left(Data data){
		move("left", data);
	}
	public void right(Data data){
		move("right", data);
	}
	
	public void down(Data data) {
		move("down", data);
	}
	
	private void move(String str, Data data){
		int i=0;
		//str�� �޾Ƽ� ������ üũ
		switch(str){
		case "down":
			for(i=0;i<4;i++){
				if(data.map[1][data.nowBlock[0][i]+1][data.nowBlock[1][i]+1])break;
			}break;
		case "left":
			for(i=0;i<4;i++){
				if(data.map[0][data.nowBlock[0][i]][data.nowBlock[1][i]])break;
			}break;
		case "right":
			for(i=0;i<4;i++){
				if(data.map[0][data.nowBlock[0][i]+2][data.nowBlock[1][i]])break;
			}break;
		}
		
		//���� ������
		if(i==4){
			switch (str) {
			case "down":
				data.lineY++;
				for (i = 0; i < 4; i++) {
					data.nowBlock[1][i]++;
				}break;
			case "left":
				data.lineX--;
				for (i = 0; i < 4; i++) {
					data.nowBlock[0][i]--;
				}break;
			case "right":
				data.lineX++;
				for (i = 0; i < 4; i++) {
					data.nowBlock[0][i]++;
				}break;
			}
			
		}else{
			if(str.equals("down")) clear(str, data);
		}
	}
	
	private void clear(String str, Data data) {
		//������ üũ ������
		data.spacestatus = false; // spaceBar �ȴ�����
		gameOverCheck(data);
		if (data.status) {
			clearCheck(data);					
			newBlock(data);
		}
	}
	
	/* ���� ������ ���� üũ */
	private void gameOverCheck(Data data) {
		for (int i = 0; i < 4; i++) {
			data.map[0][data.nowBlock[0][i] + 1][data.nowBlock[1][i]] = true;
			data.map[1][data.nowBlock[0][i] + 1][data.nowBlock[1][i]] = true;

			//���迬�� -> ������ //������ �϶� //0011 0111 //�����ʳ� ���� ���ʳ����� �� �����鼭 �������̳� ����� ��
			if (data.nowBlock[0][i] + 1 > 3 && data.nowBlock[0][i] <= 7 && data.nowBlock[1][i] == 0) {
				data.gameOver = true; // first line is game over....
				data.status = false;
				data.gameOverFlag = true;	
			}
		}
	}	
	
	//���� ����
	private void clearCheck(Data data) {
		int i = 0;
		//���� Ŭ����
		int combo = 0; // �ѹ��� ��� �����ߴ��� üũ
		for (int j = 0; j < 4; j++) {
			for (i = 1; i < 11; i++) {
				if (!data.map[0][i][data.nowBlock[1][j]]) { //�ϳ��� ������
					break;
				} else if (i == 10) {//���� ��á����
					
					// ���� ����
					data.lineTemp = data.nowBlock[1][j];
					combo++;
					data.comboCount++;
					for (int kx = 1; kx < 11; kx++) { //���λ���
						data.map[0][kx][data.nowBlock[1][j]] = false;
						data.map[1][kx][data.nowBlock[1][j]] = false;
					}
					
					//�� �� ���� ����
					for (int ky = data.nowBlock[1][j]; ky >= 0; ky--) { //�ؿ��� ����
						for (int kx = 1; kx < 11; kx++) {
							if (data.map[0][kx][ky]) {
								data.map[0][kx][ky] = false; //�Ʒ����� �����ϰ�
								data.map[1][kx][ky] = false;
								data.map[0][kx][ky + 1] = true; //������ �����ϰ�
								data.map[1][kx][ky + 1] = true;
							}
						}
					}
				}
			}
		}
		comboCount(combo, data);
		
		/*
		if(data.level % 2 == 0){
			penalty(data);
		}
		*/
	}	
	
	//�޺� ���� 
	private void comboCount(int combo, Data data) { //���� ���ǵ� ����
		if (combo > 0) {
			data.clearMsg = combo + " COMBO!";			
		} else {
			data.lineTemp = -1;
			//data.comboCount = 0;
		}
		data.lineCount = (int) Math.pow(2, combo);
		data.score += (Math.pow(2, combo) + data.comboCount); //2�� �޺����� + �޺�
		// 15������ 1������ 
		data.level = data.score / 20 + 1;
		if (data.levelSleep > 100) {
			data.levelSleep = 1600 - (data.level * 100);
		} else {
			if (data.level > 25) data.levelSleep = 50;
			else if (data.level > 32) data.levelSleep = 30;
		}
	}
	
	// ������ ��������
	
		//���� �� ���� ��ĭ �ø���  //�� ���峲
		private void lineUP (Data data) {
			//�� �پ� �÷�������
			/*
			for(int i=0; i<4 ; i++) {
				if((data.map[0][data.nowBlock[0][i] + 1][data.nowBlock[1][i]] = true) && (data.map[1][data.nowBlock[0][i] + 1][data.nowBlock[1][i]] = true)) { //����� Ʋ��
					data.map[0][data.nowBlock[0][i] + 1][data.nowBlock[1][i]-1] = true; //����� �°�
					data.map[1][data.nowBlock[0][i] + 1][data.nowBlock[1][i]-1] = true;
					data.map[0][data.nowBlock[0][i] + 1][data.nowBlock[1][i]] = false; 
					data.map[1][data.nowBlock[0][i] + 1][data.nowBlock[1][i]] = false;
				}
			}*/
			
			for(int i=1;i<11;i++){
				for(int j=4;j<20;j++){
					if(data.map[0][i+1][j] = true) { 
						data.map[0][i][j-1] = true; 					
						data.map[0][i][j] = false; 
					}
					if(data.map[1][i+1][j] = true) {
						data.map[1][i][j-1] = true;
						data.map[1][i][j] = false;
					}				
				}
				
			}
			
		}
		
		//������ ����
		private void addBadLine(Data data) {
			for(int i = 1; i < 11; i++) {//ä��� �κ�
				if(i == r.nextInt(11)) { //�������� �̻��� ������					
					data.map[0][i][20] = true; //y�� ������ 20��°�ϱ� �� �Ʒ� ��
					data.map[1][i][20] = true;
				}					
			}
		}
	private void penalty (Data a) { //���� �׿��ִ� ������ �ø��� �� �Ʒ� ������ �����Ѵ�
		
		lineUP(a);
		addBadLine(a);	
	}
	
	private void blockAttack(Data a, Data b) {
		
		if(a.comboCount%3 == 0){
			lineUP(b);
			addBadLine(b);
		}
		else if(b.comboCount%3 == 0) {
			lineUP(a);
			addBadLine(a);
		}
	}
	
	public void namePopup(Data a) { 
		a.name = JOptionPane.showInputDialog("������ �̸��� �Է��ϼ���");
	}
	
}
