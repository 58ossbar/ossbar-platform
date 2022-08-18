package com.ossbar.modules.sys.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.pagehelper.PageHelper;
import com.ossbar.common.cbsecurity.dataprivilege.annotation.DataFilter;
import com.ossbar.common.utils.ConvertUtil;
import com.ossbar.common.utils.PageUtils;
import com.ossbar.common.utils.Query;
import com.ossbar.common.utils.ServiceLoginUtil;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.sys.api.TsysRoleService;
import com.ossbar.modules.sys.domain.TsysRole;
import com.ossbar.modules.sys.persistence.TsysRoleMapper;
import com.ossbar.utils.constants.Constant;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author huj
 * @create 2022-08-17 11:09
 * @email hujun@creatorblue.com
 * @company 创蓝科技 www.creatorblue.com
 */
@Service(version = "1.0.0")
@RestController
@RequestMapping("/sys/role")
public class TsysRoleServiceImpl implements TsysRoleService {

    @Autowired
    private TsysRoleMapper tsysRoleMapper;
    @Autowired
    private ConvertUtil convertUtil;
    @Autowired
    private ServiceLoginUtil loginUtils;

    /**
     * 根据条件分页查询记录
     *
     * @param map
     * @return
     * @author huj
     * @data 2019年5月5日
     */
    @Override
    @RequestMapping("/query")
    @SentinelResource("/sys/role/query")
    @DataFilter(tableAlias = "o", customDataAuth = "", selfUser = false)
    public R query(Map<String, Object> map) {
        // 查询列表数据
        Query query = new Query(map);
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<TsysRole> list = tsysRoleMapper.selectListByMap(map);
        convertUtil.convertOrgId(list, "orgId");
        PageUtils pageUtil = new PageUtils(list, query.getPage(), query.getLimit());
        return R.ok().put(Constant.R_DATA, pageUtil);
    }

    /**
     * 根据角色id查询记录
     *
     * @param roleId
     * @return
     */
    @Override
    public TsysRole selectObjectByRoleId(String roleId) {
        return tsysRoleMapper.selectObjectById(roleId);
    }
}
