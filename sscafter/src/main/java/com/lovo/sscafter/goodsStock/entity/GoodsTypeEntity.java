package com.lovo.sscafter.goodsStock.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "type_id")
public class GoodsTypeEntity {
    @Id
    @Column(name="type_id",length = 48)
    @GenericGenerator(name = "typeId", strategy = "uuid")
    @GeneratedValue(generator = "typeId")
    private String typeId;
    @Column(name="type_name",length = 48)
    private String typeName;
    @Column(name="type_key",length = 48)
    private String typeKey;


    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }
}
