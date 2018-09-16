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
  event_id BIGINT NOT NULL,
  CONSTRAINT pk_close_events PRIMARY KEY (event_id),
  CONSTRAINT fk_close_events_to_events FOREIGN KEY (event_id) REFERENCES event.events (event_id)
)