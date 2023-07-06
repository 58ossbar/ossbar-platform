package com.ossbar.modules.evgl.medu.persistence;

import com.ossbar.core.baseclass.persistence.BaseSqlMapper;
import com.ossbar.modules.evgl.medu.domain.TmeduApiToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2017 </p>
 * <p> Company:creatorblue.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

@Mapper
public interface TmeduApiTokenMapper extends BaseSqlMapper<TmeduApiToken> {
	
	TmeduApiToken selectTokenByUserId(String userId);

    TmeduApiToken selectTokenByToken(String token);
    
    TmeduApiToken selectTokenByMobile(@Param("mobile") String mobile, @Param("userType") String userType);
    
    TmeduApiToken selectTokenByopenid(@Param("openid") String openid, @Param("userType") String userType);
    
    void updateByMobile(TmeduApiToken token);
    
    void updateByOpenid(TmeduApiToken token);
    
    void deleteTokenById(@Param("mobile") String mobile, @Param("userType") String userType);
}