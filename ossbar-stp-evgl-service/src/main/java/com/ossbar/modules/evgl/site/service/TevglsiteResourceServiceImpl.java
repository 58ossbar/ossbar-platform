package com.ossbar.modules.evgl.site.service;

import java.util.List;
import java.util.Map;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ossbar.modules.evgl.site.api.TevglsiteResourceService;
import com.ossbar.modules.evgl.site.persistence.TevglsiteResourceMapper;
import com.ossbar.modules.sys.domain.TsysResource;

/**
 * <p>【前端导航菜单】接口实现类</p>
 * <p>Title: TevglsiteResourceServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 湖南创蓝信息科技有限公司</p> 
 * @author huj
 * @date 2019年7月3日
 */
@Service(version = "1.0.0")
@RestController
@RequestMapping("/site/tevglsiteresource")
public class TevglsiteResourceServiceImpl implements TevglsiteResourceService {

	@Autowired
	private TevglsiteResourceMapper tepsiteResourceMapper;
	
	/**
	 * <p>只查出自有资源，不包括其他系统的资源</p>
	 * @author huj
	 * @data 2019年7月3日
	 * @param map
	 * @return
	 */
	@Override
	public List<TsysResource> selectSiteListByMap(Map<String, Object> map) {
		return tepsiteResourceMapper.selectSiteListByMap(map);
	}

	/**
	 * <p>只查出自有资源，不包括其他系统的资源</p>
	 * @author huj
	 * @data 2019年7月3日
	 * @param map
	 * @return
	 */
	@Override
	public List<TsysResource> selectSiteListParentId(String menuId) {
		return tepsiteResourceMapper.selectSiteListParentId(menuId);
	}

}
