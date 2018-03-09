
INSERT INTO unit(id,locationid,name,maxproduction,minproduction,communicationaddress,communicationtype,direction,earlierstart,ipaddress) VALUES (51,1,'wkk',4500,1200,'addreessss','ALPHABOXCHP','SUPPLY',5,'12.234.123.45');

INSERT INTO powerchangestep(id,power,unitid) VALUES (61,20,51);
INSERT INTO powerchangestep(id,power,unitid) VALUES (62,60,51);
INSERT INTO powerchangestep(id,power,unitid) VALUES (63,80,51);
INSERT INTO powerchangestep(id,power,unitid) VALUES (64,100,51);
