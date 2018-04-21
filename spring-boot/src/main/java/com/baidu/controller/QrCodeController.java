package com.baidu.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ImageUtil;
import cn.hutool.core.util.URLUtil;
import com.baidu.Util.MathUtil;
import com.baidu.Util.QrCodeUtil;
import com.baidu.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * Created by dangsl on 2018/4/21.
 */
@RequestMapping("image")
@Controller
public class QrCodeController {

    @RequestMapping(value = "getImage",method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(){
        //要转化二维码的链接
        User user = new User();
        user.setId("960832482708553728");
        user.setNickName("祭司");
        String url = "http://tw.joinsilk.com/user/login.html?recommendId=" + user.getId();
        //微信昵称
        String userName = user.getNickName();
        //最终图片宽高
        int w = 520,h = 853;
        //二维码高度
        int codeW = MathUtil.divide(w,3).intValue();
        //生成二维码
        BufferedImage generateCode = QrCodeUtil.generate(url, codeW, codeW);
        //获取背景图片
        BufferedImage backgroundImage = ImageUtil.read(URLUtil.url("http://livefile.51ryd.com/976295506168774656"));
        //将二维码当作图片水印放置在背景图片上
        BufferedImage pressImage = ImageUtil.pressImage(backgroundImage, generateCode, 0, codeW-40, 0.9f);
        //将“我是 ”和微信昵称当作文字水印放置在背景图片上
        BufferedImage pressText = ImageUtil.pressText(pressImage, "我是 "+userName, Color.BLACK, new Font("宋体", 0, 27), 0, codeW+112, 0.9f);
        //输出结果
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageUtil.write(pressText, FileUtil.file("E:\\weixin"+"/qrcode.png")); // 保存到本地指定目录
        ImageUtil.writeJpg(pressText,os);
        byte[] bytes = os.toByteArray();
        // 输出到前台展示
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
}
