import java.util.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener 
{
	
	
	private boolean play = false;
	private int score = 0;
	
	private int totalBricks = 48;
	
	private Timer timer;
	private int delay=8; // ball speed
	
	private int playerX = 310; // Paddle start pos
	//start ball pos
	private int ballposX = 120;
	private int ballposY = 350;
	//ball movement to go in opposite direction 
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	
	private static ArrayList<Integer> scoreArr = new ArrayList<Integer>();

	private MapGenerator map;
	public Gameplay()
	{		
		map = new MapGenerator(4, 12);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
		timer.start();
	}
	
	//recursive array list method to print scores
	public static void printTopScores(ArrayList<Integer> scores, int index, int count, Graphics g, int k) {
		//counting score for 5 players
	    if (count == scores.size() || count == 5) {
	        return;
	    }
	    
	    g.drawString(""+scores.get(index), 250+k,340);
	    System.out.println((index + 1) + ". " + scores.get(index));
	    k+=40;
	    printTopScores(scores, index + 1, count + 1, g, k+30);
	}

	public static void keepTopScores(ArrayList<Integer> scores) {
	    if (scores.size() <= 5) {
	        return;
	    }
	    scores.remove(0);
	    keepTopScores(scores);
	}
	
	
	public void paint(Graphics g)
	{    		
		// background
		g.setColor(new Color(21, 0, 80));
		g.fillRect(1, 1, 692, 592);
		
		// drawing map
		map.draw((Graphics2D) g);
		
		// borders
		g.setColor(new Color(97, 0, 148));
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		// the scores 		
		g.setColor( new Color(248, 232, 238));
		g.setFont(new Font("Lucida Handwriting",Font.BOLD, 25));
		g.drawString(""+score, 590,30);
		
		// the paddle
		g.setColor( new Color(248, 232, 238));
		g.fillRect(playerX, 550, 100, 8);
		
		// the ball
		g.setColor(new Color(248, 232, 238));
		g.fillOval(ballposX, ballposY, 20, 20);//to draw circle ball
	
		// when you won the game
		if(totalBricks <= 0) 
		{
			 play = false;
             ballXdir = 0;
     		 ballYdir = 0;
             g.setColor(new Color(253, 206, 223));
             g.setFont(new Font("Lucida Handwriting",Font.BOLD, 30));
             g.drawString("You Won", 260,300);
             
             g.setColor(new Color(253, 206, 223));
             g.setFont(new Font("Georgia",Font.BOLD, 20));           
             g.drawString("Press (Enter) to Restart", 230,350);  
		}
		
		// when you lose the game
		if(ballposY > 570)
        {
			 play = false;
             ballXdir = 0;
     		 ballYdir = 0;
             g.setColor(new Color(253, 206, 223));
             g.setFont(new Font("Lucida Handwriting",Font.BOLD, 30));
             g.drawString("Game Over, Scores: "+score, 190,300);
             
          // add the current score to the scoreArr
             scoreArr.add(score);
             
          // sort the scores in ascending order
             Collections.sort(scoreArr); 
             
          // print all the scores in ascending order
             System.out.println(" Scores:");
             printTopScores(scoreArr, 0, 0, g, 0);
             
             // keep only the top 5 scores
             keepTopScores(scoreArr);
             
             
             g.setColor(new Color(253, 206, 223));
             g.setFont(new Font("Lucida Handwriting",Font.BOLD, 20));           
             g.drawString("Press (Enter) to Restart", 230,400);        
        }
		
		g.dispose();
	}	
 
	//the key action listener 
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{        
			//to check if it's inside the panel from right
			if(playerX >= 600)
			{
				playerX = 600;
			}
			else
			{
				moveRight();
			}
        }
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{          
			//to check if it's inside the panel from left
			if(playerX < 10)
			{
				playerX = 10;
			}
			else
			{
				moveLeft();
			}
        }		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{          
			if(!play)
			{
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				map = new MapGenerator(3, 7);
				
				repaint();
			}
        }		
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void moveRight()
	{
		play = true;//start playing
		playerX+=20;	//move 20px to right
	}
	
	public void moveLeft()
	{
		play = true;
		playerX-=20;	 	
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if(play)
		{			
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 30, 8)))
			{
				ballYdir = -ballYdir;
				ballXdir = -2;
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8)))
			{
				ballYdir = -ballYdir;
				ballXdir = ballXdir + 1;
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8)))
			{
				ballYdir = -ballYdir;
			}
			
			// check map collision with the ball	
			//A for loop name
			A: for(int i = 0; i<map.map.length; i++)
			{
				for(int j =0; j<map.map[0].length; j++)
				{				
					if(map.map[i][j] > 0)//1st map is the object 2ed map var 2D array
					{
						//scores++;
						//detect intersection with bricks
						int brickX = j * map.brickWidth + 80; 
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);// rect around each brick
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20); //around ball
						Rectangle brickRect = rect;
						
						
						//detect intersection with bricks 
						if(ballRect.intersects(brickRect))
						{					
							//change brick value to 0
							map.setBrickValue(0, i, j);
							score+=5;	
							totalBricks--;
							
							// when ball hit right or left of brick
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)	
							{
								//move ball to opposite direction 
								ballXdir = -ballXdir;
							}
							// when ball hits top or bottom of brick
							else
							{
								ballYdir = -ballYdir;				
							}
							
							break A;
						}
					}
				}
			}
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			
			if(ballposX < 0)
			{
				ballXdir = -ballXdir;
			}
			if(ballposY < 0)
			{
				ballYdir = -ballYdir;
			}
			if(ballposX > 670)
			{
				ballXdir = -ballXdir;
			}		
			
			repaint();		
		}
	}
}