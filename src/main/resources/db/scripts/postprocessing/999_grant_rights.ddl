--SET SERVEROUTPUT ON
DECLARE
  V_SCHEMANAME VARCHAR2(2000);
  V_SCHEMANAME_TOMCAT VARCHAR2(2000);
  V_QUERY_STRING VARCHAR2(2000);
BEGIN
  
  SELECT
    SYS_CONTEXT( 'userenv', 'current_schema' )
  INTO
    V_SCHEMANAME
  FROM
    DUAL;
  
  SELECT V_SCHEMANAME || '_TOMCAT' INTO V_SCHEMANAME_TOMCAT FROM DUAL;
  
  BEGIN
  
    FOR t IN (SELECT object_name FROM ALL_OBJECTS WHERE OBJECT_TYPE IN ('TABLE', 'SEQUENCE', 'FUNCTION', 'PROCEDURE', 'PACKAGE') AND GENERATED = 'N' AND OWNER = V_SCHEMANAME) 
      LOOP   
          EXECUTE IMMEDIATE 'REVOKE ALL ON ' || V_SCHEMANAME ||  '.' || T.OBJECT_NAME || ' FROM ' || V_SCHEMANAME|| '_TOMCAT';    
      END LOOP;
        
    FOR t IN (SELECT object_name FROM ALL_OBJECTS WHERE OBJECT_TYPE IN ('TABLE') AND GENERATED = 'N' AND OWNER = V_SCHEMANAME AND OBJECT_NAME NOT IN ('DBMAINTAIN_SCRIPTS')) 
      LOOP   
          EXECUTE IMMEDIATE 'GRANT SELECT, UPDATE, INSERT, DELETE ON ' || V_SCHEMANAME ||  '.' || T.OBJECT_NAME || ' TO ' || V_SCHEMANAME_TOMCAT;    
      END LOOP;
      
    FOR t IN (SELECT object_name FROM ALL_OBJECTS WHERE OBJECT_TYPE IN ('SEQUENCE') AND OWNER = V_SCHEMANAME) 
      LOOP   
          EXECUTE IMMEDIATE 'GRANT SELECT ON ' || V_SCHEMANAME ||  '.' || T.OBJECT_NAME || ' TO ' || V_SCHEMANAME_TOMCAT;    
      END LOOP;
    
    FOR t IN (SELECT object_name FROM ALL_OBJECTS WHERE OBJECT_TYPE IN ('FUNCTION', 'PROCEDURE', 'PACKAGE') AND OWNER = V_SCHEMANAME) 
      LOOP   
          EXECUTE IMMEDIATE 'GRANT EXECUTE ON ' || V_SCHEMANAME ||  '.' || T.OBJECT_NAME || ' TO ' || V_SCHEMANAME_TOMCAT;    
      END LOOP;

  END;

  EXCEPTION WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE(SQLERRM);

END;
/
