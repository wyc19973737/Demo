package com.userMis.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static char[] chs = "0123456789QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
	private static int count;
	private static int width;
	private static int height;

	@SuppressWarnings("static-access")
    public void init(ServletConfig config) throws ServletException {
	    String count = config.getInitParameter("count");
	    this.count = Integer.parseInt(count);
	    String width = config.getInitParameter("width");
	    this.width = Integer.parseInt(width);
	    String height = config.getInitParameter("height");
	    this.height = Integer.parseInt(height);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);// 创建一张图片
	    Graphics2D g = image.createGraphics();// 转为2D
	    g.setColor(new Color(238, 238, 238));
	    g.fillRect(0, 0, width, height);// 边框
	    g.setFont(new Font("Times New Roman", Font.BOLD, 25));
	    
	    StringBuffer code = new StringBuffer();// 保存验证码字符串
	    Random random = new Random();
	    for (int i = 0; i < count; i++) {
	        int index = random.nextInt(chs.length);
	        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));// 随机字符颜色
	        g.drawString(String.valueOf(chs[index]), 20 * i + 5, 23);// 画出字符
	        code.append(chs[index]);// 验证码字符串
	    }
	    
	    request.getSession().setAttribute("authCode", code.toString());//将code保存到session中
	    ImageIO.write(image, "jpg", response.getOutputStream());// 字节流
	    // response.getWriter().write("aaaa");字符流
	}

}
