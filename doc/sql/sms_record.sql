CREATE TABLE `sms_record`
(
    `id`                  bigint(20) NOT NULL AUTO_INCREMENT,
    `message_template_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '消息模板ID',
    `phone`               bigint(20) NOT NULL DEFAULT '0' COMMENT '手机号',
    `supplier_id`         tinyint(4) NOT NULL DEFAULT '0' COMMENT '发送短信渠道商的ID',
    `supplier_name`       varchar(40) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '发送短信渠道商的名称',
    `msg_content`         varchar(600) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '短信发送的内容',
    `series_id`           varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '下发批次的ID',
    `charging_num`        tinyint(4) NOT NULL DEFAULT '0' COMMENT '计费条数',
    `report_content`      varchar(50)                             NOT NULL DEFAULT '' COMMENT '回执内容',
    `status`              tinyint(4) NOT NULL DEFAULT '0' COMMENT '短信状态： 10.发送 20.成功 30.失败',
    `send_date`           int(11) NOT NULL DEFAULT '0' COMMENT '发送日期：20211112',
    `created`             int(11) NOT NULL DEFAULT '0' COMMENT '创建时间',
    `updated`             int(11) NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY                   `idx_send_date` (`send_date`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='短信记录信息';
-- 实时类型 短信（无占位符）
INSERT INTO austin.message_template (id, name, audit_status, flow_id, msg_status, cron_task_id, cron_crowd_path, expect_push_time, id_type, send_channel, template_type, msg_type, msg_content, send_account, creator, updator, auditor, team, proposer, is_deleted, created, updated) VALUES (1, '买一送十活动', 10, '', 10, null, '', '', 30, 30, 20, 20, '{"content":"6666","url":"","title":""}', 10, 'Java3y', 'Java3y', '3y', '公众号Java3y', '三歪', 0, 1646274112, 1646275242);

-- 实时类型 邮件（无占位符）
INSERT INTO austin.message_template (id, name, audit_status, flow_id, msg_status, cron_task_id, cron_crowd_path, expect_push_time, id_type, send_channel, template_type, msg_type, msg_content, send_account, creator, updator, auditor, team, proposer, is_deleted, created, updated) VALUES (2, '校招信息', 10, '', 10, null, '', '', 50, 40, 20, 10, '{"content":"你已成功获取到offer","url":"","title":"招聘通知"}', 10, 'Java3y', 'Java3y', '3y', '公众号Java3y', '鸡蛋', 0, 1646274195, 1646274195);

-- 实时类型 短信（有占位符）占位符key 为 content
INSERT INTO austin.message_template (id, name, audit_status, flow_id, msg_status, cron_task_id, cron_crowd_path, expect_push_time, id_type, send_channel, template_type, msg_type, msg_content, send_account, creator, updator, auditor, team, proposer, is_deleted, created, updated) VALUES (3, '验证码通知', 10, '', 10, null, '', '', 30, 30, 20, 30, '{"content":"{$content}","url":"","title":""}', 10, 'Java3y', 'Java3y', '3y', '公众号Java3y', '孙悟空', 0, 1646275213, 1646275213);