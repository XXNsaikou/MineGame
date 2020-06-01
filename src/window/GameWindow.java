/**
*@author YJolla 
*@version create time:2020��2��25�� ����12:06:47
*/
package window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import click.MenuItemClick;
import click.buttonClick;
import click.buttonMouse;
import creaters.CreatMineThread;
import main.GameMain;

public class GameWindow extends JFrame {
	//��ò˵�������������
	static MenuItemClick m = MenuItemClick.getMenuItemClickInst();
	//��ð�ť�����������
	buttonClick bc = buttonClick.getButtonClickIns();
	buttonMouse bm = buttonMouse.getButtonMouseIns();
	CreatMineThread cmt = new CreatMineThread();
	
	//��Ϸ�Ѿ����й�
	static int gameTimes=0;
	// ���������Ѷȵĵ���
	String[] mines0 = new String[10];
	String[] mines1 = new String[40];
	String[] mines2 = new String[99];
	// ����׵�����
	public String[] mineS;
	//��̽������
	public static int findMines=0;
	// ������Ű�ť������
	public JButton btns[][];
	public JButton btns2[][];
	// ��ʼ��Ĭ����Ϸ����(�м�)
	public static int mineCount = 40;
	public static int btnrows = 16;
	public static int btncols = 16;
	public int safeButton;
	// Ĭ���Ѷ�Ϊ�е�(0,1,2�����ȼ�)
	public static int difficult = 1;
	// ��ʼ������ؼ�
	private static JPanel topPanel = new JPanel();
	private static JPanel leftpanel = new JPanel();
	private static JPanel centerPanel = new JPanel();
	private static JPanel rightPanel = new JPanel();
	private static JPanel bottomPanel = new JPanel();

	public GameWindow() {
		// ������������������
		try {
			createMines(mineCount, btnrows, btncols);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��ɲ˵������
		addMenu();
		// ��ʼ������ʹ���
		initTitle();
		
	}

	// �����Ѷ�
	public static void changeDiff(int difficult) {
		GameWindow.difficult = difficult;
	}

	// ��ʼ�����⼰����
	public void initTitle() {
		// ��ʼ��������
		this.setTitle("�ҵ�ɨ��V2.0");
		this.setIconImage(new ImageIcon("images/xxx.png").getImage());
		// ��ʼ�������С
		switch (difficult) {
		// �����Ѷ�:
		case 0:
			this.mineCount = 10;
			this.btncols = 9;
			this.btnrows = 9;
			// �����м����Ĵ�С
			centerPanel.setSize(395, 395);
			this.setSize(331, 400);
			// ����
			this.setLocationRelativeTo(null);
			if(gameTimes<1) {
				addMenu();
			}
			safeButton=9*9-10;
			break;
		case 1:
			this.mineCount = 40;
			this.btncols = 16;
			this.btnrows = 16;
			// �����м����Ĵ�С
			centerPanel.setSize(640, 640);
			this.setSize(576, 645);
			// ����
			
			this.setLocationRelativeTo(null);
			safeButton=16*16-40;
			break;
		case 2:
			this.mineCount = 99;
			this.btncols = 16;
			this.btnrows = 30;
			// �����м����Ĵ�С
			centerPanel.setSize(1130, 640);
			this.setSize(1066, 640);
			// ����
			this.setLocationRelativeTo(null);
			if(gameTimes<1) {
				addMenu();
			}
			safeButton=16*30-99;
			break;
		}
		this.setVisible(true);
		// �رշ�ʽ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameTimes=1;
	}

	// ��ɲ˵������
	private void addMenu() {
		// ����һ���˵���
		JMenuBar menubar = new JMenuBar();

		// �����˵���ť
		JMenu menu1 = new JMenu("��Ϸ(G)");
		JMenu menu2 = new JMenu("����(H)");
		JMenu menu3 = new JMenu("��ʷ�ɼ�");

		// ���˵���ť��ӵ��˵�������
		menubar.add(menu1);
		menubar.add(menu2);
		menubar.add(menu3);

		// ��Ӳ˵���
		JMenuItem item1 = new JMenuItem("����");
		item1.setActionCommand("base");
		JMenuItem item2 = new JMenuItem("�м�");
		item2.setActionCommand("middle");
		JMenuItem item3 = new JMenuItem("�߼�");
		item3.setActionCommand("high");
		JMenuItem item4 = new JMenuItem("�˳�");
		item4.setActionCommand("exit");
		JMenuItem item5 = new JMenuItem("����");
		item5.setActionCommand("about");
		JMenuItem itme6 = new JMenuItem("�鿴��ʷ�ɼ�");
		itme6.setActionCommand("cjb");

		// Ϊ�˵���ע�����¼�
		item1.addActionListener(m);
		item2.addActionListener(m);
		item3.addActionListener(m);
		item4.addActionListener(m);
		item5.addActionListener(m);
		itme6.addActionListener(m);

		// ��������ť��ӵ��˵���ť
		menu1.add(item1);

		// ��ӷָ���
		menu1.addSeparator();
		menu1.add(item2);
		menu1.addSeparator();
		menu1.add(item3);
		menu1.addSeparator();
		menu1.add(item4);
		// �����ڰ�ť��ӵ�������ť
		menu2.add(item5);

		// �� ��ʷ�ɼ��˵�����ӵ� �˵�3��
		menu3.add(itme6);

		// ���˵�����ӵ�������
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(menubar);
		// �������Ĭ�ϲ���
		centerPanel.setLayout(null);
		// �����м����Ĵ�С
		centerPanel.setSize(640, 640);
		// ��������
		 try {
			createMines(mineCount,btnrows,btncols);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ������ť ���������淽��
		 initPanel(difficult);
		
		// ����ͬ�������ӵ�������
		this.add(topPanel, BorderLayout.NORTH);
		// this.add(leftpanel,BorderLayout.WEST);
		this.add(centerPanel, BorderLayout.CENTER);
		// this.add(rightPanel,BorderLayout.EAST);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	// ����ָ��������������
	public void createMines(int mineCount, int btnrows, int btncols) throws Exception {
		/**
		 * @author YJolla
		 * @version create time:2020��2��28�� ����5:07:11
		 */
		
		mines0 = cmt.createMines(10, 9, 9);
		mines1 = cmt.createMines(40, 16, 16);
		mines2 = cmt.createMines(99, 16, 30);
	}

	// ��ɴ��尴ť�����
	public void initPanel(int diff) {
		switch (difficult) {
		// �����Ѷ�:
		case 0:
			btns = new JButton[btnrows][btncols];
			btns2 = new JButton[btnrows][btncols];
			mineS=mines0;
			safeButton=60;
			break;
		case 1:
			btns = new JButton[btnrows][btncols];
			btns2 = new JButton[btnrows][btncols];
			mineS=mines1;
			safeButton=216;
			break;
		case 2:
			btns = new JButton[16][30];
			btns2 = new JButton[16][30];
			mineS=mines2;
			safeButton=381;
			break;
		}
		//������
		centerPanel.removeAll();
		//������ť�����
		createBtns();
	}
	//��ʼ�����еİ�ť
	private void createBtns() {
		/**
		*@author YJolla 
		*@version create time:2020��2��28�� ����6:29:00
		*/
		for (int i = 0; i < btns.length; i++) {
			for (int j = 0; j < btns[i].length; j++) {
				JButton btn=new JButton();
				//���ð�ť�Ĵ�С
				btn.setSize(35, 35);
				//���ð�ť���������
				btn.setActionCommand(i+","+j);
				//����ť��ӵ���¼�
				btn.addActionListener(bc);
				//����ťע������Ҽ��¼�
				btn.addMouseListener(bm);
				//���ð�ť������е�����

				int row=i;
				int col=j;
				int x=col*35;
				int y=row*35;
				btn.setLocation(x, y);
				//����ť�������
				centerPanel.add(btn);
				//��ӵ���ά������
				btns[i][j]=btn;
				btns2[i][j]=btn;
			}
		}
	}

}
