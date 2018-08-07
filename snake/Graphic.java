import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class Graphic extends JFrame {

	private JPanel contentPane;
	JLabel[][] label = new JLabel[20][20];
	GridRobot robot = new GridRobot("Eray", 20 ,20);
	JLabel annLabel;
	
	//move the robot
	public int move(){
		robot.move();
		return 0;
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graphic frame = new Graphic();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Graphic() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Eray's Robot");
		setBounds(300, 30, 450, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(20, 20, 0, 0));
		
		
		//create labels in the game screen
		for(int j=0; j<label.length; j++){
			for(int i=0; i<label.length; i++){
				label[i][j] = new JLabel(" ");
				//label[i].setSize(5, 5);
				label[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(label[i][j]);
			}
		}
		
		Point point = robot.getLocation();
		int xval = point.getxValue();
		int yval = point.getyValue();
		label[xval][yval].setText(robot.getDirection());
		
		
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JButton btnLeft = new JButton("Turn Left");
		panel_1.add(btnLeft);
		ActionListener listLeft = new LeftListener();
		btnLeft.addActionListener(listLeft);
		
		
		JButton btnMove = new JButton("Move");
		panel_1.add(btnMove);
		ActionListener listMove = new MoveListener();
		btnMove.addActionListener(listMove);
		
		JButton btnRight = new JButton("Turn Right");
		panel_1.add(btnRight);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		annLabel = new JLabel("The Game has started.");
		//lblNewLabel.setAlignmentY(CENTER_ALIGNMENT);
		annLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(annLabel);
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		
		ActionListener rightMove = new RightListener();
		btnRight.addActionListener(rightMove);
		
		
	}
	//move button listener
	class MoveListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			//delete the previous robot's location's label  
			//then write to the robot's new location of the label
			Point point = robot.getLocation();
			int xval = point.getxValue();
			int yval = point.getyValue();
			label[xval][yval].setText("");
			canMove();
			move();
			point = robot.getLocation();
			xval = point.getxValue();
			yval = point.getyValue();
			label[xval][yval].setText(robot.getDirection());
			
			
			
		}
		
	}
	//right button listener
	class RightListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			robot.turnRight();
			Point point = robot.getLocation();
			int xval = point.getxValue();
			int yval = point.getyValue();
			label[xval][yval].setText(robot.getDirection());
			
		}
		
	}
	//left button listener
	class LeftListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			robot.turnLeft();
			Point point = robot.getLocation();
			int xval = point.getxValue();
			int yval = point.getyValue();
			label[xval][yval].setText(robot.getDirection());
			
		}
		
	}
	//print to the announcement label that if the robot went successfully or not
	public void canMove(){
		String direction = robot.getDirection();
		Point point = robot.getLocation();
		
		if(direction.equals("N")){
			if(point.getyValue() == 0){
				annLabel.setText("The robot " + robot.toString()+" go to north");
				return;
			}
			else{
				annLabel.setText("The robot " + robot.toString()+" went north successfully");
				return;
			}
		}
		else if(direction.equals("W")){
			if(point.getxValue() == 0){
				annLabel.setText("The robot " + robot.toString()+" cannot go to west");
				return;
			}
			else{
				annLabel.setText("The robot " + robot.toString()+" west successfully");
				return;
			}
		}
		else if(direction.equals("S")){
			if(point.getyValue() == (robot.getGridHeight()-1)){
				annLabel.setText("The robot " + robot.toString()+" cannot go to south");
				return;
			}
			else{
				annLabel.setText("The robot " + robot.toString()+" went south successfully");
				return;
			}
		}
		else if(direction.equals("E")){
			if(point.getxValue() == (robot.getGridWidth()-1)){
				annLabel.setText("The robot " + robot.toString()+" cannot go to east");
				return;
			}
			else{
				annLabel.setText("The robot " + robot.toString()+" went east successfully");
				return;
			}
		}
	}

}
