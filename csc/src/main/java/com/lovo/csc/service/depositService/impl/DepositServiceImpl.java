package com.lovo.csc.service.depositService.impl;

import com.lovo.csc.dao.depositDao.IDepositDao;
import com.lovo.csc.dao.depositDao.IDepositInfoDao;
import com.lovo.csc.entity.depositEntity.DepositEntity;
import com.lovo.csc.entity.depositEntity.DepositInfoEntity;
import com.lovo.csc.service.depositService.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;

@Service(value = "depositService")
public class DepositServiceImpl implements IDepositService {
    @Autowired
    private IDepositDao depositDao;
    @Autowired
    private IDepositInfoDao depositInfoDao;

    private String Lv;//等级

    @Override
    //根据用户名查询其预存款对象
    public DepositEntity findDepositByUserName(String userName) {
        return depositDao.findDepositByUserName(userName);
    }

    @Override
    //添加预存款,同时添加预存款明细
    public DepositEntity saveDepositMoney(String userName, Double saveMoney) {
        //查询出当前用户预存款对象
        DepositEntity depositEntity = depositDao.findDepositByUserName(userName);

        if (depositEntity == null) {//该用户不存在
            //添加用户预存款
            depositEntity=new DepositEntity();
            depositEntity.setUserName(userName);//用户名
            depositEntity.setCountDepositMoney(saveMoney);//累计金额
            depositEntity.setLeftDepositMoney(saveMoney);//剩余金额
            if (saveMoney <= 10000) {
                Lv = "0";
            } else if (saveMoney > 10000 && saveMoney <= 50000) {
                Lv = "1";
            } else {
                Lv = "2";
            }
            depositEntity.setDepositMoneyLevel(Lv);//等级
        } else {//用户已存在
            Double countMoney = depositEntity.getCountDepositMoney() + saveMoney;
            depositEntity.setCountDepositMoney(countMoney);//累计金额
            depositEntity.setLeftDepositMoney(depositEntity.getLeftDepositMoney() + saveMoney);//剩余金额

            if (countMoney <= 10000) {
                Lv = "0";
            } else if (countMoney > 10000 && countMoney <= 50000) {
                Lv = "1";
            } else {
                Lv = "2";
            }
            depositEntity.setDepositMoneyLevel(Lv);//等级

        }
        //保存成功得到用户对象
          DepositEntity depositEntity1=  depositDao.save(depositEntity);

        //添加明细
        DepositInfoEntity depositInfoEntity = new DepositInfoEntity();
        depositInfoEntity.setDeposit(depositEntity1);
        depositInfoEntity.setInfoTag(0);//存钱标记
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM");
        String infoTime = sf.format(System.currentTimeMillis());
        String makeTime = sf1.format(System.currentTimeMillis());
        depositInfoEntity.setInfoTime(infoTime);//操作时间
        depositInfoEntity.setMakeTime(makeTime);//按月标记
        depositInfoEntity.setMakeMoney(saveMoney);//操作金额
        depositInfoDao.save(depositInfoEntity);
        //增加预存款
        return depositEntity1;

    }

    @Override
    //查询折扣后价格
    public Double findDepositDiscount(String userName, Double allPrice) {
        //查询该用户的预存款对象
        DepositEntity depositEntity = depositDao.findDepositByUserName(userName);
        Lv=depositEntity.getDepositMoneyLevel();
        //判断预存款是否足够
        Double x = depositEntity.getLeftDepositMoney() - allPrice;
        if (x >= 0) {//足够
            if ("1".equals(Lv)){
                //黄金会员9折
                allPrice=allPrice*0.9;
            }else if ("2".equals(Lv)){
                //砖石会员8折
                allPrice=allPrice*0.8;
            }
            return allPrice;//返回折扣后的价格
        } else {
            return -1.00;//预存款不足
        }
    }

    @Override
    //用户使用预存款支付
    public Double deductionDeposit(String userName, Double allPrice) {
        //查询该用户的预存款对象
        DepositEntity depositEntity = depositDao.findDepositByUserName(userName);
           //得到用户等级
        Lv=  depositEntity.getDepositMoneyLevel();
        //判断预存款是否足够
        Double x = depositEntity.getLeftDepositMoney() - allPrice;
        if (x >= 0) {//足够
            if ("1".equals(Lv)){
                //黄金会员9折
                allPrice=allPrice*0.9;
            }else if ("2".equals(Lv)){
                //砖石会员8折
                allPrice=allPrice*0.8;
            }
            x = depositEntity.getLeftDepositMoney() - allPrice;
            //修改剩余金额
            depositEntity.setLeftDepositMoney(x);
            //更新预存款数据
            DepositEntity newDeposit= depositDao.save(depositEntity);

            //添加预存款明细
            DepositInfoEntity depositInfoEntity = new DepositInfoEntity();
            depositInfoEntity.setDeposit(newDeposit);
            depositInfoEntity.setInfoTag(1);//支付标记
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM");
            String infoTime = sf.format(System.currentTimeMillis());
            String makeTime = sf1.format(System.currentTimeMillis());
            depositInfoEntity.setInfoTime(infoTime);//支付时间
            depositInfoEntity.setMakeTime(makeTime);
            depositInfoEntity.setMakeMoney(allPrice);//支付金额
            depositInfoDao.save(depositInfoEntity);
            return allPrice;//返回实际消费金额
        } else {
            return -1.00;
        }
    }

    @Override
    //退货退款
    public String salesReturn(String userName, Double goodsPrice) {
        //查询该用户的预存款对象
        DepositEntity depositEntity = depositDao.findDepositByUserName(userName);
        Double x = depositEntity.getLeftDepositMoney() + goodsPrice;
        //添加预存款余额
        depositEntity.setLeftDepositMoney(x);
        depositDao.save(depositEntity);
        //添加预存款明细
        DepositInfoEntity depositInfoEntity = new DepositInfoEntity();
        depositInfoEntity.setDeposit(depositEntity);
        depositInfoEntity.setInfoTag(2);//退款标记
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM");
        String infoTime = sf.format(System.currentTimeMillis());
        String makeTime = sf1.format(System.currentTimeMillis());
        depositInfoEntity.setInfoTime(infoTime);//退款时间
        depositInfoEntity.setMakeTime(makeTime);
        depositInfoEntity.setMakeMoney(goodsPrice);//退款金额
        depositInfoDao.save(depositInfoEntity);
        return "退款成功";
    }
}
