## LuckPerms functional permissions
*some permissions can be given to all players bypassing LuckPerms in the plugin config file

| function      | about | permission     |config|
| ------------- | ---------------------------------- |-----------------------|----------|
| full access   | give all plugin permissions        | floatychat.*          |-|
| localchat     | give permission to use local chat  | floatychat.localchat  |+|
| globalchat    | give permission to use global chat | floatychat.globalchat |+|
| /msg          | give permission to sending private messages| floatychat.privatemessage|+|
| /mute        | give permission to muted a players | floatychat.mute|-|

## Configure config
plugin/FloatyChat/config.yaml

|parameter|data type    |Usage|
|---------|-------------|-|
| enableGreeting        | boolean | enable welcome message on entry |
| enableFirstGreeting   | boolean | enable first welcome message on entry |
|enableCustomDeathMessage| boolean| enable custom death message|
|enableTimerMessages| boolean| enable sending ads messages on a timer|
| localChatByDefault    | boolean | enable local chat for all players bypassing LuckPerms|
|globalChatByDefault    | boolean | enable global chat for all players bypassing LuckPerms|
| privateMessageByDefault| boolean| enable private message for all players bypassing LuckPerms |
| localChatRange        | int     | value of local chat coverage distance |
|intervalTimerMessages|int|value in seconds in the intervals between sending ads on the server|
| mutePunish| int |value in second of mute duration| 
|globalChatSymbol| string | symbol to denote send message to global chat |
| globalChatPrefix  | string  | global message chat prefix|
| localChatPrefix   | string  | local message chat prefix|
| greetingMessage       | string  | text of welcome message on entry |
| firstGreetingMessage       | string  | text of first welcome message on entry |
|customDeathMessage | string | text of your custom death  |
| TimerMessages| string | header for nested YAML list of message enumerations for sending messages by time|

## formatting in config messages
*messages may support some formatting to create a customized message to your liking.

```
{nickname1} - event user1 nickname
```

|nickname of the player who initiated the event|
|-|

#
```
{time} | {time:?format=} - server time in 12 hour format
```
|by default it outputs time in 12 hour format, but if you want to customize server time output you can use java time formatting|
|-|

#
```
{URL:?text=?url=} - allows you to embed a link in a message **only once**
```
|allows you to embed a link to the site once in the message by replacing the url with an arbitrary green text. It is required to strictly follow the pattern!|
|-|

#



__greetingMessage__   example:

input<= welcome back {nickname1}, now on server {time}.See you on our site {URL:?text=click?url=https://examplesite.com}!

output=> welcome back DOKOASHI, now on server 7:20 PM, See you on our site click!

__firstGreetingMessage__ example:

input<= welcome  {nickname1}, now on server {time:?format=kk:mm}, check our  {URL:?text=site?url=https://examplesite.com}!

output=> welcome back DOKOASHI, now on server 18:20, check our site!

__TimerMessages__ example:

input<= - Check out the candy shop in stall number 3 at spawn

output=> Check out the candy shop in stall number 3 at spawn