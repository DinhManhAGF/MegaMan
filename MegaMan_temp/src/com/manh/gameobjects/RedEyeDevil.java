package com.manh.gameobjects;

import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.sound.sampled.Clip;

import com.manh.effect.Animation;
import com.manh.effect.CacheDataLoader;

public class RedEyeDevil extends ParticularObject{

	private Animation forwardAnim, backAnim;
	
	private long startTimeToShoot;
	
	private Clip shooting;
	
	public RedEyeDevil(float x, float y, GameWorld gameWorld) {
		super(x, y, 127, 89, 0, 100, gameWorld);
		backAnim = CacheDataLoader.getInstance().getAnimation("redeye");
		forwardAnim = CacheDataLoader.getInstance().getAnimation("redeye");
		forwardAnim.flipAllImage();
		startTimeToShoot = 0;
		setDamage(10);
		setTimeForNoBeHurt(300000000);
		shooting = CacheDataLoader.getInstance().getSound("redeyeshooting");
	}

	@Override
	public void attack() {
		
		shooting.start();
		Bullet bullet = new RedEyeBullet(getPosX(), getPosY(), getGameWorld());
		if(getGameWorld().megaman.getPosX() < getPosX()) bullet.setSpeedX(-8);
		else bullet.setSpeedX(8);
		bullet.setTeamType(getTeamType());
		getGameWorld().bulletManager.addObject(bullet);
	}

	@Override
	public void Update() {
		super.Update();
		if(System.nanoTime() - startTimeToShoot > 25*10000000) {
			attack();
			System.out.println("Red Eye attack");
			startTimeToShoot = System.nanoTime();
		}
	}
	
	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		Rectangle rect = getBoundForCollisionWithMap();
		rect.x += 20;
		rect.width -= 40;
		
		return rect;
	}

	@Override
	public void draw(Graphics2D g2) {
		if(!isObjectOutOfCameraView()) {
			if(getState() == NOBEHURT && (System.nanoTime()/10000000) % 2 != 1) {
				//plash...
			}else {
				/*if(getDirection() == LEFT_DIR) {
					backAnim.Update(System.nanoTime());
					backAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
							(int) (getPosY() - getGameWorld().camera.getPosY()), g2);
				}else {
					forwardAnim.Update(System.nanoTime());
					forwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
							(int) (getPosX() - getGameWorld().camera.getPosY()), g2);
				}*/
				if(getGameWorld().megaman.getPosX() < getPosX()) {
					backAnim.Update(System.nanoTime());
					backAnim.setCurrentFrame(forwardAnim.getCurrentFrame());
					backAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
							(int) (getPosY() - getGameWorld().camera.getPosY()), g2);
				}else {
					forwardAnim.Update(System.nanoTime());
					forwardAnim.setCurrentFrame(backAnim.getCurrentFrame());
					forwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
							(int) (getPosY() - getGameWorld().camera.getPosY()), g2);
				}

			}
		}
		
	}
	
	
}
