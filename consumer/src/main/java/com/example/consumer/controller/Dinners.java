package com.example.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.consumer.cache.DinnerCache;
import com.example.providerApi.dto.DinnerDTO;
import com.example.providerApi.service.DinnerReadService;
import com.example.providerApi.service.DinnerWriteService;
import com.example.providerApi.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/dinner")
public class Dinners {

    @Reference(url = "dubbo://127.0.0.1:20880")
    private DinnerWriteService dinnerWriteService;

    @Reference(url = "dubbo://127.0.0.1:20880")
    private DinnerReadService dinnerReadService;

    @Autowired
    private DinnerCache dinnerCache;

    @GetMapping("{id}")
    public DinnerDTO find(@PathVariable Long id) throws Exception {
        Response<DinnerDTO> resp = dinnerReadService.findById(id);
        if (!resp.isSuccess()) {
            //TODO: 约定统一的Exception
            throw new Exception(resp.getError());
        }
        return resp.getResult();
    }

    @GetMapping
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
    public Boolean save(@RequestBody DinnerDTO dinnerDTO) throws Exception {
        Response<Boolean> response = dinnerWriteService.save(dinnerDTO);
        if (!response.isSuccess()) {
            log.error("save dinner:{} fail, cause:{}", dinnerDTO, response.getError());
            throw new Exception(response.getError());
        }
        return response.getResult();
    }

    @DeleteMapping
    public Boolean deleteById(Long id) throws Exception {
        Response<Boolean> resp = dinnerWriteService.deleteById(id);
        if (!resp.isSuccess()) {
            log.error("delete dinner by id:{} fail, cause:{}", id, resp.getError());
            throw new Exception(resp.getError());
        }
        return resp.getResult();
    }

    @GetMapping("cache")
    public DinnerDTO findByIdFromCache(Long id) throws Exception {
        try {
            return dinnerCache.findById(id);
        } catch (Exception e) {
            log.error("fail, cause:{}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

//    @GetMapping("/swagger")
//    public void swagger() throws MalformedURLException {
//        Path outputFile = Paths.get("build/swagger");
//        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
//                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
//                .withOutputLanguage(Language.ZH)
//                .withPathsGroupedBy(GroupBy.TAGS)
//                .withGeneratedExamples()
//                .withoutInlineSchema()
//                .build();
//        Swagger2MarkupConverter converter = Swagger2MarkupConverter.from(new URL("http://localhost:8000/v2/api-docs"))
//                .withConfig(config)
//                .build();
//        converter.toFile(outputFile);
//    }
}
