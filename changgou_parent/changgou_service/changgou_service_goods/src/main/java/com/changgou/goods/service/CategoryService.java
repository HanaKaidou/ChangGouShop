package com.changgou.goods.service;

import com.changgou.goods.pojo.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 常恃豪
 * @version 1.0
 * @date 2020/8/4 21:55
 */
public interface CategoryService {

    /***
     商品分类多条件分页查询
     * @param category
     * @param page
     * @param size
     * @return
     */
    PageInfo<Category> findPage(Category category, int page, int size);

    /***
     商品分类分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Category> findPage(int page, int size);

    /***
     商品分类多条件搜索方法
     * @param category
     * @return
     */
    List<Category> findList(Category category);

    /***
     * 删除商品分类
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改商品分类数据
     * @param category
     */
    void update(Category category);

    /***
     * 新增商品分类
     * @param category
     */
    void add(Category category);

    /**
     * 根据ID查询商品分类
     *
     * @param id
     * @return
     */
    Category findById(Integer id);

    /***
     * 查询所有商品分类
     * @return
     */
    List<Category> findAll();

    /***
     * 根据父节点ID查询
     * @param pid:父节点ID
     */
    List<Category> findByParentId(Integer pid);
}
