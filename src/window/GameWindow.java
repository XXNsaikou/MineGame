/**
*@author YJolla 
*@version create time:2020年2月25日 上午12:06:47
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
	//获得菜单项点击监听对象
	static MenuItemClick m = MenuItemClick.getMenuItemClickInst();
	//获得按钮点击监听对象
	buttonClick bc = buttonClick.getButtonClickIns();
	buttonMouse bm = buttonMouse.getButtonMouseIns();
	CreatMineThread cmt = new CreatMineThread();
	
	//游戏已经运行过
	static int gameTimes=0;
	// 创建三个难度的地雷
	String[] mines0 = new String[10];
	String[] mines1 = new String[40];
	String[] mines2 = new String[99];
	// 存放雷的数组
	public String[] mineS;
	//已探明的雷
	public static int findMines=0;
	// 声明存放按钮的数组
	public JButton btns[][];
	public JButton btns2[][];
	// 初始化默认游戏参数(中级)
	public static int mineCount = 40;
	public static int btnrows = 16;
	public static int btncols = 16;
	public int safeButton;
	// 默认难度为中等(0,1,2三个等级)
	public static int difficult = 1;
	// 初始化窗体控件
	private static JPanel topPanel = new JPanel();
	private static JPanel leftpanel = new JPanel();
	private static JPanel centerPanel = new JPanel();
	private static JPanel rightPanel = new JPanel();
	private static JPanel bottomPanel = new JPanel();

	public GameWindow() {
		// 先生成三个数量的雷
		try {
			createMines(mineCount, btnrows, btncols);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 完成菜单的添加
		addMenu();
		// 初始化标题和窗口
		initTitle();
		
	}

	// 更改难度
	public static void changeDiff(int difficult) {
		GameWindow.difficult = difficult;
	}

	// 初始化标题及窗口
	public void initTitle() {
		// 初始化标题栏
		this.setTitle("我的扫雷V2.0");
		this.setIconImage(new ImageIcon("images/xxx.png").getImage());
		// 初始化窗体大小
		switch (difficult) {
		// 初级难度:
		case 0:
			this.mineCount = 10;
			this.btncols = 9;
			this.btnrows = 9;
			// 设置中间面板的大小
			centerPanel.setSize(395, 395);
			this.setSize(331, 400);
			// 居中
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
			// 设置中间面板的大小
			centerPanel.setSize(640, 640);
			this.setSize(576, 645);
			// 居中
			
			this.setLocationRelativeTo(null);
			safeButton=16*16-40;
			break;
		case 2:
			this.mineCount = 99;
			this.btncols = 16;
			this.btnrows = 30;
			// 设置中间面板的大小
			centerPanel.setSize(1130, 640);
			this.setSize(1066, 640);
			// 居中
			this.setLocationRelativeTo(null);
			if(gameTimes<1) {
				addMenu();
			}
			safeButton=16*30-99;
			break;
		}
		this.setVisible(true);
		// 关闭方式
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameTimes=1;
	}

	// 完成菜单的添加
	private void addMenu() {
		// 创建一个菜单栏
		JMenuBar menubar = new JMenuBar();

		// 创建菜单按钮
		JMenu menu1 = new JMenu("游戏(G)");
		JMenu menu2 = new JMenu("帮助(H)");
		JMenu menu3 = new JMenu("历史成绩");

		// 将菜单按钮添加到菜单栏当中
		menubar.add(menu1);
		menubar.add(menu2);
		menubar.add(menu3);

		// 添加菜单项
		JMenuItem item1 = new JMenuItem("初级");
		item1.setActionCommand("base");
		JMenuItem item2 = new JMenuItem("中级");
		item2.setActionCommand("middle");
		JMenuItem item3 = new JMenuItem("高级");
		item3.setActionCommand("high");
		JMenuItem item4 = new JMenuItem("退出");
		item4.setActionCommand("exit");
		JMenuItem item5 = new JMenuItem("关于");
		item5.setActionCommand("about");
		JMenuItem itme6 = new JMenuItem("查看历史成绩");
		itme6.setActionCommand("cjb");

		// 为菜单项注册点击事件
		item1.addActionListener(m);
		item2.addActionListener(m);
		item3.addActionListener(m);
		item4.addActionListener(m);
		item5.addActionListener(m);
		itme6.addActionListener(m);

		// 将初级按钮添加到菜单按钮
		menu1.add(item1);

		// 添加分割线
		menu1.addSeparator();
		menu1.add(item2);
		menu1.addSeparator();
		menu1.add(item3);
		menu1.addSeparator();
		menu1.add(item4);
		// 将关于按钮添加到帮助按钮
		menu2.add(item5);

		// 将 历史成绩菜单项添加到 菜单3中
		menu3.add(itme6);

		// 将菜单栏添加到窗体中
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(menubar);
		// 清空面板的默认布局
		centerPanel.setLayout(null);
		// 设置中间面板的大小
		centerPanel.setSize(640, 640);
		// 产生地雷
		 try {
			createMines(mineCount,btnrows,btncols);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 创建按钮 ，调用下面方法
		 initPanel(difficult);
		
		// 将不同的面板添加到窗体上
		this.add(topPanel, BorderLayout.NORTH);
		// this.add(leftpanel,BorderLayout.WEST);
		this.add(centerPanel, BorderLayout.CENTER);
		// this.add(rightPanel,BorderLayout.EAST);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	// 创建指定数量的雷坐标
	public void createMines(int mineCount, int btnrows, int btncols) throws Exception {
		/**
		 * @author YJolla
		 * @version create time:2020年2月28日 下午5:07:11
		 */
		
		mines0 = cmt.createMines(10, 9, 9);
		mines1 = cmt.createMines(40, 16, 16);
		mines2 = cmt.createMines(99, 16, 30);
	}

	// 完成窗体按钮的添加
	public void initPanel(int diff) {
		switch (difficult) {
		// 初级难度:
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
		//清空面板
		centerPanel.removeAll();
		//创建按钮并添加
		createBtns();
	}
	//初始化所有的按钮
	private void createBtns() {
		/**
		*@author YJolla 
		*@version create time:2020年2月28日 下午6:29:00
		*/
		for (int i = 0; i < btns.length; i++) {
			for (int j = 0; j < btns[i].length; j++) {
				JButton btn=new JButton();
				//设置按钮的大小
				btn.setSize(35, 35);
				//设置按钮的命令参数
				btn.setActionCommand(i+","+j);
				//给按钮添加点击事件
				btn.addActionListener(bc);
				//给按钮注册鼠标右键事件
				btn.addMouseListener(bm);
				//设置按钮在面板中的坐标

				int row=i;
				int col=j;
				int x=col*35;
				int y=row*35;
				btn.setLocation(x, y);
				//将按钮放入面板
				centerPanel.add(btn);
				//添加到二维数组中
				btns[i][j]=btn;
				btns2[i][j]=btn;
			}
		}
	}

}
