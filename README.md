# envoys-backend

# Install

```shell
$ npm install serverless -g
$ npm install serverless-offline --save-dev
$ lein deps
```

# REPL

Make sure you have something reasonable in your `~/.lein/profiles.clj`:

```
{:repl {:dependencies [[org.clojure/tools.nrepl "0.2.12"]]
        :plugins [[cider/cider-nrepl "0.17.0"]]}}
```

    lein figwheel server

In a new prompt:

    node "resources/local/js/localserver.js"

# nREPL and Emacs

To start a repl:

    lein repl

In Emacs, `M-x cider-connect`, then:

```
    user=> (use 'figwheel-sidecar.repl-api)
    ...
    user=> (figwheel-sidecar.repl-api/start-figwheel!)
    ...
    user=> (cljs-repl)
    ...
    cljs.user=>
```

Then interact with the repl normally.

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
