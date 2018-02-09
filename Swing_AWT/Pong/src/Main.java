import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame=new JFrame("Pong");
		
		//create your 'Pong' object
		Pong pongGame = new Pong();
		
		
		//just some setting up of the JFrame, really not important
		frame.getContentPane().add(pongGame);    
	    frame.pack();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);

	}

}
