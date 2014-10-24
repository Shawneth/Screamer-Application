package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Window extends JFrame implements KeyListener, ActionListener, WindowListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2208800126997150824L;
	ImageIcon img = new ImageIcon(this.getClass().getResource("error.png"));
	private boolean NOMORE = false;
	public Window(int x, int y) {
		this.setBounds(x, y,300, 200);
		setAlwaysOnTop(true);
		
		setType(Type.POPUP);
		setTitle("Error");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		addWindowListener(this);
		addKeyListener(this);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.setBounds(70, 120, 146, 23);
		btnNewButton.addActionListener(this);
		btnNewButton.addKeyListener(this);
		getContentPane().add(btnNewButton);
		
		JLabel lblAnErrorHas = new JLabel("An error has occured:     0x000C0");
		lblAnErrorHas.setBounds(37, 45, 217, 64);
		getContentPane().add(lblAnErrorHas);
		
		JLabel pic = new JLabel("");
		pic.setBounds(10, 11, 264, 54);
		pic.setIcon(img);
		getContentPane().add(pic);
		
	}
	
	
	
	public static void main(String[] args) {
		Window w = new Window(100,100);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		NOMORE = true;
		this.dispose();
		try {
			new Splash();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if(!NOMORE){
			Random r = new Random();
			int width = r.nextInt(800) + 400;
			int height = r.nextInt(500) + 200;
			new Window(width,height);
		}
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
