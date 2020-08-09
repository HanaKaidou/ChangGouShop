package com.changgou.goods.controller;

import com.changgou.framework.entity.Result;
import com.changgou.framework.entity.StatusCode;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.service.SpecService;
import com.changgou.goods.service.SpecService;
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
@Api(value = "规格Controller", tags = {"规格访问接口"})
@RestController
@RequestMapping("/spec")
@CrossOrigin
public class SpecController {

    @Autowired
    private SpecService specService;

    /**
     * Spec分页条件搜索实现
     *
     * @param spec
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @PostMapping("/search/{pageIndex}/{pageSize}")
    public Result<PageInfo> findPage(@RequestBody(required = false) Spec spec, @PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        PageInfo<Spec> pageInfo = this.specService.findPage(spec, pageIndex, pageSize);
        return new Result<PageInfo>(true, StatusCode.OK, "分页多条件查询规格成功", pageInfo);
    }

    /**
     * Spec分页搜索实现
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/search/{pageIndex}/{pageSize}")
    public Result<PageInfo> findPage(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        PageInfo<Spec> pageInfo = specService.findPage(pageIndex, pageSize);
        return new Result<PageInfo>(true, StatusCode.OK, "分页查询规格成功", pageInfo);
    }

    /**
     * Spec多条件搜索
     *
     * @param spec
     * @return
     */
    @PostMapping("/search")
    public Result<List<Spec>> findList(@RequestBody(required = false) Spec spec) {
        List<Spec> list = specService.findList(spec);
        return new Result<List<Spec>>(true, StatusCode.OK, "多条件查询规格成功", list);
    }

    /**
     * 根据ID查询规格
     *
     * @param id
     * @return
     */
    @GetMapping("/search/{id}")
    public Result<Spec> findById(@PathVariable Integer id) {
        Spec spec = specService.findById(id);
        return new Result<Spec>(true, StatusCode.OK, "添加规格成功", spec);
    }

    /**
     * 查询所有规格
     *
     * @return
     */
    @GetMapping
    public Result<List<Spec>> findAll() {
        List<Spec> specList = specService.findAll();
        return new Result<List<Spec>>(true, StatusCode.OK, "添加规格成功", specList);
    }

    /**
     * 根据ID删除规格数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}}")
    public Result delete(@PathVariable Integer id) {
        specService.delete(id);
        return new Result(true, StatusCode.OK, "删除规格成功");
    }

    /**
     * 根据ID修改规格数据
     *
     * @param spec
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Spec spec) {
        specService.update(spec);
        return new Result(true, StatusCode.OK, "修改规格成功");
    }

    /**
     * 新增规格
     *
     * @param spec
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Spec spec) {
        specService.add(spec);
        return new Result(true, StatusCode.OK, "添加规格成功");
    }

}
