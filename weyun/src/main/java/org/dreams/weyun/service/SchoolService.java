package org.dreams.weyun.service;

import org.dreams.weyun.domain.entity.School;
import org.dreams.weyun.domain.vo.request.SchoolAddOrPutRequest;

import java.util.List;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/11/29
 */
public interface SchoolService {
    /**
     * 添加学校
     *
     * @param school 添加学校
     */
    void addSchool(SchoolAddOrPutRequest school);
    void updateSchool(SchoolAddOrPutRequest school);

    /**
     * 按 ID 删除学校
     *
     * @param id ID
     */
    void deleteSchoolById(Integer id);

    /**
     * 批量按 ID 删除学校
     *
     * @param ids IDS
     */
    void deleteSchoolByIds(List<Integer> ids);

    /**
     * 按编码和名称查询学校
     *
     * @param encode 编码
     * @param name 名称
     * @return {@link School}
     */
    School querySchoolByEncodeAndName(String encode, String name);

    /**
     * 按 ID 查询学校
     *
     * @param id ID
     * @return {@link School}
     */
    School querySchoolById(Integer id);
}
