package com.visionet.core.controller;

import com.visionet.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;





/**
 * @author liusy@visionet.com.cn
 * @param <M>
 */
@SuppressWarnings("rawtypes")
public abstract class BaseRestCRUDController<M> extends BaseController {
	@Autowired
    protected BaseService<M> baseService;

}
