import java.awt.Color;
import java.awt.Point;

public class Ball extends Point {
	
	private int speed; //how fast does this ball move?
	private int direction; //where is it moving to? In degrees
	private int orig_x;
	private int orig_y;
	private int size;
	private Color color;
	
	//		 90
	//		  ^
	//		  |
	//180 <------> 0
	//		  |
	//		  V
	//		 270

	public Ball(int board_size, Color color) {
		
		this.speed = board_size/160;
		this.size = board_size/60;
		this.color = color;
		
		orig_x = board_size/2;
		orig_y = board_size/2;
		
		setLocation(orig_x, orig_y);
		
		//randomly set which direction the ball goes
		if(Math.random()>0.5){
			direction=0; //go right
		}else {
			direction=180; //go left
		}
	}
	
	public void update(){
		x = (int) (x + Math.cos(Math.toRadians(direction))*speed);
		y = (int) (y + Math.sin(Math.toRadians(direction))*speed);
	}
	
	public void reset(){
		//modify ball position using some math/physics
		setLocation(orig_x, orig_y);
		
		//randomly set which direction the ball goes
		if(Math.random()>0.5){
			direction=0; //go right
		}else {
			direction=180; //go left
		}
		
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void setSpeed(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
}
