package xKing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import xKing.user.web.UserMustActivateFilter;
import xKing.user.web.UserSucceedLoginHander;

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
				.antMatchers("/user").anonymous()
				.antMatchers("/user/new").anonymous()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/user")
				.defaultSuccessUrl("/user/me")
				.successHandler(new UserSucceedLoginHander())
			.and()
			.logout()
				.logoutSuccessUrl("/")
			.and()
			.addFilterBefore(new UserMustActivateFilter("/login","/user"), UsernamePasswordAuthenticationFilter.class);
	}
	
	// 在内存中注册用户
	// 用于测试
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("ZhongHao").password("123456").roles("USER").and()
				.withUser("HuangLiNa").password("123456").roles("USER");

	}
	
}
