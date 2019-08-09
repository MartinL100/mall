package com.lovo.psc.groupOne;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovo.psc.groupOne.dto.GoodsInfoDto;
import com.lovo.psc.entity.IndentEntity;
import com.lovo.psc.entity.SupplierEntity;
import com.lovo.psc.entity.SupplyCenterEntity;
import com.lovo.psc.groupOne.service.IIndentService;
import com.lovo.psc.groupOne.service.ISupplyCenterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MQProcess {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private IIndentService indentService;
    @Autowired
    private ISupplyCenterService supplyCenterService;



    @JmsListener(destination="FBMQ")
    public void saveInfo(String message){
        System.out.println(message);
        ObjectMapper mapper = new ObjectMapper();
        List<GoodsInfoDto> goodsInfoDtoList;
        //GoodsInfoDto goodsInfoDto = null;
        try {

                goodsInfoDtoList = mapper.readValue(message, new TypeReference<List<GoodsInfoDto>>() {});
                for (GoodsInfoDto goodsInfoDto : goodsInfoDtoList) {
                    if (goodsInfoDto != null) {
                        /**大订单编号 */
                        String indentId = goodsInfoDto.getIndentId();
                        /**大订单日期 */
                        String indentDate = goodsInfoDto.getIndentDate();
                        /**小订单编号 */
                        String cargoId = goodsInfoDto.getCargoId();
                        /**商品名称 */
                        String goodsName = goodsInfoDto.getGoodsName();
                        /**商品规格 */
                        String goodsNorms = goodsInfoDto.getGoodsNorms();
                        /**商品类型 */
                        String goodsType = goodsInfoDto.getGoodsType();
                        /**商品单位 */
                        String goodsUnit = goodsInfoDto.getGoodsUnit();
                        /**商品数量 */
                        long goodsNum = goodsInfoDto.getGoodsNum();
                        /**供应商编号 */
                        String supplierId = goodsInfoDto.getSupplierId();
                        //保存大订单信息
                        IndentEntity indent = new IndentEntity();
                        indent.setIndentId(indentId);
                        indent.setIndentDate(indentDate);
                        indent.setIndentStatus("未审核");
                        indentService.saveIndent(indent);
                        //创建供应商对象保存编号
                        SupplierEntity supplier = new SupplierEntity();
                        supplier.setSupplierId(supplierId);
                        //创建小订单表,并保存在数据库
                        SupplyCenterEntity supplyCenter = new SupplyCenterEntity();
                        supplyCenter.setSupplier(supplier);
                        supplyCenter.setIndentEntity(indent);
                        supplyCenter.setCargoId(cargoId);
                        supplyCenter.setGoodsName(goodsName);
                        supplyCenter.setGoodsNorms(goodsNorms);
                        supplyCenter.setGoodsType(goodsType);
                        supplyCenter.setGoodsUnit(goodsUnit);
                        supplyCenter.setSupplyNum(goodsNum);
                        supplyCenter.setBjTag("未报价");
                        //supplyCenter.setZbTag("未中标");
                        supplyCenterService.saveSupplyCenter(supplyCenter);
                    }
                }
            }catch (Exception e){
            e.printStackTrace();
        }

        }
    }

