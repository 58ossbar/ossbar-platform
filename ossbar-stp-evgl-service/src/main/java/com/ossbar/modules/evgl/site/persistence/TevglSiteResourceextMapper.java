package com.ossbar.modules.evgl.site.persistence;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.ossbar.modules.evgl.site.domain.TevglSiteResourceext;
import com.ossbar.core.baseclass.persistence.BaseSqlMapper;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:ossbar.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

@Mapper
public interface TevglSiteResourceextMapper extends BaseSqlMapper<TevglSiteResourceext> {
	
	/**
	 * 根据条件查询记录，返回List<Map>
	 * @param map
	 * @return 
	 */
	List<Map<String, Object>> selectListMapByMap(Map<String, Object> map);
	
	/**
	 * <p>根据菜单ID查询对象</p>
	 * @author huj
	 * @data 2019年7月16日
	 * @param menuId
	 * @return
	 */
	TevglSiteResourceext selectObjectByMenuId(String menuId);
}