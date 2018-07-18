package cn.dankal.basic_lib.pojo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 登录信息响应体
 *
 * @author leaflc
 */
public class LoginResponse {


    /**
     * access_token : 89b40facb802d73d98775b
     * expiry_time : 2018-05-19 15:20:55
     * refresh_token : 656e68415131d3b56bf3f4
     * user_uuid : 002a5393ee1d4503a44b27c6eb4123e9
     */

    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "expiry_time")
    private String expiryTime;
    @JSONField(name = "refresh_token")
    private String refreshToken;
    @JSONField(name = "user_uuid")
    private String userUuid;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
