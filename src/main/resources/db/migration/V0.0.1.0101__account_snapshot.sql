DROP SCHEMA IF EXISTS account_snapshot CASCADE;
CREATE SCHEMA account_snapshot;

CREATE TABLE account_snapshot.account_statuses (
  account_status_id BIGINT       NOT NULL,
  account_status    VARCHAR(64)  NOT NULL,
  description       VARCHAR(128) NOT NULL,
  CONSTRAINT pk_account_statuses PRIMARY KEY (account_status_id)
);

INSERT INTO account_snapshot.account_statuses (account_status_id, account_status, description)
VALUES (1, 'ENABLE', '有効'),
       (2, 'DISABLE', '無効');

CREATE TABLE account_snapshot.account_snapshots (
  account_transaction_id BIGINT       NOT NULL,
  account_id             BIGINT       NOT NULL,
  account_name           VARCHAR(128) NOT NULL,
  email_address          VARCHAR(255) NOT NULL,
  password               VARCHAR(60)  NOT NULL,
  account_status_id      BIGINT       NOT NULL,
  account_created_at     TIMESTAMP    NOT NULL,
  account_revised_at     TIMESTAMP    NOT NULL,
  account_deleted_at     TIMESTAMP,
  captured_at            TIMESTAMP    NOT NULL DEFAULT now(),
  CONSTRAINT pk_account_snapshots PRIMARY KEY (account_transaction_id),
  CONSTRAINT fk_account_snapshots_to_account_transactions FOREIGN KEY (account_transaction_id) REFERENCES account.account_transactions (account_transaction_id),
  CONSTRAINT fk_account_snapshots_to_accounts FOREIGN KEY (account_id) REFERENCES account.accounts (account_id),
  CONSTRAINT fk_account_snapshots_to_account_statuses FOREIGN KEY (account_status_id) REFERENCES account_snapshot.account_statuses (account_status_id)
);