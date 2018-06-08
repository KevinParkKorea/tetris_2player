package rank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tetris.Data;

public class RankData {
	static String path = System.getProperty("user.dir"); 
	
	static File file = new File(path + "./src/record.txt"); //상대경로 이용
	static String fileName = path + "./src/record.txt";
	
	public String name = null;
	public int numVictory = 0;
	public static RankData[] personData = new RankData[10];
	public static RankData tempData ;
		
	
	RankData() {
		name = null;
		numVictory = 0;		
	}
	
	RankData(Data a) {
		name = a.name;
		numVictory = 0;
	}
	
	RankData(String a, int b) {
		name = a;
		numVictory = b;
	}
	
	public static void compareRank(RankData[] a) {
		
		for(int i=0 ; i<10 ; i++) {
			for(int j=i+1;j<10;j++) {
				if(a[i].numVictory < a[j].numVictory) {
					tempData = a[i];
					a[i] = a[j];
					a[j] = tempData;
				}
			}
		}			
	}
	
	public static void readRank() {
		try { 
			Scanner sc = new Scanner(file); 
			while( sc.hasNext() ) {  //토큰으로 잘라서 읽을게 있나 없나 검사 / 있으면 true				
				for(int i=0;i<10;i++) {
					personData[i] = new RankData(String.valueOf(sc.next()), Integer.parseInt(sc.next()));					
				}
			}
			
		} catch(FileNotFoundException fnfe) {
			System.out.println("not found file");
		} catch(IOException ioe) {
			System.out.println("not read file");
		} finally {
			try {
			} catch(Exception e) {
				e.printStackTrace();
			}
		}				
	}	

	public static void writeRank(RankData[] a) {
		try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
             
            for(int i=0;i<10;i++) {
            	bw.write(a[i].name + " ");
            	bw.flush();
            	bw.write(a[i].numVictory + " ");
            	bw.flush();
            }
 
            bw.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	public static void addRank(Data a) {//void함수에서 return을 쓰면 함수를 종료시킨다 (중요) 
		
		for(int i=0 ; i<10 ; i++) {
			if(personData[i].name.equals(a.name)) {
				personData[i].numVictory++;
				return;
			}
		}
		for(int i=0;i<10;i++) {
			if(personData[i].name.equals("null")) {
				personData[i].name = a.name;
				personData[i].numVictory=1;
				return;
			}
		}
		for(int i=9;i>=0;i--) {
			if(personData[i].numVictory == 1) {
				personData[i].name = a.name;
				personData[i].numVictory=1;
				return;
			}	
		}	
	}
	
	public static String returnPopup(RankData[] a) {
		System.out.println(path);
		String str = "";
		String result = "";
		
		for(int i=0; i<10; i++) {
			str = ("\n "+String.format("% 3d", i+1)+ String.format("% 10d",a[i].numVictory) + String.format("%10s", a[i].name));
			result += str;
		}
		return result;
	}
	
	public static String calRank(Data a) {
		String str = "";
		
		readRank();
		compareRank(personData);
		str = returnPopup(personData);
		writeRank(personData);	
		
		return str;
	}
}
