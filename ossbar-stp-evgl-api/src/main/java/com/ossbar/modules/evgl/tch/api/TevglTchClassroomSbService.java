package com.ossbar.modules.evgl.tch.api;

import java.util.Map;

import com.ossbar.core.baseclass.api.IBaseService;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.evgl.tch.domain.TevglTchClassroomSb;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:ossbar.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

public interface TevglTchClassroomSbService extends IBaseService<TevglTchClassroomSb>{
	
	/**
	 * 分页查询课外教材
	 * @param params
	 * @param traineeId
	 * @return
	 */
	R queryExtraBooks(Map<String, Object> params, String traineeId);
	
	/**
	 * 查询课堂已关联的教材
	 * @param ctId
	 * @param traineeId
	 * @return
	 */
	R findExtraBooks(String ctId, String traineeId);
	
	/**
	 * 保存
	 * @return
	 */
	R saveExtraBooksRelation(TevglTchClassroomSb sb, String traineeId);
	
}