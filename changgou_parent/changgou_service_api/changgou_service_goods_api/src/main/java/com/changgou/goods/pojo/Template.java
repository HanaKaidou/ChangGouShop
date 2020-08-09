package com.changgou.goods.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 常恃豪
 * @version 1.0
 * @date 2020/8/2 18:14
 */
@Table(name = "tb_template")
public class Template implements Serializable {

    private static final long serialVersionUID = 8948120600063102563L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//ID

    @Column(name = "name")
    private String name;//模板名称

    @Column(name = "spec_num")
    private Integer specNum;//规格数量

    @Column(name = "para_num")
    private Integer paraNum;//参数数量

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpecNum() {
        return specNum;
    }

    public void setSpecNum(Integer specNum) {
        this.specNum = specNum;
    }

    public Integer getParaNum() {
        return paraNum;
    }

    public void setParaNum(Integer paraNum) {
        this.paraNum = paraNum;
    }
}
