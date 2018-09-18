CREATE SCHEMA event;

CREATE SEQUENCE event.event_sequence_number;
CREATE TABLE event.events (
  event_id   BIGINT    NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_events PRIMARY KEY (event_id)
);

CREATE SEQUENCE event.event_revision_sequence_number;
CREATE TABLE event.event_revisions (
  event_revision_id     BIGINT       NOT NULL,
  event_id              BIGINT       NOT NULL,
  event_revision_number BIGINT       NOT NULL,
  event_name            VARCHAR(128) NOT NULL,
  event_description     TEXT,
  revised_at            TIMESTAMP    NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_event_revisions PRIMARY KEY (event_revision_id),
  CONSTRAINT fk_event_revisions_to_parent FOREIGN KEY (event_id) REFERENCES event.events (event_id)
);

CREATE TABLE event.latest_events (
  event_id              BIGINT NOT NULL,
  event_revision_id     BIGINT NOT NULL,
  event_revision_number BIGINT NOT NULL,
  CONSTRAINT pk_latest_events PRIMARY KEY (event_id),
  CONSTRAINT fk_latest_events_to_events FOREIGN KEY (event_id) REFERENCES event.events (event_id),
  CONSTRAINT fk_latest_events_to_event_revision FOREIGN KEY (event_revision_id) REFERENCES event.event_revisions (event_revision_id)
);

CREATE TABLE event.closed_events (
  event_id  BIGINT    NOT NULL,
  closed_at TIMESTAMP NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_close_events PRIMARY KEY (event_id),
  CONSTRAINT fk_close_events_to_events FOREIGN KEY (event_id) REFERENCES event.events (event_id)
);

CREATE SCHEMA account;

CREATE SEQUENCE account.account_sequence_number;
CREATE TABLE account.accounts (
  account_id BIGINT    NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_accounts PRIMARY KEY (account_id)
);

CREATE SEQUENCE account.email_revision_sequence_number;
CREATE TABLE account.email_revisions (
  email_revision_id BIGINT       NOT NULL,
  account_id        BIGINT       NOT NULL,
  email             VARCHAR(255) NOT NULL,
  revised_at        TIMESTAMP    NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_email_revisions PRIMARY KEY (email_revision_id),
  CONSTRAINT fk_account_revisions_to_parent FOREIGN KEY (account_id) REFERENCES account.accounts (account_id)
);

CREATE TABLE event.latest_emails (
  account_id        BIGINT NOT NULL,
  email_revision_id BIGINT NOT NULL,
  CONSTRAINT pk_latest_emails PRIMARY KEY (account_id),
  CONSTRAINT fk_latest_emails_to_accounts FOREIGN KEY (account_id) REFERENCES account.accounts (account_id),
  CONSTRAINT fk_latest_emails_to_email_revisions FOREIGN KEY (email_revision_id) REFERENCES account.email_revisions (email_revision_id)
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

CREATE TABLE event.latest_passwords (
  account_id           BIGINT NOT NULL,
  password_revision_id BIGINT NOT NULL,
  CONSTRAINT pk_latest_passwords PRIMARY KEY (account_id),
  CONSTRAINT fk_latest_passwords_to_accounts FOREIGN KEY (account_id) REFERENCES account.accounts (account_id),
  CONSTRAINT fk_latest_passwords_to_password_revisions FOREIGN KEY (password_revision_id) REFERENCES account.password_revisions (password_revision_id)
);