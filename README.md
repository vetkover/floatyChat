## LuckPerms functional permissions
*some permissions can be given to all players bypassing LuckPerms in the plugin config file

| function      | about | permission     |config|
| ------------- | ---------------------------------- |-----------------------|----------|
| full access   | give all plugin permissions        | floatychat.*          |-|
| localchat     | give permission to use local chat  | floatychat.localchat  |+|
| globalchat    | give permission to use global chat | floatychat.globalchat |+|
| /msg          | give permission to sending private messages| floatychat.privatemessage|+|


## Configure config
plugin/FloatyChat/config.yaml

|parameter|data type|Usage|
|---------|---------|-|
| enableGreeting    | boolean | enable welcome message on entry | 
| greetingMessage   | string  | text of welcome message on entry |
| localChatRange    | int     | value of local chat coverage distance |
| localChatByDefault| boolean | enable local chat for all players bypassing LuckPerms|
|globalChatByDefault| boolean | enable global chat for all players bypassing LuckPerms|
| globalChatPrefix  | string  | global message chat prefix|
| localChatPrefix   | string  | local message chat prefix|
| privateMessageByDefault| boolean| enable private message for all players bypassing LuckPerms |






