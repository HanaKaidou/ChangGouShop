package com.changgou.goods.service.impl;

import com.changgou.goods.dao.ParaMapper;
import com.changgou.goods.dao.ParaMapper;
import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.ParaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 常恃豪
 * @version 1.0
 * @date 2020/8/2 21:52
 */
@Service
public class ParaServiceImpl implements ParaService {

    @Autowired
    private ParaMapper  paraMapper;

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public PageInfo<Para> findPage(Para para, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(para);
        List<Para> paraList = paraMapper.selectByExample(example);
        return new PageInfo<Para>(paraList);
    }

    @Override
    public PageInfo<Para> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<Para> paraList = paraMapper.selectAll();
        return new PageInfo<Para>(paraList);
    }

    @Override
    public List<Para> findList(Para para) {
        Example example = createExample(para);
        List<Para> paraList = paraMapper.selectByExample(example);
        return paraList;
    }

    @Override
    public void delete(Integer id) {
        paraMapper.deleteByPrimaryKey(id);
        Para para = this.findById(id);
        updateTemplateParaNum(para, -1);
    }

    @Override
    public void update(Para para) {
        paraMapper.updateByPrimaryKey(para);
    }

    @Override
    public void add(Para para) {
        paraMapper.insert(para);
        updateTemplateParaNum(para, 1);
    }

    @Override
    public Para findById(Integer id) {
        Para para = paraMapper.selectByPrimaryKey(id);
        return para;
    }

    @Override
    public List<Para> findAll() {
        List<Para> paraList = paraMapper.selectAll();
        return paraList;
    }

    /**
     * Para构建查询对象
     *
     * @param para
     * @return
     */
    public Example createExample(Para para) {
        Example example = new Example(Para.class);
        Example.Criteria criteria = example.createCriteria();
        if (para != null) {
            if (StringUtils.isNotBlank(para.getId().toString())) {
                criteria.andEqualTo("id", para.getId());
            }
            if (StringUtils.isNotBlank(para.getName())) {
                criteria.andLike("name", "%" + para.getName() + "%");
            }
            if (StringUtils.isNotBlank(para.getOptions())) {
                criteria.andEqualTo("options", para.getOptions());
            }
            if (StringUtils.isNotBlank(para.getSeq().toString())) {
                criteria.andEqualTo("seq", para.getSeq());
            }
            if (StringUtils.isNotBlank(para.getTemplateId().toString())) {
                criteria.andEqualTo("templateId", para.getTemplateId());
            }
        }
        return example;
    }

    /**
     * 修改指定模板的规格数量
     *
     * @param para  变更的参数
     * @param count 变更得参数个数
     */
    public void updateTemplateParaNum(Para para, Integer count) {
        Integer templateId = para.getTemplateId();
        Template template = templateMapper.selectByPrimaryKey(templateId);
        template.setParaNum(template.getParaNum() + count);
        templateMapper.updateByPrimaryKeySelective(template);
    }
}
