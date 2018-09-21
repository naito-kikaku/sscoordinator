DROP SCHEMA IF EXISTS account CASCADE;
CREATE SCHEMA account;

CREATE SEQUENCE account.account_sequence_number;
CREATE TABLE account.accounts (
  account_id BIGINT    NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_accounts PRIMARY KEY (account_id)
);

CREATE SEQUENCE account.account_name_sequence_number;
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

CREATE SEQUENCE account.email_address_revision_sequence_number;
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

CREATE SEQUENCE account.password_revision_sequence_number;
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

CREATE TABLE account.active_email_addresses (
  email_address VARCHAR(255) NOT NULL,
  CONSTRAINT pk_active_email_addresses PRIMARY KEY (email_address)
);

CREATE TABLE account.deleted_accounts (
  account_id BIGINT    NOT NULL,
  deleted_at TIMESTAMP NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_deleted_accounts PRIMARY KEY (account_id)
);