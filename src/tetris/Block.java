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
		
		/*돌아가는것*/
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
	
	private static class Singleton{ //생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나, 최초 생성 이후에 호출된 생성자는 최초의 생성자가 생성한 객체를 리턴
		private static final Block instance = new Block(data);
	}

	public static Block getInstance(){
		return Singleton.instance;
	}
	
	private int random(Data data) { //기본모양 7가지
		int rNum = r.nextInt(7);		
		return rNum;		
	}
	
	private int spincheck(Data data){
		int j=0;
		for (j = 0; j < bSpinX.length; j++) {
			int i = 0;
			for (i = 0; i < 4; i++) {
				try {
					if (data.map[0][bX[data.spinIdx][i] + data.lineX + 1 + bSpinX[j]][bY[data.spinIdx][i] + data.lineY + bSpinY[j]]) //data.map[][x축 벽에 닿니?][y축 라인에 닿니?]
						break;
				} catch (ArrayIndexOutOfBoundsException e) {
					break;
				}
			}
			if (i == 4) { //안닿네?
				data.idx = data.spinIdx;
				data.lineX=data.lineX+bSpinX[j];
				data.lineY=data.lineY+bSpinY[j];
				switch(data.idx) { //ㅗ모양돌리기
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
	
	//블럭 세팅
	private void blockSetting(Data data) { //새 블럭만들기 모양 고르고 위치 정하고
		for(int i=0;i<4;i++) {
			data.nowBlock[0][i]=bX[data.idx][i]+data.lineX; //nowblock[0][i](=x좌표4개읽기) = bx[모양인덱스][i]+가운데 (= 블럭x좌표) 
			data.nowBlock[1][i]=bY[data.idx][i]+data.lineY; //nowblock[1][i](=y좌표4개읽기) = by[모양인덱스][i]+맨위 (= 블럭y좌표)  
		}
	}

	//새 블럭 생성
	private void newBlock(Data data){ //새 블럭 생성
		data.idx=data.nextIdx1;
		data.nextIdx1=random(data);
		data.lineX=4; //새 블럭이 가운데서 생성되게 시작위치
		data.lineY=0; //새 블럭이 맨 위에서 생성되게 시작위치
		blockSetting(data);
	}

	public void startCount(Data data) {
		data.startCount=3;
	}
	
	//초기화
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
		//맵 초기화
		//중간 블렇 놓는곳
		for(int i=0;i<12;i++){
			for(int j=0;j<21;j++){
				data.map[0][i][j]=false; //블럭이 아직 없는 부분 = false // 블럭이 이미 쌓여져 있는 부분 = true 회색블럭
				data.map[1][i][j]=false;
		}}
		//아래 경계줄
		for(int i=0;i<12;i++){
			data.map[0][i][20]=true;//
			data.map[1][i][20]=true;
		}
		//양 옆 경계줄
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
		//str값 받아서 움직임 체크
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
		
		//실제 움직임
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
		//움직임 체크 맞으면
		data.spacestatus = false; // spaceBar 안누르면
		gameOverCheck(data);
		if (data.status) {
			clearCheck(data);					
			newBlock(data);
		}
	}
	
	/* 블럭이 맨위에 인지 체크 */
	private void gameOverCheck(Data data) {
		for (int i = 0; i < 4; i++) {
			data.map[0][data.nowBlock[0][i] + 1][data.nowBlock[1][i]] = true;
			data.map[1][data.nowBlock[0][i] + 1][data.nowBlock[1][i]] = true;

			//관계연산 -> 논리연산 //참참참 일때 //0011 0111 //오른쪽끝 부터 왼쪽끝까지 에 있으면서 맨윗줄이냐 물어보는 말
			if (data.nowBlock[0][i] + 1 > 3 && data.nowBlock[0][i] <= 7 && data.nowBlock[1][i] == 0) {
				data.gameOver = true; // first line is game over....
				data.status = false;
				data.gameOverFlag = true;	
			}
		}
	}	
	
	//라인 삭제
	private void clearCheck(Data data) {
		int i = 0;
		//라인 클리어
		int combo = 0; // 한번에 몇개를 삭제했는지 체크
		for (int j = 0; j < 4; j++) {
			for (i = 1; i < 11; i++) {
				if (!data.map[0][i][data.nowBlock[1][j]]) { //하나라도 안차면
					break;
				} else if (i == 10) {//한줄 꽉찼을때
					
					// 라인 비우기
					data.lineTemp = data.nowBlock[1][j];
					combo++;
					data.comboCount++;
					for (int kx = 1; kx < 11; kx++) { //라인삭제
						data.map[0][kx][data.nowBlock[1][j]] = false;
						data.map[1][kx][data.nowBlock[1][j]] = false;
					}
					
					//꽉 찬 라인 삭제
					for (int ky = data.nowBlock[1][j]; ky >= 0; ky--) { //밑에서 위로
						for (int kx = 1; kx < 11; kx++) {
							if (data.map[0][kx][ky]) {
								data.map[0][kx][ky] = false; //아래줄은 삭제하고
								data.map[1][kx][ky] = false;
								data.map[0][kx][ky + 1] = true; //윗줄은 유지하고
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
	
	//콤보 설정 
	private void comboCount(int combo, Data data) { //게임 스피드 증가
		if (combo > 0) {
			data.clearMsg = combo + " COMBO!";			
		} else {
			data.lineTemp = -1;
			//data.comboCount = 0;
		}
		data.lineCount = (int) Math.pow(2, combo);
		data.score += (Math.pow(2, combo) + data.comboCount); //2에 콤보제곱 + 콤보
		// 15점마다 1씩증가 
		data.level = data.score / 20 + 1;
		if (data.levelSleep > 100) {
			data.levelSleep = 1600 - (data.level * 100);
		} else {
			if (data.level > 25) data.levelSleep = 50;
			else if (data.level > 32) data.levelSleep = 30;
		}
	}
	
	// 배드블럭을 보내보자
	
		//쌓인 블럭 위로 한칸 올리기  //아 고장남
		private void lineUP (Data data) {
			//한 줄씩 올려버리기
			/*
			for(int i=0; i<4 ; i++) {
				if((data.map[0][data.nowBlock[0][i] + 1][data.nowBlock[1][i]] = true) && (data.map[1][data.nowBlock[0][i] + 1][data.nowBlock[1][i]] = true)) { //여기는 틀림
					data.map[0][data.nowBlock[0][i] + 1][data.nowBlock[1][i]-1] = true; //여기는 맞고
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
		
		//배드라인 생성
		private void addBadLine(Data data) {
			for(int i = 1; i < 11; i++) {//채우는 부분
				if(i == r.nextInt(11)) { //랜덤으로 이빨을 빼보자					
					data.map[0][i][20] = true; //y축 시작이 20번째니까 맨 아래 줄
					data.map[1][i][20] = true;
				}					
			}
		}
	private void penalty (Data a) { //기존 쌓여있는 블럭들을 올리고 맨 아래 배드블럭을 생성한다
		
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
		a.name = JOptionPane.showInputDialog("승자의 이름을 입력하세요");
	}
	
}
