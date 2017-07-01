package ali;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;

public class testkkk 
{
public static void sample(){
		
	    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI6aJt2fTLnNyh", "aQXP7gpPRQx6tlzEnUAT9tDbbqkCa1");
	     try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms",  "sms.aliyuncs.com");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    IAcsClient client = new DefaultAcsClient(profile);
	    SingleSendSmsRequest request = new SingleSendSmsRequest();
	    try{
	        request.setSignName("³ÂéÅ");
	        request.setTemplateCode("SMS_73515001");
	        request.setParamString( "{\"code\" : \"123\"}");
	        request.setRecNum("13238377394");
	        SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
	    }catch(Exception e) {
	        e.printStackTrace();
	    }
	    }
}
