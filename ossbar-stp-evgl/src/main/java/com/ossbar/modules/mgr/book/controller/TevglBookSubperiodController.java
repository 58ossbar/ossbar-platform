package com.ossbar.modules.mgr.book.controller;

import com.ossbar.common.cbsecurity.logs.annotation.SysLog;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.evgl.book.api.TevglBookSubperiodService;
import com.ossbar.modules.evgl.book.domain.TevglBookSubperiod;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * <p> Title: 专业课程（职业课程）关系</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:creatorblue.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

@RestController
@RequestMapping("/api/book/tevglbooksubperiod")
public class TevglBookSubperiodController {
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(TevglBookSubperiodController.class);
	@Reference(version = "1.0.0")
	private TevglBookSubperiodService tevglBookSubperiodService;
	/**
	 * 查询列表(返回List<Bean>)
	 * @param params
	 * @return R
	 */
	@GetMapping("/query")
	@PreAuthorize("hasAuthority('book:tevglbooksubperiod:query')")
	@SysLog("查询列表(返回List<Bean>)")
	public R query(@RequestParam Map<String, Object> params) {
		return tevglBookSubperiodService.query(params);
	}
	/**
	 * 查询列表(返回List<Map<String, Object>)
	 * @param params
	 * @return R
	 */
	@GetMapping("/queryForMap")
	@PreAuthorize("hasAuthority('book:tevglbooksubperiod:query')")
	@SysLog("查询列表(返回List<Map<String, Object>)")
	public R queryForMap(@RequestParam Map<String, Object> params) {
		return tevglBookSubperiodService.queryForMap(params);
	}

	/**
	 * 查看明细
	 */
	@GetMapping("/view/{id}")
	@PreAuthorize("hasAuthority('book:tevglbooksubperiod:view')")
	@SysLog("查看明细")
	public R view(@PathVariable("id") String id) {
		return tevglBookSubperiodService.view(id);
	}
	
	/**
	 * 执行数据新增
	 * 
	 */
	@PostMapping("/saveorupdate")
	@PreAuthorize("hasAuthority('book:tevglbooksubperiod:add') or hasAuthority('book:tevglbooksubperiod:edit')")
	@SysLog("执行数据新增")
	public R saveOrUpdate(@RequestBody(required = false) TevglBookSubperiod tevglBookSubperiod) {
		return R.ok();
	}
	
	/**
	 * 单条删除
	 */
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('book:tevglbooksubperiod:delete')")
	@SysLog("单条删除")
	public R delete(@PathVariable("id") String id) {
		return tevglBookSubperiodService.delete(id);
	}
	
	/**
	 * 批量删除
	 */
	@PostMapping("/deletes")
	@PreAuthorize("hasAuthority('book:tevglbooksubperiod:delete')")
	@SysLog("批量删除")
	public R deleteBatch(@RequestBody(required = true) String[] ids) {
		return tevglBookSubperiodService.deleteBatch(ids);
	}
}
