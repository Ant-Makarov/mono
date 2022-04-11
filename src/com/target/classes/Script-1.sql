
create table mono.users 
(
	id bigint,
	fullName varchar(100) not null,
	email varchar(100) not null,
	phoneNumber varchar(20) not null,
	constraint users_pk primary key(id)
)

create table mono.post_offices 
(
	post_office_id bigint primary key,
	description varchar(1000)
)

create table mono.parcel_sendings
(
	sendID bigint primary key,
	senderID bigint references mono.users,
	sender_PO_ID bigint not null,
	receiver_PO_ID bigint not null,
	receiver_FIO varchar(100) not null,
	receiverPhone varchar(20) not null,
	status varchar(10) not null,
	creation_dateTime timestamp not null,
	statusChange_dateTime timestamp 
)

create table mono.notifications 
(
	sendID bigint references mono.parcel_sendings,
	message varchar(100),
	status varchar(10)
)
select * from mono.parcel_sendings
select * from mono.users 
select * from mono.post_offices 
select * from mono.notifications 
delete from mono.users where id = 10000001
delete from mono.post_offices where post_office_id = 20000001
delete from mono.post_offices where post_office_id = 20000002
delete from mono.parcel_sendings where sendid = 30000001
delete from mono.parcel_sendings where sendid = 30000002
delete from mono.parcel_sendings where sendid = 30000003
drop table mono.users
drop table mono.parcel_sendings 
drop table mono.notifications
drop table mono.post_offices 
truncate mono.users cascade
truncate mono.post_offices