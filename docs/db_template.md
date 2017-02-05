###Conversation
 - id
 - client_id
 - agent_id
 - closed(boolean)
 
----- 
###Message
 - id
 - content
 - date
 - conversation_id(foreign key)
 
-----
###Agent
 - id
 - username
 - first_name
 - last_name
 - email
 - gender
 - img_url
 - phone
 - company_id(foreign key)

------------

###Login
 - id
 - agent_id(foreign key)
 - timestamp
