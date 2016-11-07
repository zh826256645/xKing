package xKing.index.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// Controller 组件
@Controller
// 映射路径为 “/”
@RequestMapping("/")
public class HomeController {

	// 主页
	@RequestMapping(method=RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
}
