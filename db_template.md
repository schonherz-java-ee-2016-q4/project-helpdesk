###Message table
 - id
 - content
 - date
 - conversation_id(foreign key)

---------
###Conversation table
 - id
 - client_id
 - agent_id
 - closed(boolean)
 

 
-----
###Agent table
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

###Agent_activity
 - id
 - agent_id(foreign key)
 - numof_logins
 - numof_client_contacts
 - numof_new_tickets
 - numof_closed_tickets

----------

