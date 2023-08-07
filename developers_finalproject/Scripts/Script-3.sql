CREATE TABLE community(
	community_no NUMBER PRIMARY KEY,
	member_id NUMBER CONSTRAINT comu_id REFERENCES MEMBER(member_id) ON DELETE CASCADE,
	comunity_title varchar2(100) NOT null,
	community_content varchar2(500),
	create_date DATE DEFAULT sysdate,
	modify_date DATE DEFAULT sysdate,
	community_views NUMBER,
	like_count number
);

CREATE TABLE community_file(
	file_name varchar2(300) CONSTRAINT comu_file_name PRIMARY KEY,
	upload_date DATE DEFAULT sysdate,
	community_no NUMBER CONSTRAINT comu_file_no REFERENCES community(community_no) ON DELETE CASCADE
);

SELECT * FROM community_file;
SELECT * FROM community;
CREATE SEQUENCE comu_seq nocache;

ALTER TABLE community RENAME COLUMN comunity_title TO community_title;
SELECT * FROM MEMBER;

INSERT INTO community values(comu_seq.nextval,10000,'test','test',DEFAULT,DEFAULT,0,0);