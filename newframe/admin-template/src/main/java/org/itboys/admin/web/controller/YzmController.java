package org.itboys.admin.web.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.commons.utils.random.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码
 * @author ChenJunhui
 */
@RestController
public class YzmController {

	@RequestMapping(value = "/yzm")
	public String getYzm(HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 定义图片长度和宽度
		int width = 70, height = 30;
		// 创建内存图像
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();

		// 验证码图形显示为数字和英文字母
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.BOLD, 22));
		g.setColor(new Color(255, 255, 255));
		g.drawRect(0, 0, width - 1, height - 1);
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {

			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 随机获取四位字母或数字型字符
		String s = RandomUtils.getClearRandomStr(4).toLowerCase();   //RandomStringUtils.random(5, false, false);

		g.setColor(new Color(20 + random.nextInt(110),
				20 + random.nextInt(110), 20 + random.nextInt(110)));
		// 将字符画到图片上
		g.drawString(s, 6, 20);

		HttpSession session = request.getSession();
		session.setAttribute("rand", s);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
		return null;
	}

	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	@RequestMapping(value = "/validateYzm")
	public void validateYzm(@RequestParam String yzm, HttpServletRequest request,HttpServletResponse response){
		String _yzm = (String) request.getSession().getAttribute("rand");
		AjaxUtils.renderText(response, String.valueOf(StringUtils.equals(_yzm, yzm)));
	}
	
}
