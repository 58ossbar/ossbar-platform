package com.ossbar.modules.evgl.tch.api;

import com.ossbar.core.baseclass.api.IBaseService;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.evgl.tch.domain.TevglTchClassroomTop;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:ossbar.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

public interface TevglTchClassroomTopService extends IBaseService<TevglTchClassroomTop>{
	
	/**
	 * 置顶
	 * @param ctId
	 * @param loginUserId
	 * @return
	 */
	R setTop(String ctId, String loginUserId);
	
	/**
	 * 取消置顶
	 * @param ctId
	 * @param loginUserId
	 * @return
	 */
	R cancelTop(String ctId, String loginUserId);
}