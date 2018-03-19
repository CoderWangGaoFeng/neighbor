package com.neighbor.controller.web.account;

import com.neighbor.module.ResponseObject;
import com.neighbor.module.account.AccountAddressModule;
import com.neighbor.service.account.AccountAddressService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息控制器
 * @author wgf
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Api(value = "用户地址管理")
public class AccountAddressController {

    private final AccountAddressService accountAddressService;

    /**
     * 增加用户地址
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/auth/address",method = RequestMethod.POST)
    public ResponseObject inset(@ModelAttribute("entity") AccountAddressModule entity) throws Exception{
        return this.accountAddressService.insert(entity);
    }
}
