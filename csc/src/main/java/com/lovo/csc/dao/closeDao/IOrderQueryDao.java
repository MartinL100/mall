package com.lovo.csc.dao.closeDao;

import com.lovo.csc.entity.OrderEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderQueryDao {
    public List<OrderEntity> findBySome(String hql, int pageNumber, int pageSize);
    public long count(String hql);

}
