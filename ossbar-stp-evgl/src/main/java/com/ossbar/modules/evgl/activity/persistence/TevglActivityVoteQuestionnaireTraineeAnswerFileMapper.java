package com.ossbar.modules.evgl.activity.persistence;

import com.ossbar.core.baseclass.persistence.BaseSqlMapper;
import com.ossbar.modules.evgl.activity.domain.TevglActivityVoteQuestionnaireTraineeAnswerFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:creatorblue.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

@Mapper
public interface TevglActivityVoteQuestionnaireTraineeAnswerFileMapper extends BaseSqlMapper<TevglActivityVoteQuestionnaireTraineeAnswerFile> {
	
	/**
	 * 根据条件查询记录，返回List<Map>
	 * @param map
	 * @return 
	 */
	List<Map<String, Object>> selectListMapByMap(Map<String, Object> map);
	
	/**
	 * 根据条件查询记录，返回List<Map>
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectSimpleListMapByMap(Map<String, Object> map);
	
	/**
	 * 查询id为空的记录
	 * @return
	 */
	List<Map<String, Object>> queryFileInfoByIdIsNull();
}