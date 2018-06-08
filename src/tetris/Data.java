package tetris;

public class Data {
	
	public Data(){	
		
	}
	
	/*map
	 x=0,11 y=21�� �׵θ�
	 [][1~10][]
	 [][][0~20]
	 left/right-0 bottom-1 */
	public boolean[][][] map=new boolean[2][12][21]; //map[���/�׸���][xĭ][yĭ]
	
	/*0-x 1-y*/
	public int[][] nowBlock=new int[2][4]; //nowBlock[x/y][��ǥ4���б�]
	

	public final int BlockSize = 20;

	public int idx=0;
	public int saveIdx=-1;
	public int spinIdx=0;
	public int nextIdx1=0;
	public int score=0;
	public int lineX=0; //������ ����x
	public int lineY=0; //������ ����y
	public int level=0;
	public int levelSleep=0;
	public boolean status = false;
	public boolean tSpin = false;
	public boolean spacestatus = false;
	public boolean gameOver = false;
	public boolean gameOverFlag = false;
	public int winner = 2;
	public int startCount =3;
	public int comboCount = 0;
	public int lineCount = 0;
	public int lineTemp = -1;
	public String overMsg=null;
	public String clearMsg = null;
	public String name;
}


