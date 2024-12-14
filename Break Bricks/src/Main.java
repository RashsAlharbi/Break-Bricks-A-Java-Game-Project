import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;

public class Main {
	static JFrame frame1,Frame2,Frame3;
	static JButton button1,button2,button3;
    static JLabel background,background1;
    public static JTextField textField; 
    
	public static void main(String[] args) {
		frames();
		Name();
		creatButton();
		activateButtons();
		Sound s=new Sound();
		s.playMusic("RunAmok.wav");
		
		
	}  
	
	//method for the name text area 
	private static void Name() { 
		
        textField = new JTextField();
        textField.setToolTipText("Enter your name ");
        textField.setFont(new Font("Georgia",Font.BOLD, 25));
        textField.setSize(200, 50);
        textField.setLocation(250,195);
        Border border = BorderFactory.createLineBorder(Color.black, 5);
        textField.setBorder(border);
        textField.setBackground( new Color(229, 184, 244));
        textField.setForeground( new Color(21, 0, 80));
        frame1.add(textField);
	}
	
	
	//method f
	private static void activateButtons() { 
		 button1.addActionListener((ActionEvent e)->{
			 ///////////// Check if field is empty or not ////////////
            if(textField.getText().isEmpty()){
               JOptionPane.showMessageDialog(frame1, "The name field is empty please enter the name");
            }else{
            	//action listner for the start button
                frame1.setVisible(false);
	            Frame2.setVisible(true);
	            Frame3.setVisible(false);
            }
		  });
		 
		 button2.addActionListener((ActionEvent e )->{// action lesner for how 
			 
			    frame1.setVisible(false);
	            Frame2.setVisible(false);
	            Frame3.setVisible(true);

		  });
		 
		 button3.addActionListener((ActionEvent e )->{// action lesner for previous  
			 
			    frame1.setVisible(true);
	            Frame2.setVisible(false);
	            Frame3.setVisible(false);

		  });

	} 
	
	//creation button method
	private static void creatButton() {
	button1=new JButton("start");
	button1.setFont(new Font("Brush Script MT",Font.BOLD, 30));
	button1.setBackground( new Color(229, 184, 244));
	button1.setForeground( new Color(21, 0, 80));
    button1.setSize(200,50);
    frame1.add(button1);
    button1.setLocation(170,300);
		 
    button2=new JButton("How ?");
    button2.setFont(new Font("Brush Script MT",Font.BOLD, 30));
    button2.setBackground( new Color(229, 184, 244));
    button2.setForeground( new Color(21, 0, 80));
    button2.setSize(200,50);
    frame1.add(button2);
    button2.setLocation(400,300);
    
    button3=new JButton("previous");
    button3.setFont(new Font("Brush Script MT",Font.BOLD, 30));
    button3.setBackground( new Color(229, 184, 244));
    button3.setForeground( new Color(21, 0, 80));
    button3.setSize(200,50);
    Frame3.add(button3);
    button3.setLocation(100,520);
	}
	
	//method for frame setting 
	private static void frames() {
		frame1=new JFrame("start");
		frame1.setLayout(null);
		background=new JLabel(new ImageIcon("amg1.jpg"));//set path of image 
		background.setBounds(0, 0, 749, 485);//setbound of label
		frame1.add(background);
		frame1.setVisible(true);
	    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame1.setSize(749,485);
		frame1.getContentPane();
	    frame1.setLocationRelativeTo(null);// set frame in center
	    	

		//Frame2
		Frame2=new JFrame("Break bricks game");
		Gameplay gamePlay = new Gameplay();
		Frame2.setBounds(10, 10, 700, 600);
		Frame2.add(gamePlay);
		Frame2.setVisible(false);
		Frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
        //Frame3
		Frame3=new JFrame("How play ?");
		Frame3.setLayout(null);
		background1=new JLabel(new ImageIcon("f3.png"));//set path of image 
		Frame3.add(background1);
		background1.setBounds(0, 0, 932, 675);
		Frame3.setVisible(false);
		Frame3.getContentPane() ;
		Frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Frame3.setSize(932,675);
	}
}