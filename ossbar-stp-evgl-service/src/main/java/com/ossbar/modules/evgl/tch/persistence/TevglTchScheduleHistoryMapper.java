package com.ossbar.modules.evgl.tch.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ossbar.core.baseclass.persistence.BaseSqlMapper;
import com.ossbar.modules.evgl.tch.domain.TevglTchScheduleHistory;

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
public interface TevglTchScheduleHistoryMapper extends BaseSqlMapper<TevglTchScheduleHistory> {
	
	/**
	 * 根据条件查询记录，返回List<Map>
	 * @param map
	 * @return 
	 */
	List<Map<String, Object>> selectListMapByMap(Map<String, Object> map);
	
	/**
	 * 根据t_evgl_tch_schedule表主键id从t_evgl_tch_schedule_history表查询记录
	 * @param id
	 * @return
	 */
	TevglTchScheduleHistory selectObjectByOldId(@Param("scheduleId") Object id);
}