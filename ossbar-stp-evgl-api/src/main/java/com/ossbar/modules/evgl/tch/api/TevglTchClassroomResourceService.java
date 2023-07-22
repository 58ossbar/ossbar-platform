package com.ossbar.modules.evgl.tch.api;

import com.ossbar.core.baseclass.api.IBaseService;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.evgl.tch.domain.TevglTchClassroomResource;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:ossbar.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

public interface TevglTchClassroomResourceService extends IBaseService<TevglTchClassroomResource>{
	
	/**
	 * 获取树形数据
	 * @param ctId
	 * @param loginUserId
	 * @return
	 */
	R getTreeData(String ctId, String loginUserId);
	
}