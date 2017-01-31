INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(1,7,1111,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(2,10,3333,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(3,2,4444,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(4,11,9999,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(5,14,1221,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(6,16,6578,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(7,20,9123,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(8,25,7456,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(9,28,1487,true);
INSERT INTO public.conversation(id,agentid,clientid,closed) VALUES(10,30,8437,true);

INSERT INTO login(id,agentid,logindate) VALUES (11,1,to_timestamp('2012-01-03 20:27:53.611489','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (12,1,to_timestamp('2011-02-22 15:22:11.253469','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (13,1,to_timestamp('2015-07-01 23:05:30.126471','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (14,2,to_timestamp('2012-10-25 06:14:50.211489','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (15,2,to_timestamp('2010-01-01 10:55:47.911489','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (16,3,to_timestamp('2016-11-13 22:10:03.011489','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (17,4,to_timestamp('2015-05-05 05:55:55.551489','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (18,4,to_timestamp('2017-01-27 18:17:58.511489','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (19,5,to_timestamp('2017-01-05 15:27:33.311489','YYYY/MM/DD HH24:MI:SS.FF3'));
INSERT INTO login(id,agentid,logindate) VALUES (20,5,to_timestamp('2014-08-22 22:24:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'));

INSERT INTO message(id,content,senddate,conversation_id) VALUES (21,'csao', to_timestamp('2012-08-22 22:24:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),1);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (22,'mizu?', to_timestamp('2015-08-22 22:24:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),1);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (23,'nemsok', to_timestamp('2011-07-18 20:23:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),1);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (24,'veled?', to_timestamp('2017-08-21 23:26:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),1);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (25,'itt se sok', to_timestamp('2010-03-11 04:24:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),1);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (26,'hogy vagy?', to_timestamp('2016-08-22 22:24:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),2);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (27,'köszi meg vagyok', to_timestamp('2017-01-10 14:24:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),2);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (28,'és te?', to_timestamp('2009-04-22 22:24:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),2);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (29,'élek', to_timestamp('2017-02-01 10:22:13.341489','YYYY/MM/DD HH24:MI:SS.FF3'),2);
INSERT INTO message(id,content,senddate,conversation_id) VALUES (30,'na örulök', to_timestamp('2014-03-15 00:00:00.001489','YYYY/MM/DD HH24:MI:SS.FF3'),2);

INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (31,1111,to_timestamp('2012-08-12 22:20:58.611489','YYYY/MM/DD HH24:MI:SS.FF3'),'button','NAVIGATION');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (32,3333,to_timestamp('2011-07-02 19:26:20.411489','YYYY/MM/DD HH24:MI:SS.FF3'),'nav','BUTTON_CLICK');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (33,4444,to_timestamp('2016-06-15 15:14:30.311489','YYYY/MM/DD HH24:MI:SS.FF3'),'menu','INPUT_FOCUSLOSS');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (34,9999,to_timestamp('2017-05-24 10:05:40.671489','YYYY/MM/DD HH24:MI:SS.FF3'),'button','FORM_SUBMIT');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (35,1221,to_timestamp('2010-10-30 10:56:12.161489','YYYY/MM/DD HH24:MI:SS.FF3'),'footer','BUTTON_CLICK');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (36,6578,to_timestamp('2012-11-20 23:45:11.461489','YYYY/MM/DD HH24:MI:SS.FF3'),'header','INPUT_FOCUSLOSS');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (37,9123,to_timestamp('2009-12-10 04:24:09.611489','YYYY/MM/DD HH24:MI:SS.FF3'),'chat','INPUT_FOCUSLOSS');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (38,7456,to_timestamp('2016-01-9 07:24:56.211489','YYYY/MM/DD HH24:MI:SS.FF3'),'plugin','BUTTON_CLICK');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (39,1487,to_timestamp('2014-02-23 02:29:32.111489','YYYY/MM/DD HH24:MI:SS.FF3'),'button','NAVIGATION');
INSERT INTO client_activity(id,clientid,createdat,target,type) VALUES (40,8437,to_timestamp('2013-05-13 15:28:25.511489','YYYY/MM/DD HH24:MI:SS.FF3'),'button','INPUT_FOCUSLOSS');
