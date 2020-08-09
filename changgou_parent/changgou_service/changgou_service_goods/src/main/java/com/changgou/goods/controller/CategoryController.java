package com.changgou.goods.controller;

import com.changgou.framework.entity.Result;
import com.changgou.framework.entity.StatusCode;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.service.CategoryService;
import com.changgou.goods.service.CategoryService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 常恃豪
 * @version 1.0
 * @date 2020/8/2 20:23
 */
@Api(value = "商品分类Controller", tags = {"商品分类访问接口"})
@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Category分页条件搜索实现
     *
     * @param category
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public Result<PageInfo> findPage(@RequestBody(required = false) Category category, @PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        PageInfo<Category> pageInfo = this.categoryService.findPage(category, pageIndex, pageSize);
        return new Result<PageInfo>(true, StatusCode.OK, "分页多条件查询商品分类成功", pageInfo);
    }

    /**
     * Category分页搜索实现
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/search/{pageIndex}/{pageSize}")
    public Result<PageInfo> findPage(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        PageInfo<Category> pageInfo = categoryService.findPage(pageIndex, pageSize);
        return new Result<PageInfo>(true, StatusCode.OK, "分页查询商品分类成功", pageInfo);
    }

    /**
     * Category多条件搜索
     *
     * @param category
     * @return
     */
    @PostMapping("/search")
    public Result<List<Category>> findList(@RequestBody(required = false) Category category) {
        List<Category> list = categoryService.findList(category);
        return new Result<List<Category>>(true, StatusCode.OK, "多条件查询商品分类成功", list);
    }

    /**
     * 根据ID查询商品分类
     *
     * @param id
     * @return
     */
    @GetMapping("/search/{id}")
    public Result<Category> findById(@PathVariable Integer id) {
        Category category = categoryService.findById(id);
        return new Result<Category>(true, StatusCode.OK, "添加商品分类成功", category);
    }

    /**
     * 查询所有商品分类
     *
     * @return
     */
    @GetMapping
    public Result<List<Category>> findAll() {
        List<Category> categoryList = categoryService.findAll();
        return new Result<List<Category>>(true, StatusCode.OK, "添加商品分类成功", categoryList);
    }

    /**
     * 根据ID删除商品分类数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}}")
    public Result delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return new Result(true, StatusCode.OK, "删除商品分类成功");
    }

    /**
     * 根据ID修改商品分类数据
     *
     * @param category
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Category category) {
        categoryService.update(category);
        return new Result(true, StatusCode.OK, "修改商品分类成功");
    }

    /**
     * 新增商品分类
     *
     * @param category
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Category category) {
        categoryService.add(category);
        return new Result(true, StatusCode.OK, "添加商品分类成功");
    }

}
