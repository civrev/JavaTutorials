import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Pong extends JPanel {
	
	private int p1_score;
	private int p2_score;
	private int board_size;
	private Ball ball;
	private Paddle p1; //both player 1 and 2 are very similar, so we will make them both 'Paddles'
	private Paddle p2;
	private Timer timer;
	private JComponent keys;
	
	//this vvv is called a constructor. It is how you make a 'Pong' object
	public Pong() { //pong doesn't have any settings you can choose,
		//so we can think of this as needing no info to make a 'Pong' game, which is why it is Pong()
		//but Pong does have default settings
		p1_score = 0; //like the score starts at 0 for both players
		p2_score = 0;
		board_size = 800;
		ball = new Ball(board_size, Color.white); //start in middle of board
		p1 = new Paddle(1, board_size, Color.red); //specify which paddle belongs to who, in this case player '1'
		p2 = new Paddle(2, board_size, Color.blue); //here player '2'
		
		
		//this is a 'Timer' object.
		//it take in 2 arguments, first how often you want it to do something
		//and second WHAT you want it to do
		timer = new Timer(20, generateTimerActionListener());
		
		//on screen buttons that trigger the timer, and also reset the game
		JButton playButton = new JButton("Play");
		playButton.addActionListener(generatePlayButtonActionListener());
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(generateResetButtonActionListener());
		
		add(playButton);
		add(resetButton);
		
		keys = new JComponent(){};
		
		keys.addKeyListener(generateControls());
		
		add(keys);
		
		setPreferredSize(new Dimension(board_size,board_size));
		setBackground(Color.black);
	}
	

	public void paintComponent(Graphics page){
		super.paintComponent(page);
		
		page.setColor(ball.getColor()); //any changes to the 'page' will now be in this color
		page.fillOval(ball.x, ball.y, ball.getSize(), ball.getSize()); //'fillOval' is going to make a ball
		
		Font font = new Font("Score", Font.PLAIN, board_size/20);
		page.setFont(font);
		
		page.setColor(p1.getPlayer_color());
		page.fillPolygon(p1);
		page.drawString(Integer.toString(p1_score), board_size/4, board_size/8);
		
		page.setColor(p2.getPlayer_color());
		page.fillPolygon(p2);
		page.drawString(Integer.toString(p2_score), 3*(board_size/4), board_size/8);
		
		
	}
	
	public ActionListener generateTimerActionListener() {
		
		ActionListener timerListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play();
			}
			
		};
		
		
		return timerListener;
	}
	
	public ActionListener generatePlayButtonActionListener() {
		
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timer.start();
				keys.requestFocus();
			}
			
		};
		
		
		return buttonListener;
	}
	
	public ActionListener generateResetButtonActionListener() {
		
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				ball.reset();
				p1_score = 0;
				p2_score = 0;
				repaint();
				keys.requestFocus();
			}
			
		};
		
		
		return buttonListener;
	}
	
	public KeyListener generateControls() {
		KeyListener controls = new KeyListener() {
			//key codes
			//up arrow: 38
			//down arrow: 40
			//w: 87
			//s: 83
			

			@Override
			public void keyPressed(KeyEvent event) {
				int keyCode = event.getKeyCode();
				int increment = board_size/23;
				switch(keyCode) {
					case 87:
						p1.setY(p1.getY() - increment);
						break;
					case 83:
						p1.setY(p1.getY() + increment);
						break;
					case 38:
						p2.setY(p2.getY() - increment);
						break;
					case 40:
						p2.setY(p2.getY() + increment);
						break;
				}
				repaint();
				
			}

			@Override
			public void keyReleased(KeyEvent event) {
				//you have to have this code, but don't worry about it not doing anything	
			}

			@Override
			public void keyTyped(KeyEvent event) {
				//you have to have this code, but don't worry about it not doing anything
			}
			
		};
		return controls;
	}
	
	public void play() {
		
		//the ball has to move
		ball.update();
		
		//now do everything related to the ball moving
		
		//such as what conditions qualify as scoring?
		if(ball.x<=0) { //goes off screen left
			p2_score+=1;
			ball.reset();
		}else if(ball.x>=board_size) { //goes off screen right
			p1_score+=1;
			ball.reset();
		}
		
		//doing calculations that effect the ball hitting the paddle
		collisions();
		
		repaint();
		
	}

	private void collisions() {
		
		//doing calculations that effect the ball hitting the paddle and top/bottom bounds
		int leftEdge = ball.x;
		int nextLeft = ball.nextX();
		int bSize = ball.getSize();
		int rightEdge = ball.x + bSize;
		int nextRight = nextLeft + bSize;
		int top = ball.y;
		int nextTop = ball.nextY();
		int bottom = ball.y + bSize;
		int nextBottom = nextTop + bSize;
		int p2_edge = p2.getX()-p2.getWidth();
		int direction = ball.getDirection();
		
		
		//paddle collisions
		
		if(leftEdge >= p1.getX() && nextLeft < p1.getX()){ //potential collision based on x position
			
			if(top >= p1.getY() && bottom<= p1.getY()+p1.getLength()) { //confirm based on y position
				ball.x = p1.getX(); //this stuff is graphics tweaking
				repaint();
				ball = p1.collision(ball); //this is what turns the ball the other direction
			}
			
			
		}else if(rightEdge <= p2_edge && nextRight > p2_edge){ //potential collision based on x position
			
			if(top >= p2.getY() && bottom<= p2.getY()+p2.getLength()) { //confirm based on y position
				ball.x = p2.getX()-p2.getWidth()-bSize; //this stuff is graphics tweaking
				repaint();
				ball = p2.collision(ball); //this is what turns the ball the other direction
			}
			
		}
		
		
		//top and bottom of the screen collisions
		
		if(top >= 0 && nextTop <= 0) { //ball is about to go out of bounds at top of screen
			if(direction%360>=270){
				//in quadrant one
				ball.setDirection(360-direction%360);
			}else {
				//in quadrant two
				ball.setDirection(180-(direction%360 - 180));
			}
		}else if(bottom <= board_size && nextBottom >= board_size){ //ball is about to go out of bounds at bottom
			if(direction%360 <=90) {
				//in quadrant four
				ball.setDirection(360-direction%360);
			}else {
				//in quadrant three
				ball.setDirection(180+(180-direction%360));
			}
		}
		
	}
	

}

