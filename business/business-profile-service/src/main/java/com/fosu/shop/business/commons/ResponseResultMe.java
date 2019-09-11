package com.fosu.shop.business.commons;


import lombok.Data;

@Data
public class ResponseResultMe <T>{
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回对象
     */
    private T data;
    public ResponseResultMe() {
        super();
    }

    public ResponseResultMe(Integer code) {
        super();
        this.code = code;
    }

    public ResponseResultMe(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ResponseResultMe(Integer code, Throwable throwable) {
        super();
        this.code = code;
        this.message = throwable.getMessage();
    }

    public ResponseResultMe(Integer code, T data) {
        super();
        this.code = code;
        this.data = data;
    }

    public ResponseResultMe(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    /**
     * 通用状态码
     * <p>
     * Description:
     * </p>
     *

     * @date 2019-07-30 05:02:49
     * @see com.fosu.shop.commons.dto
     */
    public static class CodeStatus {
        /**
         * 请求成功
         */
        public static final int OK = 20000;

        /**
         * 请求失败
         */
        public static final int FAIL = 20002;

        /**
         * 熔断请求
         */
        public static final int BREAKING = 20004;

        /**
         * 非法请求
         */
        public static final int ILLEGAL_REQUEST = 50000;

        /**
         * 非法令牌
         */
        public static final int ILLEGAL_TOKEN = 50008;

        /**
         * 其他客户登录
         */
        public static final int OTHER_CLIENTS_LOGGED_IN = 50012;

        /**
         * 令牌已过期
         */
        public static final int TOKEN_EXPIRED = 50014;
    }
}
