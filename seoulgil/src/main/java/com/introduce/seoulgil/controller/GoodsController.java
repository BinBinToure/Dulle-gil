package com.introduce.seoulgil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoodsController {
    @GetMapping("/goods")
    public String goods(){ return "goods/goods"; }
    @GetMapping("/goodsRegistration")
    public String goodsRegistration(){
        return "goods/goodsRegistration";
    }
}
