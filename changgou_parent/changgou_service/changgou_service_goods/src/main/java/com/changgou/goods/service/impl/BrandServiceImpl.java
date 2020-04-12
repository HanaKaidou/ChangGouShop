package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 常恃豪
 * @version 1.0
 * @date 2020/4/6 19:46
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询全部品牌
     *
     * @return
     */
    @Override
    public List<Brand> findBrand() {
        return this.brandMapper.selectAll();
    }

    /**
     * 根据ID查询品牌
     *
     * @return
     */
    @Override
    public Brand findBrandById(Integer id) {
        return this.brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增品牌
     *
     * @param brand
     */
    @Override
    public void addBrand(Brand brand) {
        this.brandMapper.insert(brand);
    }

    /**
     * 修改品牌
     *
     * @param brand
     */
    @Override
    public void updateBrandById(Brand brand) {
        this.brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 删除品牌
     *
     * @param id
     */
    @Override
    public void deleteBrandById(Integer id) {
        this.brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 多条件搜索品牌方法
     *
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findBrandList(Brand brand) {
        Example example = createExample(brand);
        return this.brandMapper.selectByExample(example);
    }

    /**
     * 品牌列表分页
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Brand> findBrandListPage(Integer pageIndex, Integer pageSize) {
        //静态分页
        PageHelper.startPage(pageIndex, pageSize);
        //数据查询
        List<Brand> brandList = this.brandMapper.selectAll();
        //分页查询
        return new PageInfo<Brand>(brandList);
    }

    /**
     * 品牌列表条件+分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Brand> findBrandPage(Integer pageIndex, Integer pageSize, Brand brand) {
        PageHelper.startPage(pageIndex, pageSize);
        Example example = createExample(brand);
        List<Brand> brandList = this.brandMapper.selectByExample(example);
        return new PageInfo<Brand>(brandList);
    }

    /**
     * 构建查询条件
     *
     * @param brand
     * @return
     */
    public Example createExample(Brand brand) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (brand != null) {
            if (!StringUtils.isEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");
            }

            if (!StringUtils.isEmpty(brand.getImage())) {
                criteria.andLike("image", "%" + brand.getImage() + "%");
            }

            if (!StringUtils.isEmpty(brand.getId())) {
                criteria.andEqualTo("id", brand.getId());
            }

            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andLike("letter", "%" + brand.getLetter() + "%");
            }
        }
        return example;
    }
}
