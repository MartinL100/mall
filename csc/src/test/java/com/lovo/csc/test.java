package com.lovo.csc;

import com.lovo.csc.dao.depositDao.IDepositInfoDao;
import com.lovo.csc.service.depositService.IDepositInfoService;
import com.lovo.csc.service.depositService.IDepositService;
import com.lovo.csc.vo.DepositVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringRunner.class )
@SpringBootTest(classes = CscApplication.class)
public class test {
    @Autowired
    private IDepositService depositService;
    @Autowired
private IDepositInfoService depositInfoService;
    @Test
    public void x(){
        List<DepositVo> list= depositInfoService.getAllByUserName("阿刚");
        System.out.println(list);

    }

    @Test
    //使用预存款支付测试
    public void  findDeposit(){
        //查询折后价
       Double x=  depositService.findDepositDiscount("阿刚",200.0);
        //支付折后价
       Double y= depositService.deductionDeposit("阿刚",1000000.0);

        System.out.println(x+"--------"+y);

    }

    @Test
    //退货测试
    public void returnDeposit(){
        String str=  depositService.salesReturn("阿仁",20000.0);
        System.out.println(str);

    }



}
