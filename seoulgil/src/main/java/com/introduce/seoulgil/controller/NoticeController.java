package com.introduce.seoulgil.controller;

import com.introduce.seoulgil.dto.NoticeDTO;
import com.introduce.seoulgil.dto.PageRequestDTO;
import com.introduce.seoulgil.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notice")
@Log4j2
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService; //final

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String register(NoticeDTO noticeDTO, RedirectAttributes redirectAttributes){
        log.info("noticeDTO: " + noticeDTO);

        Long mno = noticeService.register(noticeDTO);

        redirectAttributes.addFlashAttribute("msg", mno);

        return "redirect:/notice/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("pageRequestDTO: " + pageRequestDTO);


        model.addAttribute("result", noticeService.getList(pageRequestDTO));

    }

    @GetMapping({"/read", "/modify"})
    public void read(long mno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model ){

        log.info("mno: " + mno);

        NoticeDTO noticeDTO = noticeService.getNotice(mno);

        model.addAttribute("dto", noticeDTO);

    }
}
