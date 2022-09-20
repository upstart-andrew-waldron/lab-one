create table if not exists box
(
    id        BIGSERIAL PRIMARY KEY,
    box_label VARCHAR(255),
    width     VARCHAR(255),
    height    VARCHAR(255),
    depth     VARCHAR(255),
    weight    VARCHAR(255)
)