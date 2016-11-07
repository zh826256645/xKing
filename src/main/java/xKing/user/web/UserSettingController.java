package xKing.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	// 获取用户设置页面
	@RequestMapping(method=RequestMethod.GET)
	public String upDatePage() {
		return "profileSetting";
	}
}
