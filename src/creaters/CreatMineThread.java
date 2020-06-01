/**
*@author YJolla 
*@version create time:2020年2月28日 下午4:52:42
*/
package creaters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import main.GameMain;
import window.GameWindow;

public class CreatMineThread implements Callable {
	List<String> mines = new ArrayList<String>();
	public String[] createMines(int mineCount,int btnrows,int btncols) throws Exception {
		mines = call(mineCount,btnrows,btncols);
		String[] minesArr = new String[mineCount];
		for (int i = 0; i < mines.size(); i++) {
			minesArr[i]=mines.get(i);
		}
		mines.clear();
		return minesArr;
	}
	public List<String> call(int mineCount,int btnrows,int btncols) {
		Random r=new Random();
		int count=0;
		
		while(count<mineCount) {
			int row=r.nextInt(btnrows);
			int col=r.nextInt(btncols);
			String l=row+","+col;
			if(!isHave(l)){
				mines.add(count, l);
				count++;
			}
		}
		return mines;
		
	}
	
	@Override
	public String[] call() throws Exception {
		/**
		*@author YJolla 
		*@version create time:2020年2月28日 下午4:52:46
		*/
		return null;
	}
	//判断随机产生的地雷的坐标是否存在
	private boolean isHave(String location) {
		boolean bl=false;
		if(mines.size()==0) {
			return bl;
		}
		for (int i = 0; i < mines.size(); i++) {
			if (mines.get(i)!=null) {
				if (mines.get(i).equals(location)) {
					return true;
				}
			}
		}
		return bl;
	}
}
