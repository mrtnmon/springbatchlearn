
insert into ees_us.unit(
	ID,
	LOCATIONID,
	NAME,
	EAN,
	COMMUNICATIONTYPE,
	COMMUNICATIONADDRESS,
	DIRECTION,
	EARLIERSTART,
	MINPRODUCTION,
	MAXPRODUCTION
)
select
	ees_us.unitid.nextval,
	fk_location as LOCATIONID,
	NAME,
	eancode as EAN,
	(case when f2switchable=1 then 'ALPHABOXCHP' else 'MANUAL' end) as COMMUNICATIONTYPE,
	'n/a' as COMMUNICATIONADDRESS,
	(case when type='production' then 'INJECTION' else 'SUPPLY' end) as DIRECTION,
	startuptime as EARLIERSTART,
	0 as MINPRODUCTION,
	power as MAXPRODUCTION
from owner_bhp.unit u
where not exists (select * from ees_us.unit e where e.locationid=u.fk_location and e.name=u.name);


insert into EES_US.POWERCHANGESTEP(id,unitid,power)
select EES_US.POWERCHANGESTEPid.nextval,id as unitid,power from (
  select id,MINPRODUCTION as power from ees_us.unit where MINPRODUCTION!=0
  union
  select id,MAXPRODUCTION as power from ees_us.unit
) u
where not exists (select * from EES_US.POWERCHANGESTEP s where s.unitid=u.id);

/*
for testing :
update ees_us.unit set COMMUNICATIONTYPE='MANUAL' where locationid!=1001457;
rollback :
update ees_us.unit u set u.COMMUNICATIONTYPE=(select case when f2switchable=1 then 'ALPHABOXCHP' else 'MANUAL' end from owner_bhp.unit b where u.name=b.name and u.locationid=b.fk_location) where COMMUNICATIONTYPE='MANUAL';

*/
