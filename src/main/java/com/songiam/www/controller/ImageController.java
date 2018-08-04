package com.songiam.www.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
	@Value("#{servletContext.getRealPath('/')}") // 경로
	private String realPath;
	
	/**
	 * 이미지 업로드
	 * 
	 * @param request
	 * @param response
	 * @param upload
	 */
	@RequestMapping(value = "/imageUpload")
	public void imageUpload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam MultipartFile upload, Model model) {

		logger.info("imageUpload");

		OutputStream out = null;
		PrintWriter printWriter = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		try {

			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			String uploadPath = realPath + "\\resources\\img\\upload\\" + fileName;// 저장경로

			out = new FileOutputStream(new File(uploadPath));
			out.write(bytes);

			printWriter = response.getWriter();
			String fileUrl = "/resources/img/upload/" + fileName;// url경로

			logger.info("imageUpload :: uploadPath " + uploadPath + " :: fileUrl " + fileUrl);

			printWriter.println("{'filename' : '" + fileName + "', 'uploaded' : 1, 'url':'" + fileUrl + "'}");
			printWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return;
	}

}
