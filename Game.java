import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game implements Runnable{ 
	public void run() {
		
		// where the game is gonna be on computer screen (top left)
		final JFrame frame = new JFrame("JAVABLAST");
		frame.setLocation(300, 300);
		
		// the panel and label for keeping score
		final JPanel score_panel = new JPanel();
		frame.add(score_panel, BorderLayout.NORTH);
		final JLabel score = new JLabel("SCORE");
		score_panel.add(score);

		// the panel and label for keeping score
		final JPanel levelPanel = new JPanel();
		frame.add(levelPanel, BorderLayout.NORTH);
		final JLabel level = new JLabel("Level");
		levelPanel.add(level);
		
		// panel and label for status
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("Good Luck");
		status_panel.add(status);
		
		// main playing area (GameCourt)
		final GameCourt court = new GameCourt(status, score, level);
		frame.add(court, BorderLayout.CENTER);
		
		// main panel
		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.NORTH);
		
		// Start Over button
		final JButton startover = new JButton("Start Over");
		startover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// make the reset method in GameCourt
				court.reset();
			}
		});
		
		// needed for frame to have structure
		frame.pack();
		// once the app is closed, java closes as well
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// needed to see the game
		frame.setVisible(true);
		
		// needed to show all the panels/buttons at the top
		control_panel.add(score_panel);
		control_panel.add(levelPanel);
		control_panel.add(startover);
		
		// starts the game
		court.reset();
	}
	
	public static void main(String[] args) {
		// game 
		SwingUtilities.invokeLater(new Game());
	}
}