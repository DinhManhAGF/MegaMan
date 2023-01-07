package com.manh.gameobjects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.manh.effect.Animation;
import com.manh.effect.CacheDataLoader;

public class BlueFire extends Bullet{

	public BlueFire(float x, float y, GameWorld gameWorld) {
		super(x, y, 60, 30, 1.0f, 20, gameWorld);
		forwardBulletAnim = CacheDataLoader.getInstance().getAnimation("bluefire");
		backBulletAnim = CacheDataLoader.getInstance().getAnimation("bluefire");
		backBulletAnim.flipAllImage();
	}

	private Animation forwardBulletAnim, backBulletAnim;
	
	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		
		return getBoundForCollisionWithMap();
	}
	
	@Override
	public void draw(Graphics2D g2) {
		
		if(getSpeedX() > 0) {
			if(!forwardBulletAnim.isIgnoreFrame(0) && forwardBulletAnim.getCurrentFrame() == 3) {
				forwardBulletAnim.setIgnoreFrame(0);
				forwardBulletAnim.setIgnoreFrame(1);
				forwardBulletAnim.setIgnoreFrame(2);
			}
			
			forwardBulletAnim.Update(System.nanoTime());
			forwardBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
					(int) (getPosY() - getGameWorld().camera.getPosY()), g2);
		}else {
			if(!backBulletAnim.isIgnoreFrame(0) && backBulletAnim.getCurrentFrame() == 3) {
				backBulletAnim.setIgnoreFrame(0);
				backBulletAnim.setIgnoreFrame(1);
				backBulletAnim.setIgnoreFrame(2);
			}
			backBulletAnim.Update(System.nanoTime());
			backBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
					(int) (getPosY() - getGameWorld().camera.getPosY()), g2);
		}
		
	}

	@Override
	public void Update() {
		
		if(forwardBulletAnim.isIgnoreFrame(0) || backBulletAnim.isIgnoreFrame(0))
			setPosX(getPosX() + getSpeedX());
		super.Update();
	}
	
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	

	
	
}
