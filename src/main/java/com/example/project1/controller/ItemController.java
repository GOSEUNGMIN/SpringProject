package com.example.project1.controller;

import com.example.project1.dto.ItemDto;
import com.example.project1.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController
{
    private final TaskService taskService;
    @GetMapping
    public String Item(Model model)
    {
        List<ItemDto> itemList = taskService.list();
        model.addAttribute("itemlist", itemList);
        return "Item/Item";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model)
    {
        ItemDto item = taskService.detail(id);
        model.addAttribute("item", item);
        return "Item/detail";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id)
    {
        taskService.delete(id);
        return "redirect:/item";
    }

    @GetMapping("/writer")
    public String getwriter()
    {
        return "Item/writer";
    }

    @PostMapping("/writer")
    public String postwriter(@Validated ItemDto dto)
    {
        taskService.writer(dto);
        return "redirect:/item";
    }

    @GetMapping("/modify/{id}")
    public String getmodify(@PathVariable("id") Integer id, Model model)
    {
        ItemDto item = taskService.detail(id);
        model.addAttribute("modifyitem", item);
        return "Item/modify";
    }

    @PostMapping("/modify/{id}")
    public String postmodify(@Validated ItemDto dto)
    {
        taskService.modify(dto);
        return "redirect:/item/detail/{id}";
    }

}
