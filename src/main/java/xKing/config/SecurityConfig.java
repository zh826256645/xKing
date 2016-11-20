package xKing.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

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
	
	@Autowired
	private DataSource dataSource;
	
	// 设置 URL 的访问权限
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/**/**.**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers(HttpMethod.GET, "/user").permitAll()
				.antMatchers(HttpMethod.GET, "/user/new").permitAll()
				.antMatchers(HttpMethod.POST, "/user").anonymous()
				.antMatchers(HttpMethod.POST, "/user/new").anonymous()
				.antMatchers("/user/state").anonymous()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/user")
				.defaultSuccessUrl("/user/me")
				.usernameParameter("username").passwordParameter("password")
				.successHandler(new UserSucceedLoginHander())
			.and()
			.logout()
				.logoutSuccessUrl("/");
	}
	
	// 在内存中注册用户
	// 用于测试
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, `password`, enabled from user where username=?")
				.authoritiesByUsernameQuery("select username, 'ROLE_USER' FROM user where username=?")
				.passwordEncoder(new Md5PasswordEncoder());
	}
	
}
