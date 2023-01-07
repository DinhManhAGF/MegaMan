package com.manh.gameobjects;


import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.manh.effect.Animation;
import com.manh.effect.CacheDataLoader;

public class RobotRBullet extends Bullet{
	
    private Animation forwardBulletAnim, backBulletAnim;
    
    private long startTimeForChangeSpeedY;
    
    public RobotRBullet(float x, float y, GameWorld gameWorld) {
        super(x, y, 60, 30, 1.0f, 25, gameWorld);
        forwardBulletAnim = CacheDataLoader.getInstance().getAnimation("robotRbullet");
        backBulletAnim = CacheDataLoader.getInstance().getAnimation("robotRbullet");
        backBulletAnim.flipAllImage();
    }

    
    
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        // TODO Auto-generated method stub
        return getBoundForCollisionWithMap();
    }

    @Override
    public void draw(Graphics2D g2) {
            // TODO Auto-generated method stub
    	if(getSpeedX() > 0) {
			forwardBulletAnim.Update(System.nanoTime());
			if(getSpeedY() > 0)
				forwardBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
						(int) (getPosY() - getGameWorld().camera.getPosY()), g2);
			else if(getSpeedY() < 0)
				forwardBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
						(int) (getPosY() - getGameWorld().camera.getPosY()), g2);
			else
				forwardBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
						(int) (getPosY() - getGameWorld().camera.getPosY()), g2);
		}else {
			forwardBulletAnim.Update(System.nanoTime());
			if(getSpeedY() > 0)
				backBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
						(int) (getPosY() - getGameWorld().camera.getPosY()), g2);
			else if(getSpeedY() < 0)
				backBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
						(int) (getPosY() - getGameWorld().camera.getPosY()), g2);
			else
				backBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
						(int) (getPosY() - getGameWorld().camera.getPosY()), g2);
		}
        //drawBoundForCollisionWithEnemy(g2);
    }

    private void changSpeedY() {
		if(System.currentTimeMillis() % 3 == 0)
			setSpeedY(getSpeedX());
		else if(System.currentTimeMillis() % 3 == 1)
			setSpeedY(-getSpeedX());
		else
			setSpeedY(0);
	}
	
	@Override
	public void Update() {
		super.Update();
		if(System.nanoTime() - startTimeForChangeSpeedY > 500*1000000) {
			startTimeForChangeSpeedY = System.nanoTime();
			changSpeedY();
		}
	}

    @Override
    public void attack() {}

}
