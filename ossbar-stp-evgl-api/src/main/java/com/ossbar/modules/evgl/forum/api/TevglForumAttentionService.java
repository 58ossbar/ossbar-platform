package com.ossbar.modules.evgl.forum.api;

import java.util.Map;

import com.ossbar.core.baseclass.api.IBaseService;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.evgl.forum.domain.TevglForumAttention;

/**
 * <p> Title: 微社区博客粉丝关注表</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:ossbar.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

public interface TevglForumAttentionService extends IBaseService<TevglForumAttention>{
	
	/**
	 * 我的关注列表
	 * @param params
	 * @param loginUserId 当前登录用户
	 * @return
	 */
	R queryMyFollowList(Map<String, Object> params, String loginUserId);
	
	/**
	 * 我的粉丝列表
	 * @param params
	 * @param loginUserId 当前登录用户
	 * @return
	 */
	R queryMyFansList(Map<String, Object> params, String loginUserId);
	
	/**
	 * 关注某人
	 * @param traineeId 当前被关注的人
	 * @param loginUserId 当前登录用户
	 * @return
	 */
	R follow(String traineeId, String loginUserId);
	
	/**
	 * 取关某人
	 * @param traineeId 当前被取消关注的人
	 * @param loginUserId 当前登录用户
	 * @return
	 */
	R unfollow(String traineeId, String loginUserId);
	
}