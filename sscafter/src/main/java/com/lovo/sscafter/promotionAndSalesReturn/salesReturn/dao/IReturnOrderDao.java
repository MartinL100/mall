package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.dao;

import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnGoodsEntity;
import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnOrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * 退货订单dao
 */

public interface IReturnOrderDao extends CrudRepository<ReturnOrderEntity,String> {



}
