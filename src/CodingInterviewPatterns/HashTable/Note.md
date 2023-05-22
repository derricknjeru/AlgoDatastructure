-> Take a look at Random class
-> Understand Collections.shuffle(list,new Random());
-> substring of a string using string index

#### If you're only interested in the keys, you can iterate through the keySet() of the map:

##### Map<String, Object> map = ...;

```text
for (String key : map.keySet()) {
// ...
}
```

#### If you only need the values, use values():

```text
for (Object value : map.values()) {
// ...
}
```

#### Finally, if you want both the key and value, use entrySet():

```text
for (Map.Entry<String, Object> entry : map.entrySet()) {
String key = entry.getKey();
Object value = entry.getValue();
// ...
}
```