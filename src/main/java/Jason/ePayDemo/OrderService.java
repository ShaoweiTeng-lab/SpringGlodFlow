package Jason.ePayDemo;


import Jason.ePayDemo.dao.GoldFlowDao;
import Jason.ePayDemo.dto.GoldFlowRequest;
import Jason.ePayDemo.model.GoldFlow;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.QueryTradeInfoObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

@Service
public class OrderService  {
	@Value("${returnHttps}")
	private String returnHttps;
	public OrderService() {
		System.out.println("初始化");
//		InputStream input = null;
//		try {
//			input = new FileInputStream("../returnUrl.properties");
//		} catch (FileNotFoundException e) {
//			throw new RuntimeException(e);
//		}
//		Properties prop = new Properties();
//		try {
//			prop.load(input);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//
//		// 取得屬性值
//		returnHttps= prop.getProperty("returnHttps");
 	System.out.println(returnHttps);
	}

	@Autowired
	GoldFlowDao goldFlowDao;
    AllInOne all = new AllInOne("");
	@Transactional
	public String generateEcpayNum(String goldFlowNumber,String productName,String total){

		AllInOne all = new AllInOne("");
		String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
		AioCheckOutALL obj = new AioCheckOutALL();
		obj.setMerchantTradeNo(uuId);
		obj.setMerchantTradeDate("2023/06/10 08:05:23");
		obj.setTotalAmount(total);
		obj.setTradeDesc("test Description");
		obj.setItemName(productName);
		// 交易結果回傳網址，只接受 https 開頭的網站，可以使用 ngrok	obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
		// 商店轉跳網址 (Optional)
		obj.setReturnURL(returnHttps+"/successPay/"+goldFlowNumber);
		String form = all.aioCheckOut(obj, null);
		obj.getMerchantTradeNo();

		return form;
	}
	public String  createGoldFlow(GoldFlowRequest goldFlowRequest){
		System.out.println(returnHttps);
		String goldFlowNumber = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20) ;
		GoldFlow goldFlow =new GoldFlow();
		goldFlow.setGoldFlowNum(goldFlowNumber);
		goldFlow.setIsPay("false");
		goldFlowDao.createGoldFlow(goldFlow);

		String total=String.valueOf(goldFlowRequest.getPrice()) ;

		return generateEcpayNum(goldFlowNumber,goldFlowRequest.getProductName(),total);
	}

}
