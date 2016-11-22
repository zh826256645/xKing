package xKing.interceptor;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 上传拦截器
 * @author 钟浩
 * @date 2016年11月22日
 *
 */

public class UploadInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MultipartResolver mr = new StandardServletMultipartResolver();
		if(mr.isMultipart(request)) {
			 MultipartHttpServletRequest  multipartRequest = (MultipartHttpServletRequest) request;
			 Map<String, MultipartFile> files = multipartRequest.getFileMap();
			 Iterator<String> iterator = files.keySet().iterator();
			 while(iterator.hasNext()) {
				 String formkey = iterator.next();
				 MultipartFile multipartFile = multipartRequest.getFile(formkey);
				 if(!multipartFile.getOriginalFilename().trim().isEmpty()) {
					 String type = multipartFile.getContentType();
					 if(type.equalsIgnoreCase("image/jpeg") || type.equalsIgnoreCase("image/gif") || type.equalsIgnoreCase("image/png")) {
						 request.getSession().removeAttribute("error");
						 return true;
					 } else {
						 String retUrl = request.getHeader("Referer");
						 request.getSession().setAttribute("error", "上传文件格式不正确！");
						 response.sendRedirect(retUrl);
						 return false;
					 }
				 } else {
					 String retUrl = request.getHeader("Referer");
					 request.getSession().setAttribute("error", "上传文件为空！");
					 response.sendRedirect(retUrl);
					 return false;
				 }
			 }
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
