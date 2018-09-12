package com.lhh.modules.stuMistakeBook.service;


import java.util.List;
import java.util.Map;

import com.lhh.modules.stuMistakeBook.entity.StuMistakeBookEntity;
import com.lhh.modules.stuMistakeBook.entity.StuMistakeBookVo;

/**
 * 学生错题本
 * 
 * @author m.f
 * @email miaof@lanhaihui.net
 * @date 2018-06-22 16:12:43
 */
public interface StuMistakeBookService {
	
	StuMistakeBookEntity queryObject(Integer id);
	
	List<StuMistakeBookEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(StuMistakeBookEntity stuMistakeBook);
	
	void update(StuMistakeBookEntity stuMistakeBook);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 科目信息统计  (待复习题目数)
	 * @Author: cuihp
	 * @Date: 2018/6/23
	 */
	List<Map<String,Object>> querySubjectInfo(Map<String,Object> params);
	/**
	 * 查询 最外层册别信息(待复习题目数  已掌握题目数)
	 * @Author: cuihp
	 * @Date: 2018/6/23
	 */
	Map<String,Object> queryBookInfo(Map<String,Object> params);
	/**
	 * 统计章节信息(待复习题目数  已掌握题目数)
	 * @Author: cuihp
	 * @Date: 2018/6/23
	 */
	List<Map<String,Object>> querySectionInfo (Map<String,Object> params);
	/**
	 * 根据条件查询错题信息
	 * @name: queryListByMap
	 * @params: [params]
	 * @return: java.util.List<com.lhh.modules.stuMistakeBook.entity.StuMistakeBookEntity>
	 * @Author: cuihp
	 * @Date: 2018/6/24
	 */
	List<StuMistakeBookVo > queryListByMap (Map<String,Object> params);

	List<Map<String,Object>> queryRank(Map<String,Object> params);

	Map<String,Object> queryRank4stu(Map<String,Object> params);
	/**
	 * 错题本插入(java)
	 * @name: insertMistakeBook
	 * @params: [HomeworkId, stuId, classId, bNum, sNum]
	 * @return: boolean
	 * @Author: cuihp
	 * @Date: 2018/8/21
	 */
	boolean insertMistakeBook(Integer HomeworkId,String stuId,String classId, String bNum, String sNum);

	Map<String,Object> queryList4noSection(Map<String,Object> params);

	List<StuMistakeBookVo> queryListByMap4NoSection(Map<String,Object>params);
}
