package com.manh.userinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.manh.effect.Animation;
import com.manh.effect.CacheDataLoader;
import com.manh.effect.FrameImage;
import com.manh.gameobjects.GameWorld;
import com.manh.gameobjects.MegaMan;
import com.manh.gameobjects.PhysicalMap;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	private Thread thread;
	
	private InputManager inputManager;
	
	private BufferedImage bufImage;
	private Graphics2D bufG2D;

	
	public GameWorld gameWorld;

	public GamePanel() {
		gameWorld = new GameWorld();
		inputManager = new InputManager(gameWorld);
		
		bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
	}

	@Override
	// paint là phương thức tự động gọi khi add Panel này vào
	public void paint(Graphics g) {
		// Ko cần super.draw(g) nữa vì bufferedImage có kích thước to bằng
		// panel nên ko sợ vẫn còn các hình ảnh lúc trước chưa đựo xoá
		g.drawImage(bufImage, 0, 0, this);
	}

	public void updateGame() {
		// update lại vị trí x,y của nhân vật megaMan
		gameWorld.Update();
	}

	public void RenderGame() {
		if (bufImage == null) {
			bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT,
					BufferedImage.TYPE_INT_ARGB);
		}

		if (bufImage != null) {
			bufG2D = (Graphics2D) bufImage.getGraphics();
		}
		if (bufG2D != null) {
			bufG2D.setColor(Color.white);
			//bufG2D.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
			// draw object games here
			//megaman.draw(bufG2D);
			gameWorld.Render(bufG2D);
		}
		

		
		
	}

	public void startGame() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void run() {
		long FPS = 80; // 80 frame/s
		long period = 1000000000 / FPS;
		/*
		 * period: số chu kì của 1 frame = 1/80 (s) = 1e9/80 (ns)
		 */
		long beginTime = System.nanoTime();
		while (true) {
			updateGame();
			RenderGame();
			repaint();
			//xóa bộ đệm mỗi khi draw để đỡ bị giật lag
			Toolkit.getDefaultToolkit().sync();
			long deltaTime = System.nanoTime() - beginTime;
			long sleepTime = period - deltaTime;
			try {
				// Thread.sleep(milisecond) => Từ ns/10^6 để ra ms
				if (sleepTime < 0)
					Thread.sleep(period / 2000000);
				else
					Thread.sleep(sleepTime / 1000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			beginTime = System.nanoTime();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		inputManager.processKeyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		inputManager.processKeyReleased(e.getKeyCode());
	}

}
