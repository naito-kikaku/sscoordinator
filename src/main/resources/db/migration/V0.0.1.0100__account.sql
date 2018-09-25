DROP SCHEMA IF EXISTS account CASCADE;

CREATE SCHEMA account;

-- entity
CREATE SEQUENCE account.account_id_sequence;
CREATE TABLE account.accounts (
  account_id BIGINT    NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_accounts PRIMARY KEY (account_id)
);

CREATE SEQUENCE account.account_name_revision_id_sequence;
CREATE TABLE account.account_name_revisions (
  account_name_revision_id BIGINT       NOT NULL,
  account_id               BIGINT       NOT NULL,
  account_name             VARCHAR(128) NOT NULL,
  revised_at               TIMESTAMP    NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_account_name_revisions PRIMARY KEY (account_name_revision_id),
  CONSTRAINT fk_account_name_revisions_to_parent FOREIGN KEY (account_id) REFERENCES account.accounts (account_id)
);

CREATE TABLE account.latest_account_names (
  account_id               BIGINT NOT NULL,
  account_name_revision_id BIGINT NOT NULL,
  CONSTRAINT pk_latest_account_names PRIMARY KEY (account_id),
  CONSTRAINT fk_latest_account_names_to_accounts FOREIGN KEY (account_id) REFERENCES account.accounts (account_id),
  CONSTRAINT fk_latest_account_names_to_email_revisions FOREIGN KEY (account_name_revision_id) REFERENCES account.account_name_revisions (account_name_revision_id)
);

CREATE SEQUENCE account.email_address_revision_id_sequence;
CREATE TABLE account.email_address_revisions (
  email_address_revision_id BIGINT       NOT NULL,
  account_id                BIGINT       NOT NULL,
  email_address             VARCHAR(255) NOT NULL,
  revised_at                TIMESTAMP    NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_email_address_revisions PRIMARY KEY (email_address_revision_id),
  CONSTRAINT fk_email_address_revisions_to_parent FOREIGN KEY (account_id) REFERENCES account.accounts (account_id)
);

CREATE TABLE account.latest_email_addresses (
  account_id                BIGINT NOT NULL,
  email_address_revision_id BIGINT NOT NULL,
  CONSTRAINT pk_latest_email_addresses PRIMARY KEY (account_id),
  CONSTRAINT fk_latest_email_addresses_to_accounts FOREIGN KEY (account_id) REFERENCES account.accounts (account_id),
  CONSTRAINT fk_latest_email_addresses_to_email_revisions FOREIGN KEY (email_address_revision_id) REFERENCES account.email_address_revisions (email_address_revision_id)
);

CREATE SEQUENCE account.password_revision_id_sequence;
CREATE TABLE account.password_revisions (
  password_revision_id BIGINT    NOT NULL,
  account_id           BIGINT    NOT NULL,
  password             CHAR(60)  NOT NULL,
  revised_at           TIMESTAMP NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_password_revisions PRIMARY KEY (password_revision_id),
  CONSTRAINT fk_password_revisions_to_parent FOREIGN KEY (account_id) REFERENCES account.accounts (account_id)
);

CREATE TABLE account.latest_passwords (
  account_id           BIGINT NOT NULL,
  password_revision_id BIGINT NOT NULL,
  CONSTRAINT pk_latest_passwords PRIMARY KEY (account_id),
  CONSTRAINT fk_latest_passwords_to_accounts FOREIGN KEY (account_id) REFERENCES account.accounts (account_id),
  CONSTRAINT fk_latest_passwords_to_password_revisions FOREIGN KEY (password_revision_id) REFERENCES account.password_revisions (password_revision_id)
);

CREATE TABLE account.deleted_accounts (
  account_id BIGINT    NOT NULL,
  deleted_at TIMESTAMP NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_deleted_accounts PRIMARY KEY (account_id),
  CONSTRAINT fk_deleted_accounts_to_accounts FOREIGN KEY (account_id) REFERENCES account.accounts (account_id)
);

-- active email
CREATE TABLE account.active_email_addresses (
  email_address VARCHAR(255) NOT NULL,
  CONSTRAINT pk_active_email_addresses PRIMARY KEY (email_address)
);

-- transaction
CREATE TABLE account.account_transaction_types (
  account_transaction_type_id BIGINT       NOT NULL,
  account_transaction_type    VARCHAR(64)  NOT NULL,
  desctiption                 VARCHAR(128) NOT NULL,
  CONSTRAINT pk_account_transaction_types PRIMARY KEY (account_transaction_type_id)
);

INSERT INTO account.account_transaction_types (account_transaction_type_id, account_transaction_type, desctiption)
VALUES (1, 'SIGN_UP', 'サインアップ'),
       (2, 'CHANGE_ACCOUNT_NAME', 'アカウント名変更'),
       (3, 'CHANGE_EMAIL_ADDRESS', 'メールアドレス変更'),
       (4, 'CHANGE_PASSWORD', 'パスワード変更'),
       (5, 'DELETE', '削除');

CREATE SEQUENCE account.account_transaction_id_sequence;
CREATE TABLE account.account_transactions (
  account_transaction_id      BIGINT    NOT NULL,
  account_id                  BIGINT    NOT NULL,
  account_transaction_type_id BIGINT    NOT NULL,
  account_name_revision_id    BIGINT    NOT NULL,
  email_address_revision_id   BIGINT    NOT NULL,
  password_revision_id        BIGINT    NOT NULL,
  operated_at                 TIMESTAMP NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_account_transactions PRIMARY KEY (account_transaction_id),
  CONSTRAINT fk_account_transactions_to_accounts FOREIGN KEY (account_id) REFERENCES account.accounts (account_id),
  CONSTRAINT fk_account_transactions_to_transaction_types FOREIGN KEY (account_transaction_type_id) REFERENCES account.account_transaction_types (account_transaction_type_id),
  CONSTRAINT fk_account_transactions_to_account_name_revisions FOREIGN KEY (account_name_revision_id) REFERENCES account.account_name_revisions (account_name_revision_id),
  CONSTRAINT fk_account_transactions_to_email_address_revisions FOREIGN KEY (email_address_revision_id) REFERENCES account.email_address_revisions (email_address_revision_id),
  CONSTRAINT fk_account_transactions_to_password_revisions FOREIGN KEY (password_revision_id) REFERENCES account.password_revisions (password_revision_id)
);

