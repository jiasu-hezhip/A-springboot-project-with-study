package com.sll.demo.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sll.demo.eduservice.entity.EduSubject;
import com.sll.demo.eduservice.entity.excel.Subject;
import com.sll.demo.eduservice.service.EduSubjectService;
import com.sll.servicebase.exception.GuliException;

public class SubjectExcelListener extends AnalysisEventListener<Subject> {

    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(Subject subject, AnalysisContext analysisContext) {
        if (subject == null){
            throw new GuliException(20001,"文件数据为空");
        }
        System.out.println(subject.toString());
        //添加一级分类
        EduSubject eduSubject = this.existOne(subjectService, subject.getOneSubject());
        if (eduSubject == null){
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(subject.getOneSubject());
            subjectService.save(eduSubject);
        }
        System.out.println(eduSubject.toString());
        //添加二级分类
        String pid = eduSubject.getId();
        EduSubject eduSubject2 = this.existTwo(subjectService, subject.getTwoSubject(),pid);
        if (eduSubject2 == null){
            eduSubject2 = new EduSubject();
            eduSubject2.setParentId(pid);
            eduSubject2.setTitle(subject.getTwoSubject());
            subjectService.save(eduSubject2);
        }
        System.out.println(eduSubject2.toString());
    }

    private EduSubject existOne(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }
    private EduSubject existTwo(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
