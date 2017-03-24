package com.visionet.core.filter;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.visionet.core.support.ResultType;
import com.visionet.core.util.IpUtils;
import com.visionet.core.util.security.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.TreeSet;

import static com.visionet.core.util.HttpClient.*;

/**
 * 请求过滤器
 */
public class AccessFilter extends OncePerRequestFilter {
    private static final Logger _logger = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 请求过滤
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // TODO: admin 2016/11/23 14:54  排除静态文件
        if (requestURI.contains(".js")
                || requestURI.contains(".jsp")
                || requestURI.contains(".html")
                || requestURI.contains(".png")
                || requestURI.contains(".gif")
                || requestURI.contains(".ico")
                || requestURI.contains(".jpg")
                || requestURI.contains(".jpeg")
                || requestURI.contains(".css")
                || requestURI.contains("transformation")
                || requestURI.contains("monitoring")
                || requestURI.contains("druid")
                ) {
            filterChain.doFilter(request, response);
            return;
        }

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            String ipAddr = IpUtils.getIpAddr(request);
            _logger.info("===>>请求IP地址：{}", ipAddr);
            _logger.info("===>>请求URL地址：{}", request.getRequestURL());

            // TODO: 2016/11/22 11:11  IP地址过滤

            // TODO: 2016/11/23 14:35  参数ksort排序
            TreeSet<String> setSort = sortParameter(request);
            if (setSort.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }

            String channel = request.getParameter("channel");
            if (StringUtils.isBlank(channel)) {
                setResponse(response, ResultType.SIGN_FAIL.getResult(), "渠道号不能为空");
                return;

            }
            if (!SQYC_CHANNEL.equals(channel)) {
                setResponse(response, ResultType.SIGN_FAIL.getResult(), "渠道号不存在");
                return;
            }

            // TODO: 2016/11/23 14:44  签名验证
            String signData = request.getParameter("sign");
            String sign = validateSign(request, setSort);
            _logger.info("生成签名：{},对方签名：{}", sign, signData);
            if (StringUtils.isBlank(signData)) {
                setResponse(response, ResultType.SIGN_FAIL.getResult(), ResultType.SIGN_FAIL.getErrmsg());
                return;
            }
            if (!sign.equals(signData)) {
                setResponse(response, ResultType.SIGN_FAIL.getResult(), ResultType.SIGN_FAIL.getErrmsg());
                return;
            }

        } catch (Exception e) {
            setResponse(response, ResultType.FAIL.getResult(), ResultType.FAIL.getErrmsg());
            return;
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 校验签名
     *
     * @param request
     * @param setSort
     * @return
     * @throws UnsupportedEncodingException
     */
    private String validateSign(HttpServletRequest request, TreeSet<String> setSort) throws UnsupportedEncodingException {
        _logger.info("====>请求参数:");
        StringBuilder sb2 = new StringBuilder();
        for (String key : setSort) {
            String parameter = request.getParameter(key);
            if (StringUtils.isBlank(parameter)) {
                continue;
            }
            sb2.append(key + "=" + parameter + "&");
            _logger.info(key + "=" + parameter);
        }
        sb2.append(DZCX_KEY + "=" + DZCX_KEY_VALUE);
        _logger.info("签名字符串：{}", sb2.toString());
        return Md5Utils.encode(sb2.toString());
    }

    /**
     * 对参数进行排序
     *
     * @param request
     * @return
     */
    private TreeSet<String> sortParameter(HttpServletRequest request) {
        Enumeration<String> d = request.getParameterNames();
        // TODO: 2016/11/22 11:05  对参数进行KSOET排序
        TreeSet<String> setSort = Sets.newTreeSet();
        while (d.hasMoreElements()) {
            String para = d.nextElement();
            if ("sign".equals(para)) {
                continue;
            }
            setSort.add(para);
        }
        return setSort;
    }

    /**
     * 设置错误响应
     *
     * @param response
     * @param code
     * @param msg
     * @throws IOException
     */
    private void setResponse(HttpServletResponse response, String code, String msg) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        JSONObject ret = new JSONObject();
        PrintWriter out = response.getWriter();
        ret.put("result", code);
        ret.put("errmsg", msg);
        _logger.info("====>异常信息:{}",ret.toString());
        out.print(ret.toString());
        out.close();
    }


}
