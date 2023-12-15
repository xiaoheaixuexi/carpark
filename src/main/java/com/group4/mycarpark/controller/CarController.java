package com.group4.mycarpark.controller;

import com.group4.mycarpark.entity.*;
import com.group4.mycarpark.service.CardService;
import com.group4.mycarpark.service.IncomeService;
import com.group4.mycarpark.service.ParkInfoAllService;
import com.group4.mycarpark.service.ParkInfoService;
import com.group4.mycarpark.util.CalculateCost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Random;


@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    private CardService cardService;

    @Autowired
    private ParkInfoService parkInfoService;

    @Autowired
    private ParkInfoAllService parkInfoAllService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IncomeService incomeService;

    /*
    *  车入库时发送的请求
    *  image 为待识别的图片
    * */
    @PostMapping("checkin")
    public ResponseEntity<String> checkin(@RequestParam("file") MultipartFile image , HttpSession session) throws IOException {


        //根据图片获取车牌
        Msg res= this.getCarNumByImage(image);

        //识别失败的情况
        if(res.getCode()!=0){
            return new ResponseEntity<>(res.getMsg(), HttpStatus.OK);
        }

        //获取车牌
        String carNum = res.getMsg();

        //查询车库中是否有该车辆，避免重复写入
        ParkInfo isExist = parkInfoService.findParkInfo(carNum);
        if(isExist != null){
            return new ResponseEntity<>("对不起，您已入库", HttpStatus.OK);
        }

        //根据车牌查找注册该车牌的卡号
        Card card = cardService.findCardByCarNum(carNum);

        ParkInfo parkInfo = new ParkInfo();
        parkInfo.setCarnum(carNum);
        parkInfo.setParknum(1);
        parkInfo.setParkin(new Date());
        //如果有卡
        if(card != null){
            parkInfo.setCardnum(card.getCardnum());
            parkInfo.setParktemp(0);
        }else{
            //如果没有卡，就不让进
            //本来可以让没卡的进，但还没实现支付功能，就索性拒绝了
            return new ResponseEntity<>("您的车牌未注册", HttpStatus.OK);
            //parkInfo.setParktemp(1);
        }
        //将parkInfo存入数据库
        parkInfoService.saveParkInfo(parkInfo);

        //成功返回
        return new ResponseEntity<>(carNum + "    欢迎您", HttpStatus.OK);
    }


    //车出库时发送的请求
    @PostMapping("checkout")
    @Transactional
    public ResponseEntity<String> checkout(@RequestParam("file") MultipartFile image , HttpSession session) throws IOException {

        Msg res= this.getCarNumByImage(image);

        String carNum = res.getMsg();

        //该车出库但没有入库信息的处理情况
        ParkInfo parkInfo = parkInfoService.findParkInfo(carNum);
        if(parkInfo == null){
            return new ResponseEntity<>("你是怎么进来的？", HttpStatus.OK);
        }

        //该车出库但没有卡的处理情况
        Card card = cardService.findCardByCarNum(carNum);
        if(card == null){
            return new ResponseEntity<>("您的车牌未注册,你是怎么进来的？", HttpStatus.OK);
        }

        //计算总时间
        long duration = new Date().getTime() - parkInfo.getParkin().getTime();

        //计算价格
        double cost = CalculateCost.calculateCost(duration);

        //判断余额是否充足
        if(cost > card.getMoney()){
            return new ResponseEntity<>("您的余额不足，请充值", HttpStatus.OK);
        }

        //扣除卡里的钱
        cardService.changeMoney(card.getId(),-cost);

        //设置完整的停车记录
        ParkInfoAll parkInfoAll = new ParkInfoAll();
        parkInfoAll.setCardnum(parkInfo.getCardnum());
        parkInfoAll.setParkin(parkInfo.getParkin());
        parkInfoAll.setParktemp(parkInfo.getParktemp());
        parkInfoAll.setParknum(parkInfo.getParktemp());
        parkInfoAll.setCarnum(parkInfo.getCarnum());
        parkInfoAll.setParkout(new Date());
        //将完整的信息存入数据库
        parkInfoAllService.saveParkInfoAll(parkInfoAll);

        //删除数据库中的车辆在停记录
        parkInfoService.clearRecord(carNum);

        //将收费记录写入数据库
        Income income = new Income();
        income.setMoney(cost);
        income.setMethod(9);  // 9为从卡中扣费
        income.setType(card.getType());
        income.setCarnum(carNum);
        income.setCardnum(card.getCardnum());
        income.setTime(new Date());
        income.setDuration(duration);
        incomeService.saveIncome(income);

        return new ResponseEntity<>(carNum + "     再见" + ",     本次的消费为" + cost +"元" , HttpStatus.OK);
    }

//    //将图片上传到服务器
//    @PostMapping("/upload")
//    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile image , HttpSession session) throws IOException {
//
//        String url = "http://192.168.1.108:8080/api/upload";
//        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
//        ByteArrayResource resource = new ByteArrayResource(image.getBytes()) {
//            @Override
//            public String getFilename() {
//                return image.getOriginalFilename();
//            }
//        };
//        paramMap.add("file",resource);
//        Msg res = restTemplate.postForObject(url,paramMap,Msg.class);
//        return new ResponseEntity<>(res.getMsg(), HttpStatus.OK);
//    }

    //根据图片远程调用另一台服务器获得车牌号
    //车牌号为Msg.msg
    public Msg getCarNumByImage(MultipartFile image) throws IOException {

        if(true){
            Msg msg = new Msg();
            msg.setCode(0);
            int random = new Random().nextInt(4);
            if(random == 0){
                msg.setMsg("黑A1R272");
            }else if(random == 1){
                msg.setMsg("888888");
            }else if(random ==3){
                msg.setMsg("沪KR9888");
            }else{
                msg.setMsg("沪K62933");
            }

            return msg;
        }

        String url = "http://192.168.1.108:8080/api/upload";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        ByteArrayResource resource = new ByteArrayResource(image.getBytes()) {
            @Override
            public String getFilename() {
                return image.getOriginalFilename();
            }
        };
        paramMap.add("file",resource);
        Msg res = restTemplate.postForObject(url,paramMap,Msg.class);
        return res;
    }

}
