-- 서버를 실행하면 자동으로 실행되어 DB에 값이 추가 됨.
insert into user values(9001, sysdate(), 'User1', 'test1111', '001100-1234567');
insert into user values(9002, sysdate(), 'User2', 'test2222', '001100-1234567');
insert into user values(9003, sysdate(), 'User3', 'test2222', '001100-1234567');

insert into post values(1001, 'My first post', 9001);
insert into post values(1002, 'My second post', 9001);