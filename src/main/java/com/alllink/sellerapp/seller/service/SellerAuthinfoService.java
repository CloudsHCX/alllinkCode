package com.alllink.sellerapp.seller.service;

import com.alllink.sellerapp.seller.entity.SellerAuthinfoEntity;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-12-08 19:46:48
 */
public interface SellerAuthinfoService {

    SellerAuthinfoEntity queryObject(int sauthinfoId);

    List<SellerAuthinfoEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SellerAuthinfoEntity sellerAuthinfo);

    void update(SellerAuthinfoEntity sellerAuthinfo);

    void delete(int sauthinfoId);

}
