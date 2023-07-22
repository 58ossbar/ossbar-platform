package com.ossbar.modules.evgl.question.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ossbar.core.baseclass.persistence.BaseSqlMapper;
import com.ossbar.modules.evgl.question.domain.TepExaminePaperScoreGapfilling;

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
public interface TepExaminePaperScoreGapfillingMapper extends BaseSqlMapper<TepExaminePaperScoreGapfilling> {
	
	/**
	 * 根据条件查询记录，返回List<Map>
	 * @param map
	 * @return 
	 */
	List<Map<String, Object>> selectListMapByMap(Map<String, Object> map);
	
	List<Map<String, Object>> selectSimpleListMapByMap(Map<String, Object> map);
	
	/**
	 * 批量更新简答题内容
	 * @param list
	 */
	void batchUpdateContentByDuplicate(List<TepExaminePaperScoreGapfilling> list);
	
	/**
	 * 批量新增
	 * @param list
	 */
	void insertBatch(@Param("list") List<TepExaminePaperScoreGapfilling> list);
}