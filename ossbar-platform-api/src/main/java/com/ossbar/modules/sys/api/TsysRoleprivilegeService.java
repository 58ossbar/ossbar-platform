package com.ossbar.modules.sys.api;

import java.util.List;

import com.ossbar.core.baseclass.domain.R;

/**
 * <p>角色与菜单关系</p>
 * <p>Title: TsysRoleprivilegeService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 湖南创蓝信息科技有限公司</p> 
 * @author huj
 * @date 2019年5月6日
 */
public interface TsysRoleprivilegeService {
	
	/**
	 * <p>保存角色与菜单关系</p>
	 * @author huj
	 * @data 2019年5月6日
	 * @param roleId
	 * @param menuIdList
	 */
	R saveOrUpdate(String roleId, List<String> menuIdList);
	
	/**
	 * <p>根据角色ID,查询菜单ID列表</p>
	 * @author huj
	 * @data 2019年5月6日
	 * @param roleId
	 * @return
	 */
	List<String> selectMenuListByRoleId(String roleId);
	
}
