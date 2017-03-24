package test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.visionet.core.util.spring.SpringUtils;
import com.visionet.ivr.model.Order;
import com.visionet.ivr.service.OrderService;

/**
 * projectName:webSocket
 * author:liusy@visionet.com.cn
 * data:2016/10/1
 */
@RunWith(SpringJUnit4ClassRunner.class)//"classpath:spring-mvc.xml"
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class JunitTest extends AbstractJUnit4SpringContextTests {

	Logger log=LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void test(){
		OrderService orderService=SpringUtils.getBean(OrderService.class);	
	//List<Order>list=	orderService.selectByCustomePhoneBetweenTime("13764890082",new Date(1483426000000l));
	//log.info("查询出:"+list.size()+"");
	
	}

}