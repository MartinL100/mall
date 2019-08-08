package com.lovo.csc.dao.closeDao;

import com.lovo.csc.entity.GoodsEntity;
import org.springframework.data.repository.CrudRepository;

public interface IGoodsDao extends CrudRepository<GoodsEntity,String> {
}
