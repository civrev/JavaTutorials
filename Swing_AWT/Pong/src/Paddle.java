import java.awt.Color;
import java.awt.Polygon;

public class Paddle extends Polygon {
	
	private int x;
	private int y;
	private int length;
	private int width;
	private int player; //1 is left side, 2 is right side
	private Color player_color;

	public Paddle(int player, int board_size, Color color) {
		
		this.player = player;
		this.player_color = color;
		
		if(player==1) {
			x = board_size/16;
			y = board_size/2;
		}else {
			x = board_size - board_size/16;
			y = board_size/2;
		}
		
		length = board_size/10;
		width = board_size/100;
		
		this.addPoint(x, y);
		this.addPoint(x, y+length);
		this.addPoint(x-width, y+length);
		this.addPoint(x-width, y);
	}
	
	public Ball collision(Ball ball){
		
		int direction = ball.getDirection()%360;
		
		if(player==1) {
			
			if(direction <=180) {
				//in quadrant three
				ball.setDirection(90-(direction-90));
			}else {
				//in quadrant two
				ball.setDirection(270+(270-direction));
			}
			
			
		}else { //must be player 2
			
			if(direction >=270) {
				//in quadrant one
				ball.setDirection(270-(direction-270));
			}else {
				//in quadrant four
				ball.setDirection(90+(90-direction));
			}
			
		}
		
		return ball;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		this.ypoints = new int[]{y,y+length, y+length, y}; //using setY() will now move the paddle
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public Color getPlayer_color() {
		return player_color;
	}

	public void setPlayer_color(Color player_color) {
		this.player_color = player_color;
	}

}
