CREATE TABLE tasks (
    id BIGSERIAL PRIMARY KEY,

    title VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    priority VARCHAR(50) NOT NULL,

    creator_id BIGINT NOT NULL,
    assigned_user_id BIGINT,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deadline_date_time TIMESTAMP,
    done_date_time TIMESTAMP
);