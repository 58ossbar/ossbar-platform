package com.ossbar.modules.sys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ossbar.common.utils.ConvertUtil;
import com.ossbar.common.utils.PageUtils;
import com.ossbar.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.sys.api.TsysParameterService;
import com.ossbar.modules.sys.domain.TsysParameter;
import com.ossbar.modules.sys.persistence.TsysParameterMapper;
import com.ossbar.utils.tool.DateUtils;
import com.ossbar.utils.tool.Identities;
import com.github.pagehelper.PageHelper;

/**
 * 参数管理接口实现类
 * 
 * @author huangwb
 * @date 2019-05-05 15:18
 */
@Service(version = "1.0.0")
@RestController
@RequestMapping("/sys/parameter")
public class TsysParameterServiceImpl implements TsysParameterService {
	@Autowired
	private TsysParameterMapper tsysParameterMapper;
	@Autowired
	private ConvertUtil convertUtil;

	/**
	 * 保存修改
	 * 
	 * @param tsysparameter
	 * @return R
	 * @author huangwb
	 * @date 2019-05-05 15:18
	 */
	@Transactional
	@PostMapping("/saveorupdate")
	@SentinelResource("/sys/parameter/saveorupdate")
	@Override
	@CacheEvict(value="parameter_cache", allEntries=true)
	public R saveorupdate(TsysParameter tsysparameter) {
		try {
			// 数据校验(无)
			if ((tsysparameter.getParaid() == null) || ("").equals(tsysparameter.getParaid())) { // 新增
				tsysparameter.setParaid(Identities.uuid());
				tsysparameter.setCreateTime(DateUtils.getNowTimeStamp());
				tsysParameterMapper.insert(tsysparameter);
			} else {
				tsysparameter.setUpdateTime(DateUtils.getNowTimeStamp());
				// tsysParameter.setUpdateUserId();(无)
				tsysParameterMapper.update(tsysparameter);
			}
			return R.ok().put("data", tsysparameter);
		} catch (Exception e) {
			return R.error(e.getMessage());
		}

	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return R
	 * @author huangwb
	 * @date 2019-05-05 15:18
	 */
	@Transactional
	@PostMapping(value = "/remove")
	@SentinelResource("/sys/parameter/remove")
	@Override
	@CacheEvict(value="parameter_cache", allEntries=true)
	public R delete(@RequestBody String[] ids) {
		tsysParameterMapper.deleteBatch(ids);
		return R.ok();
	}

	/**
	 * 所有配置列表
	 * 
	 * @param params
	 * @return R
	 * @author huangwb
	 * @date 2019-05-05 15:18
	 */
	@GetMapping("/query")
	@SentinelResource("/sys/parameter/query")
	@Override
	public R list(@RequestParam(required = false) Map<String, Object> params) {
		params.put("sidx", "update_time");
		params.put("order", "desc");
		// 构建查询条件对象Query
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		List<TsysParameter> configList = tsysParameterMapper.selectListByMap(params);
		Map<String, String> map = new HashMap<String, String>();
		map.put("displaysort", "paradisplaysort");
		map.put("isdefault", "isdefault");
		convertUtil.convertParam(configList, map);
		PageUtils pageUtil = new PageUtils(configList, query.getPage(), query.getLimit());
		return R.ok().put("data", pageUtil);
	}

	/**
	 * 根据paraType获取类型的所有参数 方便前端下拉框选择
	 * 
	 * @param paraType
	 * @return R
	 * @author huangwb
	 * @date 2019-05-29 15:18
	 */
	@GetMapping("/getPara")
	@SentinelResource("/sys/parameter/getPara")
	@Override
	@Cacheable(value="parameter_cache", key="'getPara_'+#paraType")
	public R getPara(@RequestParam(required = false) String paraType) {
		// 构建查询条件对象Query
		Map params = new HashMap();
		params.put("paraType", paraType);
		List<TsysParameter> parameters = tsysParameterMapper.selectListByMap(params);
		params.clear();
		params.put("displaysort", "paradisplaysort");
		params.put("isdefault", "isdefault");
		convertUtil.convertParam(parameters, params);
		return R.ok().put("data", parameters);
	}

	/**
	 * 获取指定的参数信息
	 * 
	 * @param parameterId
	 * @return R
	 * @author huangwb
	 * @date 2019-05-05 15:18
	 */
	@GetMapping("/get/{parameterId}")
	@SentinelResource("/sys/parameter/get")
	@Override
	public R get(@PathVariable("parameterId") String parameterId) {
		return R.ok().put("data", tsysParameterMapper.selectObjectById(parameterId));
	}

	/**
	 * 查询所有的参数
	 * 
	 * @author huangwb
	 * @date 2019-05-20 15:18
	 */
	@Override
	public R selectAllTsysParameter() {
		return R.ok().put("data", tsysParameterMapper.selectAllTsysParameter());
	}

	/**
	 * 根据key更新参数value
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @author huangwb
	 * @date 2019-05-20 15:18
	 */
	@Override
	@CacheEvict(value="parameter_cache", allEntries=true)
	public R updateValueByKey(String key, String value) {
		return R.ok().put("data", tsysParameterMapper.updateValueByKey(key, value));
	}

	/**
	 * 根据key获取value 如果key为空则给defaultValue默认值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 * @date 2019-05-20 15:18
	 * @author huangwb
	 */
	@Override
	public R getValue(String key, String defaultValue) {
		String value = tsysParameterMapper.queryByKey(key);
		if (StringUtils.isBlank(value)) {
			return R.ok().put("data", defaultValue);
		}
		return R.ok().put("data", value);
	}

	/**
	 * 根据参数管理左侧的树结构
	 * 
	 * @return R
	 * @author huangwb
	 * @date 2019-05-20 15:18
	 */
	@GetMapping("/paraTree")
	@SentinelResource("/sys/parameter/paraTree")
	@Override
	public R paraTree() {
		return R.ok().put("data", tsysParameterMapper.selectDistinctList());
	}

}
