/**
*@author YJolla 
*@version create time:2020年2月28日 下午4:52:42
*/
package creaters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import main.GameMain;
import window.GameWindow;

import javax.swing.*;

public class CreatMineThread implements Callable {
	List<String> mines = new ArrayList<String>();
	public static HashMap<JButton,JButton[]> nearButton = new HashMap<JButton,JButton[]>();
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


	// 检测按钮的周围有多少个雷
	private int nearMines(JButton btn) {
		int result = 0;
		// 需要获取周围的所有按钮
		JButton[] nearbtns = getNearBtn(btn);
		for (int i = 0; i < nearbtns.length; i++) {
			if (nearbtns[i] != null) {
				for (int j = 0; j < GameMain.GW.mineS.length; j++) {
					if (GameMain.GW.mineS[j].equals(nearbtns[i].getActionCommand())) {
						result++;
					}
				}
			}
		}
		return result;
	}

	// 获取一个按钮的周围的所有按钮
	private JButton[] getNearBtn(JButton btn) {
		String command = btn.getActionCommand();
		String[] strs = command.split(","); // 用,分割
		int row = Integer.parseInt(strs[0]); // passInt 和Valueof
		int col = Integer.parseInt(strs[1]);

		JButton[] result = new JButton[8];
		JButton lefttopbtn = null;
		JButton topbtn = null;
		JButton righttopbtn = null;
		JButton leftbtn = null;
		JButton rightbtn = null;
		JButton lfetbuttombtn = null;
		JButton rightbuttombtn = null;
		JButton buttombtn = null;

		if (row - 1 >= 0 && col - 1 >= 0) {// 行为row
			// 获取左上角的按钮
			lefttopbtn = GameMain.GW.btns[row - 1][col - 1];
		}
		if (row - 1 >= 0) {
			topbtn = GameMain.GW.btns[row - 1][col];
		}
		// 右上方的按钮
		if (row - 1 >= 0 && col + 1 < GameMain.GW.btncols) {
			righttopbtn = GameMain.GW.btns[row - 1][col + 1];
		}
		// 左边
		if (col - 1 >= 0) {
			leftbtn = GameMain.GW.btns[row][col - 1];
		}
		// 右边
		if (col + 1 < GameMain.GW.btncols) {
			rightbtn = GameMain.GW.btns[row][col + 1];
		}
		// 左下
		if (col - 1 >= 0 && row + 1 < GameMain.GW.btnrows) {
			lfetbuttombtn = GameMain.GW.btns[row + 1][col - 1];
		}
		// 下方
		if (row + 1 < GameMain.GW.btnrows) {
			buttombtn = GameMain.GW.btns[row + 1][col];
		}
		// 右下
		if (row + 1 < GameMain.GW.btnrows && col + 1 < GameMain.GW.btncols) {
			rightbuttombtn = GameMain.GW.btns[row + 1][col + 1];
		}
		result[0] = lefttopbtn;
		result[1] = topbtn;
		result[2] = righttopbtn;
		result[3] = leftbtn;
		result[4] = rightbtn;
		result[5] = lfetbuttombtn;
		result[6] = buttombtn;
		result[7] = rightbuttombtn;
		return result;

	}
}
