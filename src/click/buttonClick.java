/**
*@author YJolla 
*@version create time:2020��2��28�� ����6:29:53
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
		 * @version create time:2020��2��28�� ����6:30:18
		 */
		// ��ȡ������İ�ť
		JButton clickbtn = (JButton) e.getSource();
		currentBtn = clickbtn;

		// �ж���û�е�����
		if (isTreadMine(clickbtn)) {
			// ��ʾ���е���
			dieShow();
		} else {
			int minecount = nearMines(clickbtn);
			// JOptionPane.showMessageDialog(null, "��Χ��"+minecount+"����");
			// ��������İ�ť���Ӷ�ά�������Ƴ�
			String[] strs = clickbtn.getActionCommand().split(",");
			int row = Integer.parseInt(strs[0]);
			int col = Integer.parseInt(strs[1]);
			GameMain.GW.btns[row][col] = null;
			// �ж���Χ��û����
			if (minecount > 0) {
				// ƴ������ͼƬ������
				String imageNum = "images/" + minecount + ".png";
				ImageIcon image = new ImageIcon(imageNum);
				clickbtn.setIcon(image);
			} else {
				ImageIcon image = new ImageIcon("images/0.png");
				clickbtn.setIcon(image);
				// ���������Χ8����ť�ĵ��
				JButton[] nearbtns = getNearBtn(clickbtn);
				for (int i = 0; i < nearbtns.length; i++) {
					if (nearbtns[i] != null) {
						// �ô���ģ�������
						nearbtns[i].doClick();
					}
				}
				// �ػ�UI���
				GameMain.GW.repaint();
				

			}
		}
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

	// ��ⰴť����Χ�ж��ٸ���
	private int nearMines(JButton btn) {
		int result = 0;
		// ��Ҫ��ȡ��Χ�����а�ť
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

	// ��ȡһ����ť����Χ�����а�ť
	private JButton[] getNearBtn(JButton btn) {
		String command = btn.getActionCommand();
		String[] strs = command.split(","); // ��,�ָ�
		int row = Integer.parseInt(strs[0]); // passInt ��Valueof
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

		if (row - 1 >= 0 && col - 1 >= 0) {// ��Ϊrow
			// ��ȡ���Ͻǵİ�ť
			lefttopbtn = GameMain.GW.btns[row - 1][col - 1];
		}
		if (row - 1 >= 0) {
			topbtn = GameMain.GW.btns[row - 1][col];
		}
		// ���Ϸ��İ�ť
		if (row - 1 >= 0 && col + 1 < GameMain.GW.btncols) {
			righttopbtn = GameMain.GW.btns[row - 1][col + 1];
		}
		// ���
		if (col - 1 >= 0) {
			leftbtn = GameMain.GW.btns[row][col - 1];
		}
		// �ұ�
		if (col + 1 < GameMain.GW.btncols) {
			rightbtn = GameMain.GW.btns[row][col + 1];
		}
		// ����
		if (col - 1 >= 0 && row + 1 < GameMain.GW.btnrows) {
			lfetbuttombtn = GameMain.GW.btns[row + 1][col - 1];
		}
		// �·�
		if (row + 1 < GameMain.GW.btnrows) {
			buttombtn = GameMain.GW.btns[row + 1][col];
		}
		// ����
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

	// ����ȵ���������ʾ����ͼ�����еĵ���
	private void dieShow() {
		for (int i = 0; i < GameMain.GW.btncols; i++) {
			for (int j = 0; j < GameMain.GW.btns2[i].length; j++) {
				JButton btn = GameMain.GW.btns2[i][j];
				for (int k = 0; k < GameMain.GW.mineS.length; k++) {
					if (GameMain.GW.mineS[k].equals(btn.getActionCommand())) {
						// ����һ��ͼƬ����
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
		JOptionPane.showMessageDialog(null, "��Ϸ����");
	}
}
