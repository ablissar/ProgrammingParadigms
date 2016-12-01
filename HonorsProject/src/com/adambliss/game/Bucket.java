package com.adambliss.game;

public class Bucket extends Sprite {

	public Bucket() {
		super("bucket.png");
		setX( (int)(Math.random() * 950) );
		setY( (int)(Math.random() * 650) );
		
	}

}
