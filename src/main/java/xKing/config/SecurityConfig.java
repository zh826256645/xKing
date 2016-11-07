package xKing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * Spring Security 配置文件
 * @author 钟浩
 * @date 2016年11月7日
 *
 */

@Configuration
// 启用 Spring MVC 安全性
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	// 设置 URL 的访问权限
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/**/**.**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/user").permitAll()
				.antMatchers("/user/new").permitAll()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/user");
	}
	
	// 在内存中注册用户
	// 用于测试
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("ZhongHao").password("123456").roles("USER");
	}
}
