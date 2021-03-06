# envoys-backend

# Install

```shell
$ npm install serverless -g
$ npm install serverless-offline --save-dev
$ npm install ws
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
```

The prompt will hang. However, that's only because you need to start the server. Luckily for you, it was just built!

To start it:

    node "resources/local/js/localserver.js"

You should then see in your repl that the prompt will change:

```
    cljs.user=>
```

Then interact with the repl normally.

# Run locally (TODO)

I need to work out if it's possible to fix this, or provide an alternate path to local development.

```shell
$ sls offline start
```

With Serverless, you can invoke functions and pass stub data from a file. The [docs are here](https://serverless.com/framework/docs/providers/aws/cli-reference/invoke/).

Probably, you want to put some data in a directory and then invoke locally:

```
serverless invoke --function functionName --stage dev --region eu-west-1 --path data/data.json
```

The `data` directory is ignored by default.

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
