package com.ossbar.modules.evgl.book.api;

import java.util.List;
import java.util.Map;

import com.ossbar.core.baseclass.api.IBaseService;
import com.ossbar.modules.evgl.book.domain.TevglBookSubperiod;

/**
 * <p> Title: 专业课程（职业课程）关系</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:ossbar.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

public interface TevglBookSubperiodService extends IBaseService<TevglBookSubperiod>{
	
	/**
	 * <p></p>
	 * @author huj
	 * @data 2019年7月16日
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectListForEvglWeb(Map<String, Object> map);
	
}