package com.changgou.goods.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 常恃豪
 * @version 1.0
 * @date 2020/8/2 21:48
 */

@Table(name = "tb_para")
public class Para implements Serializable {

    private static final long serialVersionUID = 1824717441209601682L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//id

    @Column(name = "name")
    private String name;//名称

    @Column(name = "options")
    private String options;//选项

    @Column(name = "seq")
    private Integer seq;//排序

    @Column(name = "template_id")
    private Integer templateId;//模板ID

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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }
}
