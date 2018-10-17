package utils;

import com.alibaba.fastjson.JSONObject;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guojiabin
 * @version 2018/10/17 0017 9:57
 */
public class ProjectUtils {
    public static void main(String[] args) {
        String json="{\"order_sn\":\"8000000000049801\",\"order_id\":\"527\",\"order_logistics_type\":\"2\",\"buyer_id\":\"53123\",\"buyer_name\":\"test\",\"buyer_email\":\"test@kilimall.com\",\"order_message\":\"ASAP\",\"reciver_name\":\"test\",\"dlyp\":0,\"dlyp_name\":\"doorstep\",\"phone\":\"18978787878\",\"address\":\"Kenya Nairobi Nairobi test\",\"country\":\"ke\",\"province_id\":\"Nairobi\",\"city_id\":\"Nairobi\",\"area\":\"Kenya Nairobi Nairobi\",\"street\":\"test\",\"recvier_phone\":\"\",\"add_time\":\"2016-08-01 12:00:00\",\"order_state\":\"30\",\"order_amount\":\"1300.00\",\"goods_amount\":\"800.00\",\"shipping_fee\":\"500.00\",\"shipping_code\":\"CHIL30783\",\"tracking_code\":\"KE10000505\",\"voucher_price\":0,\"payment_code\":\"online\",\"payment_name\":\"Online Payment\",\"payment_time\":\"2016-08-01 12:00:00\",\"shipping_express\":\"KiliExpress\",\"deliver_explain\":\"asap\",\"shipping_time\":\"2016-08-01 12:00:00\",\"weight\":\"6.5Kg\",\"seller_name\":\"testing\",\"finnshed_time\":\"\",\"order_goods_lists\":[{\"goods_id\":\"15104\",\"goods_name\":\"SPORT WATCH\",\"goods_price\":\"500.00\",\"goods_num\":\"1\",\"goods_image\":\"2016/07/413_05060150106613849.jpg\",\"goods_sku\":\"123456\",\"gc_id\":\"1074\",\"goods_type\":\"1\"},{\"goods_id\":\"15105\",\"goods_name\":\"SPORT WATCH\",\"goods_price\":\"100.00\",\"goods_num\":\"1\",\"goods_image\":\"2016/07/413_05060150106613848.jpg\",\"goods_sku\":\"78910\",\"gc_id\":\"1074\",\"goods_type\":\"1\"},]}";
        json="{\"order_sn\":\"8000000003880201\",\"pay_sn\":\"826377688233478241\",\"update_time\":\"2016-06-14 22:35:16\",\"goods_amount\":\"800.00\",\"order_amount\":\"410.00\",\"shipping_fee\":\"1210.00\",\"order_state\":\"10\",\"refund_type\":\"0\",\"shipping_code\":\"CHIL30783\",\"tracking_code\":\"KE10000505\",\"order_logistics_type\":\"1\"}";
        jsonToMemberVariables(json);
    }

    /**
     * api文档 json格式参数，同意转换未本地 String类型参数
     * @param json
     */
    public static  void jsonToMemberVariables(String json){
        String mvPrefix = "public static final String ";
        String memberPrefiex = "KEY_";
        String memberSuffix = ";";
        StringBuilder tempMember = new StringBuilder();
        JSONObject jsonObject = JSONObject.parseObject(json);
        List<String> list = new ArrayList<>();
        for (String s : jsonObject.keySet()) {
            tempMember = tempMember.append(mvPrefix).append((memberPrefiex+s).toUpperCase())
                    .append(" = ")
                    .append("\"").append(s).append("\"")
                    .append(memberSuffix);
            list.add(tempMember.toString());
            System.out.println(tempMember);
            tempMember.delete(0,tempMember.length());
        }


    }
}
