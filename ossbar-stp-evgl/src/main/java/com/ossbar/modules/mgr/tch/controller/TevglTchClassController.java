package com.ossbar.modules.mgr.tch.controller;

import com.ossbar.common.cbsecurity.logs.annotation.SysLog;
import com.ossbar.common.utils.Query;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.common.DictService;
import com.ossbar.modules.evgl.tch.api.TevglTchClassService;
import com.ossbar.modules.evgl.tch.api.TevglTchTeacherService;
import com.ossbar.modules.evgl.tch.domain.TevglTchClass;
import com.ossbar.modules.sys.domain.TsysDict;
import com.ossbar.utils.constants.Constant;
import com.ossbar.utils.tool.StrUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p> Title: 班级管理</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:creatorblue.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

@RestController
@RequestMapping("/api/tch/tevgltchclass")
public class TevglTchClassController {
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(TevglTchClassController.class);
	@Reference(version = "1.0.0")
	private TevglTchClassService tevglTchClassService;
	@Reference(version = "1.0.0")
	private DictService dictService;
	@Value("${com.creatorblue.file-access-path}")
	public String creatorblueFieAccessPath;
	@Reference(version = "1.0.0")
	private TevglTchTeacherService tevglTchTeacherService;
	
	
	/**
	 * 查询列表(返回List<Bean>)
	 * @param params
	 * @return R
	 */
	@GetMapping("/query")
	@PreAuthorize("hasAuthority('tch:tevgltchclass:query')")
	@SysLog("查询列表(返回List<Bean>)")
	public R query(@RequestParam Map<String, Object> params) {
		return tevglTchClassService.query(params);
	}
	/**
	 * 查询列表(返回List<Map<String, Object>)
	 * @param params
	 * @return R
	 */
	@GetMapping("/queryForMap")
	@PreAuthorize("hasAuthority('tch:tevgltchclass:query')")
	@SysLog("查询列表(返回List<Map<String, Object>)")
	public R queryForMap(@RequestParam Map<String, Object> params) {
		return tevglTchClassService.queryForMap(params);
	}

	/**
	 * 查看明细
	 */
	@GetMapping("/view/{id}")
	@PreAuthorize("hasAuthority('tch:tevgltchclass:view')")
	@SysLog("查看明细")
	public R view(@PathVariable("id") String id) {
		return tevglTchClassService.view(id);
	}
	
	/**
	 * 执行数据新增
	 * 
	 */
	@PostMapping("/saveorupdate")
	@PreAuthorize("hasAuthority('tch:tevgltchclass:add') or hasAuthority('tch:tevgltchclass:edit')")
	@SysLog("执行数据新增")
	public R saveOrUpdate(@RequestBody(required = false) TevglTchClass tevglTchClass) {
		if(StrUtils.isEmpty(tevglTchClass.getClassId())) { //新增
			return tevglTchClassService.save(tevglTchClass);
		} else {
			return tevglTchClassService.update(tevglTchClass);
		}
	}
	
	/**
	 * 单条删除
	 */
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('tch:tevgltchclass:delete')")
	@SysLog("单条删除")
	public R delete(@PathVariable("id") String id) {
		return tevglTchClassService.delete(id);
	}
	
	/**
	 * 批量删除
	 */
	@PostMapping("/deletes")
	@PreAuthorize("hasAuthority('tch:tevgltchclass:delete')")
	@SysLog("批量删除")
	public R deleteBatch(@RequestBody(required = true) String[] ids) {
		return tevglTchClassService.deleteBatch(ids);
	}
	
	/**
	 * <p>从字典获取班级缩略图</p>  
	 * @author huj
	 * @data 2019年8月19日	
	 * @return
	 */
	@GetMapping("/getClassPic")
	public R getClassPic() {
		List<TsysDict> list = dictService.getTsysDictListByType("classPic");
		if (list.size() > 0 && list != null) {
			// 根据排序号自然顺序
			list.stream().sorted(Comparator.comparing(TsysDict::getSortNum)).collect(Collectors.toList());
			list.forEach(a -> {
				// 业务基础平台中固定了dict。暂未改动，所有
				a.setDictUrl(creatorblueFieAccessPath + "/dict/" + a.getDictUrl());
			});
		}
		return R.ok().put(Constant.R_DATA, list);
	}
	
	/**
	 * <p>获取助教</p>  
	 * @author huj
	 * @data 2019年8月20日	
	 * @param map
	 * @return
	 */
	@GetMapping("/getTeachingAssistant")
	public R getTeachingAssistant(@RequestParam Map<String, Object> map) {
		Query query = new Query(map);
		return R.ok().put(Constant.R_DATA, tevglTchTeacherService.selectListByMap(query));
	}
	
	/**
	 * 班级列表
	 * @param params
	 * @return
	 */
	@GetMapping("/listClassTrainee")
	public R listClassTrainee(@RequestParam Map<String, Object> params) {
		return R.ok().put(Constant.R_DATA, tevglTchClassService.selectSimpleListMap(params));
	}
	
	/**
	 * 获取班级树
	 * @param params
	 * @return
	 */
	@GetMapping("getClassTree")
	public R getClassTree(@RequestParam Map<String, Object> params) {
		return tevglTchClassService.getClassTree(params);
	}
	
	@GetMapping("findClassList")
	public R findClassList(@RequestParam Map<String, Object> params) {
		return R.ok().put(Constant.R_DATA, tevglTchClassService.selectListByMap(params));
	}
}
