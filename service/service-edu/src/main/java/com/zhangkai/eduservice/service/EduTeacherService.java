package com.zhangkai.eduservice.service;


import com.baomidou.mybatisplus.extension.service.IService;



import com.zhangkai.eduservice.entity.EduTeacher;



public interface EduTeacherService extends IService<EduTeacher>{

  /*  @Resource
    private TeaInfomapper teaInfomapper;

    *//*
    * 根据id进行查询
    * 根据id进行redis缓存
    * *//*
    @Cacheable(cacheNames = {"tea"},key = "#root.args[0]")
    public TeaInfo selectById(Long id){
        TeaInfo teaInfo = teaInfomapper.selectById(id);
        return teaInfo;
    }

    @Cacheable(cacheNames = {"tea"},key = "'all'")
    public List<TeaInfo> findall(){
        List<TeaInfo> teaInfos = teaInfomapper.selectList(null);
        return teaInfos;
    }

    //
    @Cacheable(cacheNames = {"tea"},key = "#page+'page'+#size+'size'")
    public IPage<TeaInfo> querypage(int page,int size){
        Page<TeaInfo> teaInfoPage = new Page<>(page,size);
        teaInfomapper.selectPage(teaInfoPage,null);

        return teaInfoPage;
    }


    *//*
    * 根据id进行保存
    * redis缓存
    * *//*
    @CachePut(cacheNames = {"tea"},key = "#result.id")
    public TeaInfo saveTea(TeaInfo teaInfo){

        teaInfomapper.insert(teaInfo);
        return teaInfo;
    }



    *//*
    * 根据id进行更新
    * redis缓存
    * *//*
    @CachePut(cacheNames = {"tea"},key = "#result.id",unless = "#result==null")
    public TeaInfo updateTea(TeaInfo teaInfo) throws CommonException {
        TeaInfo teaInfo1 = teaInfomapper.selectById(teaInfo.getId());
        if(teaInfo1.getVersion() !=1){
            throw new CommonException(ResultCode.FAIL);
        }
        teaInfo1.setName(teaInfo.getName());
        teaInfo1.setHobby(teaInfo.getHobby());
        teaInfomapper.updateById(teaInfo1);
        return teaInfo1;
    }

    @CacheEvict(cacheNames = {"tea"},key = "#id")
    public Integer deleteTea(Long id){
        int i = teaInfomapper.deleteById(id);
        return i;
    }

*/
}
