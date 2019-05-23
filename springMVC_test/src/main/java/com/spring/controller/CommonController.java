package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.dto.MemberVO;
import com.spring.service.MemberService;

@Controller
public class CommonController {
	
	@Resource(name="memberService") // = @Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/common/login", method=RequestMethod.GET)
	public String loginGET() {
		return "common/login";
	}
	
	@RequestMapping(value="/common/login", method=RequestMethod.POST)
	public void loginPOST(LoginRequest loginReq, HttpServletResponse response
							/* String id, String pwd, HttpServletRequest request,
							HttpSession session */) throws SQLException, IOException {
		MemberVO member = memberService.getMember(loginReq.getId());
		String message="";
		
		if(member != null) {
			if(loginReq.getPwd().equals(member.getPwd())) {
				message="로그인 성공";
			}else {
				message="패스워드 일치X";
			}
		}else {
			message="id가 존재X";
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>alert('"+message+"');</script>");

	}
}
