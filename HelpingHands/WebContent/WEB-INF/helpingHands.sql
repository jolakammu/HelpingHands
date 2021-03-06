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

 SELECT * FROM HH_VOLUNTEER_SCH_ITEMS

CREATE TABLE APP.HH_VOLUNTEER_SCH_ITEMS (

									VOLUNTEER_SCH_ITEM_ID INT PRIMARY KEY NOT NULL,
									USER_ID 		      INT NOT NULL, 
									VOLUNTEER_ITEM_ID     INT NOT NULL,
									SIGNED_MAN_HRS   INT NOT NULL
									
									)
									

Insert into APP.HH_VOLUNTEER_SCH_ITEMS (VOLUNTEER_SCH_ITEM_ID,USER_ID ,VOLUNTEER_ITEM_ID,SIGNED_MAN_HRS) values (?,?,?,?);								

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


Create Table app.HH_VOLUNTEER_ITEMS (
									VOLUNTEER_ITEM_ID INT PRIMARY KEY NOT NULL, 
									ORG_NAME VARCHAR(100), 
									ORG_CATEGORY VARCHAR(10), 
									WORK_DESC VARCHAR(500) NOT NULL,
									MAN_HRS   INT NOT NULL,
									WORK_BEGIN_DT   DATE not null,
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
						
truncate table app.SE_USER


select * from app.SE_USER


select * from app.HH_ELEC_COMMU


select * from app.HH_VOLUNTEER_ITEMS

truncate table app.se_user;
truncate table app.hh_doc;
truncate table app.HH_VOLUNTEER_ITEMS;
truncate table app.HH_VOLUNTEER_SCH_ITEMS;
truncate table app.HH_ELEC_COMMU;
truncate table app.HH_ADDRESS;




Select vi.VOLUNTEER_ITEM_ID, vi.ORG_NAME, vi.ORG_CATEGORY, vi.WORK_DESC, vi.MAN_HRS, vi.WORK_BEGIN_DT, vi.ADDRESS_ID, addr.DELIVERY_TXT, addr.city, addr.state_cd, 
addr.COUNTRY_CD, addr.ZIP_TXT, vi.ELEC_COMMU_ID, ec.ELEC_COMMU_TYP, ec.ELEC_COMMU_NUM 
from app.HH_VOLUNTEER_ITEMS vi, APP.HH_ADDRESS addr, APP.HH_ELEC_COMMU ec , APP.HH_VOLUNTEER_SCH_ITEMS vsi
where vi.ADDRESS_ID = addr.ADDRESS_ID (+)  and vi.ELEC_COMMU_ID = ec.ELEC_COMMU_ID and vi.VOLUNTEER_ITEM_ID = vsi.VOLUNTEER_ITEM_ID and vsi.user_id = 2	
order by vi.WORK_BEGIN_DT asc






									 


Select vi.VOLUNTEER_ITEM_ID, vi.ORG_NAME, vi.ORG_CATEGORY, vi.WORK_DESC, vi.MAN_HRS, vi.WORK_BEGIN_DT, vi.ADDRESS_ID, addr.DELIVERY_TXT, addr.city, addr.state_cd, addr.COUNTRY_CD, addr.ZIP_TXT, vi.ELEC_COMMU_ID, ec.ELEC_COMMU_TYP, ec.ELEC_COMMU_NUM from app.HH_VOLUNTEER_ITEMS vi, APP.HH_ADDRESS addr, APP.HH_ELEC_COMMU ec where vi.ADDRESS_ID = addr.ADDRESS_ID and   vi.ELEC_COMMU_ID = ec.ELEC_COMMU_ID


insert into APP.HH_VOLUNTEER_ITEMS (VOLUNTEER_ITEM_ID, 
									ORG_NAME, 
									ORG_CATEGORY , 
									WORK_DESC ,
									MAN_HRS,
									WORK_BEGIN_DT,
									ADDRESS_ID,
									ELEC_COMMU_ID)
									values (3,'TCEQ', 'EMV','Beach cleanup',10,'2016-01-01',2,2)
									
select * from app.HH_VOLUNTEER_ITEMS





Select vi.VOLUNTEER_ITEM_ID, vi.ORG_NAME, vi.ORG_CATEGORY, vi.WORK_DESC, vi.MAN_HRS, vi.WORK_BEGIN_DT, vi.ADDRESS_ID, addr.DELIVERY_TXT, addr.city, addr.state_cd, addr.COUNTRY_CD, addr.ZIP_TXT, vi.ELEC_COMMU_ID, ec.ELEC_COMMU_TYP, ec.ELEC_COMMU_NUM, vsi.VOLUNTEER_SCH_ITEM_ID,vsi.USER_ID,vsi.VOLUNTEER_ITEM_ID,vsi.SIGNED_MAN_HRS from app.HH_VOLUNTEER_ITEMS vi, APP.HH_ADDRESS addr, APP.HH_ELEC_COMMU ec , APP.HH_VOLUNTEER_SCH_ITEMS vsi where vi.ADDRESS_ID = addr.ADDRESS_ID  and vi.ELEC_COMMU_ID = ec.ELEC_COMMU_ID and vi.VOLUNTEER_ITEM_ID = vsi.VOLUNTEER_ITEM_ID and vsi.user_id = 2 order by vi.WORK_BEGIN_DT asc


select sum(SIGNED_MAN_HRS) as SIGNED_MAN_HRS from app.HH_VOLUNTEER_SCH_ITEMS where VOLUNTEER_ITEM_ID = 2


CREATE INDEX ihvipk ON HH_VOLUNTEER_ITEMS(VOLUNTEER_ITEM_ID);

drop index app.ihvipk;

CREATE unique INDEX ihvsipk ON app.HH_VOLUNTEER_SCH_ITEMS(VOLUNTEER_SCH_ITEM_ID);
CREATE INDEX ihvsi01 ON app.HH_VOLUNTEER_SCH_ITEMS(VOLUNTEER_ITEM_ID);
CREATE INDEX ihvsi02 ON app.HH_VOLUNTEER_SCH_ITEMS(USER_ID);
CREATE INDEX ihvsi03 ON app.HH_VOLUNTEER_SCH_ITEMS(USER_ID,VOLUNTEER_ITEM_ID);



Delete from  app.HH_VOLUNTEER_SCH_ITEMS where VOLUNTEER_SCH_ITEM_ID = 2;



Create table hh_doc (DOC_ID	INT PRIMARY KEY NOT NULL,
					 DOC_NAME   VARCHAR(256) NOT NULL,
					 DOC_TYP    VARCHAR(10) NOT NULL,
					 
					)
					

drop table app.hh_doc
CREATE TABLE app.hh_doc
(

  DOC_ID			INT PRIMARY KEY NOT NULL,
  FILENAME_TXT		VARCHAR(256) NOT NULL,
  FORMAT_CD     	VARCHAR(256)  NOT NULL,
  FILE_DESC_TXT 	VARCHAR(256),
  CREATED_BY_TXT	VARCHAR(100) NOT NULL,
  CREATE_DT		    Date NOT NULL,
  PARENT_TABLE_ID   INT NOT NULL,
  PARENT_TABLE_NAME   VARCHAR(25) NOT NULL,
  DATA              BLOB NOT NULL
);					


select  DOC_ID, FILENAME_TXT,  FORMAT_CD,  FILE_DESC_TXT,  CREATED_BY_TXT, CREATE_DT,  from app.hh_doc where PARENT_TABLE_ID = 1 and PARENT_TABLE_NAME = 'SE_USER';


Select vi.VOLUNTEER_ITEM_ID, vi.ORG_NAME, vi.ORG_CATEGORY, vi.WORK_DESC, vi.MAN_HRS, vi.WORK_BEGIN_DT, vi.ADDRESS_ID, addr.DELIVERY_TXT, addr.city, addr.state_cd, addr.COUNTRY_CD, addr.ZIP_TXT, vi.ELEC_COMMU_ID, ec.ELEC_COMMU_TYP, ec.ELEC_COMMU_NUM from app.HH_VOLUNTEER_ITEMS vi, APP.HH_ADDRESS addr, APP.HH_ELEC_COMMU ec where vi.ADDRESS_ID = addr.ADDRESS_ID and vi.ELEC_COMMU_ID = ec.ELEC_COMMU_ID order by vi.WORK_BEGIN_DT ASC

select * from app.se_user

alter table app.se_user add column salt  varchar(32);

alter table app.se_user alter column salt set data type varchar(32);


Update APP.HH_VOLUNTEER_SCH_ITEMS set SIGNED_MAN_HRS =  8 where VOLUNTEER_SCH_ITEM_ID = 1
