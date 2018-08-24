package com.songiam.www.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.songiam.www.model.Comment;
import com.songiam.www.model.ContactMessage;
import com.songiam.www.model.User;
import com.songiam.www.service.CommentService;
import com.songiam.www.service.UserService;
import com.songiam.www.util.Mail;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@Value("#{servletContext.getRealPath('/')}") // 경로
	private String realPath;
	@Autowired
	private JavaMailSender mailSender; //메일 보내기
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("/");

		List<Comment> commentsList;
		try {
			commentsList = commentService.getAllComments();
			logger.info("comments count :: " + commentsList.size() + " :: " + commentsList.toString() + ":: " + commentsList.get(1).getSubTitle());
			model.addAttribute("commentsList", commentsList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return "home";
	}
	
	@RequestMapping(value = "/commentDetail", method = RequestMethod.GET)
	public String commentDetail(Model model, @RequestParam(value = "index")String index) {
		logger.info("commentDetail");
	
		String commentIndex = index;
		logger.info("commentDetail : index :: " + index);
	
		try {
			commentService.increaseCommentCount(commentIndex); //count 증가
			
			Comment comment = commentService.getComment(commentIndex);
			logger.info("comment content :: " + comment.getComment());

			model.addAttribute("comment", comment);
			model.addAttribute("commentIndex", commentIndex);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return "board_detail";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		logger.info("login");
	
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		logger.info("logout");
		request.getSession().invalidate();
		
		return "redirect:" + "/";
	}
	
	@RequestMapping(value = "/createComment", method = RequestMethod.GET)
	public String createComment(Model model) {
		logger.info("createComment");
		
		Comment comment = null;
		model.addAttribute("comment", comment);
		
		return "board_edit";
	}
	
	@RequestMapping(value = "/commentEdit", method = RequestMethod.GET)
	public String commentEdit(@RequestParam(value = "index")String index, Model model) {
		logger.info("commentEdit");
		
		Comment comment;
		try {
			comment = commentService.getComment(index);
			model.addAttribute("comment", comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "board_edit";
	}
	
	@RequestMapping(value = "/commentRemove", method = RequestMethod.GET)
	public String commentRemove(@RequestParam(value = "index")String index, Model model) {
		logger.info("commentRemove");
		
		try {
			commentService.removeComment(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return "redirect:" + "/";
	}
	
	@RequestMapping(value = "/showHistory", method = RequestMethod.GET)
	public String showHistory(Model model) {
		logger.info("commentEdit");
		
		Comment comment;
		try {
			comment = commentService.getHistoryComment();
			model.addAttribute("comment", comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return "board_history";
	}
	
	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)
	public String saveComment(@RequestParam(value = "contentarea") String contentData,
			@RequestParam(value = "title") String title, @RequestParam(value = "thumbnail") String thumbnail,
			@RequestParam(value = "subTitle") String subTitle, @RequestParam(value = "index") String index,
			Model model) {
		
		logger.info("saveComment");
		logger.info("contentData :: " + contentData + " title :: " + title + " thumbnail :: " + thumbnail
				+ " subTitle :: " + subTitle + " index :: " + index);

		try {
			if (index.equals("")) { // 게시물 생성
				logger.info("index is ''");
				commentService.saveComment(contentData, title, thumbnail, subTitle);

			} else { // 게시물을 수정할 경우
				commentService.updateComment(index, contentData, title, thumbnail, subTitle);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:" + "/";
	}

	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	@ResponseBody
	public Object loginCheck(@RequestParam(value = "id")String id, @RequestParam(value = "password")String password, HttpServletRequest request, Model model) {
		logger.info("loginCheck");
		logger.info("id :: " + id + " password :: " + password);
			
		try {
			User user = userService.checkUser(id, password);
		
			logger.info("user :: " + user);
			
			if (user == null) {
				logger.info("user is not exist!");
			}

			else {
				logger.info("login success!");
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("user", user);

				request.getSession().setAttribute("loginUser", user);

				return userMap;
			}		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	@RequestMapping(value = "/sessionCheck", method = RequestMethod.GET)
	@ResponseBody
	public Object board(HttpServletRequest request) {
		logger.info("sessionCheck");

		User sessionLoginInfo = (User) request.getSession().getAttribute("loginUser");
		if (sessionLoginInfo != null) {
			logger.info("sessionLoginInfo is exist");
			return "sessionCheck";
		}

		logger.info("sessionLoginInfo is not exist");
		return null;
	}
	
	@RequestMapping(value = "/sendContactMessage", method = RequestMethod.POST)
	@ResponseBody
	public Object sendContactMessage(String name, String email, String subject, String content) {
		logger.info("saveContactMessage");
		Mail mail = new Mail(mailSender);
		ContactMessage contactMessage = new ContactMessage();
		contactMessage.setContent(content);
		contactMessage.setEmail(email);
		contactMessage.setName(name);
		contactMessage.setSubject(subject);

		logger.info("saveContactMessage :: " + name + " :: " + content);

		try {
			commentService.saveContactMessage(contactMessage); //DB 해당 내용 저장
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			
		mail.sendMail(name, email, subject, content); //해당 내용 메일 발송
		return null;
	}
}
