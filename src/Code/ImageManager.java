package Code;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImageManager {
	// "https://pbs.twimg.com/media/BMP99_YCMAUOoWe.jpg"

	public static Image imageFromWebURL(String URLImage) {
		Image imagen = null;
		try {
			URL url = new URL(URLImage);
			imagen = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error al cargar la imagen");
		}

		// JLabel label = new JLabel(new ImageIcon(imagen));
		return imagen;
	}

	public static Image resizeImage(int width, int height, Image imagen) {
		return imagen.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	}

	public static void ImagefromURLToFile(String URL, String nombreArchivo) throws IOException {

		URL url = new URL(URL);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(nombreArchivo);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}

	public static Image ImagefromFile(String nombreArchivo) {

		Image image = null;
		try {
			File pathToFile = new File(nombreArchivo);
			image = ImageIO.read(pathToFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return image;
	}

	public static BufferedImage imageToBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		int w = img.getWidth(null);
		int h = img.getHeight(null);
		BufferedImage Buffered = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics2D temp2DGraphics = Buffered.createGraphics();
		temp2DGraphics.drawImage(img, 0, 0, null);
		temp2DGraphics.dispose();

		return Buffered;
	}

}
