--set schema app;

--select * from ACC_USER

--drop table se_user

CREATE TABLE SE_USER (
						ID 						INT PRIMARY KEY NOT NULL, 
						EMAIL_TXT				VARCHAR(254) NOT NULL,
						NAME					VARCHAR(100) NOT NULL,
  						PASSWORD_TXT			VARCHAR(8) NOT NULL,
  						PASSWORD_EXPIRY_DT		DATE,
  						ROLE_CD					VARCHAR(10)
  					)
  


 select ID,EMAIL_TXT,NAME,PASSWORD_TXT,to_char(PASSWORD_EXPIRY_DT,'MM/DD/YYYY'),ROLE_CD from SE_USER where EMAIL_TXT = 'jolakammu@yahoo.com'
 
 
 
CREATE TABLE HH_ADDRESS (
							ADDRESS_ID				INT PRIMARY KEY NOT NULL, 
							DELIVERY_TXT			VARCHAR(50) NOT NULL,
							CITY					VARCHAR(32) NOT NULL,
  							STATE_CD    			VARCHAR(2) NOT NULL,
							MOBILE_PHONE_NUM		VARCHAR(10),
							WORK_PHONE_NUM		    VARCHAR(10),
							USER_ID					INT NOT NULL
  						)
   
 
