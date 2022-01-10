USE website

-- 清除表数据
truncate table `users`
truncate table `contests`

-- 查询数据
SELECT * FROM groups;
SELECT * FROM users;
SELECT * FROM contests;
SELECT * FROM messages;
SELECT * FROM enter;

-- 
SELECT * FROM `contests` WHERE groupId = 4

-- 用户表外连查询（用户组名）
SELECT * FROM users inner join groups ON users.group_id = groups.group_id


-- 学生查询自己报名的比赛
SELECT enter_id,contest_text,addresser FROM enter inner join contests ON enter.contest_id = contests.contest_id WHERE contestant = 7