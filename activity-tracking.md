#in the context of the rest api 
 - url: helpdesk/api/activites/
 - sent JSON by js plugin:
   - {"clientId":"sdasasd", "type":"CLICK", "target":"sadas.com/about#strangebutton4"}
 - response: 202 ACCEPTED
 
 - if any of theese attributes missing
    - response: 400 BAD REQUEST
 
 - if any of theese malformed
    - response: 400 BAD REQUEST
 
 

#get available agents (to be discloused)
 - url: helpdesk/api/isagenavailable
 - sent JSON by js plugin
     - {"source":"https://somecompany.com/" }
 - reponse: in json
 	 - if available
       - {"agentId":"12"}
     - else
       - {"agentid": "null"}  