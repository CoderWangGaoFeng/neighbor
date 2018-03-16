package com.neighbor.service.account.imp;

import com.neighbor.model.account.AccountAddressModel;
import com.neighbor.repository.account.AccountInfoRepository;
import com.neighbor.service.account.AccountInfoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息逻辑层实现类
 * @author wgf
 */
@Service
public class AccountInfoServiceImp implements AccountInfoService {

    @Autowired
    private AccountInfoRepository accountInfoRepository;
    /**
     * 完善用户信息
     * @param accountInfo
     */
    @Override
    public void insert(AccountAddressModel accountInfo)throws Exception {
        this.accountInfoRepository.save(accountInfo);
    }
}
