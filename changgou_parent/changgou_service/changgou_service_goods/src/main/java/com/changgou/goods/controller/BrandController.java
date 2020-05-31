package com.changgou.goods.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import com.changgou.framework.entity.Result;
import com.changgou.framework.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 常恃豪
 * @version 1.0
 * @date 2020/4/6 19:47
 */
@Api(value = "品牌Controller", tags = {"品牌访问接口"})
@RestController
@RequestMapping("/brand")
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 查询全部品牌
     *
     * @return
     */
    @ApiOperation(value = "查询全部品牌1")
    @ApiResponses(value = {@ApiResponse(code = 20000, message = "查询所有品牌成功"),
            @ApiResponse(code = 20001, message = "没有查询到品牌数据"),
            @ApiResponse(code = 20002, message = "查询失败")})
    @GetMapping
    public Result<List<Brand>> findBrand() {
        try {
            List<Brand> brandList = this.brandService.findBrand();
            if (!CollectionUtils.isEmpty(brandList)) {
                return new Result<List<Brand>>(true, StatusCode.OK, "查询所有品牌成功", brandList);
            } else {
                return new Result<List<Brand>>(false, StatusCode.NOT_FIND, "没有查询到品牌数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result<List<Brand>>(false, StatusCode.ERROR, "查询失败");
    }

    /**
     * 根据ID查询品牌
     *
     * @return
     */
    @ApiOperation(value = "根据ID查询品牌")
    @GetMapping("/{id}")
    public Result<Brand> findBrandById(@ApiParam("品牌ID") @PathVariable Integer id) {
        Brand brand = this.brandService.findBrandById(id);
        return new Result<Brand>(true, StatusCode.OK, "根据ID查询品牌成功", brand);
    }

    /**
     * 新增品牌
     *
     * @param brand
     */
    @PostMapping
    public Result addBrand(@RequestBody Brand brand) {
        this.brandService.addBrand(brand);
        return new Result(true, StatusCode.OK, "新增品牌成功");
    }

    /**
     * 修改品牌
     *
     * @param brand
     * @return
     */
    @PutMapping("/{id}")
    public Result updateBrandById(@RequestBody Brand brand, @PathVariable Integer id) {
        brand.setId(id);
        this.brandService.updateBrandById(brand);
        return new Result(true, StatusCode.OK, "修改品牌成功");
    }

    /**
     * 删除品牌
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteBrandById(@PathVariable Integer id) {
        this.brandService.deleteBrandById(id);
        return new Result(true, StatusCode.OK, "删除品牌成功");
    }

    /**
     * 多条件搜索品牌方法
     *
     * @param brand
     * @return
     */
    @PostMapping("/search")
    public Result<List<Brand>> findBrandList(@RequestBody Brand brand) {
        List<Brand> brandList = this.brandService.findBrandList(brand);
        return new Result<List<Brand>>(true, StatusCode.OK, "根据条件查询品牌成功", brandList);
    }

    /**
     * 品牌列表分页
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/search/{pageIndex}/{pageSize}")
    public Result<PageInfo> findBrandListPage(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        PageInfo<Brand> brandListPage = this.brandService.findBrandListPage(pageIndex, pageSize);
        return new Result<PageInfo>(true, StatusCode.OK, "分页查询品牌成功", brandListPage);
    }

    /**
     * 品牌列表条件+分页查询
     *
     * @param brand
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @PostMapping("/search/{pageIndex}/{pageSize}")
    public Result<PageInfo> findBrandPage(@RequestBody(required = false) Brand brand, @PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        PageInfo<Brand> pageInfo = this.brandService.findBrandPage(pageIndex, pageSize, brand);
        return new Result<PageInfo>(true, StatusCode.OK, "分页多条件查询品牌成功", pageInfo);
    }

}
