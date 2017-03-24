package com.visionet.core.controller;


import com.visionet.core.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;




/** 
 * @ClassName: BaseCRUDController 
 * @Description: TODO: 基本CRUD控制器
 * @author liusy@visionet.com.cn
 * @param <M>
 */
@SuppressWarnings("rawtypes")
public abstract class BaseCRUDController<M> extends BaseController {
	private static Logger Log = LoggerFactory.getLogger(BaseCRUDController.class);
	
	@Autowired
    protected BaseService<M> baseService;

}
