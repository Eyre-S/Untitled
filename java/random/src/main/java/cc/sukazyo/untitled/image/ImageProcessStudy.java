package cc.sukazyo.untitled.image;

import cc.sukazyo.untitled.Untitled;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageProcessStudy {
	
	public static void main(String[] args) {
		
		try {
			
			InputStream imgStream = Untitled.resPack.getResource("/img/bg.png").read();
			BufferedImage img = ImageIO.read(imgStream);
			
		} catch (IOException e) {
			System.err.println("Image Not Found!");
			e.printStackTrace();
		}
		
	}
	
}
