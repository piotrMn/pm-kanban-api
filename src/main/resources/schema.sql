DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    email TEXT NOT NULL,
    password_hash TEXT NOT NULL,
    name TEXT NOT NULL,
    role TEXT,
    primary key (id)
);

DROP TABLE IF EXISTS teams;
CREATE TABLE IF NOT EXISTS teams (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    name TEXT NOT NULL,
    created_by UUID,
    primary key (id),
    foreign key (created_by) references users(id)
);

DROP TABLE IF EXISTS boards;
CREATE TABLE IF NOT EXISTS boards (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    name TEXT NOT NULL,
    team_id UUID,
    primary key (id),
    foreign key (team_id) references teams(id)
);

DROP TABLE IF EXISTS items;
CREATE TABLE IF NOT EXISTS items (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    title TEXT NOT NULL,
    description TEXT,
    type text NOT NULL,
    state text NOT NULL,
    estimation INTEGER,
    assigned_to UUID,
    created_by UUID,
    created_at TIMESTAMP NOT NULL,
    board_id UUID,
    primary key (id),
    foreign key (assigned_to) references users(id),
    foreign key (created_by) references users(id),
    foreign key (board_id) references boards(id)
);

DROP TABLE IF EXISTS comments;
CREATE TABLE IF NOT EXISTS comments (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    item_id UUID,
    author_id UUID,
    content TEXT,
    created_at TIMESTAMP,
    primary key(id),
    foreign key (author_id) references users(id),
    foreign key (item_id) references items(id)
);

DROP TABLE IF EXISTS user_team;
CREATE TABLE IF NOT EXISTS user_team (
    user_id UUID NOT NULL,
    team_id UUID NOT NULL,
    primary key (user_id, team_id),
    foreign key (team_id) references teams(id),
    foreign key (user_id) references users(id)
);

DROP TABLE IF EXISTS wip_limits;
CREATE TABLE IF NOT EXISTS wip_limits (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    team_id UUID NOT NULL,
    state TEXT NOT NULL,
    max_items INTEGER NOT NULL,
    primary key (id),
    foreign key (team_id) references teams(id)
);
