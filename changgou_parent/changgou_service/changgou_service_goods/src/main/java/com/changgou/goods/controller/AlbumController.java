package com.changgou.goods.controller;

import com.changgou.framework.entity.Result;
import com.changgou.framework.entity.StatusCode;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 常恃豪
 * @version 1.0
 * @date 2020/8/1 19:23
 */
@Api(value = "相册Controller", tags = {"相册访问接口"})
@RestController
@RequestMapping("/album")
@CrossOrigin
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    /**
     * Album分页条件搜索实现
     *
     * @param album
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @PostMapping("/search/{pageIndex}/{pageSize}")
    public Result<PageInfo> findPage(@RequestBody(required = false) Album album, @PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        PageInfo<Album> pageInfo = albumService.findPage(album, pageIndex, pageSize);
        return new Result<PageInfo>(true, StatusCode.OK, "分页多条件查询相册成功", pageInfo);
    }

    /**
     * Album分页搜索实现
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/search/{pageIndex}/{pageSize}")
    public Result<PageInfo> findPage(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        PageInfo<Album> pageInfo = albumService.findPage(pageIndex, pageSize);
        return new Result<PageInfo>(true, StatusCode.OK, "分页查询相册成功", pageInfo);
    }

    /**
     * Album多条件搜索
     *
     * @param album
     * @return
     */
    @PostMapping("/search")
    public Result<List<Album>> findList(@RequestBody(required = false) Album album) {
        List<Album> list = albumService.findList(album);
        return new Result<List<Album>>(true, StatusCode.OK, "多条件查询相册成功", list);
    }

    /**
     * 根据ID查询相册
     *
     * @param id
     * @return
     */
    @GetMapping("/search/{id}")
    public Result<Album> findById(@PathVariable Long id) {
        Album album = albumService.findById(id);
        return new Result<Album>(true, StatusCode.OK, "添加相册成功", album);
    }

    /**
     * 查询所有相册
     *
     * @return
     */
    @GetMapping
    public Result<List<Album>> findAll() {
        List<Album> albumList = albumService.findAll();
        return new Result<List<Album>>(true, StatusCode.OK, "添加相册成功", albumList);
    }

    /**
     * 根据ID删除相册数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}}")
    public Result delete(@PathVariable Long id) {
        albumService.delete(id);
        return new Result(true, StatusCode.OK, "删除相册成功");
    }

    /**
     * 根据ID修改相册数据
     *
     * @param album
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Album album) {
        albumService.update(album);
        return new Result(true, StatusCode.OK, "修改相册成功");
    }

    /**
     * 新增相册
     *
     * @param album
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Album album) {
        albumService.add(album);
        return new Result(true, StatusCode.OK, "添加相册成功");
    }


}
