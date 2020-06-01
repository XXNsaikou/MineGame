/**
*@author YJolla 
*@version create time:2020��2��28�� ����6:44:49
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
		 * @version create time:2020��2��28�� ����6:44:49
		 */
		if (e.isMetaDown()) {
			// ���µ�������Ҽ�
			// �������µİ�ť�ϲ�һ����
			ImageIcon image = new ImageIcon("images/2.jpg");
			// ��ȡ������İ�ť
			JButton clickbtn = (JButton) e.getSource();

			// �жϰ�ť���Ƿ���ͼƬ
			if (clickbtn.getIcon() == null) {
				// ����ͼƬ
				clickbtn.setIcon(image);
				// �ж��Ҽ��İ�ť�Ƿ���һ����
				if (isTreadMine(clickbtn)) {
					GameMain.GW.findMines++;
				}
			} else {
				// ȡ��ͼƬ
				clickbtn.setIcon(null);
				if (isTreadMine(clickbtn)) {
					GameMain.GW.findMines++;
				}
			}
			// �ж��ҵ����׵������Ƿ��ԭ���׵�����һ��
			if (GameMain.GW.findMines == GameMain.GW.mineCount) {
				JOptionPane.showMessageDialog(null, "��ϲ�㣬��������׵��ų����� <(�����)>��");
			}
			
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		/**
		 * @author YJolla
		 * @version create time:2020��2��28�� ����6:44:49
		 */

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		/**
		 * @author YJolla
		 * @version create time:2020��2��28�� ����6:44:49
		 */

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		/**
		 * @author YJolla
		 * @version create time:2020��2��28�� ����6:44:49
		 */

	}

	@Override
	public void mouseExited(MouseEvent e) {
		/**
		 * @author YJolla
		 * @version create time:2020��2��28�� ����6:44:49
		 */

	}

	// �ж���û�е�����
	private boolean isTreadMine(JButton btn) {
		for (int i = 0; i < GameMain.GW.mineS.length; i++) {
			if (GameMain.GW.mineS[i].equals(btn.getActionCommand())) {
				return true;
			}
		}
		return false;
	}

}
