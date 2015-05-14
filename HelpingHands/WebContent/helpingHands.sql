--set schema app;

--select * from ACC_USER

--drop table se_user

CREATE TABLE SE_USER (
						USER_ID 				INT PRIMARY KEY NOT NULL, 
						EMAIL_TXT				VARCHAR(254) NOT NULL,
						NAME					VARCHAR(100) NOT NULL,
  						PASSWORD_TXT			VARCHAR(8) NOT NULL,
  						PASSWORD_EXPIRY_DT		DATE,
  						ROLE_CD					VARCHAR(10),
  						USER_TYP				VARCHAR(10) NOT NULL, 
  						ADDRESS_ID				INT NOT NULL,
  						ELEC_COMMU_ID			INT
  					)
  


 select ID,EMAIL_TXT,NAME,PASSWORD_TXT,to_char(PASSWORD_EXPIRY_DT,'MM/DD/YYYY'),ROLE_CD from SE_USER where EMAIL_TXT = 'jolakammu@yahoo.com'
 
 
Drop table HH_ADDRESS
 
CREATE TABLE HH_ADDRESS (
							ADDRESS_ID				INT PRIMARY KEY NOT NULL, 
							DELIVERY_TXT			VARCHAR(50) NOT NULL,
							CITY					VARCHAR(32) NOT NULL,
  							STATE_CD    			VARCHAR(2) NOT NULL,
  							COUNTRY_CD    			VARCHAR(3) NOT NULL,
  							ZIP_TXT						VARCHAR(5)
  						)

drop table HH_ELEC_COMMU

CREATE TABLE HH_ELEC_COMMU
			(
				ELEC_COMMU_ID	INT PRIMARY KEY NOT NULL,
				ELEC_COMMU_TYP  VARCHAR(10) NOT NULL,
				ELEC_COMMU_NUM	VARCHAR(10) NOT NULL
			)
  													
 
drop table HH_VOLUNTEER_ITEMS


Create Table HH_VOLUNTEER_ITEMS (
									VOLUNTEER_ITEM_ID INT PRIMARY KEY NOT NULL, 
									ORG_NAME VARCHAR(100), 
									ORG_CATEGORY VARCHAR(10), 
									WORK_DESC VARCHAR(500) NOT NULL,
									MAN_HRS   INT NOT NULL,
									WORK_BEGIN_DT   DATE NOT NULL,
									ADDRESS_ID      INT NOT NULL,
									ELEC_COMMU_ID	INT
								)  
								

DROP TABLE HH_GEN_CD_TYP 

CREATE TABLE HH_GEN_CD_TYP (
								GEN_CD_TYP_ID	INT PRIMARY KEY NOT NULL,
								GEN_CD_TYP_NAME		VARCHAR(100) NOT NULL,
								GEN_CD_TYP_DESC		VARCHAR(250)
							)								


CREATE TABLE HH_GEN_CD	(
							GEN_CD_TXT		VARCHAR(10) NOT NULL,
							GEN_CD_NAME		VARCHAR(100) NOT NULL,
							GEN_CD_BEGIN_DT DATE,
							GEN_CD_END_DT DATE,
							GEN_CD_TYP_ID	INT NOT NULL							
						)
 
						select coalesce(Max(ADDRESS_ID),0) + 1 as ADDRESS_ID from app.HH_ADDRESS
						
						select Max(RECIPE_ID) + 1 as ID from app.RECIPE
						
truncate table app.HH_ELEC_COMMU
truncate table app.HH_ADDRESS
truncate table app.SE_USER


select * from app.SE_USER
