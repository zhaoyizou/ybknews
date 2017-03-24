package com.visionet.platform.thirdpartydata.mapper;

import com.visionet.core.mapper.BaseMapper;
import com.visionet.platform.thirdpartydata.model.ThirdPartyOrder;
import org.apache.ibatis.annotations.Param;

public interface ThirdPartyOrderMapper extends BaseMapper<ThirdPartyOrder> {

    /**
     * 更新合作订单完成信息
     *
     * @param tpOrder
     * @return
     * @author 秦朝胜 2016/11/25
     */
    void updateBySelective(ThirdPartyOrder tpOrder);

    ThirdPartyOrder selectByOrderIdAndPartnerOrderNo(String orderId,String partnerOrderNo);
    
    /**
     * 订单改派修改合作方订单号
     *
     * @param tpOrder
     * @return
     * @author 秦朝胜 2016/12/11
     */
    void updateByParams(ThirdPartyOrder tpOrder);
    
    /**
     * 根据合作方订单号查询合作订单
     * 
     * @param partnerOrderNo
     * @return
     * @author 秦朝胜
     */
    ThirdPartyOrder selectOneByPartnerOrderNo(@Param("partnerOrderNo") String partnerOrderNo);
}