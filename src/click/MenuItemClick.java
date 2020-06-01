/**
*@author YJolla 
*@version create time:2020��2��28�� ����4:22:24
*/
package click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.GameMain;
import window.GameWindow;


public class MenuItemClick implements ActionListener {
	private static MenuItemClick m = new MenuItemClick();
	//˽�л�����,����ģʽ
	private MenuItemClick() {
	}
	public static MenuItemClick getMenuItemClickInst() {
		return m;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * @author YJolla
		 * @version create time:2020��2��28�� ����4:22:55
		 */
		String type = e.getActionCommand();
		// ����˳���ť�����˳�
		if (type.equals("exit")) {
			System.exit(0);
		} else
		// ���������ť
		if (type.equals("base")) {
			// initPanel(9, 9, 10);
			GameWindow.changeDiff(0);
			GameMain.GW.initTitle();
			GameMain.GW.initPanel(0);
		} else
		// ����м���ť
		if (type.equals("middle")) {
			// initPanel(16, 16, 40);
			GameWindow.changeDiff(1);
			GameMain.GW.initTitle();
			GameMain.GW.initPanel(1);
		} else
		// ����߼���ť
		if (type.equals("high")) {
			// initPanel(16, 30, 99);
			GameWindow.changeDiff(2);
			GameMain.GW.initTitle();
			GameMain.GW.initPanel(2);
		} else
		// ������ڰ�ť
		if (type.equals("about")) {
			JOptionPane.showMessageDialog(null,
					"MyMineGame" + "\n" + "Version: 2016" + "\n" + " Build id: 233.0.0-20161206" + "\n"
							+ " (c) Copyright YJolla,2016-2333.  All rights reserved." + "\n"
							+ " Email:yhz873923096@gmail.com");
		}
	}
}
