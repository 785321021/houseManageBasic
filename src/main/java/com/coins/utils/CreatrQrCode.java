package com.coins.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @ClassName: CreatrQrCode    q:quick   r :response 
 * @Description: 生成二维码
 * @date 2018/7/12 13:37
 */
public class CreatrQrCode {
	// logo默认边框颜色
	public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
	// logo默认边框宽度
	public static final int DEFAULT_BORDER = 1;
	// logo大小默认为照片的1/6
	public static final int DEFAULT_LOGOPART = 4;

	private final int border = DEFAULT_BORDER;
	// 自定义颜色
	private final Color borderColor;
	// 自定义logo大小
	private final int logoPart;

	/**
	 * 颜色上创建一个默认配置,生成正常的黑白条码。
	 */
	public CreatrQrCode() {
		this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
	}

	public CreatrQrCode(Color borderColor, int logoPart) {
		this.borderColor = borderColor;
		this.logoPart = logoPart;
	}

	/**
	 * 给二维码图片添加Logo
	 * 
	 * @param qrPic   二维码文件
	 * @param logoPic logo文件
	 */
	public static void addLogo_QRCode(File qrPic, File logoPic, CreatrQrCode creatrQrCode) {
		try {
			if (!qrPic.isFile() || !logoPic.isFile()) {
//                LogUtils.error(logger, "找不到对应文件！", new Object[]{"qrPic", qrPic,"logoPic",logoPic});
				return;
			}
			// 读取二维码图片，并构建绘图对象
			BufferedImage image = ImageIO.read(qrPic);
			Graphics2D g = image.createGraphics();
			// 读取Logo图片
			BufferedImage logo = ImageIO.read(logoPic);
			// 保持二维码是正方形的
			int widthLogo = image.getWidth() / creatrQrCode.getLogoPart();
			int heightLogo = image.getWidth() / creatrQrCode.getLogoPart();
			// 计算图片放置位置
			int x = (image.getWidth() - widthLogo) / 2;
			int y = (image.getHeight() - heightLogo) / 2;
			// 开始绘制图片
			g.drawImage(logo, x, y, widthLogo, heightLogo, null);
			g.drawRoundRect(x, y, widthLogo, heightLogo, 10, 10);
			g.setStroke(new BasicStroke(creatrQrCode.getBorder()));
			g.setColor(creatrQrCode.getBorderColor());
			g.drawRect(x, y, widthLogo, heightLogo);
			g.dispose();
			ImageIO.write(image, "jpeg", qrPic);
		} catch (Exception e) {
//            LogUtils.error(logger,  " 为二维码添加logo错误！", e);
		}
	}

	/**
	 * 二维码图片添加文字
	 * 
	 * @param pressText 文字
	 * @param newImg    带文字的图片
	 * @param targetImg 需要添加文字的图片
	 * @param fontStyle 文字样式
	 * @param color     颜色
	 * @param fontSize  字体大小
	 * @param width     图片宽度
	 * @param height    图片高度
	 *
	 */
	public static void pressText(String pressText, String newImg, String targetImg, int fontStyle, Color color,
			int fontSize, int width, int height) {

		// 计算文字开始的位置
		// x开始的位置：（图片宽度-字体大小*字的个数）/2
		int startX = (width - (fontSize * pressText.length())) / 300;
		// y开始的位置：图片高度-（图片高度-图片宽度）/2
		int startY = height - (height - width) / 3;
		try {
			File file = new File(targetImg);
			Image src = ImageIO.read(file);
			int imageW = src.getWidth(null);
			int imageH = src.getHeight(null);
			BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, imageW, imageH, null);
			g.setColor(color);
			g.setFont(new Font(null, fontStyle, fontSize));
			g.drawString(pressText, startX, startY);
			g.dispose();
			FileOutputStream out = new FileOutputStream(newImg);
			ImageIO.write(image, "JPEG", out);
//			JPEGImageEncoder encoder = (JPEGImageEncoder) JPEGCodec.createJPEGEncoder(out);
//			encoder.encode(image);
			out.close();
		} catch (Exception e) {
//            LogUtils.error(logger,  " 为二维码添加文字错误！", e);
		}
	}

	/**
	 * 生成二维码（无logo，无文字）
	 * 
	 * @param qrcPath 用来存放生成的二维码图片
	 * @param content 二维码表示的内容
	 * @param width   图片完整的宽
	 * @param height  图片完整的高 建议：如果要在二维码下方附上文字，把图片设置为长方形（高大于宽）
	 */
	@SuppressWarnings("unchecked")
	public static void  createQrCode(String qrcPath, String content, int width, int height) {
		try {
			File qrcFile = new File(qrcPath);
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			Map hints = new HashMap();
			// 设置UTF-8， 防止中文乱码
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			// 设置二维码四周白色区域的大小
			hints.put(EncodeHintType.MARGIN, 0);
			// 设置二维码的容错性
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
			// 画二维码，记得调用multiFormatWriter.encode()时最后要带上hints参数，不然上面设置无效
			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			// 开始画二维码
			MatrixToImageWriter.writeToFile(bitMatrix, "jpg", qrcFile);
		} catch (Exception e) {
//            LogUtils.error(logger,  " 二维码生成错误！", e);
		}
	}

	/**
	 * 生成二维码（有logo，无文字）
	 * 
	 * @param qrcPath  用来存放生成的二维码图片
	 * @param content  二维码表示的内容
	 * @param width    图片完整的宽
	 * @param height   图片完整的高
	 * @param logoPath 附加在二维码中的图片
	 */
	public void createLogoQrCode(String qrcPath, String content, int width, int height, String logoPath) {
		// 准备一张二维码
		createQrCode(qrcPath, content, width, height);
		// 在二维码中加入图片
		CreatrQrCode creatrQrCode = new CreatrQrCode();
		File logoFile = new File(logoPath);
		File qrcFile = new File(qrcPath);
		addLogo_QRCode(qrcFile, logoFile, creatrQrCode);
	}

	/**
	 * 生成二维码（无logo，有文字）
	 * 
	 * @param newImageWithText 用来存放的带有文字的二维码图片
	 * @param targetImage      原二维码图片
	 * @param text             附加在图片上的文字信息
	 * @param width            图片宽度（用来计算文字x开始位置）
	 * @param height           图片高度（用来计算文字y开始位置）
	 */
	public void createWordQrcode(String newImageWithText, String targetImage, String text, int width, int height) {
		// 准备一张二维码
		createQrCode(newImageWithText, text, width, height);
		// 字体大小
		int font = 20;
		// 字体风格
		int fontStyle = 4;
		// 在二维码下方添加文字（是否替换原图）
		if (StringUtils.isNotBlank(targetImage)) {
			pressText(text, newImageWithText, targetImage, fontStyle, Color.red, font, width, height);
		} else {
			pressText(text, newImageWithText, newImageWithText, fontStyle, Color.red, font, width, height);
		}
	}

	/**
	 * 生成二维码（有logo，有文字）
	 * 
	 * @param newImageWithText 用来存放的带有文字的二维码图片
	 * @param targetImage      原二维码图片
	 * @param text             附加在二维码上的文字信息
	 * @param width            图片宽度（用来计算文字x开始位置）
	 * @param height           图片高度（用来计算文字y开始位置）
	 * @param logoPath         附加在二维码上的图片
	 */
	public void createWordLogoQrcode(String newImageWithText, String targetImage, String text, int width, int height,
			String logoPath) {
		// 准备一张二维码
		createLogoQrCode(newImageWithText, text, width, height, logoPath);
		// 字体大小
		int font = 20;
		// 字体风格
		int fontStyle = 4;
		// 在二维码下方添加文字（是否替换原图）
		if (StringUtils.isNotBlank(targetImage)) {
			pressText(text, newImageWithText, targetImage, fontStyle, Color.red, font, width, height);
		} else {
			pressText(text, newImageWithText, newImageWithText, fontStyle, Color.red, font, width, height);
		}
	}

	public static void main(String args[]) {
		try {
			CreatrQrCode creatrQrCode = new CreatrQrCode();
			creatrQrCode.createQrCode("D:\\wu.png", "单纯二维码：11112345", 300, 300);
//			creatrQrCode.createLogoQrCode("D:\\have_logo.png", "只有logo：12345", 300, 300, "D:\\logo.png");
//			creatrQrCode.createWordQrcode("D:\\have_word.png", "", "只有文字：12345", 400, 470);
//			creatrQrCode.createWordLogoQrcode("D:\\have_logo_word.png", "", "有logo有文字：12345", 400, 470, "D:\\logo.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public int getBorder() {
		return border;
	}

	public int getLogoPart() {
		return logoPart;
	}
}