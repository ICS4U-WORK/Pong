package ca.pvpcraft.pingpong;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Game instance including scores
 * @author macdja38
 *
 */
public class PingPongGame extends JPanel implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6441909965015455427L;
	//Box Ball = new Box(0);
	Graphics GameObject;
	JFrame FrameReference;
	JLabel Score;
	PingPongBall Ball = new PingPongBall();
	PingPongPaddle PaddleL = new PingPongPaddle();
	PingPongPaddle PaddleR = new PingPongPaddle();
	GridBagConstraints c = new GridBagConstraints();
	KeyListener keyBoard;
	double Height;
	double Width;
	double PaddleHeight = 100;
	int playerR = 0;
	int playerL = 0;
	boolean AI = true;
	private int speed = 1000/60;
	Timer timer = new Timer(speed , this);
	@Override
	public void paintComponent(Graphics page)
	{
		GameObject = page;
		super.paintComponent(page);
		page.setColor(Color.GREEN);
		page.fillRect((int)Ball.getX()-10,(int)Ball.getY()-10,20,20);
		page.fillRect((int)this.getWidth()-10,(int)(PaddleR.getY()),10,(int)PaddleHeight);
		page.fillRect(                      0,(int)(PaddleL.getY()),10,(int)PaddleHeight);
	} 

	public PingPongGame(JFrame frame, JLabel L) {
		frame.setBounds(10, 10, 640, 480);
		frame.setBackground(Color.BLACK);
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 100;      //make this component tall
		c.weighty = 1.0;
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		frame.add(this, c);
		Score = L;
		frame.requestFocusInWindow();
		frame.addKeyListener(this);
		FrameReference = frame;
		setBackground(Color.BLACK);
	}
	/**
	 * stop the game timer
	 */
	public void end() {
		//GameObject.remove(GameObject);
		timer.stop();
	}
	public void requestListener() {
		FrameReference.requestFocusInWindow();
	}
	/**
	 * initialize objects, place things in the correct positions and repaint the field
	 */
	public void start() {
		Height = FrameReference.getHeight();
		Width = FrameReference.getWidth();
		Ball.reset(Height, Width);
		PaddleL.reset(Height);
		PaddleR.reset(Height);
		timer.start();
		playerR = 0;
		playerL = 0;
		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent Timer) {
		Height = this.getHeight();
		Width = this.getWidth();
		PaddleL.tick(Height, false, PaddleHeight, Ball.getY());
		PaddleR.tick(Height, AI, PaddleHeight, Ball.getY());
		Ball.tick(Height, Width);
		if(Ball.isRight() && Ball.getX()>(this.getWidth()-20) && Math.abs((Ball.getY())-(PaddleR.getY()+(PaddleHeight/2)))<=60) {
			Ball.setDirL();
			Ball.applyPaddle(PaddleR);
		}
		else if(Ball.getX()>this.getWidth()) {
			playerL+=1;
			this.resetField();
		}
		if(Ball.isLeft() && Ball.getX()<20 && Math.abs((Ball.getY())-(PaddleL.getY()+(PaddleHeight/2)))<=60) {
			Ball.setDirR();
			Ball.applyPaddle(PaddleL);
		}
		else if(Ball.getX()<0) {
			playerR+=1;
			this.resetField();
		}
		scoreTick();
		revalidate();
		repaint();
	}

	private void resetField() {
		revalidate();
		repaint();
		Height = FrameReference.getHeight();
		Width = FrameReference.getWidth();
		Ball.reset(Height, Width);
		PaddleL.reset(Height);
		PaddleR.reset(Height);
		timer.start();
	}

	/**
	 * Update the scoreboard label and in the event of a win restart the game
	 */
	private void scoreTick() {
		Score.setText("L:"+playerL+" R:"+playerR);
		if(playerL>=2 || playerR>=2) {
			resetField();
			end();
			JOptionPane.showMessageDialog(FrameReference, "Player " + (playerL>=2 ? "L" : "R") + " wins!");
			start();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==222 || e.getKeyCode()==81) { //supports both querty and dvorak
			PaddleL.setYV(-1);
		}
		if(e.getKeyCode()==65) {
			PaddleL.setYV(1);
		}
		if(e.getKeyCode()==38) {
			PaddleR.setYV(-1);
		}
		if(e.getKeyCode()==40) {
			PaddleR.setYV(1);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==222 || e.getKeyCode()==81 && PaddleL.getYV()<0) {
			PaddleL.setYV(0);
		}
		if(e.getKeyCode()==65&&PaddleL.getYV()>0) {
			PaddleL.setYV(0);
		}
		if(e.getKeyCode()==38&&PaddleR.getYV()<0) {
			PaddleR.setYV(0);
		}
		if(e.getKeyCode()==40&&PaddleR.getYV()>0) {
			PaddleR.setYV(0);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	public void toggleAI() {
		AI=!AI;
	}
}
