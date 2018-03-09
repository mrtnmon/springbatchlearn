DECLARE
  username         VARCHAR2(255);
  tomcatuser       VARCHAR2(255);
  alterSessionStmt VARCHAR2(255);
BEGIN
  SELECT USER INTO username FROM dual;
  tomcatuser       := username || '_TOMCAT';
  EXECUTE immediate 'create user '|| tomcatuser ||' identified by '|| tomcatuser;
  EXECUTE immediate 'grant create session to ' || tomcatuser;
  EXECUTE immediate 'grant create trigger to ' || tomcatuser;
  alterSessionStmt := 'alter session set current_schema = ';
  EXECUTE immediate alterSessionStmt || tomcatuser ;
  EXECUTE immediate 'create or replace trigger schema_logon after logon on ' || tomcatuser || '.schema begin execute immediate ''' || alterSessionStmt || username || '''; end;';
  EXECUTE immediate 'revoke create trigger from ' || tomcatuser;
  EXECUTE immediate alterSessionStmt || username ;
END;
/
