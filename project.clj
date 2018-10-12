(defproject envoys-backend "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure       "1.9.0"]
                 [org.clojure/clojurescript "1.10.339"]
                 [io.nervous/cljs-lambda    "0.3.5"]
                 [org.clojure/core.async "0.4.474"]
                 [funcool/promesa "1.9.0"]]

  :profiles {:dev {:dependencies [[cider/piggieback "0.3.6"]
                                  [org.clojure/tools.nrepl "0.2.13"]
                                  [figwheel-sidecar "0.5.16"]
                                  [cider/cider-nrepl "0.17.0"]]
                   :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}
                   :figwheel {:nrepl-middleware ["cider.nrepl/cider-middleware"]}}}

  :plugins [[lein-npm "0.6.2"]
            [lein-figwheel "0.5.16"]
            [lein-cljsbuild "1.1.7" :exclusions [org.clojure/clojure]]
            [io.nervous/lein-cljs-lambda "0.6.6"]]

  ;;:source-paths ["src"]

  :npm {:dependencies [[serverless-cljs-plugin "0.1.2"]
                       [aws-sdk "2.316.0"]
                       [lodash "4.17.5"]]}

  :cljs-lambda {:compiler
                {:inputs  ["src"]
                 :options {:output-to     "target/envoys_backend.js"
                           :output-dir    "target"
                           :target        :nodejs
                           :language-in   :ecmascript6
                           :optimizations :simple}}}

  :cljsbuild {:builds
              [{:id "server"
                :source-paths ["env/dev" "src"]
                :figwheel true
                :compiler {:main server
                           :target :nodejs
                           :source-map true
                           :output-to "resources/local/js/localserver.js"
                           :output-dir "resources/local/js" }}]})
