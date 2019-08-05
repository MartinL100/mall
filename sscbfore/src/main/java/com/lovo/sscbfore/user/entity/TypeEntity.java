package com.lovo.sscbfore.user.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="sys_type")
public class TypeEntity {
    @Id
    @GenericGenerator(name = "typeUuid", strategy = "uuid")
    @GeneratedValue(generator = "typeUuid")
    @Column(name="type_id",length = 32)
    /**下拉ID*/
    private String typeId;
    @Column(name="type_name",length = 32)
    /**下拉名称*/
    private String typeName;
    @Column(name="type_key",length = 32)
    /**下拉key值*/
    private String typeKey;
    @Column(name="type_value",length = 32)
    /**下拉value值*/
    private String typeValue;

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

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
}
