package com.example.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.providerApi.dto.DinnerDTO;
import com.example.providerApi.service.DinnerReadService;
import com.example.providerApi.service.DinnerWriteService;
import com.example.providerApi.util.Response;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RestController
@Api("大吉大利，今晚吃鸡")
@RequestMapping("/api/dinner")
public class Dinners {

    @Reference(url = "dubbo://127.0.0.1:20880")
    private DinnerWriteService dinnerWriteService;

    @Reference(url = "dubbo://127.0.0.1:20880")
    private DinnerReadService dinnerReadService;

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID查找")
    public DinnerDTO find(@PathVariable Long id) throws Exception {
        Response<DinnerDTO> resp = dinnerReadService.findById(id);
        if (!resp.isSuccess()) {
            log.error("find dinner by id:{} fail, cause:{}", id, resp.getError());
            //TODO: 约定统一的Exception
            throw new Exception(resp.getError());
        }
        return resp.getResult();
    }

    @GetMapping
    @ApiOperation(value = "批量查找")
    public List<DinnerDTO> findByIds(List<Long> ids) throws Exception {
        Response<List<DinnerDTO>> resp = dinnerReadService.findByIds(ids);
        if (!resp.isSuccess()) {
            log.error("find dinner by ids:{} fail, cause:{}", ids, resp.getError());
            //TODO: 约定统一的Exception
            throw new Exception(resp.getError());
        }
        return resp.getResult();
    }

    @PostMapping
    @ApiOperation(value = "创建&更新")
    public Boolean save(@RequestBody DinnerDTO dinnerDTO) throws Exception {
        Response<Boolean> response = dinnerWriteService.save(dinnerDTO);
        if (!response.isSuccess()) {
            log.error("save dinner:{} fail, cause:{}", dinnerDTO, response.getError());
            throw new Exception(response.getError());
        }
        return response.getResult();
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Boolean deleteById(Long id) throws Exception {
        Response<Boolean> resp = dinnerWriteService.deleteById(id);
        if (!resp.isSuccess()) {
            log.error("delete dinner by id:{} fail, cause:{}", id, resp.getError());
            throw new Exception(resp.getError());
        }
        return resp.getResult();
    }

    @GetMapping("/swagger")
    public void swagger() throws MalformedURLException {
        Path outputFile = Paths.get("build/swagger");
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();
        Swagger2MarkupConverter converter = Swagger2MarkupConverter.from(new URL("http://localhost:8000/v2/api-docs"))
                .withConfig(config)
                .build();
        converter.toFile(outputFile);
    }
}
