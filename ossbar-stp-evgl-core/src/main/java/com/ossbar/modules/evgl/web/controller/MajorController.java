package com.ossbar.modules.evgl.web.controller;

import java.util.Map;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.evgl.book.api.TevglBookMajorService;
import com.ossbar.utils.constants.Constant;

/**
 * <p>专业</p>
 * <p>Title: MajorController.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 湖南创蓝信息科技有限公司</p> 
 * @author huj
 * @date 2019年7月11日
 */
@RestController
@RequestMapping("major-api")
public class MajorController {

	@Reference(version = "1.0.0")
	private TevglBookMajorService tevglBookMajorService;
	
	/**
	 * <p>查询列表</p>
	 * @author huj
	 * @data 2019年7月12日
	 * @param parmas
	 * @return
	 */
	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> parmas) {
		parmas.put("state", "Y"); // 状态(Y有效N无效)
		return tevglBookMajorService.selectListByMapForWeb(parmas);
	}
	
	/**
	 * <p>查询所有的职业课程路径(无分页)</p>
	 * @author huj
	 * @data 2019年7月12日
	 * @param parmas
	 * @return
	 */
	@GetMapping("/queryAll")
	public R queryAll(@RequestParam Map<String, Object> parmas) {
		return R.ok().put(Constant.R_DATA, tevglBookMajorService.queryAll(parmas));
	}
	
	/**
	 * <p>查看明细</p>
	 * @author huj
	 * @data 2019年7月12日
	 * @param id
	 * @return
	 */
	@GetMapping("/view/{id}")
	public R view(@PathVariable("id") String id) {
		return tevglBookMajorService.view(id);
	}
	
}
