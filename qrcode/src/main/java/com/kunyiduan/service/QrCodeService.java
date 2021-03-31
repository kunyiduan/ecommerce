package com.kunyiduan.service;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

@Service
public class QrCodeService {

    public String createQrCode(){
        return QrCodeUtil.generateAsBase64("duankunyi", new QrConfig(), ImgUtil.IMAGE_TYPE_PNG);
    }

}
