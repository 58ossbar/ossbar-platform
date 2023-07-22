package com.ossbar.modules.evgl.site.api;

import com.ossbar.modules.evgl.site.domain.TevglSitePartner;
import com.ossbar.core.baseclass.api.IBaseService;
import com.ossbar.core.baseclass.domain.R;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:ossbar.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

public interface TevglSitePartnerService extends IBaseService<TevglSitePartner>{
	
	/**
	 * <p>新增</p>
	 * @author huj
	 * @data 2019��7��26��
	 * @param tevglSitePartner
	 * @param attachId
	 * @return
	 */
	R save2(TevglSitePartner tevglSitePartner, String attachId);
	
	/**
	 * <p>修改</p>
	 * @author huj
	 * @data 2019��7��26��
	 * @param tevglSitePartner
	 * @param attachId
	 * @return
	 */
	R update2(TevglSitePartner tevglSitePartner, String attachId);
	
	/**
	 * <p>更新状态̬</p>
	 * @author huj
	 * @data 2019��7��28��
	 * @param tevglSitePartner
	 * @return
	 */
	R updateState(TevglSitePartner tevglSitePartner);
}