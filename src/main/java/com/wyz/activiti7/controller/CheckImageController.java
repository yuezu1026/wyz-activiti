package com.wyz.activiti7.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyz.activiti7.utils.VerifyCode;

@Controller
@RequestMapping("/check")
public class CheckImageController {

	private static final Logger logger = LoggerFactory.getLogger(CheckImageController.class);

	@RequestMapping(value = "/showVerifyCode", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public void getVerificationCode(HttpServletResponse response, HttpServletRequest request) {
		try {
			int width = 200;
			int height = 69;
			BufferedImage verifyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			//生成对应宽高的初始图片
			String randomText = VerifyCode.drawRandomText(width, height, verifyImg);
			//单独的一个类方法，出于代码复用考虑，进行了封装。
			//功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符                   
			request.getSession().setAttribute("verifyCode", randomText);
			response.setContentType("image/png");// 必须设置响应内容类型为图片，否则前台不识别
			OutputStream os = response.getOutputStream(); // 获取文件输出流
			ImageIO.write(verifyImg, "png", os);// 输出图片流
			os.flush();
			os.close();// 关闭流

		} catch (IOException e) {
			logger.error("ERROR",e);
		}

	}
}
