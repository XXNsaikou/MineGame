/**
*@author YJolla 
*@version create time:2020年2月28日 下午6:44:49
*/
package click;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.GameMain;
import window.GameWindow;

public class buttonMouse implements MouseListener {
	static buttonMouse bm = new buttonMouse();
	
	public static buttonMouse getButtonMouseIns(){
		return bm;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		/**
		 * @author YJolla
		 * @version create time:2020年2月28日 下午6:44:49
		 */
		if (e.isMetaDown()) {
			// 按下的是鼠标右键
			// 在所按下的按钮上插一面旗
			ImageIcon image = new ImageIcon("images/2.jpg");
			// 获取所点击的按钮
			JButton clickbtn = (JButton) e.getSource();

			// 判断按钮上是否有图片
			if (clickbtn.getIcon() == null) {
				// 设置图片
				clickbtn.setIcon(image);
				// 判断右键的按钮是否是一个雷
				if (isTreadMine(clickbtn)) {
					GameMain.GW.findMines++;
				}
			} else {
				// 取消图片
				clickbtn.setIcon(null);
				if (isTreadMine(clickbtn)) {
					GameMain.GW.findMines++;
				}
			}
			// 判断找到的雷的数量是否跟原有雷的数量一致
			if (GameMain.GW.findMines == GameMain.GW.mineCount) {
				JOptionPane.showMessageDialog(null, "恭喜你，完成所有雷的排除！！ <(￣︶￣)>　");
			}
			
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		/**
		 * @author YJolla
		 * @version create time:2020年2月28日 下午6:44:49
		 */

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		/**
		 * @author YJolla
		 * @version create time:2020年2月28日 下午6:44:49
		 */

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		/**
		 * @author YJolla
		 * @version create time:2020年2月28日 下午6:44:49
		 */

	}

	@Override
	public void mouseExited(MouseEvent e) {
		/**
		 * @author YJolla
		 * @version create time:2020年2月28日 下午6:44:49
		 */

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

}
