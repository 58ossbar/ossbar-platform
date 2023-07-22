package com.ossbar.modules.evgl.site.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ossbar.core.baseclass.domain.R;

/**
 * <p>【前端首页】接口</p>
 * <p>Title: TevglIndexService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 湖南创蓝信息科技有限公司</p> 
 * @author huj
 * @date 2019年7月3日
 */
public interface TevglIndexService {
 
	/**
	 * <p>首页（初始化部分数据）</p>
	 * @author huj
	 * @data 2019年7月4日
	 * @param params
	 * @return 返回了广告轮播图、布道师、实训故事、校企合作信息
	 */
	R index(Map<String, Object> params);
	
	/**
	 * <p>获取导航菜单</p>
	 * @author huj
	 * @data 2019年7月4日
	 * @param request
	 * @param response
	 * @return
	 */
	R getInitMenu(HttpServletRequest request, HttpServletResponse response);
}
