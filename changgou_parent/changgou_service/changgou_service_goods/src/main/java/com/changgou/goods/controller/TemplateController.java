package com.changgou.goods.controller;

import com.changgou.framework.entity.Result;
import com.changgou.framework.entity.StatusCode;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
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
@Api(value = "规格参数模板Controller", tags = {"规格参数模板访问接口"})
@RestController
@RequestMapping("/template")
@CrossOrigin
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    /**
     * Template分页条件搜索实现
     *
     * @param template
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public Result<PageInfo> findPage(@RequestBody(required = false) Template template, @PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        PageInfo<Template> pageInfo = this.templateService.findPage(template, pageIndex, pageSize);
        return new Result<PageInfo>(true, StatusCode.OK, "分页多条件查询规格参数模板成功", pageInfo);
    }

    /**
     * Template分页搜索实现
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/search/{pageIndex}/{pageSize}")
    public Result<PageInfo> findPage(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        PageInfo<Template> pageInfo = templateService.findPage(pageIndex, pageSize);
        return new Result<PageInfo>(true, StatusCode.OK, "分页查询规格参数模板成功", pageInfo);
    }

    /**
     * Template多条件搜索
     *
     * @param template
     * @return
     */
    @PostMapping("/search")
    public Result<List<Template>> findList(@RequestBody(required = false) Template template) {
        List<Template> list = templateService.findList(template);
        return new Result<List<Template>>(true, StatusCode.OK, "多条件查询规格参数模板成功", list);
    }

    /**
     * 根据ID查询规格参数模板
     *
     * @param id
     * @return
     */
    @GetMapping("/search/{id}")
    public Result<Template> findById(@PathVariable Integer id) {
        Template template = templateService.findById(id);
        return new Result<Template>(true, StatusCode.OK, "添加规格参数模板成功", template);
    }

    /**
     * 查询所有规格参数模板
     *
     * @return
     */
    @GetMapping
    public Result<List<Template>> findAll() {
        List<Template> templateList = templateService.findAll();
        return new Result<List<Template>>(true, StatusCode.OK, "添加规格参数模板成功", templateList);
    }

    /**
     * 根据ID删除规格参数模板数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}}")
    public Result delete(@PathVariable Integer id) {
        templateService.delete(id);
        return new Result(true, StatusCode.OK, "删除规格参数模板成功");
    }

    /**
     * 根据ID修改规格参数模板数据
     *
     * @param template
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Template template) {
        templateService.update(template);
        return new Result(true, StatusCode.OK, "修改规格参数模板成功");
    }

    /**
     * 新增规格参数模板
     *
     * @param template
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Template template) {
        templateService.add(template);
        return new Result(true, StatusCode.OK, "添加规格参数模板成功");
    }

}
