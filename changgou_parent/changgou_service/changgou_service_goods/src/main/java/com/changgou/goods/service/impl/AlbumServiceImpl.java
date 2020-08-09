package com.changgou.goods.service.impl;

import com.changgou.goods.dao.AlbumMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
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
 * @date 2020/8/1 20:07
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public PageInfo<Album> findPage(Album album, int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //构建查询条件
        Example example = createExample(album);
        //分页查询
        return new PageInfo<Album>(albumMapper.selectByExample(example));
    }

    @Override
    public PageInfo<Album> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<Album>(albumMapper.selectAll());
    }

    @Override
    public List<Album> findList(Album album) {
        Example example = createExample(album);
        return albumMapper.selectByExample(example);
    }

    @Override
    public void delete(Long id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKey(album);
    }

    @Override
    public void add(Album album) {
        albumMapper.insert(album);
    }

    @Override
    public Album findById(Long id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }

    /**
     * Album构建查询对象
     *
     * @param album
     * @return
     */
    public Example createExample(Album album) {
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if (album != null) {
            // 编号
            if (StringUtils.isNotBlank(album.getId().toString())) {
                criteria.andEqualTo("id", album.getId());
            }
            // 相册名称
            if (StringUtils.isNotBlank(album.getTitle())) {
                criteria.andLike("title", "%" + album.getTitle() + "%");
            }
            // 相册封面
            if (!StringUtils.isNotBlank(album.getImage())) {
                criteria.andEqualTo("image", album.getImage());
            }
            // 图片列表
            if (StringUtils.isNotBlank(album.getImageItems())) {
                criteria.andEqualTo("imageItems", album.getImageItems());
            }
        }
        return example;
    }


}
