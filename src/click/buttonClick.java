/**
*@author YJolla 
*@version create time:2020年2月28日 下午6:29:53
*/
package click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.GameMain;
import window.GameWindow;

public class buttonClick implements ActionListener {
	JButton currentBtn;
	private static buttonClick bc = new buttonClick();

	public static buttonClick getButtonClickIns() {
		return bc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * @author YJolla
		 * @version create time:2020年2月28日 下午6:30:18
		 */
		// 获取所点击的按钮
		JButton clickbtn = (JButton) e.getSource();
		currentBtn = clickbtn;

		// 判断有没有点中雷
		if (isTreadMine(clickbtn)) {
			// 显示所有的雷
			dieShow();
		} else {
			int minecount = nearMines(clickbtn);
			// JOptionPane.showMessageDialog(null, "周围有"+minecount+"个雷");
			// 将所点击的按钮，从二维数组中移除
			String[] strs = clickbtn.getActionCommand().split(",");
			int row = Integer.parseInt(strs[0]);
			int col = Integer.parseInt(strs[1]);
			GameMain.GW.btns[row][col] = null;
			// 判断周围有没有雷
			if (minecount > 0) {
				// 拼接数字图片的名称
				String imageNum = "images/" + minecount + ".png";
				ImageIcon image = new ImageIcon(imageNum);
				clickbtn.setIcon(image);
			} else {
				ImageIcon image = new ImageIcon("images/0.png");
				clickbtn.setIcon(image);
				// 继续完成周围8个按钮的点击
				JButton[] nearbtns = getNearBtn(clickbtn);
				for (int i = 0; i < nearbtns.length; i++) {
					if (nearbtns[i] != null) {
						// 用代码模拟鼠标点击
						nearbtns[i].doClick();
					}
				}
				// 重绘UI面板
				GameMain.GW.repaint();
				

			}
		}
	}

	// 判断有没有点中雷
	private boolean isTreadMine(JButton btn) {
		for (int i = 0; i < GameMain.GW.mineS.length; i++) {
			if (GameMain.GW.mineS[i].equals(btn.getActionCommand())) {
				return true;
			}
		}
		return false;
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

	// 如果踩到地雷则显示出地图上所有的地雷
	private void dieShow() {
		for (int i = 0; i < GameMain.GW.btncols; i++) {
			for (int j = 0; j < GameMain.GW.btns2[i].length; j++) {
				JButton btn = GameMain.GW.btns2[i][j];
				for (int k = 0; k < GameMain.GW.mineS.length; k++) {
					if (GameMain.GW.mineS[k].equals(btn.getActionCommand())) {
						// 创建一个图片对象
						ImageIcon image = new ImageIcon("images/index1.png");
						btn.setIcon(image);
					}
				}
			}
		}
		for (int i = 0; i < GameMain.GW.btncols; i++) {
			for (int j = 0; j < GameMain.GW.btnrows; j++) {
				for (int k = 0; k < GameMain.GW.mineS.length; k++) {
					GameMain.GW.btns2[i][j].setEnabled(false);
				}
			}
		}
		try {
			GameMain.GW.createMines(2, 3, 3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "游戏结束");
	}
}
