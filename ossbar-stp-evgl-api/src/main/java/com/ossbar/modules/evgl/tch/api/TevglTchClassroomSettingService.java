package com.ossbar.modules.evgl.tch.api;

import com.ossbar.core.baseclass.api.IBaseService;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.evgl.tch.domain.TevglTchClassroomSetting;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:ossbar.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

public interface TevglTchClassroomSettingService extends IBaseService<TevglTchClassroomSetting>{
	
	/**
	 * 保存设置
	 * @param ctId
	 * @param traineeId
	 * @param radio1
	 * @param radio2 up标识升序，down标识降序
	 * @return
	 */
	R saveSetting(String ctId, String traineeId, String radio1, String radio2);
	
	/**
	 * 查看
	 * @param ctId
	 * @return
	 */
	R viewSetting(String ctId);
	
}