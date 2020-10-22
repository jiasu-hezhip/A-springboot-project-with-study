package com.sll.demo.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sll.demo.eduservice.entity.EduSubject;
import com.sll.demo.eduservice.entity.OneSubject;
import com.sll.demo.eduservice.entity.TwoSubject;
import com.sll.demo.eduservice.entity.excel.Subject;
import com.sll.demo.eduservice.listener.SubjectExcelListener;
import com.sll.demo.eduservice.mapper.EduSubjectMapper;
import com.sll.demo.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-10-17
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try{
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, Subject.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e){

        }
    }

    @Override
    public List<OneSubject> getAllSubject() {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id","0");
        List<EduSubject> list = baseMapper.selectList(queryWrapper);

        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper.ne("parent_id","0");
        List<EduSubject> list2 = baseMapper.selectList(queryWrapper2);

        List<OneSubject> oneSubjectList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            EduSubject eduSubject = list.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);//spring工具类的复制
            oneSubjectList.add(oneSubject);
            List<TwoSubject> twoSubjectList = new ArrayList<>();

            for (int i1 = 0; i1 < list2.size(); i1++) {
                EduSubject eduSubject1 = list2.get(i1);
                if (eduSubject1.getParentId().equals(oneSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubject1,twoSubject);
                    twoSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoSubjectList);
        }


        return oneSubjectList;
    }
}
