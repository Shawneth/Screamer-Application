package app;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;






import sun.audio.*;

public class SoundStuff extends JFrame {
	
	public final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	public final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	int dynx = 50;
	int dyny = 100;
	public JLabel l;
	Image img; 
	
	public SoundStuff() {
		
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				playAudio();
			}

		});
		thread.start();

		BufferedImage picture;
		
		try {
			
			picture = ImageIO.read(this.getClass().getResourceAsStream("scary.png"));
			img = picture.getScaledInstance(WIDTH, HEIGHT, 1);

			getContentPane().setBackground(
					Color.getHSBColor(0, 0, 0));
			this.setUndecorated(true);
			this.setSize(WIDTH, HEIGHT);
			this.setVisible(true);
			l = new JLabel(new ImageIcon(img));
			l.setSize(WIDTH, HEIGHT);
			this.add(l);
			playAnimation(l, HEIGHT, WIDTH, 100);
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.exit(NORMAL);
	}

	private void increaseImageSize() {
		img = img.getScaledInstance(dynx + 100, dyny + 100, 1);
		l.setIcon(new ImageIcon(img));
		l.setBounds(0,600,WIDTH, HEIGHT );
		dynx += 300;
		dyny += 300;
		return;
	}
	private void setImageSize(int x, int y) {
		img = img.getScaledInstance(x, y, 1);
		l.setIcon(new ImageIcon(img));
		l.setSize(WIDTH, HEIGHT);
		return;
	}

	@SuppressWarnings("restriction")
	public void playAudio() {

		InputStream audioFile = this.getClass().getResourceAsStream(
				"scream3.au");
		try {
			AudioStream as = new AudioStream(audioFile);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void playAnimation(JLabel l, int h, int w, int hardness) {
		Random rand = new Random();
		for (int i = 0; i < 10; i++){
			
			try {
				Thread.sleep(25);
				increaseImageSize();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//	this.update(getContentPane().getGraphics());
			this.update(l.getGraphics());
		}
		try {
			img = ImageIO.read(getClass().getResourceAsStream("scary2.png"));
			setImageSize(WIDTH, HEIGHT + 500);
			Thread.sleep(2000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
