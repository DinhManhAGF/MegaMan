package com.manh.gameobjects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Human extends ParticularObject{

	private boolean isJumping;//trang thai dang nhay hay khong
	private boolean isDicking;//trang thai dang ngoi hay khong
	
	private boolean isLanding;//trang thai dang quy xuong
	
	public Human(float x,float y,float width, float height, float mass, int blood, GameWorld gameWorld) {
		super(x, y, width, height, mass, blood, gameWorld);
		setState(ALIVE);
	}
	
	public abstract void run();
	
	public abstract void jump();
	
	public abstract void dick();
	
	public abstract void standUp();
	
	public abstract void stopRun();
	
	
	
	
	public boolean getIsJumping() {
		return isJumping;
	}

	public void setIsJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public boolean getIsDicking() {
		return isDicking;
	}

	public void setIsDicking(boolean isDicking) {
		this.isDicking = isDicking;
	}

	public boolean getIsLanding() {
		return isLanding;
	}

	public void setIsLanding(boolean isLanding) {
		this.isLanding = isLanding;
	}
	
	@Override
	public void Update() {
		super.Update();
		
		if(getState() == ALIVE || getState() == NOBEHURT) {
			
			if(!isLanding) {
				
				setPosX(getPosX() + getSpeedX());
				
				if(getDirection() == LEFT_DIR &&
						getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) != null) {
					
					Rectangle rectLeftWall = getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
					setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2);
				}
				if(getDirection() == RIGHT_DIR &&
						getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap()) != null) {
					Rectangle rectRightWall = getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());
					setPosX(rectRightWall.x - getWidth()/2);
				}
				
				Rectangle boundForCollisionWithMapFuture = getBoundForCollisionWithMap();
				boundForCollisionWithMapFuture.y += (getSpeedY() != 0 ? getSpeedY() : 2);
				Rectangle rectLand = getGameWorld().physicalMap.haveCollisionWithLand(boundForCollisionWithMapFuture);
				
				Rectangle rectTop = getGameWorld().physicalMap.haveCollisionWithTop(boundForCollisionWithMapFuture);
				
				if(rectTop != null) {
					
					setSpeedY(0);
					setPosY(rectTop.y + getGameWorld().physicalMap.getTileSize() + getHeight()/2);
				} else if(rectLand != null) {
					setIsJumping(false);
					if(getSpeedY() > 0) setIsLanding(true);
					setSpeedY(0);
					setPosY(rectLand.y - getHeight()/2 - 1);
				} else {
					setIsJumping(true);
					setSpeedY(getSpeedY() + getMass());
					setPosY(getPosY() + getSpeedY());
				}
				
			}
			
		}
	}
	
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBoundForCollisionWithEnemy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
