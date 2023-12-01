package org.dreams.weyun.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dreams.weyun.domain.entity.School;
import org.dreams.weyun.domain.vo.request.SchoolAddOrPutRequest;
import org.dreams.weyun.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:  前端控制器
 *
 * @author luoan
 * @since 2023/11/29
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/weyun/api/school")
public class SchoolController {
    private SchoolService schoolService;
    @PostMapping()
    public void add(@Valid @RequestBody SchoolAddOrPutRequest school) {
        schoolService.addSchool(school);
    }
    @PutMapping()
    public void put(@Valid @RequestBody SchoolAddOrPutRequest school) {
        schoolService.updateSchool(school);
    }
    @DeleteMapping()
    public void batchDel(@RequestBody List<Integer> ids) {
        schoolService.deleteSchoolByIds(ids);
    }
    @DeleteMapping("{id}")
    public void del(@PathVariable("id") Integer id) {
        schoolService.deleteSchoolById(id);
    }
    @GetMapping()
    public List<School> queryAll() {
        School school = School.builder()
                .schoolId(1)
                .schoolName("好梦教育")
                .regionId(100)
                .schoolAddress("洛神遗迹")
                .schoolBrief("千百年前的遗迹")
                .schoolMailbox("1756174331@qq.com")
                .schoolType("教育edu")
                .schoolPhone("173****5575")
                .build();
        return List.of(school);
    }

    @GetMapping("{id}")
    public School queryById(@PathVariable("id") Integer id) {
        return schoolService.querySchoolById(id);
    }

    @GetMapping("{encode}/{name}")
    public School queryByEncodeAndName(@PathVariable("encode") String encode, @PathVariable("name") String name) {
        return schoolService.querySchoolByEncodeAndName(encode, name);
    }
}
