package com.ibeetl.utils;

/**
 * 常量
 *
 * @author Mr.C
 * @date 2018-6-26 10:00
 */
public class Const {

    /**
     * 删除标识 未删除
     */
    public static final int DEL_FLAG_NORMAL = 0;

    /**
     * 删除标识 已删除
     */
    public static final int DEL_FLAG_DELETE = 1;

    /**
     * 通用ID
     */
    public static final Long COMMON_ZERO_ID = 0L;
    /**
     * 字符串通用ID
     */
    public static final String COMMON_ZERO_ID_STRING = COMMON_ZERO_ID.toString();
    /**
     * 系统操作人ID
     */
    public static final Long SYSTEM_USER_ID = 2L;
    /**
     * 费率基数 百万分之一
     */
    public static final int RATE_BASE = 1000000;

    /**
     * 短信发送冷却时间(单位:S)
     */
    public static final Integer SMS_SEND_CD_TIME = 60;

    /**
     * 验证码有效时间(单位:S)
     */
    public static final Integer SMS_CHECK_VALID_TIME = 1800;


    /**
     * 一天同一个商户最大发送数量
     */
    public static final Integer SMS_ONE_DAY_MAX_SEND_COUNT = 3;

    /**
     * 发送短信类型 1 忘记密码
     */
    public static final String SMS_TYPE_2_FIND_PWD = "2";

    public static class User {
        //状态: 未认证;
        public static final int USER_STATUS_UNCERTIFIED = 0;
        /**
         * 已认证;
         */
        public static final int USER_STATUS_NORMAL = 1;
        /**
         * 认证中
         */
        public static final int USER_STATUS_CERTIFING = 2;
        /**
         * 审核失败
         */
        public static final int USER_STATUS_FAIL = 3;

        /**
         * 注册奖励状态：未处理
         */
        public static final int REGISTER_REWARD_STATUS_UNTREATED = 0;
        /**
         * 注册奖励状态：已处理
         */
        public static final int REGISTER_REWARD_STATUS_PROCESSED = 1;

    }

    public static class UserCardAuditRecord {

        /**
         * 银行卡类型: 1:信用卡
         */
        public static final int CARD_TYPE_CREDIT = 1;

        /**
         * 银行卡类型: 2:储蓄卡
         */
        public static final int CARD_TYPE_DEBIT = 2;

        /**
         * 状态: 1-已绑定
         */
        public static final int STATUS_BINDING = 1;
        /**
         * 状态: 2-已解绑
         */
        public static final int STATUS_UNTYING = 2;


        /**
         * 状态: 0-未审核
         */
        public static final int STATUS_UNAUDIT = 0;
        /**
         * 状态: 1-审核成功
         */
        public static final int STATUS_AUDIT_SUCCESS = 1;
        /**
         * 状态: 2-审核失败
         */
        public static final int STATUS_AUDIT_FAIL = 2;


        /**
         * 申请类型: 1-创客审核
         */
        public static final int USER_AUDIT = 1;
        /**
         * 申请类型: 2-修改结算卡
         */
        public static final int UPDATE_CARD = 2;
    }

    public static class UserAccount {
        /**
         * 正常
         */

        public static final int STATUS_NORMAL = 1;

        /**
         * 锁定
         */
        public static final int STATUS_LOCK = 2;

    }
//    public static class UserAccountConstants {
//        /**
//         * 商机钱包
//         */
//        public static final int TYPE_PROJECT = 1;
//
//        /**
//         * 推广钱包
//         */
//        public static final int TYPE_PROMOTION = 2;
//    }
    public static class UserAccountDealConstants {
        /**
         * 流水类型: 收入
         */
        public static final int DEAL_TYPE_INCOME = 1;
        /**
         * 流水类型: 支出
         */
        public static final int DEAL_TYPE_PAY = 2;

        /**
         * 线上冻结钱包
         */
        public static final int ACCOUNT_TYPE_ONLINE_FREEZE = 1;
        /**
         * 线下冻结钱包
         */
        public static final int ACCOUNT_TYPE_OFFLINE_FREEZE = 2;
        /**
         * 线上钱包
         */
        public static final int ACCOUNT_TYPE_ONLINE = 3;
        /**
         * 线下钱包
         */
        public static final int ACCOUNT_TYPE_OFFLINE = 4;
        /**
         * 收益钱包
         */
        public static final int ACCOUNT_TYPE_PROFIT = 5;
        /**
         * 推广钱包
         */
        public static final int ACCOUNT_TYPE_PROMOTION = 6;
    }
    public static class PosInfoConstants {

        /**
         * 空闲
         */
        public static final int FREE = 0;

        /**
         * 划拨商机
         */
        public static final int PROJECT = 1;

        /**
         * 划拨创客
         */
        public static final int USER = 2;
    }

    public static class PosTradeDealConstants {
        /**
         * 数据来源sourceType：1-接口获取；2-平台导入
         */
        public static final int SOURCETYPE_INTERFACE_TO_GET = 1;
        public static final int SOURCETYPE_PLATFORM_IMPORT = 2;

        /**
         * 未计算收益
         */
        public static final int INCOMESTATUS_NO = 0;

        /**
         * 已计算收益
         */
        public static final int INCOMESTATUS_YES = 1;

    }
    public static class ProjectConstants {
        /**
         * 商机状态 0-未开启;1-已取消;2-认筹中;3-进行中;4-结息中;5-已完成;
         */
        public static final int STATUS_INIT = 0;
        public static final int STATUS_CANCLED = 1;
        public static final int STATUS_IDENTIFY = 2;
        public static final int STATUS_RUNNING = 3;
        public static final int STATUS_SETTLEMENT = 4;
        public static final int STATUS_FINISHED = 5;
        public static final Integer[] STATUS_ORDER_INFO = {STATUS_IDENTIFY, STATUS_RUNNING, STATUS_SETTLEMENT};
        public static final Integer[] PAGE_PROJECT_EARNINGS = {STATUS_IDENTIFY, STATUS_RUNNING, STATUS_SETTLEMENT, STATUS_FINISHED};

        /**
         * 认购状态 0-无;1-认购;2-赎回
         */
        public static final int SUBSCRIBE_STATUS_NULL = 0;
        public static final int SUBSCRIBE_STATUS_IDENTIFY = 1;
        public static final int SUBSCRIBE_STATUS_REDEMPTION = 2;
    }

    /**
     * 交易业务类型常量
     */
    public static class BusinessTypeConstants {
        /**
         * 交易业务类型：线上充值
         */
        public static final int RECHARGE_ONLINE = 0;
        /**
         * 交易业务类型：线下充值
         */
        public static final int RECHARGE_OFFLINE = 1;
        /**
         * 交易业务类型：认购
         */
        public static final int SUBSCRIBE = 2;
        /**
         * 交易业务类型：线上赎回
         */
        public static final int REDEMPTION_ONLINE = 3;
        /**
         * 交易业务类型：线下赎回
         */
        public static final int REDEMPTION_OFFLINE = 7;
        /**
         * 交易业务类型：提现
         */
        public static final int WITHDRAW = 4;
        /**
         * 交易业务类型：注册推广
         */
        public static final int REGISTERED_PROMOTION = 5;
        /**
         * 交易业务类型：商机推广-推荐奖励 推荐认购-推荐收益
         */
        public static final int PROJECT_PROMOTION = 6;
        /**
         * 交易业务类型：收益(月结)
         */
        public static final int PROFIT = 8;
    }

    public static class DictionaryConstants {
        /**
         * 账户交易业务类型
         */
        public static final String BUSINESS_TYPE = "business_type";
    }

    /**
     * 分润规则常量配置
     */
    public static class PromotionIncomeRuleConstants {

        /**
         * 1-推广注册收益;
         */
        public static final int INCOME_TYPE_REGISTER = 1;

        /**
         * 2-推广商机收益
         */
        public static final int INCOME_TYPE_PROJECT = 2;
        /**
         * 0-未生效
         */
        public static final int STATUS_INVALID = 0;
        /**
         * 1-已生效
         */
        public static final int STATUS_VALID = 1;

        /**
         * 1-直推;2-间推
         */
        public static final int PROMOTION_LEVEL_DIRECT = 1;
        public static final int PROMOTION_LEVEL_INDIRECT = 2;
    }

    public static class UserPromotionIncomeRecordConstants {
        /**
         * 0-未生效
         */
        public static final int STATUS_INVALID = 0;
        /**
         * 1-已生效
         */
        public static final int STATUS_VALID = 1;

        /**
         * 推广级别 1-直推
         */
        public static final int PROMOTION_LEVEL_DIRECT = 1;
        /**
         * 推广级别 2-间推
         */
        public static final int PROMOTION_LEVEL_INDIRECT = 2;
    }


    //线下充值
    public static class UserRechargeOfflineConstants {


        /**
         * 审核状态 0 待审核 1 初审通过 2 初审拒绝 3 复审通过 4 复审拒绝
         */
        public static final int STATUS_PENDING_REVIEW_0 = 0;

        public static final int STATUS_FIRST_TRIAL_PASS_1 = 1;

        public static final int STATUS_FIRST_TRIAL_REFUSE_2 = 2;

        public static final int STATUS_REVIEW_TRIAL_PASS_3 = 3;

        public static final int STATUS_REVIEW_TRIAL_REFUSE_4 = 4;

    }

    public static class SysPolicyRewardConfigureConstants {
        /**
         * 政策类型-注册推广-0
         */
        public static final int POLICY_TYPE_REGISTER_SPREA = 0;
        /**
         * 政策类型-推荐认购-1
         */
        public static final int POLICY_TYPE_RECOMMEND_SUBSCRIPTION = 1;
        /**
         * 政策类型-推荐收益-2
         */
        public static final int POLICY_TYPE_RECOMMEND_INCOME = 2;
        /**
         * 政策类型-商机发布-3
         */
        public static final int POLICY_TYPE_PROJECT_RELEASE = 3;
    }
}
