package sm.sm4;

import com.alibaba.fastjson.JSON;
import com.youzan.pay.core.api.model.response.DataResult;
import com.youzan.pay.crypt.sdk.common.enums.EncryptorEnum;
import com.youzan.pay.crypt.sdk.ead.EadClient;
import com.youzan.pay.crypt.sdk.ead.config.EadConfig;
import com.youzan.pay.crypt.sdk.ead.request.DecryptRequest;
import com.youzan.pay.crypt.sdk.ead.request.EncryptRequest;
import com.youzan.pay.crypt.sdk.ead.response.EncryptResponse;

/**
 * @ClassName TestSm4
 * @Description TODO
 * @Author boy
 * @Date 2020/9/2 1:51 PM
 */
public class TestSm4 {
    public static void main(String args[]){
        // 有参构造，可以自定义路径
        EadConfig eadConfig = new EadConfig();
        eadConfig.setAppId("pay-crypt");


        EadClient third = new EadClient(eadConfig);
        // 无参构造，不需要自定义密钥存储路径
//        EadClient third = new EadClient();

        //加密示例
        EncryptRequest request = new EncryptRequest();
        request.setPlainText("     中国 人");
        request.setAppId("pay-crypt");
        request.setKeyIndex("0");
        request.setEncryptorEnum(EncryptorEnum.SM4);
        DataResult<EncryptResponse> rep =  third.encrypt(request);
        System.out.println(JSON.toJSONString(rep));

        //解密示例
        DecryptRequest request1 = new DecryptRequest();
        request1.setCipherText(rep.getData().getCipherText());
        request1.setAppId("pay-crypt");
        request1.setKeyIndex("0");
        request1.setEncryptorEnum(EncryptorEnum.SM4);
        System.out.println(JSON.toJSONString(third.decrypt(request1)));
    }
}
