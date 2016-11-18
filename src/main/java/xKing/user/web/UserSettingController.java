package xKing.user.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xKing.user.domain.ChangePassword;
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
	public String profileSetting(User user, Principal principal, RedirectAttributes reMoldel) {
		user.setUsername(principal.getName());
		userService.updateProfile(user);
		reMoldel.addFlashAttribute("message", "用户信息修改成功！");
		return "redirect:/setting";
	}
	
	@RequestMapping(value="/password", method=RequestMethod.POST)
	public String passwordChange(
			@Validated ChangePassword changePassword, Errors errors,
			Principal principal,
			RedirectAttributes reModel) {
		if(errors.hasErrors()) {
			reModel.addFlashAttribute("error", "密码格式错误，更改失败！");
			return "redirect:/setting";
		}
		if(userService.changePassword(principal.getName(), changePassword)) {
			reModel.addFlashAttribute("message", "密码更改成功！");
			return "redirect:/setting";
		} else {
			reModel.addFlashAttribute("error", "密码错误，更改失败！");
			return "redirect:/setting";
		}
	}
}
