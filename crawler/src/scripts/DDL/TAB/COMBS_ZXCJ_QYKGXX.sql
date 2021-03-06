-- 资讯采集：企业控股信息
CREATE TABLE COMBS_ZXCJ_QYKGXX
(
  id             VARCHAR2(36)             NOT NULL,
  ent_id         VARCHAR2(20),
  ent_name       VARCHAR2(100),
  pull_date      VARCHAR2(10),
  subject_type   VARCHAR2(10),
  id_card        VARCHAR2(30),
  cer_type       VARCHAR2(10),
  final_ent_name VARCHAR2(100),
  address        VARCHAR2(600),
  final_ratio    NUMBER(7, 4),
  final_subs_num NUMBER(17, 4),
  final_acc_num  NUMBER(17, 4),
  ratio_road     VARCHAR2(2000),
  position       VARCHAR2(64),
  capital_chain  VARCHAR2(3000),
  unique_token   VARCHAR2(36) DEFAULT '0' NOT NULL,
  created_at     NUMBER(15)               NOT NULL,
  creator        VARCHAR2(20) DEFAULT 'system',
  updated_at     NUMBER(15)               NOT NULL,
  updater        VARCHAR2(20) DEFAULT 'system',
  is_delete      NUMBER(1) DEFAULT 0      NOT NULL
)
TABLESPACE AJAY_DATA;
-- Add comments to the table
COMMENT ON TABLE COMBS_ZXCJ_QYKGXX
IS '数据采集-企业控股信息';
-- Add comments to the columns
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.id
IS '物理主键';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.ent_id
IS '企业id(元素)';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.pull_date
IS '拉取时间';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.ent_name
IS '企业名称';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.subject_type
IS '控股主体类型';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.id_card
IS '证件号码';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.cer_type
IS '证件类型';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.final_ent_name
IS '受益人姓名';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.address
IS '地址';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.final_ratio
IS '最终占比';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.final_subs_num
IS '最终认缴';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.final_acc_num
IS '最终实缴';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.ratio_road
IS '出资计算过程';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.position
IS '职位';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.capital_chain
IS '出资链';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.unique_token
IS '唯一性控制 软删除后翻转为id';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.created_at
IS '创建日期';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.creator
IS '创建人';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.updated_at
IS '修改日期';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.updater
IS '修改人';
COMMENT ON COLUMN COMBS_ZXCJ_QYKGXX.is_delete
IS '是否失效 0-否 1-是';
-- Create/Recreate indexes
CREATE INDEX IDX_COMBS_ZXCJ_QYKGXX_1 ON COMBS_ZXCJ_QYKGXX (FINAL_ENT_NAME)
TABLESPACE AJAY_IDX;
CREATE INDEX IDX_COMBS_ZXCJ_QYKGXX_2 ON COMBS_ZXCJ_QYKGXX (ENT_NAME)
TABLESPACE AJAY_IDX;
-- Create/Recreate primary, unique and foreign key constraints
ALTER TABLE COMBS_ZXCJ_QYKGXX
  ADD CONSTRAINT PK_COMBS_ZXCJ_QYKGXX PRIMARY KEY (ID)
  USING INDEX
  TABLESPACE AJAY_IDX;
ALTER TABLE COMBS_ZXCJ_QYKGXX
  ADD CONSTRAINT UK_COMBS_ZXCJ_QYKGXX_1 UNIQUE (ENT_ID, FINAL_ENT_NAME, UNIQUE_TOKEN, IS_DELETE)
  USING INDEX
  TABLESPACE AJAY_IDX;
