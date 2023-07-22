package com.ossbar.modules.evgl.activity.service;

import java.util.List;
import java.util.Map;
import com.ossbar.common.utils.ConvertUtil;
import com.ossbar.utils.tool.DateUtils;
import com.ossbar.common.utils.ServiceLoginUtil;
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
import com.ossbar.modules.evgl.activity.api.TevglActivityTaskTraineeAnswerFileService;
import com.ossbar.modules.evgl.activity.domain.TevglActivityTaskTraineeAnswerFile;
import com.ossbar.modules.evgl.activity.persistence.TevglActivityTaskTraineeAnswerFileMapper;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:ossbar.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

@Service(version = "1.0.0")
@RestController
@RequestMapping("/activity/tevglactivitytasktraineeanswerfile")
public class TevglActivityTaskTraineeAnswerFileServiceImpl implements TevglActivityTaskTraineeAnswerFileService {
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(TevglActivityTaskTraineeAnswerFileServiceImpl.class);
	@Autowired
	private TevglActivityTaskTraineeAnswerFileMapper tevglActivityTaskTraineeAnswerFileMapper;
	@Autowired
	private ConvertUtil convertUtil;
	@Autowired
	private ServiceLoginUtil serviceLoginUtil;
	
	/**
	 * 查询列表(返回List<Bean>)
	 * @param Map
	 * @return R
	 */
	@SysLog(value="查询列表(返回List<Bean>)")
	@GetMapping("/query")
	@SentinelResource("/activity/tevglactivitytasktraineeanswerfile/query")
	public R query(@RequestParam Map<String, Object> params) {
		// 构建查询条件对象Query
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(),query.getLimit());
		List<TevglActivityTaskTraineeAnswerFile> tevglActivityTaskTraineeAnswerFileList = tevglActivityTaskTraineeAnswerFileMapper.selectListByMap(query);
		convertUtil.convertUserId2RealName(tevglActivityTaskTraineeAnswerFileList, "createUserId", "updateUserId");
		convertUtil.convertUserId2RealName(tevglActivityTaskTraineeAnswerFileList, "createUserId", "updateUserId");
		PageUtils pageUtil = new PageUtils(tevglActivityTaskTraineeAnswerFileList,query.getPage(),query.getLimit());
		return R.ok().put(Constant.R_DATA, pageUtil);
	}
	
	/**
	 * 查询列表(返回List<Map<String, Object>)
	 * @param Map
	 * @return R
	 */
	@SysLog(value="查询列表(返回List<Map<String, Object>)")
	@GetMapping("/queryForMap")
	@SentinelResource("/activity/tevglactivitytasktraineeanswerfile/queryForMap")
	public R queryForMap(@RequestParam Map<String, Object> params) {
		// 构建查询条件对象Query
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(),query.getLimit());
		List<Map<String, Object>> tevglActivityTaskTraineeAnswerFileList = tevglActivityTaskTraineeAnswerFileMapper.selectListMapByMap(query);
		convertUtil.convertUserId2RealName(tevglActivityTaskTraineeAnswerFileList, "create_user_id", "update_user_id");
		PageUtils pageUtil = new PageUtils(tevglActivityTaskTraineeAnswerFileList,query.getPage(),query.getLimit());
		return R.ok().put(Constant.R_DATA, pageUtil);
	}
	/**
	 * 新增
	 * @param tevglActivityTaskTraineeAnswerFile
	 * @throws OssbarException
	 */
	@SysLog(value="新增")
	@PostMapping("save")
	@SentinelResource("/activity/tevglactivitytasktraineeanswerfile/save")
	public R save(@RequestBody(required = false) TevglActivityTaskTraineeAnswerFile tevglActivityTaskTraineeAnswerFile) throws OssbarException {
		tevglActivityTaskTraineeAnswerFile.setFileId(Identities.uuid());
		tevglActivityTaskTraineeAnswerFile.setCreateUserId(serviceLoginUtil.getLoginUserId());
		tevglActivityTaskTraineeAnswerFile.setCreateTime(DateUtils.getNowTimeStamp());
		ValidatorUtils.check(tevglActivityTaskTraineeAnswerFile);
		tevglActivityTaskTraineeAnswerFileMapper.insert(tevglActivityTaskTraineeAnswerFile);
		return R.ok();
	}
	/**
	 * 修改
	 * @param tevglActivityTaskTraineeAnswerFile
	 * @throws OssbarException
	 */
	@SysLog(value="修改")
	@PostMapping("update")
	@SentinelResource("/activity/tevglactivitytasktraineeanswerfile/update")
	public R update(@RequestBody(required = false) TevglActivityTaskTraineeAnswerFile tevglActivityTaskTraineeAnswerFile) throws OssbarException {
	    tevglActivityTaskTraineeAnswerFile.setUpdateUserId(serviceLoginUtil.getLoginUserId());
	    tevglActivityTaskTraineeAnswerFile.setUpdateTime(DateUtils.getNowTimeStamp());
	    ValidatorUtils.check(tevglActivityTaskTraineeAnswerFile);
		tevglActivityTaskTraineeAnswerFileMapper.update(tevglActivityTaskTraineeAnswerFile);
		return R.ok();
	}
	/**
	 * 单条删除
	 * @param id
	 * @throws OssbarException
	 */
	@SysLog(value="单条删除")
	@GetMapping("delete/{id}")
	@SentinelResource("/activity/tevglactivitytasktraineeanswerfile/delete")
	public R delete(@PathVariable("id") String id) throws OssbarException {
		tevglActivityTaskTraineeAnswerFileMapper.delete(id);
		return R.ok();
	}
	/**
	 * 批量删除
	 * @param ids
	 * @throws OssbarException
	 */
	@SysLog(value="批量删除")
	@PostMapping("deleteBatch")
	@SentinelResource("/activity/tevglactivitytasktraineeanswerfile/deleteBatch")
	public R deleteBatch(@RequestParam(required = true) String[] ids) throws OssbarException {
		tevglActivityTaskTraineeAnswerFileMapper.deleteBatch(ids);
		return R.ok();
	}
	/**
	 * 查看明细
	 * @param id
	 * @throws OssbarException
	 */
	@SysLog(value="查看明细")
	@GetMapping("view/{id}")
	@SentinelResource("/activity/tevglactivitytasktraineeanswerfile/view")
	public R view(@PathVariable("id") String id) {
		return R.ok().put(Constant.R_DATA, tevglActivityTaskTraineeAnswerFileMapper.selectObjectById(id));
	}

	/**
	 * 批量新增
	 *
	 * @param list
	 */
	@Override
	@SysLog(value="批量新增")
	public void insertBatch(List<TevglActivityTaskTraineeAnswerFile> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		tevglActivityTaskTraineeAnswerFileMapper.insertBatch(list);
	}
}
