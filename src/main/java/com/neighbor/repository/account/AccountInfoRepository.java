package com.neighbor.repository.account;

import com.neighbor.module.account.AccountAddressModule;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * 用户信息持久层
 * @author wgf
 */
@Transactional
public interface AccountInfoRepository extends CrudRepository<AccountAddressModule,Serializable> {

    /**
     * 通过省市区小区名称查找该用户是否已经在该小区创建位置
     * @param province
     * @param city
     * @param area
     * @param village
     * @return
     */
    public AccountAddressModule findByProvinceAndCityAndAreaAndVillage(
            String province ,String city ,String area ,String village);
}
