/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Isyrak
 */
import javax.swing.JFrame;

public class snakeFrame extends JFrame{

	snakeFrame(){
		
		this.add(new snakePanel());
		this.setTitle("Snake Game with JAVA");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}

