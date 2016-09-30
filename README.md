# fake_bank_demo
Demo API showing off Spring-Boot's 1.4 hypermedia-based REST API

Details: Using a H2 database that stays in memory. See the default loaded data via /console route
You can deposit and withdrawal. 
Uses Oauth2 for Authentication - static user/pass for demo purposes

# Getting started

# 1. Get yourself a token:
```
curl -XPOST -k foo:foosecret@localhost:8080/oauth/token -d grant_type=password \
-d client_id=foo -d client_secret=abc123 -d username=bar -d password=barsecret
```

# 2. Check out the hyperlinked API:
```
curl -H "Authorization: Bearer $TOKEN" localhost:8080
```

# 3. Sample Deposit:
```
curl -v -X POST -H "Content-Type: application/json" -H "Authorization: Bearer $TOKEN" localhost:8080/transaction -d '{"amount":15,"transactionType": 0,"account":{"id":"1"}}'
```

# 4. Sample Withdrawal:
```
curl -v -X POST -H "Content-Type: application/json" -H "Authorization: Bearer $TOKEN" localhost:8080/transaction -d '{"amount":25,"transactionType": 1,"account":{"id":"1"}}'
```

#Extras:
Refreshing Token:
```
curl -v --data "grant_type=refresh_token&client_id=foo&refresh_token=$REFRESH_TOKEN" -k foo:foosecret@localhost:8080/oauth/token
```
