# envoys-backend

# Install

```shell
$ npm install serverless -g
$ npm install serverless-offline --save-dev
$ lein deps
```

# Run locally (TODO)

I need to work out if it's possible to fix this, or provide an alternate path to local development.

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
$ serverless deploy function -f index
```

# Invoke

```shell
$ curl -X GET <url> -H 'Content-Type: application/json'
```
