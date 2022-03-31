USE `contest_web`;

-- 清除表数据
truncate table `users`;

-- 查询数据
SELECT * FROM groups;
SELECT * FROM users;
SELECT * FROM contests;
SELECT * FROM messages;
SELECT * FROM scores;


SELECT * FROM scores WHERE contest_id = 6

INSERT INTO `messages`
VALUES
	( DEFAULT, '1', '测试公告01', '这是一条测试公告01', '1', DEFAULT, DEFAULT ),


SELECT contestant FROM scores WHERE contest_id = 1

SELECT scores_id,scores.contest_id,scores.`status`,text,result,u1.`name` 'contestant',u2.`name` 'judge',c.contest_title,u1.`name`,u1.phone,u1.school 
FROM scores 
LEFT JOIN users u1 ON u1.user_id=scores.contestant 
LEFT JOIN users u2 ON u2.user_id=scores.judge 
LEFT JOIN contests c ON c.contest_id=scores.contest_id 
WHERE scores_id = 24

SELECT contests.contest_id,COUNT(scores_id) number FROM contests
LEFT JOIN scores ON contests.contest_id=scores.contest_id
WHERE contests.contest_id = 1

SELECT contest_id,contest_title,contest_text,group_name,`name`,`status`,status_text FROM contests 
INNER JOIN groups ON contests.group_id=groups.group_id
INNER JOIN users ON contests.promulgator = users.user_id
WHERE `status`=FALSE AND contest_title LIKE '%比赛2%' ORDER BY contest_id DESC;

SELECT COUNT(*) FROM messages;
SELECT COUNT(*) FROM messages LIMIT 0,18
SELECT * FROM messages LIMIT 0,18
SELECT * FROM messages LIMIT 18,18
SELECT * FROM messages LIMIT 36,17

{userStart3=33, userStart4=44, userEnd4=10, userStart1=11, userEnd3=10, userStart2=21, userEnd2=11, userEnd1=11, userStart0=0, userEnd0=11}

SELECT * FROM messages LIMIT 0,10;
SELECT * FROM messages LIMIT 10,10;
SELECT * FROM messages LIMIT 20,10;
SELECT * FROM messages LIMIT 30,10;
SELECT * FROM messages LIMIT 40,10;

SELECT * FROM messages LIMIT 0,11;
SELECT * FROM messages LIMIT 11,11;
SELECT * FROM messages LIMIT 22,11;
SELECT * FROM messages LIMIT 33,10;
SELECT * FROM messages LIMIT 43,10;

SELECT COUNT(*) FROM messages LIMIT 0,18
SELECT COUNT(*) FROM messages LIMIT 18,18
SELECT COUNT(*) FROM messages LIMIT 36,17;

select * FROM users WHERE group_id = 3

SELECT contests.contest_id,COUNT(scores_id) number 
FROM contests 
LEFT JOIN scores ON contests.contest_id=scores.contest_id 
WHERE contests.group_id = 3 GROUP BY contest_id

SELECT groups.*,COUNT(user_id) number FROM groups
LEFT JOIN users ON groups.group_id = users.group_id
WHERE CONCAT(group_name,encoding) LIKE '%设计%'
GROUP BY groups.group_id

select * from groups where CONCAT(group_name,encoding) LIKE '%设计%'
select * from contests where CONCAT(contest_text,contest_title) LIKE '%设计%'

SELECT contest_id,contest_text,contest_title,url,name,group_name,reg_start_time,start_time,reg_end_time,end_time,promulgator FROM contests 
INNER JOIN groups g ON contests.group_id=g.group_id 
INNER JOIN users u ON contests.promulgator=u.user_id 
WHERE status = true and contest_id = 7

SELECT contest_id,contest_title,url,reg_start_time,reg_end_time FROM contests WHERE status = TRUE

SELECT contests.contest_id,contest_title,contests.`status`,status_text,`name`,COUNT(scores_id) number 
FROM contests 
LEFT JOIN scores ON contests.contest_id=scores.contest_id 
INNER JOIN users ON promulgator = user_id
WHERE contests.group_id = 3 GROUP BY contest_id

SELECT *
FROM contests 
LEFT JOIN scores ON contests.contest_id=scores.contest_id 
-- INNER JOIN users ON promulgator = user_id
WHERE contests.group_id = 3 GROUP BY contest_id

SELECT * FROM scores WHERE scores_id = 25

DELETE FROM messages WHERE recipient=2 AND state=TRUE;

SELECT contests.contest_id,contest_title,COUNT(scores_id) number FROM contests LEFT JOIN scores ON contests.contest_id=scores.contest_id WHERE group_id = 3 GROUP BY contest_id
SELECT contests.contest_id,contest_title FROM contests INNER JOIN scores ON contests.contest_id=scores.contest_id WHERE group_id = 3 GROUP BY contest_title

SELECT contests.contest_id,contest_title,status,COUNT(scores_id) number FROM contests LEFT JOIN scores ON contests.contest_id=scores.contest_id WHERE group_id = 3 GROUP BY contest_id

SELECT   contests.contest_id,contest_title,contests.`status`,COUNT(scores_id) number   FROM contests LEFT JOIN scores ON contests.contest_id=scores.contest_id  WHERE group_id = 3 GROUP BY contest_id


SELECT * FROM contests LEFT JOIN scores ON contests.contest_id=scores.contest_id WHERE group_id = 3 GROUP BY contest_title



-- 发消息

-- 评审
SELECT scores_id,user_id,`name`,school,phone,state FROM scores 
INNER JOIN users ON scores.contestant=users.user_id
WHERE contest_id = 5 ORDER BY state;

SELECT *FROM scores 
INNER JOIN users ON scores.contestant=users.user_id
WHERE contest_id = 1;

-- 根据组id查询比赛列表
SELECT * FROM contests WHERE group_id = 3

-- contest_title,COUNT(contest_title)
SELECT contests.contest_id,contest_title,COUNT(contest_title) number
 FROM contests
 INNER JOIN scores ON contests.contest_id=scores.contest_id
 WHERE group_id = 3 GROUP BY contest_title

SELECT * FROM contests c,groups g,users u WHERE c.promulgator=u.user_id AND c.group_id=g.group_id AND contest_id=1

SELECT * FROM contests
 INNER JOIN groups g ON contests.group_id=g.group_id
 INNER JOIN users u ON contests.promulgator=u.user_id
 WHERE  contest_id=1
 AND  AND 

SELECT message_id,title,time,state,`name` FROM messages INNER JOIN users ON messages.sender = users.user_id WHERE recipient = 2 ORDER BY state,time DESC
 
SELECT * FROM contests WHERE group_id = 3

SELECT * FROM contests WHERE contest_title LIKE '%年度%'

SELECT contest_id,contest_title,reg_end_time FROM contests WHERE (contest_title LIKE '%2022%') ORDER BY reg_end_time DESC

/messages/insert

SELECT message_id,title,time FROM messages WHERE recipient=1 ORDER BY time DESC
SELECT COUNT(*) FROM messages
INNER JOIN users  ON scores.contestant,scores.judge = users.user_id
 WHERE recipient=2 ORDER BY time DESC
 
UPDATE  messages SET state=TRUE  WHERE message_id  = 33
 
UPDATE  messages SET `status`=FALSE  WHERE recipient = 2
 
UPDATE  messages SET state=TRUE  WHERE message_id IN(
SELECT message_id,recipient,title,text,sender,time,state,`name` FROM messages
 INNER JOIN users  ON messages.recipient=users.user_id
 WHERE message_id=31);

SELECT message_id,title FROM messages WHERE recipient=1 ORDER BY time DESC LIMIT 5;

SELECT * FROM messages LIMIT 0,10;

SELECT scores.contest_id as '好家伙' FROM `scores` inner join `contests` ON scores.contest_id = contests.contest_id  WHERE contestant = 12;

SELECT users.name as contestant FROM `scores` 
SELECT scores_id,state,scores.contest_id,name FROM `scores` 
SELECT * FROM `scores` 
left join `users`  ON scores.contestant,scores.judge = users.user_id AS '1' AND scores.judge=users.user_id AS '2'
-- inner join`contests`ON scores.contest_id =contests.contest_id  
WHERE scores_id = 34 GROUP BY scores_id;

SELECT * FROM scores WHERE contest_id = 1 ORDER BY result DESC

select * from scores where contest_id = 5
select count(result) ranking from scores where result > 78 AND contest_id = 3
select count(*) ranking from scores where result IS NULL AND contest_id = 5

select contest_title from scores inner join contests ON scores.contest_id = contests.contest_id WHERE result IS NOT NULL AND contestant = 7

SELECT scores_id,text,result,u1.`name` 'contestant',u2.`name` 'judge',c.contest_title
 FROM scores 
 INNER JOIN users u1 ON u1.user_id=scores.contestant
 INNER JOIN users u2 ON u2.user_id=scores.judge
 INNER JOIN contests c ON c.contest_id=scores.contest_id
 WHERE scores_id = 34

SELECT scores_id,scores.contest_id,text,result,u1.`name` 'contestant',u2.`name` 'judge',c.contest_title 
FROM scores 
INNER JOIN users u1 ON u1.user_id=scores.contestant 
INNER JOIN users u2 ON u2.user_id=scores.judge 
INNER JOIN contests c ON c.contest_id=scores.contest_id 
WHERE scores_id = 15

SELECT * FROM (scores s,users u,contests c) 
WHERE s.contestant=u.user_id 
AND s.judge=u.user_id
AND scores_id = 1

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