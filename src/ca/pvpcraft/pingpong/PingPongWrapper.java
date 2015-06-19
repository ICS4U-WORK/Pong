package ca.pvpcraft.pingpong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class PingPongWrapper extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2179694500601976317L;
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
	static JFrame frame = new JFrame("Pong");
	static JButton Start;
	static JButton AI;
	static JButton Help;
	static JLabel Score;
	static JButton Exit;
	static PingPongGame game;
	static Timer timer;

	public static void addComponentsToPane(Container pane) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			//natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		Start = new JButton("Start");
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(Start, c);

		AI = new JButton("AI Toggle");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(AI, c);

		Help = new JButton("Help");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(Help, c);
		
		Score = new JLabel("score");
		c.ipady = 0;
		c.weighty = 0.0;
		c.weightx = 0.0;
		c.gridx = 0;
		c.gridy = 2;
		pane.add(Score, c);

		Exit = new JButton("Exit");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(0,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 2;       //third row
		pane.add(Exit, c);
	}

	private static void createAndShowGUI(){
		/*JPanel menu = new JPanel();
		menu.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		while(true) {

		}
		 */
		//Create and set up the window.
		frame = new JFrame("Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set up the content pane.
		addComponentsToPane(frame.getContentPane());
		Start.addActionListener(StartListener);
		AI.addActionListener(AIListener);
		Help.addActionListener(HelpListener);
		Exit.addActionListener(ExitListener);
		frame.setSize(640,480);
		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
				/*timer = new Timer(1000/4, this);
				timer.setInitialDelay(250 );
				timer.start();*/
			}
		});
	}


	static ActionListener StartListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getSource());
			if(game!=null) {
				game.end();
			}
			game = new PingPongGame(frame, Score);
			game.start();
		}
	};

	static ActionListener AIListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			game.toggleAI();
			game.requestListener();
		}
	};

	static ActionListener HelpListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(frame, "P1, Q and A move up and down.\n" 
											   + "P2, uses arrows", "Help!"
										 ,JOptionPane.INFORMATION_MESSAGE);
			game.requestListener();
		}
	};

	static ActionListener ExitListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
}
