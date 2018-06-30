(ns envoys-backend.core
  (:require [cljs-lambda.macros :refer-macros [defgateway]]))

(def page-structure
  {:title "Envoys | Software Engineering"
   :hero-text "A functional-first engineering collective with a focus on doing things right, first time."
   :body-text ""
   :services-list ["Full-stack software engineering"
                   "Serverless applications"
                   "Data engineering"
                   "Prototyping and MVPs"
                   "Delivery support"
                   "User engagement and research"
                   "Workshops and training"]
   :technologies ["Clojure"
                  "Javascript"
                  "Ruby"
                  "Docker"
                  "Kubernetes"
                  "AWS"
                  "GCP"
                  "Apache Kafka"
                  "Apache Spark"
                  "Neo4j"
                  "MongoDB"
                  "PostgreSQL"
                  "Node.js"
                  "Linked data"
                  "GraphQL"]})

(defgateway index [event ctx]
  {:status 200
   :headers {:content-type "application/json"}
   :body (-> page-structure
             clj->js
             JSON/stringify)})
