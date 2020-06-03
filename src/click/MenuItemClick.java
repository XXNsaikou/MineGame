/**
*@author YJolla 
*@version create time:2020年2月28日 下午4:22:24
*/
package click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.GameMain;
import window.GameWindow;


public class MenuItemClick implements ActionListener {
	private static MenuItemClick m = new MenuItemClick();
	//私有化构造,单例模式
	private MenuItemClick() {
	}
	public static MenuItemClick getMenuItemClickInst() {
		return m;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * @author YJolla
		 * @version create time:2020年2月28日 下午4:22:55
		 */
		String type = e.getActionCommand();
		// 点击退出按钮程序退出
		if (type.equals("exit")) {
			System.exit(0);
		} // 点击初级按钮
		 else if (type.equals("base")) {
			// initPanel(9, 9, 10);
			buttonClick.safeButton.clear();
			GameWindow.changeDiff(0);
			try {
				GameMain.GW.initTitle();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				GameMain.GW.initPanel(0);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else
		// 点击中级按钮
		if (type.equals("middle")) {
			buttonClick.safeButton.clear();
			// initPanel(16, 16, 40);
			GameWindow.changeDiff(1);
			try {
				GameMain.GW.initTitle();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				GameMain.GW.initPanel(1);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else
		// 点击高级按钮
		if (type.equals("high")) {
			buttonClick.safeButton.clear();
			// initPanel(16, 30, 99);
			GameWindow.changeDiff(2);
			try {
				GameMain.GW.initTitle();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				GameMain.GW.initPanel(2);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else
		// 点击关于按钮
		if (type.equals("about")) {
			JOptionPane.showMessageDialog(null,
					"MyMineGame" + "\n" + "Version: 2016" + "\n" + " Build id: 233.0.0-20161206" + "\n"
							+ " (c) Copyright YJolla,2016-2333.  All rights reserved." + "\n"
							+ " Email:yhz873923096@gmail.com");
		}
	}
}
