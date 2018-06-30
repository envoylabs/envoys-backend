(defproject envoys-backend "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure       "1.9.0"]
                 [org.clojure/clojurescript "1.10.312"]
                 [io.nervous/cljs-lambda    "0.3.5"]]

  :plugins [[lein-npm                    "0.6.2"]
            [io.nervous/lein-cljs-lambda "0.6.6"]]
  :npm {:dependencies [[serverless-cljs-plugin "0.1.2"]]}
  :cljs-lambda {:compiler
                {:inputs  ["src"]
                 :options {:output-to     "target/envoys-backend/envoys_backend.js"
                           :output-dir    "target/envoys-backend"
                           :target        :nodejs
                           :language-in   :ecmascript6
                           :optimizations :simple}}})
