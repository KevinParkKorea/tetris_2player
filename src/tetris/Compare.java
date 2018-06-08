package tetris;

public class Compare extends Thread{
	
	Data data1 = new Data();
	Data data2 = new Data();
	
	public Compare(Data a, Data b) {
		data1 = a;
		data2 = b;
	}

	//���� ������ ����Ǹ� �ٸ��ʵ� ������ ���߰� ���ڸ� ���Ѵ�
	public void gameOverStop(Data data1, Data data2) {
		if (data1.gameOverFlag == true) {
			data2.gameOver = true;
			data2.status = false;			
			winnerChoose(data1, data2);
		}
		else if (data2.gameOverFlag == true) {
			data1.gameOver = true;
			data1.status = false;
			winnerChoose(data1, data2);
		}
	}
	
	//���� �� ���� ����
	public void winnerChoose(Data data1, Data data2) {
		if(data1.score > data2.score) {
			data1.winner = 1;
			data2.winner = 0;
		}
		else if(data1.score < data2.score) {
			data2.winner = 1;
			data1.winner = 0;
		}else {
			//���� ��¿��?	
		}
	}
		
	public void run(){
		while(true){
			try {Thread.sleep(1);} catch (InterruptedException e1) {}
			gameOverStop(data1, data2);
		}
	}

}
