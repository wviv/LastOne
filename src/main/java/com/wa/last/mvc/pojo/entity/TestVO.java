package com.wa.last.mvc.pojo.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author viv
 * @since 2021-05-10
 */
@Data
public class TestVO implements Serializable {

    public static void main(String[] args) {
        String a = re(null);
        System.out.println(a);

    }

    public static @NotNull String re(@Valid @NotNull String a){
        return null;
    }

    public static void time(){
        //        String patternStr = "yyyy-MM-dd HH:mm:ss";
//        // 北京时间（new出来就是默认时区的时间）
//        Date bjDate = new Date();
//        System.out.println(new SimpleDateFormat(patternStr).format(bjDate));
//        // 得到纽约的时区
//        TimeZone newYorkTimeZone = TimeZone.getTimeZone("America/New_York");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternStr);
//        simpleDateFormat.setTimeZone(newYorkTimeZone);
//        String newYorkTime = simpleDateFormat.format(bjDate);
//        System.out.println(newYorkTime);
//        String[] availableIDs = TimeZone.getAvailableIDs();
//        for (int i = 0; i < availableIDs.length; i++) {
//            System.out.println(availableIDs[i]);
//        }
//        ZonedDateTime zonedDateTime = ZonedDateTime.now();
//        System.out.println(zonedDateTime);
//        System.out.println(ZoneId.from(ZonedDateTime.now()));
    }
}
