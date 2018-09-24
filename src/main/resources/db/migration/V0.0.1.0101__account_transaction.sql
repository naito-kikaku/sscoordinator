DROP SCHEMA IF EXISTS account_transaction CASCADE;
CREATE SCHEMA account_transaction;

CREATE TABLE account_transaction.account_transaction_types (
  account_transaction_type_id BIGINT       NOT NULL,
  account_transaction_type    VARCHAR(64)  NOT NULL,
  desctiption                 VARCHAR(128) NOT NULL,
  CONSTRAINT pk_account_transaction_types PRIMARY KEY (account_transaction_type_id)
);

INSERT INTO account_transaction.account_transaction_types (account_transaction_type_id,
                                                           account_transaction_type,
                                                           desctiption)
VALUES (1, 'SIGN_UP', 'サインアップ'),
       (2, 'CHANGE_ACCOUNT_NAME', 'アカウント名変更'),
       (3, 'CHANGE_EMAIL_ADDRESS', 'メールアドレス変更'),
       (4, 'CHANGE_PASSWORD', 'パスワード変更'),
       (5, 'DELETE', '削除');

CREATE SEQUENCE account_transaction.account_transaction_id_sequence;
CREATE TABLE account_transaction.account_transactions (
  account_transaction_id      BIGINT    NOT NULL,
  account_id                  BIGINT    NOT NULL,
  account_transaction_type_id BIGINT    NOT NULL,
  created_at                  TIMESTAMP NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_account_transactions PRIMARY KEY (account_transaction_id),
  CONSTRAINT fk_account_transactions_to_accounts FOREIGN KEY (account_id) REFERENCES account.accounts (account_id),
  CONSTRAINT fk_account_transactions_to_transaction_types FOREIGN KEY (account_transaction_type_id) REFERENCES account_transaction.account_transaction_types (account_transaction_type_id)
);

CREATE TABLE account_transaction.account_sign_up_transactions (
  account_transaction_id    BIGINT NOT NULL,
  account_name_revision_id  BIGINT NOT NULL,
  email_address_revision_id BIGINT NOT NULL,
  password_revision_id      BIGINT NOT NULL,
  CONSTRAINT pk_sign_up_transactions PRIMARY KEY (account_transaction_id),
  CONSTRAINT fk_sign_up_transactions_to_parent FOREIGN KEY (account_transaction_id) REFERENCES account_transaction.account_transactions (account_transaction_id),
  CONSTRAINT fk_sign_up_transactions_to_account_name_revisions FOREIGN KEY (account_name_revision_id) REFERENCES account.account_name_revisions (account_name_revision_id),
  CONSTRAINT fk_sign_up_transactions_to_email_address_revisions FOREIGN KEY (email_address_revision_id) REFERENCES account.email_address_revisions (email_address_revision_id),
  CONSTRAINT fk_sign_up_transactions_to_password_revisions FOREIGN KEY (password_revision_id) REFERENCES account.password_revisions (password_revision_id)
);

CREATE TABLE account_transaction.change_account_name_transactions (
  account_transaction_id   BIGINT NOT NULL,
  account_name_revision_id BIGINT NOT NULL,
  CONSTRAINT pk_change_account_name_transactions PRIMARY KEY (account_transaction_id),
  CONSTRAINT fk_change_account_name_transactions_to_parent FOREIGN KEY (account_transaction_id) REFERENCES account_transaction.account_transactions (account_transaction_id),
  CONSTRAINT fk_change_account_name_transactions_to_account_name_revisions FOREIGN KEY (account_name_revision_id) REFERENCES account.account_name_revisions (account_name_revision_id)
);

CREATE TABLE account_transaction.change_email_address_transactions (
  account_transaction_id    BIGINT NOT NULL,
  email_address_revision_id BIGINT NOT NULL,
  CONSTRAINT pk_change_email_address_transactions PRIMARY KEY (account_transaction_id),
  CONSTRAINT fk_change_email_address_transactions_to_parent FOREIGN KEY (account_transaction_id) REFERENCES account_transaction.account_transactions (account_transaction_id),
  CONSTRAINT fk_change_email_address_transactions_to_email_address_revisions FOREIGN KEY (email_address_revision_id) REFERENCES account.email_address_revisions (email_address_revision_id)
);

CREATE TABLE account_transaction.change_password_transactions (
  account_transaction_id BIGINT NOT NULL,
  password_revision_id   BIGINT NOT NULL,
  CONSTRAINT pk_change_password_transactions PRIMARY KEY (account_transaction_id),
  CONSTRAINT fk_change_password_transactions_to_parent FOREIGN KEY (account_transaction_id) REFERENCES account_transaction.account_transactions (account_transaction_id),
  CONSTRAINT fk_change_password_transactions_to_password_revisions FOREIGN KEY (password_revision_id) REFERENCES account.password_revisions (password_revision_id)
);