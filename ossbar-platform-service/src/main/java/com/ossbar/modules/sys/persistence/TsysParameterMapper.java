package com.ossbar.modules.sys.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
import com.ossbar.core.baseclass.persistence.BaseSqlMapper;
import com.ossbar.modules.sys.domain.TsysParameter;

/**
 * Title: 系统参数配置 Description: Copyright: Copyright (c) 2017
 * Company:ossbar.co.,ltd
 * 
 * @author ossbar.co.,ltd
 * @version 1.0
 */
@Mapper
public interface TsysParameterMapper extends BaseSqlMapper<TsysParameter> {

	/**
	 * 根据key，查询value
	 */
	@Cacheable(value="parameter_cache", key="'queryByKey_'+#paramKey")
	String queryByKey(String paramKey);

	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
	@Cacheable(value="parameter_cache")
	public List<TsysParameter> selectAllTsysParameter();

	/**
	 * 去除重复的参数名称
	 * 
	 * @param parameterId
	 * @return R
	 * @author huangwb
	 * @date 2019-05-24 15:18
	 */
	List<TsysParameter> selectDistinctList();

}
