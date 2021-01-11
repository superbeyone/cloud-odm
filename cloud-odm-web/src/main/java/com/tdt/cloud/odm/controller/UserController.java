package com.tdt.cloud.odm.controller;

import com.tdt.cloud.commons.result.JsonResult;
import com.tdt.cloud.commons.result.ResultCodeEnum;
import com.tdt.cloud.commons.result.TdtStaticConst;
import com.tdt.cloud.odm.exception.CommonException;
import com.tdt.cloud.odm.pojo.Division;
import com.tdt.cloud.odm.pojo.user.User;
import com.tdt.cloud.odm.pojo.user.UserVo;
import com.tdt.cloud.odm.service.DivisionService;
import com.tdt.cloud.odm.service.MongoConnectionService;
import com.tdt.cloud.odm.service.MongoDBService;
import com.tdt.cloud.odm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Mr.superbeyone
 * @project cloud-odm
 * @className UserController
 * @description 用户 Controller
 * @date 2021-01-11 10:38
 **/

@Slf4j
@RestController
public class UserController {

    @Autowired
    UserService userService;


    @Autowired
    MongoConnectionService mongoConnectionService;

    @Autowired
    MongoDBService mongoDBService;

    @Autowired
    DivisionService divisionService;

    private Long start = null;
    private Long end = null;


    /**
     * 添加用户
     *
     * @param user 用户信息 （超级管理员使用）
     * @return
     */
    @PostMapping("/user")
    public JsonResult<Void> addUser(@Valid User user, @RequestParam(name = "dbIds", required = false) Integer[] dbIds) {
        user.setPassword(DigestUtils.md5DigestAsHex("000000".getBytes()));
        if (StringUtils.isBlank(user.getNickname())) {
            user.setNickname(user.getUsername());
        }
        User user1 = userService.getUserBasicInfoByUsername(user.getUsername());
        if (user1 != null) {
            throw new CommonException(ResultCodeEnum.USER_HAD_EXISTED);
        }
        user.setCreateTime(new Date());
        log.debug("添加用户信息{}", user);
        end = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        if (start != null) {
            if ((end - start) < 3) {
                throw new CommonException(5003, "手速太快了，请等待<span style='color:red'>&nbsp;" + (3 - (end - start)) + "&nbsp;</span>秒后重试...");
            }
        }
        start = end;
        int uId = userService.addUser(user);
        if (uId > 0) {
            if (dbIds != null && dbIds.length > 0) {
                userService.addUserOfOperateDBAuthData(user.getId(), Arrays.asList(dbIds));
            }
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

    /**
     * 用户个人基本信息修改
     *
     * @param user
     * @return
     */
    @PutMapping("/user")
    public JsonResult<Void> updateUserBasicInfo(@RequestBody User user, HttpServletRequest request) {
        Object user1 = request.getSession().getAttribute(TdtStaticConst.USER_SESSION_KEY);
        if (null == user1) {
            throw new CommonException(ResultCodeEnum.NO_USER_INFO);
        }
        if (user.getId() == null) {
            throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL);
        }
        log.debug("修改用户基本信息{}", user);
        boolean result = userService.updateUserBasicInfo(user);
        if (result) {
            if (((User) user1).getId().equals(user.getId())) {
                User info = userService.getUserBasicInfoById(user.getId());
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(info, userVo);
                Division division = divisionService.getDivisionByCode(user.getProvinceCode());
                userVo.setDivision(division.getName());
                if (user.getRole() == 1) {
                    userVo.setRoleVo("管理员");
                } else {
                    userVo.setRoleVo("普通用户");
                }
                request.getSession().setAttribute(TdtStaticConst.USER_SESSION_KEY, userVo);
            }
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

}
