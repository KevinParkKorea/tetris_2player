import tetris.Compare;
import tetris.Data;
import tetris.DropBlock;
import tetris.GameFrame;

public class Main{
	
	public static void main(String[] args) {
		// thread
		Data data1 = new Data();
		Data data2 = new Data();
		DropBlock block1 = new DropBlock(data1);
		DropBlock block2 = new DropBlock(data2);
		Thread t1 = new Thread(new GameFrame(data1, data2));
		Compare compare = new Compare(data1, data2);
		t1.start();
		block1.start();
		block2.start();
		compare.start();
	}
}

//2player tetris