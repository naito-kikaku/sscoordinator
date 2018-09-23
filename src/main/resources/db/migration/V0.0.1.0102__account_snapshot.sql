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

CREATE SEQUENCE account_snapshot.account_snapshot_id_sequence;
CREATE TABLE account_snapshot.account_snapshots (
  account_snapshot_id BIGINT       NOT NULL,
  account_id          BIGINT       NOT NULL,
  account_name        VARCHAR(128) NOT NULL,
  email_address       VARCHAR(255) NOT NULL,
  password            VARCHAR(60)  NOT NULL,
  account_status_id   BIGINT       NOT NULL,
  account_deleted_at  TIMESTAMP,
  account_revised_at  TIMESTAMP    NOT NULL,
  account_created_at  TIMESTAMP    NOT NULL,
  captured_at         TIMESTAMP    NOT NULL DEFAULT now(),
  CONSTRAINT pk_account_snapshots PRIMARY KEY (account_snapshot_id),
  CONSTRAINT fk_account_snapshots_to_accounts FOREIGN KEY (account_id) REFERENCES account.accounts (account_id),
  CONSTRAINT fk_account_snapshots_to_account_statuses FOREIGN KEY (account_status_id) REFERENCES account_snapshot.account_statuses (account_status_id)
);

CREATE TABLE account_snapshot.snapshot_x_account_name_revision (
  account_snapshot_id      BIGINT NOT NULL,
  account_name_revision_id BIGINT NOT NULL,
  CONSTRAINT pk_snapshot_x_account_name_revision PRIMARY KEY (account_snapshot_id, account_name_revision_id),
  CONSTRAINT fk_snapshot_x_account_name_revision_to_parent FOREIGN KEY (account_snapshot_id) REFERENCES account_snapshot.account_snapshots (account_snapshot_id),
  CONSTRAINT fk_snapshot_x_account_name_revision_to_account_name_revisions FOREIGN KEY (account_name_revision_id) REFERENCES account.account_name_revisions (account_name_revision_id)
);

CREATE TABLE account_snapshot.snapshot_x_email_address_revision (
  account_snapshot_id       BIGINT NOT NULL,
  email_address_revision_id BIGINT NOT NULL,
  CONSTRAINT pk_snapshot_x_email_address_revision PRIMARY KEY (account_snapshot_id, email_address_revision_id),
  CONSTRAINT fk_snapshot_x_email_address_revision_to_parent FOREIGN KEY (account_snapshot_id) REFERENCES account_snapshot.account_snapshots (account_snapshot_id),
  CONSTRAINT fk_snapshot_x_email_address_revision_to_email_address_revisions FOREIGN KEY (email_address_revision_id) REFERENCES account.email_address_revisions (email_address_revision_id)
);

CREATE TABLE account_snapshot.snapshot_x_password_revision (
  account_snapshot_id  BIGINT NOT NULL,
  password_revision_id BIGINT NOT NULL,
  CONSTRAINT pk_snapshot_x_password_revision PRIMARY KEY (account_snapshot_id, password_revision_id),
  CONSTRAINT fk_snapshot_x_password_revision_to_parent FOREIGN KEY (account_snapshot_id) REFERENCES account_snapshot.account_snapshots (account_snapshot_id),
  CONSTRAINT fk_snapshot_x_password_revision_to_password_revisions FOREIGN KEY (password_revision_id) REFERENCES account.password_revisions (password_revision_id)
);