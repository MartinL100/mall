package com.lovo.sscafter.promotionAndSalesReturn.salesReturn.dao;

import com.lovo.sscafter.promotionAndSalesReturn.salesReturn.entity.ReturnOrderEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * 退货订单dao
 */

public interface IReturnOrderDao extends CrudRepository<ReturnOrderEntity,String> {



}
