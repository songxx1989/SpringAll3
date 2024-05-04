CREATE TABLE SYS_LOG
(
    ID          BIGINT NOT NULL,
    USERNAME    VARCHAR(50),
    OPERATION   VARCHAR(50),
    TIME        BIGINT,
    METHOD      VARCHAR(200),
    PARAMS      VARCHAR(500),
    IP          VARCHAR(64),
    CREATE_TIME TIMESTAMP
);

COMMENT ON COLUMN SYS_LOG.USERNAME IS '用户名';
COMMENT ON COLUMN SYS_LOG.OPERATION IS '用户操作';
COMMENT ON COLUMN SYS_LOG.TIME IS '响应时间';
COMMENT ON COLUMN SYS_LOG.METHOD IS '请求方法';
COMMENT ON COLUMN SYS_LOG.PARAMS IS '请求参数';
COMMENT ON COLUMN SYS_LOG.IP IS 'IP地址';
COMMENT ON COLUMN SYS_LOG.CREATE_TIME IS '创建时间';

CREATE SEQUENCE seq_sys_log START WITH 1 INCREMENT BY 1;