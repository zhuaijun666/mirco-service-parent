package com.zhizu.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 通用的CURD service
 * Created by zhengfeng on 2016-09-27 11:44.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private final static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired
    private Mapper<T> mapper;

    @Override
    public List<T> search(T record) {
        return this.search(record, null);
    }

    @Override
    public List<T> search(T record, String order) {
        Example example = new Example(record.getClass(), false);
        Example.Criteria criteria = example.createCriteria();

        try {
            Map<String, Object> map = PropertyUtils.describe(record);
            for (String name : map.keySet()) {
                if("class".equals(name)) continue;
                Object value = map.get(name);
                if(value != null) {
                    if(String.class.isAssignableFrom(value.getClass())) {
                        criteria.andLike(name, "%"+value+"%");
                    } else {
                        criteria.andEqualTo(name, value);
                    }
                }

            }
        } catch (Exception e) {
            logger.error("common search error: {}", e);
            return Collections.emptyList();
        }

        if(StringUtils.isNotBlank(order)) {
            example.setOrderByClause(order);
        }

        return mapper.selectByExample(example);
    }

    @Override
    public PageInfo<T> search(T record, int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(this.search(record));
    }

    @Override
    public PageInfo<T> search(T record, int page, int size, String order) {
        PageHelper.startPage(page, size, order);
        return new PageInfo<>(this.search(record));
    }

    @Override
    public T findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findOne(T record) {
        List<T> list = mapper.select(record);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public int selectCount(T record) {
        return mapper.selectCount(record);
    }

    @Override
    public List<T> select(T record) {
        return mapper.select(record);
    }

    @Override
    public PageInfo<T> select(T record, int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(mapper.select(record));
    }

    @Override
    public PageInfo<T> select(T record, int page, int size, String order) {
        PageHelper.startPage(page, size, order);
        return new PageInfo<>(mapper.select(record));
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public PageInfo<T> selectAll(int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(mapper.selectAll());
    }

    @Override
    public PageInfo<T> selectAll(int page, int size, String order) {
        PageHelper.startPage(page, size, order);
        return new PageInfo<>(mapper.selectAll());
    }

    public PageInfo<T> selectByConditions(int page, int size, Map<String,Object> equalMap,
                                          Map<String,Object> likeMap, Class cls) {
        PageHelper.startPage(page, size,"id desc");
        Example example = new Example(cls);
        Example.Criteria criteria = example.createCriteria();
        if(equalMap != null && equalMap.size() > 0) {
            for (Map.Entry<String, Object> equalObj : equalMap.entrySet()) {
                criteria.andEqualTo(equalObj.getKey(), equalObj.getValue());
            }
        }
        if(likeMap != null && likeMap.size() > 0) {
            for (Map.Entry<String, Object> equalObj : likeMap.entrySet()) {
                criteria.andLike(equalObj.getKey(), "%" + equalObj.getValue() + "%");
            }
        }
        return new PageInfo<>(mapper.selectByExample(example));
    }

    public List<T> selectByCondition( Map<String,Object> equalMap,Map<String,Object> likeMap,Class cls){
        Example example = new Example(cls);
        Example.Criteria criteria = example.createCriteria();
        if(equalMap != null && equalMap.size() > 0) {
            for (Map.Entry<String, Object> equalObj : equalMap.entrySet()) {
                criteria.andEqualTo(equalObj.getKey(), equalObj.getValue());
            }
        }
        if(likeMap != null && likeMap.size() > 0) {
            for (Map.Entry<String, Object> equalObj : likeMap.entrySet()) {
                criteria.andLike(equalObj.getKey(), "%" + equalObj.getValue() + "%");
            }
        }
        return mapper.selectByExample(example);
    }

    @Override
    public int insert(T record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    @Override
    public int updateById(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByIdSelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(T record) {
        return mapper.delete(record);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public Mapper<T> getMapper() {
        return mapper;
    }
}
