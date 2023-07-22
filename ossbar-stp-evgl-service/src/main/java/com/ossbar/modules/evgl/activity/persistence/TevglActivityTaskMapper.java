package com.ossbar.modules.evgl.activity.persistence;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.ossbar.modules.evgl.activity.domain.TevglActivityTask;
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
public interface TevglActivityTaskMapper extends BaseSqlMapper<TevglActivityTask> {
	
	/**
	 * 根据条件查询记录，返回List<Map>
	 * @param map
	 * @return 
	 */
	List<Map<String, Object>> selectListMapByMap(Map<String, Object> map);
	
	/**
	 * 根据条件获取最大排序号（已+1）
	 * @param map
	 * @return
	 */
	Integer getMaxSortNum(Map<String, Object> map);
	
	/**
	 * 查询对象
	 * @param id
	 * @return
	 */
	Map<String, Object> selectObjectMapById(Object id);
	
	/**
	 * 查询对象
	 * @param params
	 * @return
	 */
	TevglActivityTask selectObjectByIdAndPkgId(Map<String, Object> params);
}