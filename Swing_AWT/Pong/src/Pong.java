import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Pong extends JPanel {
	
	private int p1_score;
	private int p2_score;
	private int board_size;
	private Ball ball;
	private Paddle p1; //both player 1 and 2 are very similar, so we will make them both 'Paddles'
	private Paddle p2;
	private Timer timer;
	
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
			}
			
		};
		
		
		return buttonListener;
	}
	
	public void play() {
		
		//the ball has to move
		ball.update();
		
		//now do everything related to the ball moving
		
		//such as what conditions qualify as scoring?
		if(ball.x<=0) { //goes off screen left
			p1_score+=1;
			ball.reset();
		}else if(ball.x>=board_size) { //goes off screen right
			p2_score+=1;
			ball.reset();
		}
		
		
		repaint();
		
	}

}

