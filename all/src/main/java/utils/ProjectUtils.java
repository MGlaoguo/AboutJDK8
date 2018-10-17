package utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author guojiabin
 * @version 2018/10/17 0017 9:57
 */
public class ProjectUtils {
    public static void main(String[] args) throws IOException {
        String orderDetailJson="{\"order_sn\":\"8000000000049801\",\"order_id\":\"527\",\"order_logistics_type\":\"2\",\"buyer_id\":\"53123\",\"buyer_name\":\"test\",\"buyer_email\":\"test@kilimall.com\",\"order_message\":\"ASAP\",\"reciver_name\":\"test\",\"dlyp\":0,\"dlyp_name\":\"doorstep\",\"phone\":\"18978787878\",\"address\":\"Kenya Nairobi Nairobi test\",\"country\":\"ke\",\"province_id\":\"Nairobi\",\"city_id\":\"Nairobi\",\"area\":\"Kenya Nairobi Nairobi\",\"street\":\"test\",\"recvier_phone\":\"\",\"add_time\":\"2016-08-01 12:00:00\",\"order_state\":\"30\",\"order_amount\":\"1300.00\",\"goods_amount\":\"800.00\",\"shipping_fee\":\"500.00\",\"shipping_code\":\"CHIL30783\",\"tracking_code\":\"KE10000505\",\"voucher_price\":0,\"payment_code\":\"online\",\"payment_name\":\"Online Payment\",\"payment_time\":\"2016-08-01 12:00:00\",\"shipping_express\":\"KiliExpress\",\"deliver_explain\":\"asap\",\"shipping_time\":\"2016-08-01 12:00:00\",\"weight\":\"6.5Kg\",\"seller_name\":\"testing\",\"finnshed_time\":\"\",\"order_goods_lists\":[{\"goods_id\":\"15104\",\"goods_name\":\"SPORT WATCH\",\"goods_price\":\"500.00\",\"goods_num\":\"1\",\"goods_image\":\"2016/07/413_05060150106613849.jpg\",\"goods_sku\":\"123456\",\"gc_id\":\"1074\",\"goods_type\":\"1\"},{\"goods_id\":\"15105\",\"goods_name\":\"SPORT WATCH\",\"goods_price\":\"100.00\",\"goods_num\":\"1\",\"goods_image\":\"2016/07/413_05060150106613848.jpg\",\"goods_sku\":\"78910\",\"gc_id\":\"1074\",\"goods_type\":\"1\"},]}";
        String orderJson="{\"order_sn\":\"8000000003880201\",\"pay_sn\":\"826377688233478241\",\"update_time\":\"2016-06-14 22:35:16\",\"goods_amount\":\"800.00\",\"order_amount\":\"410.00\",\"shipping_fee\":\"1210.00\",\"order_state\":\"10\",\"refund_type\":\"0\",\"shipping_code\":\"CHIL30783\",\"tracking_code\":\"KE10000505\",\"order_logistics_type\":\"1\"}";
        String orderProductJson="{\"goods_id\":\"15104\",\"goods_name\":\"SPORT WATCH\",\"goods_price\":\"500.00\",\"goods_num\":\"1\",\"goods_image\":\"2016/07/413_05060150106613849.jpg\",\"goods_sku\":\"123456\",\"gc_id\":\"1074\",\"goods_type\":\"1\"}";
//        jsonToMemberVariables(json);
        String orderListFilePath = "C:\\Users\\Administrator\\Desktop\\kilimall\\kilimallOrderList.xlsx";
        String orderFilePath = "C:\\Users\\Administrator\\Desktop\\kilimall\\kilimallOrder.xlsx";
        String orderProductFilePath = "C:\\Users\\Administrator\\Desktop\\kilimall\\kilimallOrderProduct.xlsx";
        Map<String,String> jsonParam = getJsonParamMap(orderFilePath);
        jsonToMemberVariables(orderDetailJson,jsonParam);
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
    /**
     * api文档 json格式参数，同意转换未本地 String类型参数
     * @param json
     */
    public static  void jsonToMemberVariables(String json,Map<String,String> jsonCnParam){
        String mvPrefix = "public static final String ";
        String memberPrefiex = "KEY_";
        String memberSuffix = ";";
        StringBuilder comment = new StringBuilder();
        StringBuilder tempMember = new StringBuilder();
        JSONObject jsonObject = JSONObject.parseObject(json);

        List<String> list = new ArrayList<>();
        for (String s : jsonObject.keySet()) {
            if (jsonCnParam!=null) {
                if(jsonCnParam.containsKey(s)){
                    comment.append("/** ").append(jsonCnParam.get(s)).append(" */");
                }else{
                    for (String s1 : jsonCnParam.keySet()) {
                        if(s1.contains(s)){
                            comment.append("/** ").append(jsonCnParam.get(s1)).append(" */");
                        }
                    }
                }
            }
            System.out.println(comment);
            tempMember = tempMember.append(mvPrefix).append((memberPrefiex+s).toUpperCase())
                    .append(" = ")
                    .append("\"").append(s).append("\"")
                    .append(memberSuffix);
            list.add(tempMember.toString());
            System.out.println(tempMember);
            comment.delete(0,comment.length());
            tempMember.delete(0,tempMember.length());
        }
    }

    /**
     * 获取参数名对应值
     * @param excelFilePath
     * @return
     * @throws IOException
     */
    public static Map<String,String> getJsonParamMap(String excelFilePath) throws IOException {
        File file = new File(excelFilePath);
        List<Map<Integer, String>> excelList = readExcel(file,1,false);
        Map<String,String> mapData = Maps.newHashMap();
        for (Map<Integer, String> map : excelList) {
            if(map.get(0) == null){
                break;
            }
            System.out.println(map);
            mapData.put(map.get(0),map.get(2));
        }
        return mapData;
    }
    /**
     * 读取excel文件
     * @param file excel文件
     * @param index 从第几行开始读取
     * @return
     * @throws IOException
     */
    public static List<Map<Integer,String>> readExcel(File file, int index, boolean isExcel2003) throws IOException{
        List<Map<Integer,String>> retList = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(file);
        Workbook hssfwb = isExcel2003?new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
        // 循环工作表Sheet
        for(int i = 0;i < hssfwb.getNumberOfSheets();i++){
            Sheet hssfSheet = hssfwb.getSheetAt(i);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            int emptyrows = 0;
            for (int rowNum = index; rowNum <= hssfSheet.getLastRowNum(); rowNum++){
                Row hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    if(emptyrows > 2){
                        break;
                    }
                    emptyrows ++ ;
                    continue;
                }
                Map<Integer,String> map = Maps.newHashMap();
                // 循环列Cell
                for(int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++){
                    Cell xh = hssfRow.getCell(cellNum);
                    if(xh!=null){
                        map.put(cellNum, getValue(xh));
                    }
                }
                if(!map.isEmpty()){
                    retList.add(map);
                }
                emptyrows = 0;
            }
        }
        return retList;
    }
    /**
     * 得到Excel表中的值
     *
     * @param hssfCell Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    private static String getValue(Cell hssfCell) {
        if(hssfCell == null){
            return null;
        }else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        }
        else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            return NumberToTextConverter.toText(hssfCell.getNumericCellValue());
        }
        else {
            hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}
