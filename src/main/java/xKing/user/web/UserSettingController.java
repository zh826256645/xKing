package xKing.user.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xKing.user.domain.User;
import xKing.user.service.UserService;

/**
 * User Setting 控制器
 * 
 * @author 钟浩
 * @date 2016年11月7日
 *
 */

@Controller
@RequestMapping("/setting")
public class UserSettingController {

	@Autowired
	private UserService userService;
	
	// 获取用户设置页面
	@RequestMapping(method=RequestMethod.GET)
	public String upDatePage(Model model, Principal principal) {
		final String currentUsername = principal.getName();
		User currentUser = userService.getUserByUsername(currentUsername);
		model.addAttribute("currentUser", currentUser);
		return "profileSetting";
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.POST)
	public String profileSetting(User user, Principal principal) {
		user.setUsername(principal.getName());
		userService.updateProfile(user);
		return "redirect:/setting";
	}
}
