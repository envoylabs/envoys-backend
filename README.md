# envoys-backend

# Install

```shell
$ npm install serverless -g
$ npm install serverless-offline --save-dev
$ lein deps
```

# REPL

Assuming you have a working repl - [instructions here](https://github.com/bhauman/lein-figwheel/wiki/Using-the-Figwheel-REPL-within-NRepl), and the following in `init.el`:

```
(setq cider-cljs-lein-repl
      "(do (require 'figwheel-sidecar.repl-api)
           (figwheel-sidecar.repl-api/start-figwheel!)
           (figwheel-sidecar.repl-api/cljs-repl))")
```

Assuming you have nashorn in your local dev dependencies:

```
lein repl
...
user=> (require '[cljs.repl.nashorn :as nashorn])
nil
user=> (cider.piggieback/cljs-repl (nashorn/repl-env))
To quit, type: :cljs/quit
nil
cljs.user=> 
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
