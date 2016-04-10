DROP TABLE IF EXISTS Users ;

CREATE TABLE Users (
uid DECIMAL(9,0) KEY,
last_name VARCHAR(20),
first_name VARCHAR(20),
flagged BOOLEAN NOT NULL DEFAULT FALSE,
role ENUM('supervisor','librarian','patron'));

DROP TABLE IF EXISTS Assets ;

CREATE TABLE Assets (
ISBN DECIMAL(13) KEY,
title TEXT,
publication DATE,
num_tot DECIMAL(4));

DROP TABLE IF EXISTS Wrote ;

CREATE TABLE Wrote (
last_name VARCHAR(20),
first_name VARCHAR(20),
middle_name VARCHAR(20),
ISBN DECIMAL(13));

DROP TABLE IF EXISTS Subjects ;

CREATE TABLE Subjects (
subject VARCHAR(20),
ISBN DECIMAL(13));

DROP TABLE IF EXISTS CheckedOut ;

CREATE TABLE CheckedOut (
PRIMARY KEY (uid, ISBN, due_date),
uid DECIMAL(9),
ISBN DECIMAL(13),
due_date DATE,
num_out DECIMAL(2));
