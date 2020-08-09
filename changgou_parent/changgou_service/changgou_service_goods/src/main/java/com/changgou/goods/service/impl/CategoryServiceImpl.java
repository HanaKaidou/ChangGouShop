package com.changgou.goods.service.impl;

import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.service.CategoryService;
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
 * @date 2020/8/4 21:57
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageInfo<Category> findPage(Category category, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(category);
        List<Category> categoryList = categoryMapper.selectByExample(example);
        return new PageInfo<Category>(categoryList);
    }

    @Override
    public PageInfo<Category> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<Category> categoryList = categoryMapper.selectAll();
        return new PageInfo<Category>(categoryList);
    }

    @Override
    public List<Category> findList(Category category) {
        Example example = createExample(category);
        List<Category> categoryList = categoryMapper.selectByExample(example);
        return categoryList;
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public Category findById(Integer id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = categoryMapper.selectAll();
        return categoryList;
    }

    @Override
    public List<Category> findByParentId(Integer pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categoryList = categoryMapper.select(category);
        return categoryList;
    }

    public Example createExample(Category category) {

        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();

        if (category != null) {
            // 分类ID
            if (!StringUtils.isEmpty(category.getId().toString())) {
                criteria.andEqualTo("id", category.getId());
            }
            // 分类名称
            if (!StringUtils.isEmpty(category.getName())) {
                criteria.andLike("name", "%" + category.getName() + "%");
            }
            // 商品数量
            if (!StringUtils.isEmpty(category.getGoodsNum().toString())) {
                criteria.andEqualTo("goodsNum", category.getGoodsNum());
            }
            // 是否显示
            if (!StringUtils.isEmpty(category.getIsShow())) {
                criteria.andEqualTo("isShow", category.getIsShow());
            }
            // 是否导航
            if (!StringUtils.isEmpty(category.getIsMenu())) {
                criteria.andEqualTo("isMenu", category.getIsMenu());
            }
            // 排序
            if (!StringUtils.isEmpty(category.getSeq().toString())) {
                criteria.andEqualTo("seq", category.getSeq());
            }
            // 上级ID
            if (!StringUtils.isEmpty(category.getParentId().toString())) {
                criteria.andEqualTo("parentId", category.getParentId());
            }
            // 模板ID
            if (!StringUtils.isEmpty(category.getTemplateId().toString())) {
                criteria.andEqualTo("templateId", category.getTemplateId());
            }
        }
        return example;
    }


}
