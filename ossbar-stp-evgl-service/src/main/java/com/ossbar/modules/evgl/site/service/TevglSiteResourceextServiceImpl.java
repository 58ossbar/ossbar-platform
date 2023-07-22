package com.ossbar.modules.evgl.site.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ossbar.common.cbsecurity.logs.annotation.SysLog;
import com.ossbar.common.exception.OssbarException;
import com.ossbar.common.utils.ConvertUtil;
import com.ossbar.common.utils.DictUtil;
import com.ossbar.common.utils.PageUtils;
import com.ossbar.common.utils.Query;
import com.ossbar.common.utils.ServiceLoginUtil;
import com.ossbar.common.validator.ValidatorUtils;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.evgl.site.api.TevglSiteResourceextService;
import com.ossbar.modules.evgl.site.domain.TevglSiteAvd;
import com.ossbar.modules.evgl.site.domain.TevglSiteResourceext;
import com.ossbar.modules.evgl.site.persistence.TevglSiteAvdMapper;
import com.ossbar.modules.evgl.site.persistence.TevglSiteNewsMapper;
import com.ossbar.modules.evgl.site.persistence.TevglSiteResourceextMapper;
import com.ossbar.modules.evgl.site.persistence.TevglSiteSeoMapper;
import com.ossbar.modules.evgl.site.persistence.TevglsiteResourceMapper;
import com.ossbar.modules.evgl.site.vo.TsysResourceVo;
import com.ossbar.modules.sys.domain.TsysDict;
import com.ossbar.modules.sys.domain.TsysResource;
import com.ossbar.modules.sys.persistence.TsysResourceMapper;
import com.ossbar.utils.constants.Constant;
import com.ossbar.utils.tool.DateUtils;
import com.ossbar.utils.tool.Identities;
import com.ossbar.utils.tool.StrUtils;
import com.github.pagehelper.PageHelper;

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
@RequestMapping("/site/tevglsiteresourceext")
public class TevglSiteResourceextServiceImpl implements TevglSiteResourceextService {
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(TevglSiteResourceextServiceImpl.class);
	@Autowired
	private TevglSiteResourceextMapper tevglSiteResourceextMapper;
	@Autowired
	private TevglsiteResourceMapper tevglsiteResourceMapper;
	@Autowired
	private ConvertUtil convertUtil;
	@Autowired
	private TsysResourceMapper tsysResourceMapper;
	@Autowired
	private TevglSiteSeoMapper tevglSiteSeoMapper;
	@Autowired
	private ServiceLoginUtil serviceLoginUtil;
	@Autowired
	private DictUtil dictUtil;
	@Autowired
	private TevglSiteAvdMapper tevglSiteAvdMapper;
	@Autowired
	private TevglSiteNewsMapper tevglSiteNewsMapper;
	
	/**
	 * 查询列表(返回List<Bean>)
	 * @param Map
	 * @return R
	 */
	@SysLog(value="查询列表(返回List<Bean>)")
	@GetMapping("/query")
	@SentinelResource("/site/tevglsiteresourceext/query")
	public R query(@RequestParam Map<String, Object> params) {
		// 构建查询条件对象Query
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(),query.getLimit());
		List<TevglSiteResourceext> tevglSiteResourceextList = tevglSiteResourceextMapper.selectListByMap(query);
		PageUtils pageUtil = new PageUtils(tevglSiteResourceextList,query.getPage(),query.getLimit());
		return R.ok().put(Constant.R_DATA, pageUtil);
	}
	
	/**
	 * 查询列表(返回List<Map<String, Object>)
	 * @param Map
	 * @return R
	 */
	@SysLog(value="查询列表(返回List<Map<String, Object>)")
	@GetMapping("/queryForMap")
	@SentinelResource("/site/tevglsiteresourceext/queryForMap")
	public R queryForMap(@RequestParam Map<String, Object> params) {
		// 构建查询条件对象Query
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(),query.getLimit());
		List<Map<String, Object>> tevglSiteResourceextList = tevglSiteResourceextMapper.selectListMapByMap(query);
		PageUtils pageUtil = new PageUtils(tevglSiteResourceextList,query.getPage(),query.getLimit());
		return R.ok().put(Constant.R_DATA, pageUtil);
	}
	/**
	 * 新增
	 * @param tevglSiteResourceext
	 * @throws OssbarException
	 */
	@SysLog(value="新增")
	@PostMapping("save")
	@SentinelResource("/site/tevglsiteresourceext/save")
	public R save(@RequestBody(required = false) TevglSiteResourceext tevglSiteResourceext) throws OssbarException {
		tevglSiteResourceext.setSiteId(Identities.uuid());
		ValidatorUtils.check(tevglSiteResourceext);
		tevglSiteResourceextMapper.insert(tevglSiteResourceext);
		return R.ok();
	}
	/**
	 * 修改
	 * @param tevglSiteResourceext
	 * @throws OssbarException
	 */
	@SysLog(value="修改")
	@PostMapping("update")
	@SentinelResource("/site/tevglsiteresourceext/update")
	public R update(@RequestBody(required = false) TevglSiteResourceext tevglSiteResourceext) throws OssbarException {
	    ValidatorUtils.check(tevglSiteResourceext);
		tevglSiteResourceextMapper.update(tevglSiteResourceext);
		return R.ok();
	}
	/**
	 * 单条删除
	 * @param id
	 * @throws OssbarException
	 */
	@SysLog(value="单条删除")
	@GetMapping("delete/{id}")
	@SentinelResource("/site/tevglsiteresourceext/delete")
	public R delete(@PathVariable("id") String id) throws OssbarException {
		tevglSiteResourceextMapper.delete(id);
		return R.ok();
	}
	/**
	 * 批量删除
	 * @param ids
	 * @throws OssbarException
	 */
	@SysLog(value="批量删除")
	@PostMapping("deleteBatch")
	@SentinelResource("/site/tevglsiteresourceext/deleteBatch")
	public R deleteBatch(@RequestBody(required = true) String[] ids) throws OssbarException {
		tevglSiteResourceextMapper.deleteBatch(ids);
		return R.ok();
	}
	/**
	 * 查看明细
	 * @param id
	 * @throws OssbarException
	 */
	@SysLog(value="查看明细")
	@GetMapping("view/{id}")
	@SentinelResource("/site/tevglsiteresourceext/view")
	public R view(@PathVariable("id") String id) {
		return R.ok().put(Constant.R_DATA, tevglSiteResourceextMapper.selectObjectById(id));
	}
	
	/**
	 * <p>查询所有站点及栏目列表</p>
	 * @author huj
	 * @data 2019年7月12日
	 * @param menuId
	 * @return
	 */
	@Override
	@SysLog(value="查询所有站点及栏目列表")
	@GetMapping("/querySite")
	@SentinelResource("/site/tevglsiteresourceext/querySite")
	public List<TsysResourceVo> querySite(String menuId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", "order_num");
		map.put("order", "asc");
		List<TsysResourceVo> menuList = (menuId == null)? 
				tevglsiteResourceMapper.selectSiteListVoByMap(map)
				:tevglsiteResourceMapper.selectSiteListVoParentId(menuId);
		menuList.stream().forEach(item -> {
			Integer num = tevglSiteNewsMapper.countNewsNumByMenuId(item.getMenuId());
			item.setNum(num);
		});	
		// 构建树形数据 门户中心:b43e1950c19d40deb8b9b701ffec5f65
		//List<TsysResource> list = build("b43e1950c19d40deb8b9b701ffec5f65", menuList, 0);
		return menuList;
	}

	/**
	 * <p>构建树形数据</p>
	 * @author huj
	 * @data 2019年7月15日
	 * @param parentId 父ID
	 * @param allList 带构建的数据
	 * @param level 级别
	 * @return
	 */
	public List<TsysResource> build(String parentId, List<TsysResource> allList, int level){
		if (allList == null || allList.size() == 0) {
			return new ArrayList<>();
		}
		List<TsysResource> parentNode = allList.stream().filter(a -> a.getParentId().equals(parentId)).collect(Collectors.toList());
		if (parentNode.size() > 0) {
			level ++; // level计算当前处于第几级
			final int level2 = level;
			parentNode.forEach(a -> {
				a.setLevel(level2);
				a.setChildren(build(a.getMenuId(), allList, level2));
			});
		}
		return parentNode;
	}
	
	/**
	 * <p>查看站点信息</p>
	 * @author huj
	 * @data 2019年7月15日
	 * @param id
	 * @return
	 */
	@Override
	@GetMapping("/viewSite/{id}")
	@SysLog(value="查询站点信息")
	public TsysResource viewSite(@PathVariable("id") String id) {
		TsysResource menu = tsysResourceMapper.selectObjectById(id);
		convertUtil.convertOrgId(menu, "orgId");
		List<TsysResource> menuList = tevglsiteResourceMapper.selectSiteListByMap(new HashMap<String, Object>());
		Map<String, TsysResource> map = new HashMap<String, TsysResource>();
		for (TsysResource res : menuList) {
			map.put(res.getMenuId(), res);
		}
		convertUtil.convertBean(menu, map, "name", "parentId");
		return menu;
	}

	/**
	 * <p>新增站点栏目</p>
	 * @author huj
	 * @data 2019年7月16日
	 * @param menu
	 * @return
	 */
	@Override
	@SysLog(value="新增站点栏目")
	@PostMapping("/saveOrUpdateSite")
	public R saveOrUpdateSite(@RequestBody(required = false) TsysResource menu) {
		verifyForm(menu);
		if(menu.getMenuId()==null || menu.getMenuId().length()==0){
			menu.setMenuId(Identities.uuid());
			menu.setCreateTime(DateUtils.getNowTimeStamp());
			menu.setCreateUserId(serviceLoginUtil.getLoginUserId());
			tsysResourceMapper.insert(menu);
			TevglSiteResourceext tevglSiteResourceext = new TevglSiteResourceext();
			tevglSiteResourceext.setMenuId(menu.getMenuId());
			tevglSiteResourceext.setSiteId(Identities.uuid());
			tevglSiteResourceextMapper.insert(tevglSiteResourceext);
		} else{
			menu.setUpdateUserId(serviceLoginUtil.getLoginUserId());
			menu.setUpdateTime(DateUtils.getNowTimeStamp());
			tsysResourceMapper.update(menu);
		}
		return R.ok().put("menuId", menu.getMenuId()).put("row", tsysResourceMapper.selectObjectById(menu.getParentId()));
	}

	/**
	 * <p>删除站点</p>
	 * @author huj
	 * @data 2019年7月16日
	 * @param id
	 * @return
	 */
	@Override
	public R deleteSite(String id) {
		// 判断是否有子菜单或按钮
		List<TsysResource> menuList = tevglsiteResourceMapper.selectSiteListParentId(id);
		if(menuList.size() > 0){
			return R.error("请先删除子站点或栏目");
		}
		// 判断是否有栏目图片
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuId", id);
		List<TevglSiteAvd> avdList = tevglSiteAvdMapper.selectListByMap(map);
		if (avdList.size() > 0 && avdList != null) {
			return R.error("该栏目下还存在栏目图片，请先去删除对应的栏目图片");
		}
		doDeleteSite(id);
		return R.ok();
	}
	
	/**
	 * <p>实际删除站点的方法</p>
	 * @author huj
	 * @data 2019年7月16日
	 * @param id
	 * @throws OssbarException
	 */
	public void doDeleteSite(String id) throws OssbarException {
		TevglSiteResourceext tevglSiteResourceext = tevglSiteResourceextMapper.selectObjectByMenuId(id);
		if (tevglSiteResourceext != null){
			// 删除站点拓展信息
			tevglSiteResourceextMapper.delete(tevglSiteResourceext.getSiteId());
			// 删除站点SEO
			tevglSiteSeoMapper.deleteByRelationId(tevglSiteResourceext.getSiteId());
		} else {
			// 删除栏目SEO
			tevglSiteSeoMapper.deleteByRelationId(id);
		}
		//删除站点资源
		tsysResourceMapper.deleteBatchRolPri(new String[]{id});
		tsysResourceMapper.deleteBatchRes(new String[]{id});
	}

	/**
	 * <p>获取类型,如【站点】【栏目】</p>
	 * @author huj
	 * @data 2019年7月16日
	 * @return
	 */
	@Override
	public R getSiteType() {
		List<TsysDict> list = dictUtil.getByType("siteType");
		return R.ok().put(Constant.R_DATA, list);
	}
	
	/**
	 * 验证站点参数是否正确
	 */
	private void verifyForm(TsysResource menu){
		
		if(StringUtils.isBlank(menu.getName())){
			throw new OssbarException("名称不能为空");
		}

		// 同级结点，不能允许有同名称的栏目，要做栏目名称唯一性校验
		Map<String, Object> map = new HashMap<>();
		map.put("parentId", menu.getParentId());
		if (StrUtils.isEmpty(menu.getMenuId())) { // 新增
			List<TsysResource> list = tsysResourceMapper.selectListParentId2(menu.getParentId());
			if (list.size() > 0) {
				list.forEach(a -> {
					if (a.getName().equals(menu.getName())) {
						throw new OssbarException("名称已存在,同级节点不允许有相同的名称");
					}
				});
				return ;
			}
		} else {
			TsysResource resource = tsysResourceMapper.selectObjectById(menu.getMenuId());
			if (resource != null) {
				if (!menu.getName().equals(resource.getName())) {
					List<TsysResource> list = tsysResourceMapper.selectListParentId2(menu.getParentId());
					if (list.size() > 0) {
						list.forEach(a -> {
							if (a.getName().equals(menu.getName()) && !a.getMenuId().equals(menu.getMenuId())) {
								throw new OssbarException("名称已存在,同级节点不允许有相同的名称");
							}
						});
						return ;
					}
				}
			}
		}
		
		//上级菜单类型(3站点4栏目)
		int parentType = -1;
		if(menu.getParentId().length()>0 && !"0".equals(menu.getParentId())){
			TsysResource parentMenu = tsysResourceMapper.selectObjectById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		//站点
		if(menu.getType() == 3){
			if(parentType == 4){
				throw new OssbarException("父级节点只能为站点类型或者为空");
			}
			return ;
		}
		//栏目
		if(menu.getType() == 4){
			if(parentType != 3 && parentType != 4 || parentType==-1){
				throw new OssbarException("父级节点只能为站点或者栏目类型");
			}
			return ;
		}
		
	}

	/**
	 * <p>查看明细</p>
	 * @author huj
	 * @data 2019年7月17日
	 * @param id
	 * @return
	 */
	@Override
	public TsysResource editSite(String id) {
		return tsysResourceMapper.selectObjectById(id);
	}

	/**
	 * <p>获取站点行业类型,如IT互联网、汽车等</p>
	 * @author huj
	 * @data 2019年7月20日
	 * @return
	 */
	@Override
	public List<TsysDict> getTradeType() {
		List<TsysDict> list = dictUtil.getByType("tradeType");
		return list;
	}
	
}
