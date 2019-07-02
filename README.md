# JamesMojangAPI
A library for easy access to Mojang's API with built in Caching. Cache lasts until an hour and then is reset. The plugin handles login events to update cache as well to avoid API strain.

Basic Usage
```
UserInfo.getUUID("james090500");
UserInfo.getParsedUUID("james090500");
UserInfo.getName("ba4161c03a42496c8ae07d13372f3371");
```

Would return

```
ba4161c03a42496c8ae07d13372f3371
ba4161c0-3a42-496c-8ae0-7d13372f3371
james090500
```

Null will be returned for any query if it is invalid, not a real username/uuid or you've capped your API limit.
In the event the API limit has been reached, it will fall back to cache, if that fails it will return null

https://www.spigotmc.org/resources/james-mojang-api-manager-library.62649/