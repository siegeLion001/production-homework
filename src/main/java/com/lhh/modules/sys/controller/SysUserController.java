package com.lhh.modules.sys.controller;


import com.lhh.common.annotation.SysLog;
import com.lhh.modules.sys.entity.UserVo;
import com.lhh.modules.sys.shiro.ShiroUtils;
import com.lhh.modules.sys.entity.SysUserEntity;
import com.lhh.common.utils.PageUtils;
import com.lhh.common.utils.Query;
import com.lhh.common.utils.R;
import com.lhh.common.validator.Assert;
import com.lhh.common.validator.ValidatorUtils;
import com.lhh.common.validator.group.AddGroup;
import com.lhh.common.validator.group.UpdateGroup;
import com.lhh.modules.sys.service.SysUserRoleService;
import com.lhh.modules.sys.service.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author ma.zd
 * @email mazengdi@lanhaihui.net
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysUserEntity> userList = sysUserService.queryList(query);
        int total = sysUserService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public R info() {
        return R.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @RequestMapping("/password")
    public R password(String password, String newPassword) {
        Assert.isBlank(newPassword, "新密码不为能空");

        //原密码
        password = ShiroUtils.sha256(password, getUser().getSalt());
        //新密码
        newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());

        //更新密码
        int count = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (count == 0) {
            return R.error("原密码不正确");
        }

        return R.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("userId") Long userId) {
        SysUserEntity user = sysUserService.queryObject(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return R.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    public R save(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);
        sysUserService.save(user);

        return R.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public R update(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        sysUserService.update(user);

        return R.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public R delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return R.error("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return R.error("当前用户不能删除");
        }

        sysUserService.deleteBatch(userIds);

        return R.ok();
    }

    /**
     * 管理员录入
     */
    @SysLog("管理员录入")
    @RequestMapping("/userEnter")
    @RequiresPermissions("sys:user:save")
    public R userEnter(@RequestBody UserVo userVo) {

        //  userVo 转 user
        SysUserEntity user = new SysUserEntity();
        Long roleId = userVo.getRoleId();
        BeanUtils.copyProperties(userVo, user);
        List<Long> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        user.setRoleIdList(roleIdList);

        user.setDeptId(1L);
        user.setDeptName("人人开源集团");
        user.setEmail("root@vt.com");
        user.setStatus(1);
        ValidatorUtils.validateEntity(user, AddGroup.class);
        sysUserService.save(user);
        return R.ok().put("shift", user.getShift());
    }

    /**
     * 管理员修改
     */
    @SysLog("修改用户")
    @RequestMapping("/userUpdate")
    @RequiresPermissions("sys:user:update")
    public R userUpdate(@RequestBody UserVo userVo) {


        //  userVo 转 user
        SysUserEntity user = new SysUserEntity();
        Long roleId = userVo.getRoleId();
        BeanUtils.copyProperties(userVo, user);
        List<Long> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        user.setRoleIdList(roleIdList);


        // 先查询 设置 passwd
        user.setDeptId(1L);
        user.setDeptName("人人开源集团");
        user.setEmail("root@vt.com");
        ValidatorUtils.validateEntity(user, AddGroup.class);
        user.setPassword(null);

        sysUserService.update(user);
        return R.ok().put("shift", user.getShift());
    }

    /**
     * 管理员修改
     */
    @SysLog("修改用户")
    @RequestMapping("/userUpdatePasswd")
    @RequiresPermissions("sys:user:update")
    public R userUpdatePasswd(@RequestBody UserVo userVo) {

        //  userVo 转 user
        SysUserEntity user = new SysUserEntity();
        Long roleId = userVo.getRoleId();
        BeanUtils.copyProperties(userVo, user);
        List<Long> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        user.setRoleIdList(roleIdList);


        user.setDeptId(1L);
        user.setDeptName("人人开源集团");
        user.setEmail("root@vt.com");
        ValidatorUtils.validateEntity(user, AddGroup.class);

        // 先查询 设置 passwd


        String newPassword = user.getPassword();
        String newPasswordSha = ShiroUtils.sha256(newPassword, getUser().getSalt());

        SysUserEntity sysUserEntity = sysUserService.queryObject(user.getUserId());
        String password = sysUserEntity.getPassword();

        //更新密码
        int count = sysUserService.updatePassword(getUserId(), password, newPasswordSha);
        if (count == 0) {
            return R.error("原密码不正确");
        }
        user.setPassword(null);


        sysUserService.update(user);
        return R.ok().put("shift", user.getShift());
    }


    /**
     * 所有用户列表
     */
    @RequestMapping("/userList")
    @RequiresPermissions("sys:user:list")
    public R userList(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysUserEntity> userList = sysUserService.queryList(query);
        int total = sysUserService.queryTotal(query);


        List<UserVo> us = new ArrayList<>();
        for (SysUserEntity sysUserEntity : userList) {
            UserVo userVo = new UserVo();

            List<Long> roleIdList = sysUserRoleService.queryRoleIdList(sysUserEntity.getUserId());

            if(roleIdList!=null){
                Long roleId =roleIdList.get(0);
                BeanUtils.copyProperties(sysUserEntity, userVo);
                userVo.setRoleId(roleId);
            }else {
                BeanUtils.copyProperties(sysUserEntity, userVo);
            }
            us.add(userVo);
        }

        PageUtils pageUtil = new PageUtils(us, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @RequestMapping("/userDelete")
    @RequiresPermissions("sys:user:delete")
    public R userDelete(Long userId) {
        if (userId==1L) {
            return R.error("系统管理员不能删除");
        }

        if (userId == getUserId()) {
            return R.error("当前用户不能删除");
        }

        Long[] userIds = new Long[]{userId};
        sysUserService.deleteBatch(userIds);
        return R.ok();
    }


}
