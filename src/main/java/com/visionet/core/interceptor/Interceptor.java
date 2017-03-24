package com.visionet.core.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor implements HandlerInterceptor {
	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");
	private static final Logger _logger = LoggerFactory.getLogger(Interceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
			throws Exception {
		startTimeThreadLocal.set(System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			_logger.debug("ReturnViewName:{}", modelAndView.getViewName());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
		long endTime = System.currentTimeMillis();// 2、结束时间
		long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
		long consumeTime = endTime - beginTime;// 3、消耗的时间
		_logger.debug("本次请求URI：{}，本次请求时间：{}，", httpServletRequest.getRequestURI(), consumeTime);
//		System.err.println("请求完成，本次请求URI：" + httpServletRequest.getRequestURI() + "，本次请求时间：" + consumeTime);
	}
}
