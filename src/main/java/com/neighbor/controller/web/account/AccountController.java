package com.neighbor.controller.web.account;

import com.neighbor.exception.NeiException;
import com.neighbor.module.ResponseObject;
import com.neighbor.module.ResponseStatus;
import com.neighbor.module.account.vo.AccountVo;
import com.neighbor.module.sys.AccountModule;
import com.neighbor.service.account.AccountService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 * @author wangaofeng
 */
@RestController
@AllArgsConstructor//用于给所有对象初始化时赋值
@RequestMapping("/api")
@Api(value = "用户")
public class AccountController {

    private final AccountService accountService;

    /**
     * 注册
     * @param account
     */
    @RequestMapping(value="/signup",method = RequestMethod.POST)
    public void signUp(AccountModule account)throws Exception{
        this.accountService.signUp(account);
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    public AccountVo signIn(@RequestParam("userName")String userName ,
                            @RequestParam("password")String password,
                            @RequestParam(name="rememberMe",defaultValue = "false")boolean rememberMe)
                            throws Exception{
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        token.setRememberMe(rememberMe);
//        try {
//            subject.login(token);
//        } catch (AuthenticationException e) {
//            e.printStackTrace();
//            throw new NeiException("帐号或密码错误",401);
//        }
        AccountModule account = new AccountModule().setUserName(userName).setPassword(password);
        return this.accountService.signIn(account);
    }
}
