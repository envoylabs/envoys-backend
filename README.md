# envoys-backend

# Install

```shell
$ npm install serverless -g
$ lein deps
```

# Run locally (TODO)

```shell
$ sls offline start
```

# Deploy

Make sure your AWS keys are available in your PATH, then:

```shell
$ serverless deploy
```

# Redeploy Function

```
$ serverless deploy function -f echo
```

# Invoke

```shell
$ curl -X POST <url> -H 'Content-Type: application/json' -d '{"body": "Hi"}'
```
