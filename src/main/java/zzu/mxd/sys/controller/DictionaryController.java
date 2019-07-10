package zzu.mxd.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import zzu.mxd.common.PageRequest;
import zzu.mxd.common.R;
import zzu.mxd.sys.entity.Dictionary;
import zzu.mxd.sys.service.IDictionaryService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mxd
 * @since 2019-04-22
 */
@Api(tags = "字典表管理")
@RestController
@RequestMapping("/sys/dictionary")
public class DictionaryController {

    @Autowired
    private IDictionaryService dictionaryService;

    @ApiOperation("字典表条件查询")
    @PostMapping(consumes = "application/json")
    public R<Page<Dictionary>> select(@RequestBody Dictionary dictionary){
        Page<Dictionary> page = new Page(1,50);
        return R.succC((Page<Dictionary>)dictionaryService.page(page,new QueryWrapper<>(dictionary)));
    }
}
