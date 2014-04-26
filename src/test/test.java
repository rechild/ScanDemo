package test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class test {

	/**
	 * 转换Image数据为byte数组
	 * 
	 * @param image
	 *            Image对象
	 * @param format
	 *            image格式字符串.如"gif","png"
	 * @return byte数组
	 */
	public static byte[] imageToBytes(Image image, String format) {
		BufferedImage bImage = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics bg = bImage.getGraphics();
		bg.drawImage(image, 0, 0, null);
		bg.dispose();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, format, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// -------------------------------------------------------------------------------

		try {
			System.out.println("Start~~~~~~~");
			BufferedImage bi = (BufferedImage) ImageIO.read(new File(
					"F:\\android\\tests\\ean13-3\\02.png"));
			LuminanceSource source = new BufferedImageLuminanceSource(bi);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			Result result;
			try {
				result = new MultiFormatReader().decode(bitmap);
				System.out.println(String.valueOf(result.getText()));
			} catch (Exception e) {
			}

			// byte[] bmp = imageToBytes(bi, "png");
			// PlanarYUVLuminanceSource source = new
			// PlanarYUVLuminanceSource(bmp,
			// bi.getWidth(null), bi.getHeight(null), 50, 50, 50, 50,
			// false);
			// if (source != null) {
			// BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
			// source));
			// MultiFormatReader mfr = new MultiFormatReader();
			// Result rs = mfr.decodeWithState(bitmap);
			// System.out.println("ISBN: " + rs.getText().toString());
			// }
		} catch (Exception re) {
			System.out.println(re.getMessage());
			re.printStackTrace();
			// continue
		}
	}
}
