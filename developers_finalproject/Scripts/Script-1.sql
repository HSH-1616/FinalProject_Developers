<<<<<<< HEAD
		SELECT *
		FROM ACCOMMODATION a
		LEFT JOIN AC_FILE USING(AC_ID)
		LEFT JOIN AC_PAY USING(AC_ID)
		LEFT JOIN AC_REVIEW r USING(AC_ID)
		LEFT JOIN MEMBER m ON r.MEMBER_ID =m.MEMBER_ID  
		LEFT JOIN AR_FILE USING(AR_ID)
		LEFT JOIN AC_FACILITIES USING(AC_ID)
		LEFT JOIN AFA_LIST USING(AFA_ID)
		WHERE AC_ID=5
		ORDER BY AF_MAIN_YN DESC
		;
	
		SELECT *
		FROM ACCOMMODATION
		LEFT JOIN AC_REVIEW USING(AC_ID)
		LEFT JOIN MEMBER USING(MEMBER_ID)
		WHERE AC_ID=5;
		
		
	SELECT * FROM "MEMBER" m ;
	SELECT * FROM AC_REVIEW ar ;
	
	SELECT a.*,(SELECT COUNT(*) FROM AC_REVIEW WHERE AC_ID=a.AC_ID)REVIEWCOUNT,
	(SELECT AVG(AR_GRADE)FROM AC_REVIEW r WHERE a.AC_ID=r.AC_ID)REVIEWGRADE  
	FROM ACCOMMODATION a WHERE AC_ID =5;
	
SELECT a.*,f.*,
		(SELECT AVG(AR_GRADE)FROM AC_REVIEW r WHERE a.AC_ID=r.AC_ID)REVIEWGRADE 
		FROM ACCOMMODATION a
		LEFT JOIN AC_FILE f ON a.AC_ID=f.AC_ID ;		
	
	SELECT a.*,f.*,
		(SELECT COUNT(*) FROM AC_REVIEW WHERE AC_ID=a.AC_ID)REVIEWCOUNT,
		(SELECT AVG(AR_GRADE)FROM AC_REVIEW r WHERE a.AC_ID=r.AC_ID)REVIEWGRADE  
		FROM ACCOMMODATION a
		LEFT JOIN AC_FILE f ON a.AC_ID=f.AC_ID
		WHERE a.AC_ID =5;
		
SELECT*FROM MEMBER;