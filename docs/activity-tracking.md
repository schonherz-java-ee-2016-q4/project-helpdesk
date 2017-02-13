#Create new activity
Request type: POST
 - url: helpdesk/api/activites/
 - sent JSON example by js plugin:
```json
{
    "clientId":"sdasasd",
    "type":"NAVIGATION",
    "target":"sadas.com/about#strangebutton4"
}
```
 - response:
   - 202 ACCEPTED
 - if any of theese attributes missing:
  - response: 400 BAD REQUEST
 - if any of theese malformed
    - response: 400 BAD REQUEST

#Get available agents
Request type: POST
 - url: helpdesk/api/agents/available
 - sent JSON example by js plugin:
```json
{
    "source": "https://somecompany.com/",
    "clientId": "uuid",
    "clientEmail": "testclient@test.com"
}
```
 - reponse: in json
 - example:
 	 - if available
```json
{
    "conversationId": "12"
}
```
 	 - else
     ```json
{
    "conversationId":"null"
}
```