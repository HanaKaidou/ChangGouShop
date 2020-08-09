package com.changgou.goods.service.impl;

import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
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
 * @date 2020/8/2 18:23
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public PageInfo<Template> findPage(Template template, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(template);
        List<Template> templateList = this.templateMapper.selectByExample(example);
        return new PageInfo<Template>(templateList);
    }

    @Override
    public PageInfo<Template> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<Template> templateList = templateMapper.selectAll();
        return new PageInfo<Template>(templateList);
    }

    @Override
    public List<Template> findList(Template template) {
        Example example = createExample(template);
        List<Template> templateList = templateMapper.selectByExample(example);
        return templateList;
    }

    @Override
    public void delete(Integer id) {
        templateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Template template) {
        templateMapper.updateByPrimaryKey(template);
    }

    @Override
    public void add(Template template) {
        templateMapper.insert(template);
    }

    @Override
    public Template findById(Integer id) {
        Template template = templateMapper.selectByPrimaryKey(id);
        return template;
    }

    @Override
    public List<Template> findAll() {
        List<Template> templateList = templateMapper.selectAll();
        return templateList;
    }

    /**
     * Template构建查询对象
     *
     * @param template
     * @return
     */
    public Example createExample(Template template) {
        Example example = new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();

        if (template != null) {
            if (StringUtils.isNotBlank(template.getId().toString())) {
                criteria.andEqualTo("id", template.getId());
            }
            if (StringUtils.isNotBlank(template.getName())) {
                criteria.andLike("name", "%" + template.getName() + "%");
            }
            if (StringUtils.isNotBlank(template.getParaNum().toString())) {
                criteria.andEqualTo("paraNum", template.getParaNum());
            }
            if (StringUtils.isNotBlank(template.getSpecNum().toString())) {
                criteria.andEqualTo("specNum", template.getSpecNum());
            }

        }
        return example;
    }
}
