INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(1,7,1111,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(2,10,3333,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(5,2,4444,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(3,11,9999,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(12,14,1221,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(9,16,6578,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(17,20,9123,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(22,25,7456,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(42,28,1487,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(10,30,8437,true);


INSERT INTO login(id,agentid,logindate) VALUES (1,7,to_timestamp('2012-01-03 20:27:53.611489','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (2,10,to_timestamp('2011-02-22 15:22:11.253469','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (3,2,to_timestamp('2015-07-01 23:05:30.126471','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (4,11,to_timestamp('2012-10-25 06:14:50.211489','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (5,14,to_timestamp('2010-01-01 10:55:47.911489','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (6,16,to_timestamp('2016-11-13 22:10:03.011489','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (7,20,to_timestamp('2015-05-05 05:55:55.551489','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (8,25,to_timestamp('2017-01-27 18:17:58.511489','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (9,28,to_timestamp('2017-01-05 15:27:33.311489','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (10,30,to_timestamp('2014-08-22 22:24:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'));

INSERT INTO message(id,content,senddate,conversation_id) VALUES (11,'csao', to_timestamp('2012-08-22 22:24:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),1);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (12,'mizu?', to_timestamp('2015-08-22 22:24:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),2);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (13,'nemsok', to_timestamp('2011-07-18 20:23:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),5);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (14,'veled?', to_timestamp('2017-08-21 23:26:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),3);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (15,'itt se sok', to_timestamp('2010-03-11 04:24:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),12);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (16,'hogy vagy?', to_timestamp('2016-08-22 22:24:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),9);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (17,'köszi meg vagyok', to_timestamp('2017-01-10 14:24:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),17);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (18,'és te?', to_timestamp('2009-04-22 22:24:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),22);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (19,'élek', to_timestamp('2017-02-01 10:22:13.341489','YYYY/MM/DD HH24:MI:SS.FF3'),42);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (20,'na örulök', to_timestamp('2014-03-15 00:00:00.001489','YYYY/MM/DD HH24:MI:SS.FF3'),10);

INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (21,1111,to_timestamp('2012-08-12 22:20:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),'button','type');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (22,3333,to_timestamp('2011-07-02 19:26:20.411489','YYYY/MM/DD HH24:MI:SS.FF3'),'nav','type');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (23,4444,to_timestamp('2016-06-15 15:14:30.311489','YYYY/MM/DD HH24:MI:SS.FF3'),'menu','type');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (24,9999,to_timestamp('2017-05-24 10:05:40.671489','YYYY/MM/DD HH24:MI:SS.FF3'),'button','type');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (25,1221,to_timestamp('2010-10-30 10:56:12.161489','YYYY/MM/DD HH24:MI:SS.FF3'),'footer','type');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (26,6578,to_timestamp('2012-11-20 23:45:11.461489','YYYY/MM/DD HH24:MI:SS.FF3'),'header','type');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (27,9123,to_timestamp('2009-12-10 04:24:09.611489','YYYY/MM/DD HH24:MI:SS.FF3'),'chat','type');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (28,7456,to_timestamp('2016-01-9 07:24:56.211489','YYYY/MM/DD HH24:MI:SS.FF3'),'plugin','type');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (29,1487,to_timestamp('2014-02-23 02:29:32.111489','YYYY/MM/DD HH24:MI:SS.FF3'),'button','type');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (30,8437,to_timestamp('2013-05-13 15:28:25.511489','YYYY/MM/DD HH24:MI:SS.FF3'),'button','type');



