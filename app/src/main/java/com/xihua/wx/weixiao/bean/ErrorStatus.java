package com.xihua.wx.weixiao.bean;

public enum ErrorStatus {
    SUCCESS(200,"success"),
    PARAM_ERROR(400, "param error"),
    INVALID_KEY(401, "invalid securityKey"),
    OFFEN_ERROR(402, "login frequency"),
    NOT_ADMIN(409,"not admin, no permission to access"),

    UNEXPECTED_ERROR(500, "unexpected error"),
    UNKNOWN_ERROR(600, "unknown error"),
    INTERNAL_ERROR(700, "internal error"),
    REST_ERROR(800, "rest request error"),
    OPENAPI_HYSTRIX(900, "the service is busy, please try again later"),
    INVALID_PARAMETER(1001, "invalid.parameter"),
    DEVICE_NOT_EXISTS(1002, "device not exist"),
    DEVICE_NOT_ONLINE(1004, "device not online"),
    ENTERPRISE_NOT_EXIST(1005, "enterprise not exist"),
    DEVICE_WRONG_PASSWORD_DENY(4103, "device.input.wrong.password.deny"),

    INVALID_PASSWORD(2001, "login.invalid.account.psw"),
    EMPTY_ACCOUNT(2004, "validate.empty.account"),

    FILE_NO_SUPPORTED(20001, "file not supported"),// 不支持的文件类型
    FILE_UPLOAD_ERROR(20002, "file upload error"),// 文件上传出错
    IMAGE_TOO_LARGE(20101, "image too large"),// 图片过大
    MAIL_ILLEGAL(40038,"mail illegal"),//邮箱不合法
    FILE_DATA_NUM_TOO_BIG(2003,"file data num too big"),//excel数据超过5000条
    COMPRESS_IS_NULL(2004,"compress is null"),//空压缩包


    OK(0, "OK"),
    BUSY(-1, "sytem is busy"),// 系统繁忙
    PARAM_ILLEGAL(40035, "param illegal"),// 参数错误
    PHONE_ILLEGAL(40036, "phone illegal"),// 参数错误
    OPENAPI_INVALID_SIGNATURE(60003, "openapi.invalid.signature"),
    PHONE_HAS_BEEN_ADDED(8001, "phone has been added"),// 手机号已经在企业
    EMAIL_HAS_BEAN_ADDED(8003, "email has bean added"),
    PHONE_IN_OTHER_ENTERPRISE(8002, "phone has been in other enterprise"),// 手机号在其它企业
    EMAIL_IN_OTHER_ENTERPRISE(8004, "email has been in other enterprise"),// 手机号在其它企业
    DEPARTMENT_CODE_INVALID(8006, "department code  invalid"),// 无效的部门编码

    NAME_INVALID(8007,"name invalid"),//姓名无效
    PHONE_EMAIL_ISEMPTY(8008,"phone and email is empty"),//电话和邮箱都是空
    PHONE_INVALID(8009,"phone invalid"),//电话非法
    EMAIL_INVALID(8010,"email invlid"),//无效邮箱
    STUDENT_IDNO_CLASSID_NAME_EXIST(8011,"idNo/classID/name exist"),//学生学号，部门、姓名已存在
    IMAGE_NOT_EXIST(8012,"image not exist"),//学生图片不存在
    STUDENT_NOT_EXIST(8013,"student not exist"),//学生不存在
    STUDENT_ACCOUNT_DUPLICATE(8014,"student account duplicate"),
    TEMPLATE_ERROR(8015,"template error"),
    EXCEL_DATA_BIG(8016,"excel 数据大于5000条"),

    METHOD_ERROR_INVOKE(9001, "method error invoke"),// 調用了不正確的方法
    DEVICE_IS_NOT_BINDED(10001, "device is not binded"), // 設備未綁定
    DEVICE_IS_NOT_ENTERPRISE_TYPE(10002, "device is not enterprise type"), // 設備未綁定
    NEMONUMBER_INVALID(10003, "nemo number is invalid"),
    NEMO_SN_INVALID(10004, "nemo sn is invalid"),
    GROUP_NOT_UNIQUE(10005, "group not unique"),
    DEVICE_HAS_BEEN_ADDED(10006, "device has bean added"),
    DEVICE_IN_OTHER_ENTERPRISE(4109, "device has bean in other enterprise"),
    DEVICE_NOT_EXIST(10008, "device not exist"),
    DEVICE_TYPE_NOT_MATCH(10009, "device type not match"), // 设备类型不匹配
    DEVICE_NUMBER_EXIST(10010, "device number existed"),
    WORD_IS_DUPLICATED(10011, "word is duplicated"),
    INVALID_NEMO_NUMBER(4116, "invalid nemo number"),
    INVALID_DEVICE_SN(2008, "invalid device sn"),
    NEMOID_NUMBERPO_NOT_EXISTS(8002, "nemoid.numberPO.not.exists"),
    CAN_NOT_DELETE_MEMBER(10012,"can not delete member"),


    EntityNotUnique(1001, "entity not unique"),
    EntityNotExist(1002, "entity not exist"),
    MailSendFailure(1003, "mail send failure"),
    INVALID_PHONE(1005, "invalid phone"),
    UPDATE_MAIL_CONFIG_FAILURE(1006, "update mail config failure"),
    LICENSE_ERROR(1007, "license invalidate"),
    REGISTER_MAIL_DUPLICATE(1008, "register mail duplicate"),
    REGISTER_ENTERPRISE_DUPLICATE(1009, "register enterprise name duplicate"),
    ENTERPRISE_CANNOT_DELETE(1010, "normal status enterprise can not delete"),

    LOGIN_EMAIL_ALREADY_REGISTERED(2100, "validate.email.already.registered"),

    //for modelMachine
    DEVICE_MODEL_NOT_EXIST(204,"Device sn is not exist"),

    MSG_NOT_EXIST(12002, "msg is not exist"), //不存在的消息
    DISTRIBUTOR_HAS_BEEN_ADDED(12007,"distributor has been added"),
    ENTERPRISE_IS_ALREADY_DISTRIBUTOR(12008,"enterprise is already distributor "),
    DISTRIBUTOR_NOT_MACH_ENTERPRISE(12009,"distributor not mach enterprise"),//企业经销商不匹配
    ILLEGAL_SECURITYKEY(12010, "illegal securitykey"), //API查询接口 sk不合法

    //for external order
    EXTERNAL_EXIST(13001, "external order is exist"),//已经存在的外部企业订单
    EXTERNAL_EMAIL(13002, "email is empty or phone is empty"),//email是空的
    PRODUCT_TYPE_NOT_SUPPORT(13004,"product type not supported"),//目前不支持的产品
    PRODUCT_CYCLE_IS_NOT_SUPPORT(13005,"product cycle not supported"),//产品周期为0
    DEVICE_CONFIG_PART_UPDATE(13008,"part of the update was successfu"),//终端功能配置部分更新成功

    EXTERNAL_NOT_EXIST(13003,"external order is not exist"),//订单不存在

    //for external prodation order
    EXTERNAL_PRODATION_ORDER_EXIST(13006,"external prodation order sn is exist"),
    EXTERNAL_PRODATION_ORDER_TIME_INVALID(13007,"external prodation order end time error"),


    //sso
    TOKEN_NULL(50001, "request param token is null"), //参数token 为空
    TOKEN_EXPIRE(50002, "request token is expire"), //参数token 失效
    TOKEN_USED(50003, "request token is used"), //参数token 被使用

    NEMO_ONLINE(60001, "nemo online"),

    DEPARTMENT_BUSINESS_ERROR(70001, "department business error"),
    //word
    FAIL_FIND_WORDS_ERROR(80001, "words is nul") ,
    SUPERADMIN_CAN_NOT_DELETE(70002, "superadmin can't delete"),
    SELF_CAN_NOT_DELETE(70003, "self can't delete"),

    //user create
    CREATE_USER_EXIST_NO_ENTERPRISE(90001, "user exist and no enterprise"),
    CREATE_USER_COUNTRY_CODE_ERROR(90002, "user country code error"),
    DELETE_ROLE_DEPARTMENT_ERROR(90003, "delete role department error"),

    //contact
    CREATE_DEVICE_H323_REPEAT(4001, "create device H323 repeat"),

    //login
    USER_EXIST_NO_ENTERPRISE(9000001, "user not in enterprise"),
    ;




    private int errorCode = 0;
    private String userMessage;

    ErrorStatus(int errorCode, String userMessage) {
        this.errorCode = errorCode;
        this.userMessage = userMessage;
    }

    public static ErrorStatus valueOf(int errorCode) {
        for (ErrorStatus type : ErrorStatus.values()) {
            if (type.errorCode == errorCode)
                return type;
        }

        return null;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getUserMessage() {
        return userMessage;
    }

    @Override
    public String toString() {
        return "ErrorStatus{" +
                "errorCode=" + errorCode +
                ", userMessage='" + userMessage + '\'' +
                "} ";
    }
}