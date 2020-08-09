package com.changgou.goods.service.impl;

import com.changgou.goods.dao.SpecMapper;
import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.SpecService;
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
 * @date 2020/8/2 21:01
 */
@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public PageInfo<Spec> findPage(Spec spec, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(spec);
        List<Spec> specList = specMapper.selectByExample(example);
        return new PageInfo<Spec>(specList);
    }

    @Override
    public PageInfo<Spec> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<Spec> specList = specMapper.selectAll();
        return new PageInfo<Spec>(specList);
    }

    @Override
    public List<Spec> findList(Spec spec) {
        Example example = createExample(spec);
        List<Spec> specList = specMapper.selectByExample(example);
        return specList;
    }

    @Override
    public void delete(Integer id) {
        specMapper.deleteByPrimaryKey(id);
        Spec spec = this.findById(id);
        updateTemplateSpecNum(spec, -1);
    }

    @Override
    public void update(Spec spec) {
        specMapper.updateByPrimaryKey(spec);
    }

    @Override
    public void add(Spec spec) {
        specMapper.insert(spec);
        updateTemplateSpecNum(spec, 1);
    }

    @Override
    public Spec findById(Integer id) {
        Spec spec = specMapper.selectByPrimaryKey(id);
        return spec;
    }

    @Override
    public List<Spec> findAll() {
        List<Spec> specList = specMapper.selectAll();
        return specList;
    }

    /**
     * Spec构建查询对象
     *
     * @param spec
     * @return
     */
    public Example createExample(Spec spec) {
        Example example = new Example(Spec.class);
        Example.Criteria criteria = example.createCriteria();
        if (spec != null) {
            if (StringUtils.isNotBlank(spec.getId().toString())) {
                criteria.andEqualTo("id", spec.getId());
            }
            if (StringUtils.isNotBlank(spec.getName())) {
                criteria.andLike("name", "%" + spec.getName() + "%");
            }
            if (StringUtils.isNotBlank(spec.getOptions())) {
                criteria.andEqualTo("options", spec.getOptions());
            }
            if (StringUtils.isNotBlank(spec.getSeq().toString())) {
                criteria.andEqualTo("seq", spec.getSeq());
            }
            if (StringUtils.isNotBlank(spec.getTemplateId().toString())) {
                criteria.andEqualTo("templateId", spec.getTemplateId());
            }
        }
        return example;
    }

    /**
     * 修改指定模板的规格数量
     *
     * @param spec  变更的参数
     * @param count 变更得参数个数
     */
    public void updateTemplateSpecNum(Spec spec, Integer count) {
        Integer templateId = spec.getTemplateId();
        Template template = templateMapper.selectByPrimaryKey(templateId);
        template.setSpecNum(template.getSpecNum() + count);
        templateMapper.updateByPrimaryKeySelective(template);
    }
}
