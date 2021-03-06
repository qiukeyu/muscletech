package com.muscletech.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.muscletech.entity.Staff;
import com.muscletech.entity.User;
import com.muscletech.service.IStaffService;
import com.muscletech.service.IUserService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {

    private static IStaffService staticStaffService;
    private static IUserService staticUserService;

    @Resource
    private IStaffService staffService;

    @Resource
    private IUserService userService;

    public static String genToken(String id, String sign) {
        return JWT.create().withAudience(id)
                .withExpiresAt(DateUtil.offsetHour(new Date(), 5))
                .sign(Algorithm.HMAC256(sign));
    }

    public static Staff getCurrentStaff() {
        try {
            HttpServletRequest request;
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                String staffId = JWT.decode(token).getAudience().get(0);
                return staticStaffService.get(Integer.valueOf(staffId));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static User getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.get(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @PostConstruct
    public void setStaffService() {
        staticStaffService = staffService;
    }

    @PostConstruct
    public void setUserService() {
        staticUserService = userService;
    }
}

