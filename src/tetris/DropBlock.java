package tetris;

import rank.RankData;

public class DropBlock extends Thread{
	
	Data data = new Data(); 
	
	public DropBlock(Data a) {		
		data = a;
	}
	
	private void startCount(Data data) {
		try{
			for(int i=0;i<3;i++){
				Thread.sleep(300);
				GameFrame.bgp.repaint();
			}
			data.startCount--;
			if(data.startCount==0){
				Block.getInstance().gameStart(data);
				GameFrame.bgp.repaint();
			}
		}catch(Exception e){}
	}
	
	private void gameOver(Data data) {
		if(data.winner == 1) {
			//game over Count
			try {
				for(int i=1;i<=8;i++){
					Thread.sleep(300);
					data.overMsg=" YOU WIN".substring(0, i);
					GameFrame.bgp.repaint();					
				}
			} catch (Exception e) {}
			Block.getInstance().namePopup(data); //승자 이름 입력
			RankData.readRank();
			RankData.addRank(data);
			RankData.compareRank(RankData.personData);			
			RankData.writeRank(RankData.personData);
			
			Block.getInstance().gameOver(data);			
		}
		else if(data.winner == 0){
			try {
				for(int i=1;i<=8;i++){
					Thread.sleep(300);
					data.overMsg="YOU LOSE".substring(0, i);
					GameFrame.bgp.repaint();
				}
			} catch (Exception e) {}
			Block.getInstance().gameOver(data);
		}		
	}
	
	private void gameStart() {
		try{
			Thread.sleep(data.levelSleep);
			Block.getInstance().down(data); //한번 내려감 //여기 스래드 도니까 주기적으로 블럭내려감
			GameFrame.bgp.repaint();
		}catch(Exception e){}
	}
	
	public void run(){
		while(true){
			try {Thread.sleep(1);} catch (InterruptedException e1) {}
			if(data.gameOver) gameOver(data);
			if(data.startCount>0) startCount(data);
			if(data.status) gameStart(); 
		}
	}
}
