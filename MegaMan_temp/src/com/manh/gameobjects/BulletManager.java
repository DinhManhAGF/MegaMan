package com.manh.gameobjects;

public class BulletManager extends ParticularObjectManager{

	public BulletManager(GameWorld gameWorld) {
		super(gameWorld);
	}
	
	@Override
	public void UpdateObjects() {
		super.UpdateObjects();
		synchronized (particularObjects) {
			for(int i = 0;i < particularObjects.size();i++) {
				
				ParticularObject object = particularObjects.get(i);
				
				if(object.isObjectOutOfCameraView() || object.getState() == ParticularObject.DEATH) {
					particularObjects.remove(i);
				}
			}
		}
	}
	
	
}
