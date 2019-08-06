package com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.dao.daoImp;

        import com.lovo.sscafter.promotionAndSalesReturn.promotion.beginPromotion.dao.IPromotionGoodsDao;
        import com.lovo.sscafter.upperAndLowerGoods.entity.GoodsEntity;
        import com.lovo.sscafter.util.StringUtil;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
        import org.springframework.stereotype.Repository;


        import javax.persistence.EntityManager;
        import javax.persistence.Query;
        import java.util.ArrayList;
        import java.util.List;

@Repository
public class PromotionGoodsDaoImp implements IPromotionGoodsDao {

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    //获得entityManager
    public EntityManager getEntityManager(){

        return entityManagerFactory.getObject().createEntityManager();
    }





    /**
     * 动态查询所有已上架的商品并分页
     * @param goodsName 商品名
     * @param goodsType 商品类型
     * @param pageNumber 当前页（用于计算起始行数）
     * @param pageLine 每页显示的行数
     * @return 商品集合
     */
    @Override
    public List<GoodsEntity> findBygoodsNameAndgoodsState(String goodsName, String goodsType, int pageNumber, int pageLine) {
        String hql="from GoodsEntity where 1=1 and goodsState='已上架'  ";

        //商品名不为空，商品类型为空
        if (!StringUtil.blString(goodsName)&& StringUtil.blString(goodsType)){
            hql+="and  goodsName like  '%"+goodsName+"%' ";
        }
        //商品名为空，商品类型不为空
        else if (!StringUtil.blString(goodsType)&& StringUtil.blString(goodsName)){
            hql+=" and  goodsType like  '%"+goodsType+"%' ";
        }
        //商品名，商品类型都不为空
        else if (!StringUtil.blString(goodsType)&& !StringUtil.blString(goodsName)){
            hql+=" and  goodsName like '%"+goodsName+"%' and goodsType like  '%"+goodsType+"%' " ;
        }

        Query query=getEntityManager().createQuery(hql).setFirstResult(pageNumber).setMaxResults(pageLine);
        return query.getResultList();
    }




    /**
     * 动态查询总行数
     * @param goodsName 商品名
     * @param goodsType  商品类型
     * @return 总行数
     */
    @Override
    public long findCount(String goodsName, String goodsType) {
        String hql="select  count(*) from GoodsEntity where 1=1 and goodsState='已上架' ";
        //商品名不为空，商品类型为空
        if (!StringUtil.blString(goodsName)&&StringUtil.blString(goodsType)){
            hql+="and goodsName like '%"+goodsName+"%'  ";
        }
        //商品名为空，商品类型不为空
        else if(StringUtil.blString(goodsName)&& !StringUtil.blString(goodsType)){
            hql+="and goodsType like '%"+goodsType+"%' ";
        }
        //商品名，商品类型都不为空
        else if (!StringUtil.blString(goodsName)&& !StringUtil.blString(goodsType)){
            hql+="and goodsName like '%"+goodsName+"%'  and goodsType like '%"+goodsType+"%' ";
        }
        Query query=getEntityManager().createQuery(hql);
        return (Long)query.getSingleResult();
    }

    /**
     * 根据id查询商品集合
     * @param listId id集合
     * @return 商品集合
     */
    public List<GoodsEntity> findByGoodsId(List<String> listId){
        List<GoodsEntity> goodsEntitieList=new ArrayList<>();
        String hql="";
        for (String f:listId) {
            hql="from GoodsEntity where goodsId='"+f+"'  ";
             GoodsEntity s=(GoodsEntity) getEntityManager().createQuery(hql).getSingleResult();
            goodsEntitieList.add(s);
        }

        return goodsEntitieList;
    }

}
