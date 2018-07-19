package utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author guojiabin
 * @version 2018/7/17 0017 10:37
 */
public class SignUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignUtils.class);
    /**
     * 获取签名,hex encode
     * @param msg 待加密
     */
    public static String getMessageDigestHexEncoded(String msg,String algorithm){
//        try {
//        try {
//            MessageDigest md = MessageDigest.getInstance(algorithm);
//            byte[] bytes = md.digest(msg.getBytes(Consts.));
//            return Hex.encodeHexString(bytes);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//            byte[] bytes = md.digest(msg.getBytes(Consts.));
//            return Hex.encodeHexString(bytes);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;

    }
    /**
     *参数acsii,顺序排序
     * @param params 参数
     * @param isUrlEncode 是否urlEncode
     * @param split key和value链接符
     */
    public static String getParamString(Map<String, String> params, boolean isUrlEncode, String split){
        StringBuffer sign = new StringBuffer();
        if(params == null || params.isEmpty()){
            LOGGER.error("getParamString,缺少必填参数,params={}",params);
            return "";
        }
        String finalSplit;
        if(StringUtils.isBlank(split)){
            finalSplit = "";
        }else {
            finalSplit = split;
        }
        params.remove(null);
        List<Map.Entry<String,String>> paramList = params.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey)).collect(Collectors.toList());
        paramList.forEach(entry ->sign.append(entry.getKey()).append(finalSplit).append(entry.getValue()));
        LOGGER.debug("sign={}",sign.toString());
        try {
            if(isUrlEncode){
                sign .replace(0,sign.length(), URLEncoder.encode(sign.toString(),"UTF-8"));
                LOGGER.debug("urlEncode,sign={}",sign.toString());
            }
            return sign.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
