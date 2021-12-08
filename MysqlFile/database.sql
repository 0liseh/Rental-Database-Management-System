DROP DATABASE IF EXISTS PROPERTYMANAGEMENT;
CREATE DATABASE PROPERTYMANAGEMENT; 
USE PROPERTYMANAGEMENT;

CREATE TABLE USER (
	username 	varchar(50), 
    id			integer not null,
    email 		varchar(50), 
    phoneNumber char(12),
    password1 	varchar(50), 
    type1 		varchar(50),
    primary key(id)
    
);

DROP TABLE IF EXISTS PROPERTY;
CREATE TABLE PROPERTY (
	propertyId		integer not null,
	propertyType	varchar(50),
	numberOfBed		integer,
    numberOfBath	integer,
    furnished		boolean,
    area			varchar(50), 
	status1			varchar(50), 
    landlordID		integer not null,
	primary key (propertyId),
    foreign key (landlordID) references LOGIN(id) ON UPDATE CASCADE
);

DROP TABLE IF EXISTS FEE;
CREATE TABLE FEE (
	fee double, 
    duration integer not null,
    primary key(duration)
    
);
