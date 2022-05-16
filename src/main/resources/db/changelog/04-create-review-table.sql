CREATE TABLE review(
    id UUID NOT NULL PRIMARY KEY,
    score FLOAT NOT NULL,
    text VARCHAR NULL,
    reviewer_id UUID NOT NULL,
    whisky_id UUID NOT NULL,
    CONSTRAINT fk_reviewer FOREIGN KEY(reviewer_id) REFERENCES reviewer(id),
    CONSTRAINT fk_whisky FOREIGN KEY(whisky_id) REFERENCES whisky(id)
);