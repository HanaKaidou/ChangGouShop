package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 常恃豪
 * @version 1.0
 * @date 2020/4/6 19:40
 */
public interface BrandService {

    /**
     * 查询全部品牌
     *
     * @return
     */
    List<Brand> findBrand();

    /**
     * 根据ID查询品牌
     *
     * @return
     */
    Brand findBrandById(Integer id);

    /**
     * 新增品牌
     *
     * @param brand
     */
    void addBrand(Brand brand);

    /**
     * 修改品牌
     *
     * @param brand
     */
    void updateBrandById(Brand brand);

    /**
     * 删除品牌
     *
     * @param id
     */
    void deleteBrandById(Integer id);

    /**
     * 多条件搜索品牌方法
     *
     * @param brand
     * @return
     */
    List<Brand> findBrandList(Brand brand);

    /**
     * 品牌列表分页
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageInfo<Brand> findBrandListPage(Integer pageIndex, Integer pageSize);

    /**
     * 品牌列表条件+分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageInfo<Brand> findBrandPage(Integer pageIndex, Integer pageSize, Brand brand);

}
