package com.ossbar.modules.evgl.forum.persistence;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.ossbar.modules.evgl.forum.domain.TevglForumCommentInfo;
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
public interface TevglForumCommentInfoMapper extends BaseSqlMapper<TevglForumCommentInfo> {
	
	/**
	 * 根据条件查询记录，返回List<Map>
	 * @param map
	 * @return 
	 */
	List<Map<String, Object>> selectListMapByMap(Map<String, Object> map);
	
	/**
	 * 博客评论列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryBlogCommentList(Map<String, Object> map);
	
	/**
	 * 查询评论人（已去重）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryTraineeList(Map<String, Object> map);
	
	/**
	 * 根据条件获取最后的楼层
	 * @param map
	 * @return
	 */
	Integer getLastCiFloor(Map<String, Object> map);
	
	/**
	 * 更新数量
	 * @param tevglForumCommentInfo
	 */
	int plusNum(TevglForumCommentInfo tevglForumCommentInfo);
	
	/**
	 * 查询评论列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectCommentList(Map<String, Object> map);
	
}