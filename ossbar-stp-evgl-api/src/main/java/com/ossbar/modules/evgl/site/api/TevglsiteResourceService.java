package com.ossbar.modules.evgl.site.api;

import java.util.List;
import java.util.Map;

import com.ossbar.modules.sys.domain.TsysResource;

/**
 * <p>前端导航菜单</p>
 * <p>Title: TevglsiteResourceService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 湖南创蓝信息科技有限公司</p> 
 * @author huj
 * @date 2019年7月3日
 */
public interface TevglsiteResourceService {
	
	/**
	 * <p>只查出自有资源，不包括其他系统的资源</p>
	 * @author huj
	 * @data 2019年7月3日
	 * @param map
	 * @return
	 */
	List<TsysResource> selectSiteListByMap(Map<String, Object> map);
	 
	/**
	 * <p>只查出自有资源，不包括其他系统的资源</p>
	 * @author huj
	 * @data 2019年7月3日
	 * @param menuId
	 * @return
	 */
	List<TsysResource> selectSiteListParentId(String menuId);
}
