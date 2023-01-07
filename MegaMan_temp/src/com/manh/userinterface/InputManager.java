package com.manh.userinterface;

import java.awt.event.KeyEvent;

import com.manh.gameobjects.GameWorld;
import com.manh.gameobjects.MegaMan;

//Lớp này nhận 
public class InputManager {
	
	private GameWorld gameWorld;
	
	public InputManager(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	public void processKeyPressed(int keyCode) {
		
		switch (keyCode) {
		case KeyEvent.VK_UP:
			break;
			
		case KeyEvent.VK_DOWN:
			gameWorld.megaman.dick();
			break;
			
		case KeyEvent.VK_LEFT:
			gameWorld.megaman.setDirection(gameWorld.megaman.LEFT_DIR);
			gameWorld.megaman.run();
			break;
			
		case KeyEvent.VK_RIGHT:
			gameWorld.megaman.setDirection(gameWorld.megaman.RIGHT_DIR);
			gameWorld.megaman.run();
			break;
			
		case KeyEvent.VK_ENTER:
			if(gameWorld.state == gameWorld.PAUSEGAME) {
				if(gameWorld.previousState == gameWorld.GAMEPLAY)
					gameWorld.switchState(gameWorld.GAMEPLAY);
				else
					gameWorld.switchState(gameWorld.TUTORIAL);
				gameWorld.bgMusic.loop(0);
				gameWorld.bgMusic.start();
			}
			if(gameWorld.state == gameWorld.TUTORIAL && gameWorld.storyTutorial >= 1) {
				//Chạy đến hết phần mảng giới thiệu
				if(gameWorld.storyTutorial <= 3) {
					gameWorld.storyTutorial++;
					gameWorld.currentSize = 1;
					gameWorld.textTutorial = gameWorld.texts[gameWorld.storyTutorial - 1];
				}else
					gameWorld.switchState(gameWorld.GAMEPLAY);
				
				//Gặp boss
				if(gameWorld.tutorialState == gameWorld.MEETFINALBOSS)
					gameWorld.switchState(gameWorld.GAMEPLAY);
			}
			break;
			
		case KeyEvent.VK_SPACE:
			gameWorld.megaman.jump();
			break;
			
		case KeyEvent.VK_A:
			gameWorld.megaman.attack();
			break;
		}
	}

	public void processKeyReleased(int keyCode) {
		switch (keyCode) {
		
		case KeyEvent.VK_UP:
			break;
			
		case KeyEvent.VK_DOWN:
			gameWorld.megaman.standUp();
			break;
			
		case KeyEvent.VK_LEFT:
			if(gameWorld.megaman.getSpeedX() < 0)
				gameWorld.megaman.stopRun();
			break;
			
		case KeyEvent.VK_RIGHT:
			if(gameWorld.megaman.getSpeedX() > 0)
				gameWorld.megaman.stopRun();
			break;
			
		case KeyEvent.VK_ENTER:
			break;
			
		case KeyEvent.VK_SPACE:
			break;
			
		case KeyEvent.VK_A:
			break;
		}
	}
}
