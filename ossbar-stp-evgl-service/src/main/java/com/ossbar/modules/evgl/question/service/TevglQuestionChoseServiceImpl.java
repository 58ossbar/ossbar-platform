package com.ossbar.modules.evgl.question.service;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ossbar.common.cbsecurity.logs.annotation.SysLog;
import com.ossbar.utils.tool.Identities;
import com.ossbar.common.utils.PageUtils;
import com.ossbar.common.utils.Query;
import com.github.pagehelper.PageHelper;
import com.ossbar.common.validator.ValidatorUtils;
import com.ossbar.utils.constants.Constant;
import com.ossbar.common.exception.OssbarException;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.evgl.question.api.TevglQuestionChoseService;
import com.ossbar.modules.evgl.question.domain.TevglQuestionChose;
import com.ossbar.modules.evgl.question.persistence.TevglQuestionChoseMapper;

/**
 * <p> Title: 题目选项</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:ossbar.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

@Service(version = "1.0.0")
@RestController
@RequestMapping("/question/tevglquestionchose")
public class TevglQuestionChoseServiceImpl implements TevglQuestionChoseService {
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(TevglQuestionChoseServiceImpl.class);
	@Autowired
	private TevglQuestionChoseMapper tevglQuestionChoseMapper;
	
	/**
	 * 查询列表(返回List<Bean>)
	 * @param Map
	 * @return R
	 */
	@SysLog(value="查询列表(返回List<Bean>)")
	@GetMapping("/query")
	@SentinelResource("/question/tevglquestionchose/query")
	public R query(@RequestParam Map<String, Object> params) {
		// 构建查询条件对象Query
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(),query.getLimit());
		List<TevglQuestionChose> tevglQuestionChoseList = tevglQuestionChoseMapper.selectListByMap(query);
		PageUtils pageUtil = new PageUtils(tevglQuestionChoseList,query.getPage(),query.getLimit());
		return R.ok().put(Constant.R_DATA, pageUtil);
	}
	
	/**
	 * 查询列表(返回List<Map<String, Object>)
	 * @param Map
	 * @return R
	 */
	@SysLog(value="查询列表(返回List<Map<String, Object>)")
	@GetMapping("/queryForMap")
	@SentinelResource("/question/tevglquestionchose/queryForMap")
	public R queryForMap(@RequestParam Map<String, Object> params) {
		// 构建查询条件对象Query
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(),query.getLimit());
		List<Map<String, Object>> tevglQuestionChoseList = tevglQuestionChoseMapper.selectListMapByMap(query);
		PageUtils pageUtil = new PageUtils(tevglQuestionChoseList,query.getPage(),query.getLimit());
		return R.ok().put(Constant.R_DATA, pageUtil);
	}
	/**
	 * 新增
	 * @param tevglQuestionChose
	 * @throws OssbarException
	 */
	@SysLog(value="新增")
	@PostMapping("save")
	@SentinelResource("/question/tevglquestionchose/save")
	public R save(@RequestBody(required = false) TevglQuestionChose tevglQuestionChose) throws OssbarException {
		tevglQuestionChose.setOptionId(Identities.uuid());
		ValidatorUtils.check(tevglQuestionChose);
		tevglQuestionChoseMapper.insert(tevglQuestionChose);
		return R.ok();
	}
	/**
	 * 修改
	 * @param tevglQuestionChose
	 * @throws OssbarException
	 */
	@SysLog(value="修改")
	@PostMapping("update")
	@SentinelResource("/question/tevglquestionchose/update")
	public R update(@RequestBody(required = false) TevglQuestionChose tevglQuestionChose) throws OssbarException {
	    ValidatorUtils.check(tevglQuestionChose);
		tevglQuestionChoseMapper.update(tevglQuestionChose);
		return R.ok();
	}
	/**
	 * 单条删除
	 * @param id
	 * @throws OssbarException
	 */
	@SysLog(value="单条删除")
	@GetMapping("delete/{id}")
	@SentinelResource("/question/tevglquestionchose/delete")
	public R delete(@PathVariable("id") String id) throws OssbarException {
		tevglQuestionChoseMapper.delete(id);
		return R.ok();
	}
	/**
	 * 批量删除
	 * @param ids
	 * @throws OssbarException
	 */
	@SysLog(value="批量删除")
	@PostMapping("deleteBatch")
	@SentinelResource("/question/tevglquestionchose/deleteBatch")
	public R deleteBatch(@RequestParam(required = true) String[] ids) throws OssbarException {
		tevglQuestionChoseMapper.deleteBatch(ids);
		return R.ok();
	}
	/**
	 * 查看明细
	 * @param id
	 * @throws OssbarException
	 */
	@SysLog(value="查看明细")
	@GetMapping("view/{id}")
	@SentinelResource("/question/tevglquestionchose/view")
	public R view(@PathVariable("id") String id) {
		return R.ok().put(Constant.R_DATA, tevglQuestionChoseMapper.selectObjectById(id));
	}

	/**
	 * <p>根据条件查询记录</p>  
	 * @author huj
	 * @data 2019年12月25日	
	 * @param map
	 * @return
	 */
	@Override
	public List<TevglQuestionChose> selectListByMap(Map<String, Object> map) {
		return tevglQuestionChoseMapper.selectListByMap(map);
	}

	/**
	 * <p>根据题目正确选项ID批量查询 </p>  
	 * @author huj
	 * @data 2019年12月30日	
	 * @param arrays
	 * @return
	 */
	@Override
	public List<TevglQuestionChose> selectBatchQuestionsChoseByReplyIds(String[] arrays) {
		return tevglQuestionChoseMapper.selectBatchQuestionsChoseByReplyIds(arrays);
	}

	@Override
	public TevglQuestionChose selectObjectById(Object id) {
		return tevglQuestionChoseMapper.selectObjectById(id);
	}
}
