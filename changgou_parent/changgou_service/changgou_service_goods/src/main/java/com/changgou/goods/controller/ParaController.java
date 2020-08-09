package com.changgou.goods.controller;

import com.changgou.framework.entity.Result;
import com.changgou.framework.entity.StatusCode;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.service.ParaService;
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
@Api(value = "参数Controller", tags = {"参数访问接口"})
@RestController
@RequestMapping("/para")
@CrossOrigin
public class ParaController {

    @Autowired
    private ParaService paraService;

    /**
     * Para分页条件搜索实现
     *
     * @param para
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @PostMapping("/search/{pageIndex}/{pageSize}")
    public Result<PageInfo> findPage(@RequestBody(required = false) Para para, @PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        PageInfo<Para> pageInfo = this.paraService.findPage(para, pageIndex, pageSize);
        return new Result<PageInfo>(true, StatusCode.OK, "分页多条件查询参数成功", pageInfo);
    }

    /**
     * Para分页搜索实现
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/search/{pageIndex}/{pageSize}")
    public Result<PageInfo> findPage(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        PageInfo<Para> pageInfo = paraService.findPage(pageIndex, pageSize);
        return new Result<PageInfo>(true, StatusCode.OK, "分页查询参数成功", pageInfo);
    }

    /**
     * Para多条件搜索
     *
     * @param para
     * @return
     */
    @PostMapping("/search")
    public Result<List<Para>> findList(@RequestBody(required = false) Para para) {
        List<Para> list = paraService.findList(para);
        return new Result<List<Para>>(true, StatusCode.OK, "多条件查询参数成功", list);
    }

    /**
     * 根据ID查询参数
     *
     * @param id
     * @return
     */
    @GetMapping("/search/{id}")
    public Result<Para> findById(@PathVariable Integer id) {
        Para para = paraService.findById(id);
        return new Result<Para>(true, StatusCode.OK, "添加参数成功", para);
    }

    /**
     * 查询所有参数
     *
     * @return
     */
    @GetMapping
    public Result<List<Para>> findAll() {
        List<Para> paraList = paraService.findAll();
        return new Result<List<Para>>(true, StatusCode.OK, "添加参数成功", paraList);
    }

    /**
     * 根据ID删除参数数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}}")
    public Result delete(@PathVariable Integer id) {
        paraService.delete(id);
        return new Result(true, StatusCode.OK, "删除参数成功");
    }

    /**
     * 根据ID修改参数数据
     *
     * @param para
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Para para) {
        paraService.update(para);
        return new Result(true, StatusCode.OK, "修改参数成功");
    }

    /**
     * 新增参数
     *
     * @param para
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Para para) {
        paraService.add(para);
        return new Result(true, StatusCode.OK, "添加参数成功");
    }

}
