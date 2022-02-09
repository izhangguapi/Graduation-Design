USE website;

-- 清除表数据
truncate table `users`;
truncate table `contests`;

-- 查询数据
SELECT * FROM `groups`;
SELECT * FROM `users`;
SELECT * FROM `contests`;
SELECT * FROM `messages`;
SELECT * FROM `scores`;

SELECT scores.contest_id as '好家伙' FROM `scores` inner join `contests` ON scores.contest_id = contests.contest_id  WHERE contestant = 12;


SELECT * FROM `scores` inner join `users`  ON scores.contestant = users.user_id inner join`contests`ON scores.contest_id =contests.contest_id  WHERE scores_id = 34 GROUP BY scores_id;

SELECT   scores_id,contest_title   FROM scores           inner join `contests` ON scores.contest_id = contests.contest_id WHERE contestant = 7
SELECT * FROM scores WHERE (user_id = 7)
-- 
SELECT * FROM `contests` inner join groups g ON users.group_id = g.group_id WHERE group_id = 4;

-- 用户表外连查询（用户组名）
SELECT * FROM users inner join `groups` ON users.group_id = `groups`.group_id;

SELECT user_id, name, sex, birthday, school, address, phone, email, users.group_id, password, group_name, encoding FROM users inner join groups ON users.group_id = groups.group_id;

SELECT COUNT(*) FROM users INNER JOIN groups ON users.group_id = groups.group_id;

SELECT * FROM groups WHERE encoding = 'YVN1U' AND group_id NOT IN ('1','2','3');

SELECT * FROM users LIMIT 10,5

SELECT * FROM users u inner join groups g ON u.group_id = g.group_id order by user_id LIMIT 5

SELECT * FROM users inner join groups ON users.group_id = groups.group_id order users

SELECT COUNT(*) FROM (SELECT users.* groups.group_name FROM users INNER JOIN groups ON users.group_id = groups.group_id GROUP BY user_id) TOTAL

SELECT user_id,name,sex,birthday,school,address,phone,email,group_id,password FROM users WHERE (phone = '' OR email = '13886961359@q.com') and password = '13886961359'
-- 学生查询自己报名的比赛
-- SELECT scores_id,contest_title,promulgator FROM scores inner join contests ON enter.contest_id = contests.contest_id WHERE contestant = 7

SELECT COUNT(*) AS total FROM users INNER JOIN groups ON users.group_id = groups.group_id

SELECT * FROM users inner join groups ON users.group_id = groups.group_id order by user_id LIMIT 5

INSERT INTO `groups` VALUES (DEFAULT,'asd','OY6TT');SELECT LAST_INSERT_ID();

INSERT INTO `groups` VALUES ('1','352');

INSERT INTO groups(group_name,encoding) VALUES ('141','31542');

SELECT name,group_name,contest_title,contest_text,start_time,end_time FROM contests c,groups g,users u WHERE c.promulgator=u.user_id AND c.group_id=g.group_id AND contest_id='2'

SELECT COUNT( * ) FROM scores WHERE (contest_id = 2 AND contestant = 11)
SELECT COUNT( * ) FROM scores WHERE (contest_id = 5 AND contestant = 11)
INSERT INTO scores  ( contest_id, contestant )  VALUES  ( '2', '11' )


SELECT contest_id,contest_title,name,reg_start_time,reg_end_time FROM contests c,users u WHERE c.promulgator=u.user_id LIMIT 8,4