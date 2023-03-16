package com.wa.last.ws;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MqData {

    /**
     * userid
     */
    String userId;

    /**
     * userid
     */
    String chanledId;

    /**
     * 推送数据
     */
    String data;

}
