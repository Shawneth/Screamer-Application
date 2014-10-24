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

@SuppressWarnings("serial")
public class Splash extends JFrame {

	public final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	public final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

	public Splash() throws IOException {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				playAudio();
			}

		});
		thread.start();

		BufferedImage picture = ImageIO.read(this.getClass()
				.getResourceAsStream("scary2.png"));
		Image p = picture.getScaledInstance(WIDTH, HEIGHT, 1);
		getContentPane().setBackground(Color.getHSBColor(0, 0, 0));
		this.setUndecorated(true);
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		JLabel l = new JLabel(new ImageIcon(p));
		l.setSize(WIDTH, HEIGHT);
		this.add(l);
		playAnimation(l, HEIGHT, WIDTH, 100);
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
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
		for (int i = 0; i < 66; i++) {
			l.setSize(w + rand.nextInt(hardness) - hardness * 2,
					h + rand.nextInt(hardness) - hardness * 2);

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.update(getContentPane().getGraphics());

		}
	}
}
