package com.lovo.csc.dao.closeDao;


import com.lovo.csc.vo.IndentSupplyDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IIndentSupplyDao {

    /**
     * 根据订单ID查找该订单所有的商品和其报价
     * @param indentId
     * @return
     */
    public List<IndentSupplyDto>findIndentSupplyListByIndentId(int pageNumber, int pageSize,String indentId);

    public long countIndentSupplyListByIndentId(String indentId);
}
