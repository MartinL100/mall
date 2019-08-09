package com.lovo.psc.wangwei.service.Impl;

import com.lovo.psc.wangwei.dao.ISupplierGoodsDao;
import com.lovo.psc.wangwei.dao.ISupplierGoodsQueryDao;
import com.lovo.psc.entity.SupplierEntity;
import com.lovo.psc.entity.SupplierGoodsEntity;
import com.lovo.psc.wangwei.service.ISupplierGoodsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "supplierGoodsQueryService")
public class SupplierGoodsQueryServiceImpl implements ISupplierGoodsQueryService {
    @Autowired
    private ISupplierGoodsQueryDao supplierGoodsQueryDao;

    @Autowired
    private ISupplierGoodsDao supplierGoodsDao;
    @Override
    public void savaGoods(SupplierGoodsEntity supplierGoodsEntity) {
        supplierGoodsDao.save(supplierGoodsEntity);

    }

    @Override
    public void delgoods(String codeId) {
        supplierGoodsDao.deleteById(codeId);
    }

    @Override
    public List<SupplierGoodsEntity> getPageListGoods( String goodsName, String supplierName, String goodsType,String codeId,int pageNumber, int pageSize) {
        pageNumber= pageNumber-1;

        List<SupplierGoodsEntity> list= supplierGoodsQueryDao.pageList( goodsName,  supplierName,  goodsType ,codeId, pageNumber, pageSize);




      return  list;
    }

    @Override
    public long getPageListGoodsCount(String goodsName, String supplierName, String goodsType,String codeId) {

        return supplierGoodsQueryDao.countPage( goodsName,  supplierName,  goodsType,codeId);
    }

    public SupplierGoodsEntity finByiD(String codeId){

        return supplierGoodsDao.findByID(codeId);
    }


    public SupplierEntity fininId(String ID){

        return supplierGoodsDao.findInByID(ID);
    }

    public void update(String goodsName, String goodsNoms, String goodsType, String goodsUnit, int goodsNum,String codeId) {
        supplierGoodsDao.updateByID( goodsName,  goodsNoms,  goodsType,  goodsUnit,  goodsNum,codeId);


    }

}
