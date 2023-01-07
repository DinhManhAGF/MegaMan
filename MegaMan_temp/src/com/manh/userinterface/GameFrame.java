package com.manh.userinterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.manh.effect.CacheDataLoader;

public class GameFrame extends JFrame {
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 600;

	private GamePanel gamePanel;

	public GameFrame() {
		Toolkit toolkit  = this.getToolkit();
		Dimension dimension = toolkit.getScreenSize();
        this.setBounds((dimension.width - SCREEN_WIDTH)/2, (dimension.height - SCREEN_HEIGHT)/2, SCREEN_WIDTH, SCREEN_HEIGHT);

		long begin = System.currentTimeMillis();
		this.setTitle("Mega Man");
		Image img = new ImageIcon(this.getClass().getResource("/megaman2.png")).getImage();
		this.setIconImage(img);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// LoadData tại đây để vì tại và trước dòng newGamePanel() vì trong constructor
		// GamePanel có sử dụng việc getAnimation/getFrameImage từ Hashtable nên bắt
		// buộc Hashtable phải có dữ liệu trước đó rồi
		try {
			CacheDataLoader.getInstance().LoadData();
		} catch (IOException e) {
			e.printStackTrace();
		}

		gamePanel = new GamePanel();
		add(gamePanel);

		// Bắt sự kiện bàn phím
		this.addKeyListener(gamePanel);
	}

	public static void main(String[] args) {
		GameFrame gameFrame = new GameFrame();
		gameFrame.setVisible(true);
		gameFrame.startGame();
	}

	public void startGame() {
		gamePanel.startGame();
	}
}
