/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Isyrak
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;

public class snakePanel extends JPanel implements ActionListener{
	
	static final int width = 800;
	static final int height = 800;
	static final int unitSize = 25;
	static final int gameUnit = (width*height)/unitSize;
	int delay = 50;
        char difficulty;
        String level;
	final int x[] = new int[gameUnit];
	final int y[] = new int[gameUnit];
	int bodyParts = 8;
	int foodsEaten = 0;
	int foodX;
	int foodY;
	char direction = 'R';
	boolean running = false;
	Random random;
	Timer timer;
	
	
	snakePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(width,height));
		this.setBackground(Color.darkGray);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
                //delay = Integer.parseInt(JOptionPane.showInputDialog("Enter speed for the game"));
                gameSpeed();
		startGame();
	}
	
        public void gameSpeed(){
            difficulty = JOptionPane.showInputDialog("Enter game difficulty Easy(E) | Medium(M) | Hard(H)").charAt(0);
            switch (difficulty){
                case 'E':
                    delay = 75;
                    level ="Easy";
                    break;
                case 'e':
                    delay = 75;
                    level ="Easy";
                    break;
                case 'M':
                    delay = 55;
                    level ="Medium";
                    break;
                case 'm':
                    delay = 55;
                    level ="Medium";
                    break;
                case 'H':
                    delay = 40;
                    level ="Hard";
                    break;
                case 'h':
                    delay = 40;
                    level ="Hard";
                    break;
                default:
                    delay = 75;
                    
            }
                    
        }
        
	public void startGame() {
		newFood();
		running = true;
		timer = new Timer(delay,this);
		timer.start();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g); 
	}
	
public void draw(Graphics g) {
		
		if(running) {
			for(int i=0;i<height/unitSize;i++) {
				g.drawLine(i*unitSize, 0, i*unitSize, height);
				g.drawLine(0,i*unitSize, width, i*unitSize);
			}
			g.setColor(Color.black);
			g.fillOval(foodX, foodY, unitSize,unitSize);
		
			for(int i=0;i<bodyParts;i++) {
				if(i==0) {
					g.setColor(Color.green);
					g.fillRect(x[i],y[i],unitSize,unitSize);
				}
				else {
					g.setColor(Color.cyan);
					g.fillRect(x[i],y[i],unitSize,unitSize);
				}
			
			}
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.PLAIN,20));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+foodsEaten+" | Level: "+level,(width-metrics.stringWidth("Score: "+foodsEaten+" | Level: "+level))/2,g.getFont().getSize());
			
			
		}
		else {
			gameOver(g);
		}
	}
	
	public void newFood() {
		foodX = random.nextInt((int)(width/unitSize))*unitSize;
		foodY = random.nextInt((int)(height/unitSize))*unitSize;
		
	}
	
	public void move() {
		for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		switch(direction) {
		case 'U':
			y[0] = y[0] - unitSize;
			break;
		case 'D':
			y[0] = y[0] + unitSize;
			break;
		case 'L':
			x[0] = x[0] - unitSize;
			break;
		case 'R':
			x[0] = x[0] + unitSize;
			break;
		}
	}
	
	public void checkFood() {
		if((x[0] == foodX) && (y[0] == foodY)) {
			bodyParts++;
			foodsEaten++;
			newFood();
		}
	}
	
	public void checkCollision(){
		for(int i= bodyParts;i>0;i--) {
			if((x[0] == x[i]) && (y[0] == y[i])){
				running = false;
			}
		}
		
		if(x[0] < 0) {
			running = false;
		}
		
		if(x[0] > width) {
			running = false;
		}
		
		if(y[0] < 0) {
			running = false;
		}
		
		if(y[0] > height) {
			running = false;
		}
		
		if(!running) {
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Courier New", Font.BOLD,75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over",(width - metrics.stringWidth("Game Over"))/2, height/2);
		
		g.setColor(Color.white);
		g.setFont(new Font("Courier New", Font.BOLD,50));
		FontMetrics m = getFontMetrics(g.getFont());
		g.drawString("Score: "+foodsEaten,(width - m.stringWidth("Score: "+foodsEaten))/2, height/3);
		

	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(running) {
			move();
			checkFood();
			checkCollision();
		}
		repaint();
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}

}
