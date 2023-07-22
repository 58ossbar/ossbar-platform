package com.ossbar.modules.sys.api;

import java.util.List;
import java.util.Map;

import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.sys.domain.TorgUser;

/**
 * <p>机构用户api</p>
 * <p>Title: TorgUserService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 湖南创蓝信息科技有限公司</p> 
 * @author huj
 * @date 2019年5月13日
 */
public interface TorgUserService {

	/**
	 * <p>根据条件查询记录</p>
	 * @author huj
	 * @data 2019年5月13日
	 * @param map
	 * @return
	 */
	List<TorgUser> selectListByMap(Map<String, Object> map);
	
	/**
	 * <p>修改</p>
	 * @author huj
	 * @data 2019年5月13日
	 * @param torgUser
	 * @return
	 */
	R update(TorgUser torgUser);
	
	/**
	 * <p>删除</p>
	 * @author huj
	 * @data 2019年5月13日
	 * @param id
	 * @return
	 */
	R delete(String id);
	
	/**
	 * <p>批量删除</p>
	 * @author huj
	 * @data 2019年5月13日
	 * @param ids
	 * @return
	 */
	R deleteBatch(String[] ids);
	
	/**
	 * <p>查看明细</p>
	 * @author huj
	 * @data 2019年5月13日
	 * @param id
	 * @return
	 */
	R selectObjectById(String id);
	
	R saveOrUpdate(String userId, List<String> orgIdList);
	
	int selectTotalByMap(Map<String,Object> map);
	
	/**
	 * <p>根据用户ID查询记录</p>
	 * @author huj
	 * @data 2019年5月13日
	 * @param userId
	 * @return
	 */
	List<String> selectListByUserId(String userId);
	
}
