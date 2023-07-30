CREATE TABLE notice(
	notice_no NUMBER CONSTRAINT notice_pk PRIMARY KEY,
	notice_title varchar2(100) NOT NULL,
	notice_content varchar2(100) NOT NULL,
	notice_writer varchar2(50),
	write_date DATE DEFAULT sysdate,
	update_date DATE DEFAULT sysdate,
	notice_views number
);

CREATE SEQUENCE notice_seq nocache;

SELECT * FROM notice;